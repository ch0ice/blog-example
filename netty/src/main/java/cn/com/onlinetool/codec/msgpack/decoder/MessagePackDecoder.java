package cn.com.onlinetool.codec.msgpack.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.HashMap;
import java.util.List;

/**
 * @author choice
 * @date create in 2020/4/4 22:40
 */
@Slf4j
public class MessagePackDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] req = new byte[in.readableBytes()];
        in.readBytes(req);
        MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(req, Templates.tMap(Templates.TString,Templates.TString)));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.info("MessagePack decoder error：");
        cause.printStackTrace();
        ctx.close();
    }

}
