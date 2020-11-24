
package cn.com.onlinetool.protocol.http.xml.codec;

import io.netty.handler.codec.http.FullHttpResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpXmlResponse {
    private FullHttpResponse httpResponse;
    private Object result;

}
