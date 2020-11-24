package cn.com.onlinetool.codec.msgpack.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author choice
 * @date create in 2020/4/1 00:01
 */
@Slf4j
public class MessagePackNettyTimeServerHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Map<String,String> req = (HashMap<String,String>) msg;
        log.info("Server receive the msgpack message : " + req.toString() + "，the count is ：" + ++count);
        String command = req.get("command");
        String currentTime = "QUERY".equalsIgnoreCase(command) ? new Date(System.currentTimeMillis()).toString() : "QUERY FAIL";
        Map<String,String> res = new HashMap<>();
        res.put("name", "choice");
        res.put("age", "23");
        res.put("data", currentTime);
        ctx.write(res);
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
