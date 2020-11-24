package cn.com.onlinetool.codec.diy.client;

import cn.com.onlinetool.codec.diy.decoder.DiyByteToMessageDecoder;
import cn.com.onlinetool.codec.diy.decoder.DiyMessageToMessageDecoder;
import cn.com.onlinetool.codec.diy.encoder.DiyMessageToByteEncoder;
import cn.com.onlinetool.codec.diy.encoder.DiyMessageToMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author choice
 * @date create in 2020/3/25 14:46
 */
@Slf4j
public class DiyCodecNettyTimeClient {


    public static void main(String[] args) {
        int port = 8080;
        new DiyCodecNettyTimeClient().connect("127.0.0.1", port);
    }


    private void connect(String host, int port) {
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelHandler());
            // Start the client.
            ChannelFuture f = bootstrap.connect(host, port).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Shut down the event loop to terminate all threads.
            clientGroup.shutdownGracefully();
        }
    }


    private static class ChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
            ch.pipeline().addLast(new DiyByteToMessageDecoder());//4
            ch.pipeline().addLast(new DiyMessageToMessageDecoder());//5
            ch.pipeline().addLast(new DiyMessageToByteEncoder());//3
            ch.pipeline().addLast(new DiyMessageToMessageEncoder());//2
            ch.pipeline().addLast(new DiyCodecNettyTimeClientHandler());//1
        }
    }

}
