
package cn.com.onlinetool.protocol.http.xml.codec;

import cn.com.onlinetool.protocol.http.xml.util.XStreamUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;


@Slf4j
public abstract class AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T> {
    private boolean isPrint;
    final static String CHARSET_NAME = "UTF-8";
    final static Charset UTF_8 = Charset.forName(CHARSET_NAME);
    public AbstractHttpXmlEncoder(boolean isPrint){
        this.isPrint = isPrint;
    }


    protected ByteBuf encode0(ChannelHandlerContext ctx, Object body) throws Exception {
        String xmlStr = XStreamUtil.obj2xml(body);
        if (isPrint)
            log.info("The encoded message is : \r\n" + xmlStr);
        ByteBuf encodeBuf = Unpooled.copiedBuffer(xmlStr, UTF_8);
        return encodeBuf;
    }

}
