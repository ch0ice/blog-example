package cn.com.onlinetool.codec.diy.server;

import cn.com.onlinetool.codec.diy.decoder.DiyByteToMessageDecoder;
import cn.com.onlinetool.codec.diy.decoder.DiyMessageToMessageDecoder;
import cn.com.onlinetool.codec.diy.encoder.DiyMessageToByteEncoder;
import cn.com.onlinetool.codec.diy.encoder.DiyMessageToMessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author choice
 * @date create in 2020/3/25 14:46
 */
public class DiyCodecNettyTimeServer {


    public static void main(String[] args) {
        int port = 8080;
        new DiyCodecNettyTimeServer().bind(port);
    }

    private void bind(int port){
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

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new DiyByteToMessageDecoder());//1
            ch.pipeline().addLast(new DiyMessageToMessageDecoder());//2
            ch.pipeline().addLast(new DiyMessageToByteEncoder());//5
            ch.pipeline().addLast(new DiyMessageToMessageEncoder());//4
            ch.pipeline().addLast(new DiyCodecNettyTimeServerHandler());//3
        }
    }



}
