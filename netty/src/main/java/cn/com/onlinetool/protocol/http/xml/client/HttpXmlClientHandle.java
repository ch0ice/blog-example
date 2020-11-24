
package cn.com.onlinetool.protocol.http.xml.client;

import cn.com.onlinetool.protocol.http.xml.codec.HttpXmlRequest;
import cn.com.onlinetool.protocol.http.xml.codec.HttpXmlResponse;
import cn.com.onlinetool.protocol.http.xml.pojo.OrderFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class HttpXmlClientHandle extends SimpleChannelInboundHandler<HttpXmlResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        HttpXmlRequest request = new HttpXmlRequest(null, OrderFactory.create(123));
        ctx.writeAndFlush(request);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                HttpXmlResponse msg) throws Exception {
        log.info("The client receive response of http header is : "
                + msg.getHttpResponse().headers().names());
        log.info("The client receive response of http body is : "
                + msg.getResult());
    }
}
