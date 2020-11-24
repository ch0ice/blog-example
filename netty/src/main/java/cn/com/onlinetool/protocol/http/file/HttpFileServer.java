
package cn.com.onlinetool.protocol.http.file;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author choice
 */
@Slf4j
public class HttpFileServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        final String url = "/";
        new HttpFileServer().bind("127.0.0.1", port, url);
    }


    public void bind(String host, final int port, final String url) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelChildHandler(url));
            // Start the server.
            ChannelFuture future = bootstrap.bind(host, port).sync();
            log.info("HTTP文件服务器启动成功，网址是 : " + "http://" + host + ":" + port + url);
            // Wait until the server socket is closed.
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }


    private static class ChannelChildHandler extends ChannelInitializer<SocketChannel> {
        final String url;

        public ChannelChildHandler(String url) {
            this.url = url;
        }

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new HttpRequestDecoder()); // 请求消息解码器
            ch.pipeline().addLast(new HttpObjectAggregator(65536));// 目的是将多个消息转换为单一的request或者response对象
            ch.pipeline().addLast(new HttpResponseEncoder());//响应解码器
            ch.pipeline().addLast(new ChunkedWriteHandler());//目的是支持异步大文件传输（）
            ch.pipeline().addLast(new HttpFileServerHandler(url));// 业务逻辑
        }
    }
}
