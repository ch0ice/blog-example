package cn.com.onlinetool.codec.protobuf.server;

import cn.com.onlinetool.codec.protobuf.proto.PbMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author choice
 * @date create in 2020/4/1 00:01
 */
@Slf4j
public class ProtobufNettyTimeServerHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PbMessage.TimeServerMessage req = (PbMessage.TimeServerMessage)msg;


        log.info("Server receive the protoBuf message : " + req.toString().replaceAll("\r|\n|\r\n","") + "，the count is ：" + ++count);
        String currentTime = "QUERY".equalsIgnoreCase(req.getData()) ? new Date(System.currentTimeMillis()).toString() : "QUERY FAIL";
        PbMessage.TimeServerMessage.Builder pbRes = PbMessage.TimeServerMessage.newBuilder();
        pbRes.setId(req.getId()).setUsername(req.getUsername()).setData(currentTime);
        ctx.write(pbRes);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
