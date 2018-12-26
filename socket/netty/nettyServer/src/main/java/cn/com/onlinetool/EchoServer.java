package cn.com.onlinetool;

import cn.com.onlinetool.common.constant.HostInfo;
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
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author choice
 * @description: 实现了基础的线程池与网络的配置项
 * @date 2018-12-25 20:32
 *
 */
public class EchoServer {

    /**
     * 进行服务器端的启动处理
     * @throws Exception
     */
//    //Echo  ```````````````````
//    public void runServer() throws Exception{
//        //线程池是提升服务器性能的重要技术手段，利用鼎昌的线程池可以保证核心线程的有效数量
//        //在Netty中线程池的实现分为两类：主线程池（接收客户端连接），工作线程池（处理客户端连接）
//        //
//        EventLoopGroup boosGroup = new NioEventLoopGroup(10); //创建主线程池（接收线程池）
//        EventLoopGroup workGroup = new NioEventLoopGroup(20); //创建工作线程池
//        System.out.println("服务器启动成功");
//        try {
//            //创建一个服务器端的程序类进行NIO的启动，同事可以设置Channel
//            ServerBootstrap serverBootstrap = new ServerBootstrap(); //服务器端
//            //设置要使用的线程池以及当前的Channel 的类型
//            serverBootstrap.group(boosGroup,workGroup).channel(NioServerSocketChannel.class);
//            //接收到的信息之后需要进行处理，于是定义字处理器
//            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
//
//                @Override
//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline().addLast(new EchoServerHandler());//追加了处理器
//                }
//            });
//            //可以直接利用常量进行TCP协议的相关配置
//            serverBootstrap.option(ChannelOption.SO_BACKLOG,128);
//            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
//            //ChannelFuture描述异步回调的处理操作
//            ChannelFuture future = serverBootstrap.bind(HostInfo.PORT).sync();
//            future.channel().closeFuture().sync(); //等待socket被关闭
//        } finally {
//            workGroup.shutdownGracefully();
//            boosGroup.shutdownGracefully();
//        }
//    }
//    //Echo  ```````````````````


    /**
     * 进行服务器端的启动处理
     * @throws Exception
     */
//    //测试粘包拆包  ```````````````````
//    public void runServer() throws Exception{
//        //线程池是提升服务器性能的重要技术手段，利用鼎昌的线程池可以保证核心线程的有效数量
//        //在Netty中线程池的实现分为两类：主线程池（接收客户端连接），工作线程池（处理客户端连接）
//        //
//        EventLoopGroup boosGroup = new NioEventLoopGroup(10); //创建主线程池（接收线程池）
//        EventLoopGroup workGroup = new NioEventLoopGroup(20); //创建工作线程池
//        System.out.println("服务器启动成功");
//        try {
//            //创建一个服务器端的程序类进行NIO的启动，同事可以设置Channel
//            ServerBootstrap serverBootstrap = new ServerBootstrap(); //服务器端
//            //设置要使用的线程池以及当前的Channel 的类型
//            serverBootstrap.group(boosGroup,workGroup).channel(NioServerSocketChannel.class);
//            //接收到的信息之后需要进行处理，于是定义字处理器
//            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
//
//                @Override
//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));//拆包器
//                    socketChannel.pipeline().addLast(new EchoServerHandler());//追加了处理器
//                }
//            });
//            //可以直接利用常量进行TCP协议的相关配置
//            serverBootstrap.option(ChannelOption.SO_BACKLOG,128);
//            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
//            //ChannelFuture描述异步回调的处理操作
//            ChannelFuture future = serverBootstrap.bind(HostInfo.PORT).sync();
//            future.channel().closeFuture().sync(); //等待socket被关闭
//        } finally {
//            workGroup.shutdownGracefully();
//            boosGroup.shutdownGracefully();
//        }
//    }
//    //测试粘包拆包  ```````````````````


    /**
     * 进行服务器端的启动处理
     * @throws Exception
     */
    //测试序列化  ```````````````````
    public void runServer() throws Exception{
        //线程池是提升服务器性能的重要技术手段，利用鼎昌的线程池可以保证核心线程的有效数量
        //在Netty中线程池的实现分为两类：主线程池（接收客户端连接），工作线程池（处理客户端连接）
        //
        EventLoopGroup boosGroup = new NioEventLoopGroup(10); //创建主线程池（接收线程池）
        EventLoopGroup workGroup = new NioEventLoopGroup(20); //创建工作线程池
        System.out.println("服务器启动成功");
        try {
            //创建一个服务器端的程序类进行NIO的启动，同事可以设置Channel
            ServerBootstrap serverBootstrap = new ServerBootstrap(); //服务器端
            //设置要使用的线程池以及当前的Channel 的类型
            serverBootstrap.group(boosGroup,workGroup).channel(NioServerSocketChannel.class);
            //接收到的信息之后需要进行处理，于是定义字处理器
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(100));

                    //自定义分隔符
                    ByteBuf delimiter = Unpooled.copiedBuffer(HostInfo.SEPARATOR.getBytes());
                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                    //使用系统的分隔符
//                    socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));

                    socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                    socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                    socketChannel.pipeline().addLast(new EchoServerHandler());//追加了处理器
                }
            });
            //可以直接利用常量进行TCP协议的相关配置
            serverBootstrap.option(ChannelOption.SO_BACKLOG,128);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
            //ChannelFuture描述异步回调的处理操作
            ChannelFuture future = serverBootstrap.bind(HostInfo.PORT).sync();
            future.channel().closeFuture().sync(); //等待socket被关闭
        } finally {
            workGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }
    }
    //测试序列化  ```````````````````
}
