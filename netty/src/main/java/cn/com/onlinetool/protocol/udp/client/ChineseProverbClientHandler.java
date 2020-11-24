
package cn.com.onlinetool.protocol.udp.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChineseProverbClientHandler extends
        SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg)
	    throws Exception {
	String response = msg.content().toString(CharsetUtil.UTF_8);
	if (response.startsWith("谚语查询结果: ")) {
	    log.info(response);
	    ctx.close();
	}
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	    throws Exception {
	cause.printStackTrace();
	ctx.close();
    }
}
