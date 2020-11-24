
package cn.com.onlinetool.codec.serializable.server;

import cn.com.onlinetool.codec.serializable.pojo.SerializableTestPojo;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Sharable
public class SerializableNettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        SerializableTestPojo req = (SerializableTestPojo) msg;
		log.info("Server receive the java message : " + req.toString());
        req.setName("choice-res");
		ctx.writeAndFlush(req);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();// 发生异常，关闭链路
    }

}
