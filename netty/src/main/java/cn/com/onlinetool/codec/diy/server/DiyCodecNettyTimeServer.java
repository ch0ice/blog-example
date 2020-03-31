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

/**
 * @author choice
 * @date create in 2020/3/25 14:46
 */
public class DiyCodecNettyTimeServer {

    private void bind(int port){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            //绑定端口，同步等待成功
            ChannelFuture f = bootstrap.bind(port).sync();
            //等待服务器端口监听关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new DiyByteToMessageDecoder());//1
            ch.pipeline().addLast(new DiyMessageToMessageDecoder());//2
            ch.pipeline().addLast(new DiyMessageToByteEncoder());//5
            ch.pipeline().addLast(new DiyMessageToMessageEncoder());//4
            ch.pipeline().addLast(new DiyCodecNettyTimeServerHandler());//3
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
        new DiyCodecNettyTimeServer().bind(port);
    }
}
