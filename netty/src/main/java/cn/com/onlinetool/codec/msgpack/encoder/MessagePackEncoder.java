package cn.com.onlinetool.codec.msgpack.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author choice
 * @date create in 2020/4/4 22:40
 */
public class MessagePackEncoder extends MessageToByteEncoder<HashMap<String,Object>> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HashMap<String, Object> msg, ByteBuf out) throws Exception {
        MessagePack messagePack = new MessagePack();
        out.writeBytes(messagePack.write(msg));
    }

}
