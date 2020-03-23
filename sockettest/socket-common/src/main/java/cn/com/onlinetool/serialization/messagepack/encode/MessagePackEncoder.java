package cn.com.onlinetool.serialization.messagepack.encode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @author choice
 * @description:
 * @date 2018-12-26 20:29
 *
 */
public class MessagePackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        MessagePack msgPack = new MessagePack();
        byte[] raw = msgPack.write(msg); //进行对象的编码操作
        byteBuf.writeBytes(raw);
    }
}
