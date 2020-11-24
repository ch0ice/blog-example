package cn.com.onlinetool.codec.diy.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.List;

/**
 * 示例代码为了尽可能的简单，这里直接将字符串进行base64编码然后发出
 * @author choice
 * @date create in 2020/3/27 17:25
 */
@Slf4j
public class DiyMessageToMessageEncoder extends MessageToMessageEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        log.info("message to message encoder");
        //base64 编码
        out.add(Base64.getEncoder().encodeToString(msg.getBytes()));
    }
}
