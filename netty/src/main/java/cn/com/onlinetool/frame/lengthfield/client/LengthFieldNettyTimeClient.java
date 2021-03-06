package cn.com.onlinetool.frame.lengthfield.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author choice
 * @date create in 2020/4/1 00:04
 */
public class LengthFieldNettyTimeClient {


    public static void main(String[] args) {
        int port = 8080;
        new LengthFieldNettyTimeClient().connect("127.0.0.1", port);
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


    private static class ChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4,-4,4));
            ch.pipeline().addLast(new LengthFieldNettyTimeClientHandler());
        }
    }
}
