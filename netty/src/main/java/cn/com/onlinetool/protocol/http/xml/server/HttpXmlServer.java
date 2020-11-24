
package cn.com.onlinetool.protocol.http.xml.server;

import cn.com.onlinetool.protocol.http.xml.codec.HttpXmlRequestDecoder;
import cn.com.onlinetool.protocol.http.xml.codec.HttpXmlResponseEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author choice
 */
@Slf4j
public class HttpXmlServer {


    public static void main(String[] args) throws Exception {
        int port = 8080;
        new HttpXmlServer().bind(port);
    }


    private void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelChildHandler());
            // Start the server.
            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            log.info("HTTP订购服务器启动，网址是 : " + "http://127.0.0.1:" + port);
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private static class ChannelChildHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new HttpRequestDecoder());
            ch.pipeline().addLast(new HttpObjectAggregator(65536));
            ch.pipeline().addLast(new HttpXmlRequestDecoder(true));
            ch.pipeline().addLast(new HttpResponseEncoder());
            ch.pipeline().addLast(new HttpXmlResponseEncoder(true));
            ch.pipeline().addLast(new HttpXmlServerHandler());

        }
    }
}
