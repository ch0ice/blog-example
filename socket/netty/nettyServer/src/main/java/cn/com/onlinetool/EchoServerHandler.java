package cn.com.onlinetool;

import cn.com.onlinetool.serialization.serializable.Member;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author choice
 * @description:
 * ChannelInboundHandlerAdapter 输入数据处理适配器，是针对于数据输入的处理
 * Netty是基于NIO的一种开发框架的封装，这里和AIO是没有任何关系的。
 * @date 2018-12-25 20:16
 *
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


//    //Echo  ``````````````
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        //当客户端了连接成功之后会进行此方法的调用，明确可以给客户端发送一些信息
//        byte data[] = "【服务器激活信息】连接通道已经创建，服务器开始进行交互".getBytes();
//        //NIO是基于缓存的操作，所以Netty也提供有一系列的缓存类（封装了NIO中的Buffer）
//        ByteBuf buf = Unpooled.buffer(data.length); //Netty 自己定义的缓存类
//        buf.writeBytes(data); //将数据写入到缓存之中
//        ctx.writeAndFlush(buf); //强制性发送所有数据
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        try {
//            //要进行数据信息的额读取操作，对于读取操作之后也可以直接回应
//            //对于客户端发送过来的信息，由于没有进行指定的数据类型，所以都同意按照Object进行接收。
//            ByteBuf buf = (ByteBuf) msg; // 默认情况下的类型就是ByteBuf类型
//            //在进行数据类型过程转换之中还可以进行编码指定（NIO的时候）
//            String inputData = buf.toString(CharsetUtil.UTF_8).trim(); //将字节缓冲区的内容转为字符串 (这里就省去了 好多缓冲区重置的工作量)
//            String echoData = "[ECHO]" + inputData; // 将数据回应处理
//            //exit 是客户端发过来的内容，而quit可以理解为客户端的结束
//            if("exit".equalsIgnoreCase(inputData)){ //进行沟通断开
//                echoData = "quit";//结束当前交互
//            }
//            byte[] data = echoData.getBytes();
//            ByteBuf echoBuf = Unpooled.buffer(data.length);
//            echoBuf.writeBytes(data); // 将内容保存在缓存之中
//            ctx.writeAndFlush(echoBuf); //回应的输出操作
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            ReferenceCountUtil.release(msg); //释放缓存
//        }
//
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//    //Echo  ``````````````




//    //测试拆包解包    ···················
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        try {
//            //要进行数据信息的额读取操作，对于读取操作之后也可以直接回应
//            //对于客户端发送过来的信息，由于没有进行指定的数据类型，所以都同意按照Object进行接收。
//            ByteBuf buf = (ByteBuf) msg; // 默认情况下的类型就是ByteBuf类型
//            //在进行数据类型过程转换之中还可以进行编码指定（NIO的时候）
//            String inputData = buf.toString(CharsetUtil.UTF_8); //将字节缓冲区的内容转为字符串 (这里就省去了 好多缓冲区重置的工作量)
//            System.err.println("{服务器}" + inputData);
//            String echoData = "[ECHO]" + inputData  + System.getProperties().getProperty("line.separator"); // 将数据回应处理
//            byte[] data = echoData.getBytes();
//            ByteBuf echoBuf = Unpooled.buffer(data.length);
//            echoBuf.writeBytes(data); // 将内容保存在缓存之中
//            ctx.writeAndFlush(echoBuf); //回应的输出操作
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            ReferenceCountUtil.release(msg); //释放缓存
//        }
//
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//    //测试拆包解包    ···················



//    //测试序列化    ···················
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        try {
//            String inputData = msg.toString();
//            System.err.println("{服务器}" + inputData);
//            //使用系统默认分隔符拆包
////            String echoData = "[ECHO]" + inputData  + System.getProperties().getProperty("line.separator"); // 将数据回应处理
//
//            //使用自定义分隔符拆包
//            String echoData = "[ECHO]" + inputData  + HostInfo.SEPARATOR; // 将数据回应处理
//
//            ctx.writeAndFlush(echoData); //回应的输出操作
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            ReferenceCountUtil.release(msg); //释放缓存
//        }
//
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//    //测试序列化    ···················



    //测试序列化  自定义对象  ···················
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            Member member = (Member) msg;
            System.err.println("{服务器}" + member.toString());
            member.setName("[ECHO]" + member.getName());
            ctx.writeAndFlush(member); //回应的输出操作
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg); //释放缓存
        }

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
    //测试序列化  自定义对象  ···················


}
