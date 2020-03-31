package cn.com.onlinetool.codec.diy.decoder;

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
public class DiyMessageToMessageDecoder extends MessageToMessageDecoder<String> {

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        System.out.println("message to message decoder");
        byte[] data = msg.getBytes();
        //base64解码
        out.add(new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8));
    }
}
