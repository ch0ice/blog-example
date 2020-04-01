package cn.com.onlinetool.codec.inner.lengthfield.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author choice
 * @date create in 2020/4/1 00:04
 */
public class LengthFieldNettyTimeServerHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("The time server receive : " + body + "，the count is ：" + ++count);
        String currentTime = "QUERY".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "QUERY FAIL";
        ByteBuf res = Unpooled.buffer(currentTime.getBytes().length + 4);
        res.writeInt(currentTime.length());
        res.writeBytes(currentTime.getBytes());
        ctx.write(res);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}