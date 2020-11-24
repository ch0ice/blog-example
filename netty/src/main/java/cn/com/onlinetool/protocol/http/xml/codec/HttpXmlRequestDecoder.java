
package cn.com.onlinetool.protocol.http.xml.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


public class HttpXmlRequestDecoder extends
        AbstractHttpXmlDecoder<FullHttpRequest> {

    public HttpXmlRequestDecoder(boolean isPrint) {
        super(isPrint);
    }

    @Override
    protected void decode(ChannelHandlerContext arg0, FullHttpRequest arg1,
                          List<Object> arg2) throws Exception {
        if (!arg1.decoderResult().isSuccess()) {
            sendError(arg0, BAD_REQUEST);
            return;
        }
        HttpXmlRequest request = new HttpXmlRequest(arg1, super.decode0(arg0, arg1.content()));
        arg2.add(request);
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("Failure: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
