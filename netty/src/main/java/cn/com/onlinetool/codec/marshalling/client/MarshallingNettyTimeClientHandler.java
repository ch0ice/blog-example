package cn.com.onlinetool.codec.marshalling.client;

import cn.com.onlinetool.codec.marshalling.pojo.MarshallingTestPojo;
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
public class MarshallingNettyTimeClientHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 100; i ++){
            MarshallingTestPojo pojo = new MarshallingTestPojo();
            pojo.setId(i);
            pojo.setUsername("choice");
            pojo.setData("QUERY");
            ctx.writeAndFlush(pojo);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MarshallingTestPojo pojo = (MarshallingTestPojo)msg;
        log.info("Client receive the marshalling message : " + pojo.toString()  + "，the count is ：" + ++count);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
