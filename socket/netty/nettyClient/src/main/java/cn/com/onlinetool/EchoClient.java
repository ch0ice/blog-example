package cn.com.onlinetool;

import cn.com.onlinetool.constant.HostInfo;
import cn.com.onlinetool.serialization.marshalling.factory.MarshallingCodeFactory;
import cn.com.onlinetool.serialization.messagepack.decode.MessagePackDecoder;
import cn.com.onlinetool.serialization.messagepack.encode.MessagePackEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author choice
 * @description:
 * @date 2018-12-25 20:51
 *
 */
public class EchoClient {
//    //Echo ``````````````
//    public void runClient() throws Exception{
//        //如果现在客户端不同，那么也可以不实用多线程模式来处理
//        //在netty中考虑到代码的统一性，也允许你在客户端设置线程池
//        EventLoopGroup group = new NioEventLoopGroup();//创建一个线程池
//        try {
//            Bootstrap client = new Bootstrap();//创建客户毒案处理程序
//            client.group(group)
//                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY,true) //允许接收大块的返回数据
//                    .handler(new ChannelInitializer<SocketChannel>() {
//
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new EchoClientHandler()); //追加处理器
//                        }
//                    })
//                    //如果只有一个处理器的话也可以这样写
////                    .handler(new EchoClientHandler());
//                    ;
//            ChannelFuture channelFuture = client.connect(HostInfo.HOST_NAME, HostInfo.PORT).sync();//等待连接处理
//            channelFuture.addListener(new GenericFutureListener() {
//                @Override
//                public void operationComplete(Future future) throws Exception {
//                    if(future.isSuccess()){
//                        System.out.println("服务器连接已经完成，可以确保进行消息准确传输");
//                    }
//                }
//            });
//            channelFuture.channel().closeFuture().sync();  //等待关闭连接
//
//        }finally {
//            group.shutdownGracefully();
//        }
//    }
//    //Echo ``````````````



//    //测试粘包拆包     ``````````````
//    public void runClient() throws Exception{
//        //如果现在客户端不同，那么也可以不实用多线程模式来处理
//        //在netty中考虑到代码的统一性，也允许你在客户端设置线程池
//        EventLoopGroup group = new NioEventLoopGroup();//创建一个线程池
//        try {
//            Bootstrap client = new Bootstrap();//创建客户毒案处理程序
//            client.group(group)
//                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY,true) //允许接收大块的返回数据
//                    .handler(new ChannelInitializer<SocketChannel>() {
//
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
////                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));//拆包器
//                            socketChannel.pipeline().addLast(new EchoClientHandler()); //追加处理器
//                        }
//                    })
//            //如果只有一个处理器的话也可以这样写
////                    .handler(new EchoClientHandler());
//            ;
//            ChannelFuture channelFuture = client.connect(HostInfo.HOST_NAME, HostInfo.PORT).sync();//等待连接处理
//            channelFuture.addListener(new GenericFutureListener() {
//                @Override
//                public void operationComplete(Future future) throws Exception {
//                    if(future.isSuccess()){
//                        System.out.println("服务器连接已经完成，可以确保进行消息准确传输");
//                    }
//                }
//            });
//            channelFuture.channel().closeFuture().sync();  //等待关闭连接
//
//        }finally {
//            group.shutdownGracefully();
//        }
//    }
//    //测试粘包拆包     ``````````````



//    //测试序列化     ``````````````
//    public void runClient() throws Exception{
//        //如果现在客户端不同，那么也可以不实用多线程模式来处理
//        //在netty中考虑到代码的统一性，也允许你在客户端设置线程池
//        EventLoopGroup group = new NioEventLoopGroup();//创建一个线程池
//        try {
//            Bootstrap client = new Bootstrap();//创建客户毒案处理程序
//            client.group(group)
//                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY,true) //允许接收大块的返回数据
//                    .handler(new ChannelInitializer<SocketChannel>() {
//
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
////                            socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(100));
//
//                            //自定义分隔符
//                            ByteBuf delimiter = Unpooled.copiedBuffer(HostInfo.SEPARATOR.getBytes());
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
//                            //使用系统的分隔符
////                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                            socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
//                            socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
//                            socketChannel.pipeline().addLast(new EchoClientHandler()); //追加处理器
//                        }
//                    })
//            //如果只有一个处理器的话也可以这样写
////                    .handler(new EchoClientHandler());
//            ;
//            ChannelFuture channelFuture = client.connect(HostInfo.HOST_NAME, HostInfo.PORT).sync();//等待连接处理
//            channelFuture.addListener(new GenericFutureListener() {
//                @Override
//                public void operationComplete(Future future) throws Exception {
//                    if(future.isSuccess()){
//                        System.out.println("服务器连接已经完成，可以确保进行消息准确传输");
//                    }
//                }
//            });
//            channelFuture.channel().closeFuture().sync();  //等待关闭连接
//
//        }finally {
//            group.shutdownGracefully();
//        }
//    }
//    //测试序列化     ``````````````



//    //测试序列化  java原生对象序列化   ``````````````
//    public void runClient() throws Exception{
//        //如果现在客户端不同，那么也可以不实用多线程模式来处理
//        //在netty中考虑到代码的统一性，也允许你在客户端设置线程池
//        EventLoopGroup group = new NioEventLoopGroup();//创建一个线程池
//        try {
//            Bootstrap client = new Bootstrap();//创建客户毒案处理程序
//            client.group(group)
//                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY,true) //允许接收大块的返回数据
//                    .handler(new ChannelInitializer<SocketChannel>() {
//
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
//                            socketChannel.pipeline().addLast(new ObjectEncoder());
//                            socketChannel.pipeline().addLast(new EchoClientHandler()); //追加处理器
//                        }
//                    })
//            //如果只有一个处理器的话也可以这样写
////                    .handler(new EchoClientHandler());
//            ;
//            ChannelFuture channelFuture = client.connect(HostInfo.HOST_NAME, HostInfo.PORT).sync();//等待连接处理
//            channelFuture.addListener(new GenericFutureListener() {
//                @Override
//                public void operationComplete(Future future) throws Exception {
//                    if(future.isSuccess()){
//                        System.out.println("服务器连接已经完成，可以确保进行消息准确传输");
//                    }
//                }
//            });
//            channelFuture.channel().closeFuture().sync();  //等待关闭连接
//
//        }finally {
//            group.shutdownGracefully();
//        }
//    }
//    //测试序列化  java原生对象序列化   ``````````````




//    //测试序列化  messagePack对象传输序列化   ``````````````
//    public void runClient() throws Exception{
//        //如果现在客户端不同，那么也可以不实用多线程模式来处理
//        //在netty中考虑到代码的统一性，也允许你在客户端设置线程池
//        EventLoopGroup group = new NioEventLoopGroup();//创建一个线程池
//        try {
//            Bootstrap client = new Bootstrap();//创建客户毒案处理程序
//            client.group(group)
//                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY,true) //允许接收大块的返回数据
//                    .handler(new ChannelInitializer<SocketChannel>() {
//
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,4));
//                            socketChannel.pipeline().addLast(new MessagePackDecoder());
//                            socketChannel.pipeline().addLast(new LengthFieldPrepender(4));//与属性成员保持一致
//                            socketChannel.pipeline().addLast(new MessagePackEncoder());
//                            socketChannel.pipeline().addLast(new EchoClientHandler()); //追加处理器
//                        }
//                    })
//            //如果只有一个处理器的话也可以这样写
////                    .handler(new EchoClientHandler());
//            ;
//            ChannelFuture channelFuture = client.connect(HostInfo.HOST_NAME, HostInfo.PORT).sync();//等待连接处理
//            channelFuture.addListener(new GenericFutureListener() {
//                @Override
//                public void operationComplete(Future future) throws Exception {
//                    if(future.isSuccess()){
//                        System.out.println("服务器连接已经完成，可以确保进行消息准确传输");
//                    }
//                }
//            });
//            channelFuture.channel().closeFuture().sync();  //等待关闭连接
//
//        }finally {
//            group.shutdownGracefully();
//        }
//    }
//    //测试序列化  messagePack对象传输序列化   ``````````````




    //测试序列化  marshalling对象传输序列化  原生序列化方式的升级版  ``````````````
    public void runClient() throws Exception{
        //如果现在客户端不同，那么也可以不实用多线程模式来处理
        //在netty中考虑到代码的统一性，也允许你在客户端设置线程池
        EventLoopGroup group = new NioEventLoopGroup();//创建一个线程池
        try {
            Bootstrap client = new Bootstrap();//创建客户毒案处理程序
            client.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true) //允许接收大块的返回数据
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
                            socketChannel.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                            socketChannel.pipeline().addLast(new EchoClientHandler()); //追加处理器
                        }
                    })
            //如果只有一个处理器的话也可以这样写
//                    .handler(new EchoClientHandler());
            ;
            ChannelFuture channelFuture = client.connect(HostInfo.HOST_NAME, HostInfo.PORT).sync();//等待连接处理
            channelFuture.addListener(new GenericFutureListener() {
                @Override
                public void operationComplete(Future future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println("服务器连接已经完成，可以确保进行消息准确传输");
                    }
                }
            });
            channelFuture.channel().closeFuture().sync();  //等待关闭连接

        }finally {
            group.shutdownGracefully();
        }
    }
    //测试序列化  marshalling对象传输序列化  原生序列化方式的升级版  ``````````````
}
