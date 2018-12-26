package cn.com.onlinetool.serialization.json.decode;

import cn.com.onlinetool.serialization.json.JSONMember;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author choice
 * @description:
 * @date 2018-12-26 22:00
 *
 */
public class JSONDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> list) throws Exception {
        int len = msg.readableBytes(); //可用的数据长度
        byte[] data = new byte[len];
        msg.getBytes(msg.readerIndex(),data,0,len);
        list.add(JSON.parseObject(data, JSONMember.class));
    }
}
