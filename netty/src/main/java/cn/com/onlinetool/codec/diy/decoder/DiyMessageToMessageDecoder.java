package cn.com.onlinetool.codec.diy.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * base64解码
 * @author choice
 * @date create in 2020/3/27 17:27
 */
public class DiyMessageToMessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        System.out.println("message to message decoder");
        byte[] data = new byte[msg.readableBytes()];
        msg.readBytes(data);

        //base64解码
        out.add(new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8));
    }
}
