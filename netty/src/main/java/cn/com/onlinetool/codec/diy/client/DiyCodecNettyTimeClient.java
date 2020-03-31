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

/**
 * @author choice
 * @date create in 2020/3/25 14:46
 */
public class DiyCodecNettyTimeClient {

    private void connect(String host, int port) {
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiyByteToMessageDecoder());//4
                            ch.pipeline().addLast(new DiyMessageToMessageDecoder());//5
                            ch.pipeline().addLast(new DiyMessageToByteEncoder());//3
                            ch.pipeline().addLast(new DiyMessageToMessageEncoder());//2
                            ch.pipeline().addLast(new DiyCodecNettyTimeClientHandler());//1
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
        new DiyCodecNettyTimeClient().connect("127.0.0.1", port);
    }
}