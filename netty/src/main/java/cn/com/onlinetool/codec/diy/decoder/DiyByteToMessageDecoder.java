package cn.com.onlinetool.codec.diy.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 示例代码为了尽可能的简单，这里直接将byte转换为字符串 然后转发给下一个处理器
 * @author choice
 * @date create in 2020/3/27 17:27
 */
public class DiyByteToMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        System.out.println("byte to message decoder");
        byte[] data = new byte[msg.readableBytes()];
        msg.readBytes(data);

        out.add(new String(data, StandardCharsets.UTF_8));
    }
}
