
package cn.com.onlinetool.protocol.http.xml.client;

import cn.com.onlinetool.protocol.http.xml.codec.HttpXmlRequestEncoder;
import cn.com.onlinetool.protocol.http.xml.codec.HttpXmlResponseDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author choice
 */
public class HttpXmlClient {


    public static void main(String[] args) {
        int port = 8080;
        new HttpXmlClient().connect(port);
    }


    private void connect(int port) {
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelHandler());
            // Start the client.
            ChannelFuture f = bootstrap.connect(new InetSocketAddress(port)).sync();
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
            ch.pipeline().addLast(new HttpResponseDecoder());
            ch.pipeline().addLast(new HttpObjectAggregator(65536));
            ch.pipeline().addLast(new HttpXmlResponseDecoder(true));
            ch.pipeline().addLast(new HttpRequestEncoder());
            ch.pipeline().addLast(new HttpXmlRequestEncoder(true));
            ch.pipeline().addLast(new HttpXmlClientHandle());
        }
    }


}
