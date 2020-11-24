package cn.com.onlinetool.codec.protobuf.client;

import cn.com.onlinetool.codec.protobuf.proto.PbMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author choice
 * @date create in 2020/4/1 00:02
 */
@Slf4j
public class ProtobufNettyTimeClientHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        PbMessage.TimeServerMessage.Builder pbMessage = null;
        for(int i = 0; i < 100; i ++){
            pbMessage = PbMessage.TimeServerMessage.newBuilder();
            pbMessage.setId(i).setUsername("choice").setData("QUERY");
            ctx.writeAndFlush(pbMessage);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PbMessage.TimeServerMessage pbMessage = (PbMessage.TimeServerMessage)msg;
        log.info("Client receive the protoBuf message : " + pbMessage.toString().trim().replaceAll("\r|\n|\r\n","")  + "，the count is ：" + ++count);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
