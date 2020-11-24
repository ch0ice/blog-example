package cn.com.onlinetool.codec.diy.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 示例代码为了尽可能的简单，这里直接将字符串当做我们自定义的对象转换为ByteBuf然后发出
 * @author choice
 * @date create in 2020/3/27 17:24
 */
@Slf4j
public class DiyMessageToByteEncoder extends MessageToByteEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        log.info("message to byte encoder");
        out.writeBytes(msg.getBytes());
        ctx.writeAndFlush(out);
        out.release();
    }
}
