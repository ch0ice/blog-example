package cn.com.onlinetool.codec.inner.delimiter.client;

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

/**
 * @author choice
 * @date create in 2020/4/1 00:02
 */
public class DelimiterNettyTimeClient {
    private void connect(String host, int port) {
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            ByteBuf[] delimiters = new ByteBuf[]{Unpooled.copiedBuffer("*_*".getBytes()),Unpooled.copiedBuffer("^_^".getBytes())};
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiters));
                            ch.pipeline().addLast(new DelimiterNettyTimeClientHandler());
                        }

                    });
            //发起异步连接
            ChannelFuture future = bootstrap.connect(host,port).sync();
            //等待客户端链路关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //资源释放
            clientGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException ne){
                ne.printStackTrace();
            }
        }
        new DelimiterNettyTimeClient().connect("127.0.0.1", port);
    }
}
