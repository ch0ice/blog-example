package cn.com.onlinetool.protocol.file.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author choice
 * @date create in 2020/4/1 00:02
 */
public class FileClient {


    public static void main(String[] args) {
        int port = 8080;
        new FileClient().connect("127.0.0.1", port);
    }


    private void connect(String host, int port) {
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelChildHandler());
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


    private static class ChannelChildHandler extends ChannelInitializer<SocketChannel> {
        ByteBuf[] delimiters = new ByteBuf[]{Unpooled.copiedBuffer("*_*".getBytes()), Unpooled.copiedBuffer("^_^".getBytes())};

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(
                    new StringEncoder(CharsetUtil.UTF_8),
                    new DelimiterBasedFrameDecoder(65535, delimiters),
                    new StringDecoder(CharsetUtil.UTF_8),
                    new FileClientHandler());
        }
    }

}
