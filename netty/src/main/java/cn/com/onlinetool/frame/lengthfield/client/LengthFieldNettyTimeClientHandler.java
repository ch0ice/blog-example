package cn.com.onlinetool.frame.lengthfield.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author choice
 * @date create in 2020/4/1 00:04
 */
@Slf4j
public class LengthFieldNettyTimeClientHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 100; i ++){
            byte[] req = "QUERY".getBytes();
            ByteBuf buf = Unpooled.buffer(req.length + 4);
            buf.writeInt(req.length + 4);
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
        log.info("Now time is : " + body + "，the count is ：" + ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
