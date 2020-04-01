package cn.com.onlinetool.codec.inner.line.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author choice
 * @date create in 2020/4/1 00:05
 */
public class LineNettyTimeClientHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 100; i ++){
            byte[] req = ("QUERY" + System.getProperty("line.separator")).getBytes();
            ByteBuf buf = Unpooled.buffer(req.length);
            buf.writeBytes(req);
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] res = new byte[buf.readableBytes()];
        buf.readBytes(res);
        String body = new String(res, StandardCharsets.UTF_8);
        System.out.println("Now time is : " + body + "，the count is ：" + ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
