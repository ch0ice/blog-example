package cn.com.onlinetool.serialization.json.encode;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author choice
 * @description:
 * @date 2018-12-26 22:00
 *
 */
public class JSONEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        byte[] data = JSONObject.toJSONString(msg).getBytes();
        byteBuf.writeBytes(data);
    }
}
