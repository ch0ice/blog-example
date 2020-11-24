package cn.com.onlinetool.codec.marshalling.server;

import cn.com.onlinetool.codec.marshalling.pojo.MarshallingTestPojo;
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
public class MarshallingNettyTimeServerHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MarshallingTestPojo pojo = (MarshallingTestPojo)msg;
        log.info("Client receive the marshalling message : " + msg.toString()  + "，the count is ：" + ++count);
        String currentTime = "QUERY".equalsIgnoreCase(pojo.getData()) ? new Date(System.currentTimeMillis()).toString() : "QUERY FAIL";
        pojo.setData(currentTime);
        ctx.write(pojo);
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
