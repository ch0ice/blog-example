package cn.com.onlinetool.frame.delimiter.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author choice
 * @date create in 2020/4/1 00:01
 */
public class DelimiterNettyTimeServer {


    public static void main(String[] args) {
        int port = 8080;
        new DelimiterNettyTimeServer().bind(port);
    }

    private void bind(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelChildHandler());
            // Start the server.
            ChannelFuture f = bootstrap.bind(port).sync();

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }


    private static class ChannelChildHandler extends ChannelInitializer<SocketChannel> {

        static final ByteBuf[] DELIMITERS = new ByteBuf[]{Unpooled.copiedBuffer("*_*".getBytes()), Unpooled.copiedBuffer("^_^".getBytes())};

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, DELIMITERS));
            ch.pipeline().addLast(new DelimiterNettyTimeServerHandler());
        }
    }


}
