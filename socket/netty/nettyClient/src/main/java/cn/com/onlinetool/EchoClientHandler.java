package cn.com.onlinetool;

import cn.com.onlinetool.common.constant.HostInfo;
import cn.com.onlinetool.common.util.InputUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author choice
 * @description: 需要进行数据的读取，服务器端处理完的信息会进行读取
 * @date 2018-12-25 20:52
 *
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

//    //Echo  ````````````````
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //只要服务器端发送完成数据之后，都会执行此方法进行内容的操作
//        try {
//            ByteBuf readBuf = (ByteBuf) msg;
//            String readData = readBuf.toString(CharsetUtil.UTF_8).trim(); //接收返回数据内容
//            if("quit".equalsIgnoreCase(readData)) { //结束操作
//                System.out.println("[Exit] 拜拜，您已经结束了本次网络传输，再见");
//                ctx.close();//关闭通道
//            }else {
//                System.out.println(readData); //输出服务器端的响应内容
//                String inputData = InputUtil.getString("请输入要发送的信息:");
//                byte[] data = inputData.getBytes(); //将输入的数据变为字节数组
//                ByteBuf sendBuf = Unpooled.buffer(data.length);
//                sendBuf.writeBytes(data); //将数据保存在缓存之中
//                ctx.writeAndFlush(sendBuf);//数据发送
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            ReferenceCountUtil.release(msg); //释放缓存
//        }
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//    //Echo  ````````````````



//    //测试拆包解包    ···················
//    private static final  int REPEAT = 500;//消息重复发送次数（测试战报拆包）
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        for(int i = 0; i < REPEAT; i++){ //消息重复发送
//            byte[] data = ("[" + i + "]" + " Hello world" + System.getProperties().getProperty("line.separator")).getBytes();
//            ByteBuf buf = Unpooled.buffer(data.length);
//            buf.writeBytes(data);
//            ctx.writeAndFlush(buf);
//        }
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //只要服务器端发送完成数据之后，都会执行此方法进行内容的操作
//        try {
//            ByteBuf readBuf = (ByteBuf) msg;
//            String readData = readBuf.toString(CharsetUtil.UTF_8).trim(); //接收返回数据内容
//            System.out.println(readData); //输出服务器端的响应内容
//        }finally {
//            ReferenceCountUtil.release(msg); //释放缓存
//        }
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//    //测试拆包解包    ···················




    //测试序列化    ···················
    private static final  int REPEAT = 500;//消息重复发送次数（测试战报拆包）
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < REPEAT; i++){ //消息重复发送
            //使用系统默认分隔符进行拆包
//            ctx.writeAndFlush("[" + i + "]" + " Hello world" + System.getProperties().getProperty("line.separator"));

            //使用自定义分隔符拆包
            ctx.writeAndFlush("[" + i + "]" + " Hello world" + HostInfo.SEPARATOR);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //只要服务器端发送完成数据之后，都会执行此方法进行内容的操作
        try {
            String readData = msg.toString().trim(); //接收返回数据内容
            System.out.println(readData); //输出服务器端的响应内容
        }finally {
            ReferenceCountUtil.release(msg); //释放缓存
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
    //测试序列化    ···················

}
