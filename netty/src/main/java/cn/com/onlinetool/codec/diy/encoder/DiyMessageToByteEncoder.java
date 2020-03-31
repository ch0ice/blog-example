package cn.com.onlinetool.codec.diy.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 示例代码为了尽可能的简单，这里直接将字符串当做我们自定义的对象转换为ByteBuf然后发出
 * @author choice
 * @date create in 2020/3/27 17:24
 */
public class DiyMessageToByteEncoder extends MessageToByteEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        System.out.println("message to byte encoder");
        out.writeBytes(msg.getBytes());
        ctx.writeAndFlush(out);
        out.release();
    }
}
