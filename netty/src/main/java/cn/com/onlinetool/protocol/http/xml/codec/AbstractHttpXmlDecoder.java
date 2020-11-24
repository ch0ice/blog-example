
package cn.com.onlinetool.protocol.http.xml.codec;

import cn.com.onlinetool.protocol.http.xml.util.XStreamUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public abstract class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {
    private boolean isPrint;
    private final static String CHARSET_NAME = "UTF-8";
    private final static Charset UTF_8 = Charset.forName(CHARSET_NAME);

    protected AbstractHttpXmlDecoder(boolean isPrint) {
        this.isPrint = isPrint;
    }

    protected Object decode0(ChannelHandlerContext arg0, ByteBuf body) throws Exception {
        String content = body.toString(UTF_8);
        if (isPrint)
            log.info("The decoded message is : \r\n" + content);
        return XStreamUtil.xml2obj(content);
    }

}
