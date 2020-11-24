
package cn.com.onlinetool.protocol.tcp.struct;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class NettyMessage {

    private Header header;

    private Object body;

}
