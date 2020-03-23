package cn.com.onlinetool.serialization.messagepack.decode;

import cn.com.onlinetool.serialization.messagepack.MsgPackMember;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @author choice
 * @description:
 * @date 2018-12-26 20:29
 *
 */
public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> list) throws Exception {
        int len = msg.readableBytes(); //获取读取的数据长度
        byte[] data = new byte[len];
        msg.getBytes(msg.readerIndex(),data,0,len); //读取数据
        MessagePack msgPack = new MessagePack();
//        list.add(msgPack.read(data));
        list.add(msgPack.read(data,msgPack.lookup(MsgPackMember.class)));

    }
}
