package cn.com.onlinetool.protocol.file.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


/**
 * @author choice
 * @date create in 2020/4/1 00:02
 */
@Slf4j
public class FileClientHandler extends ChannelInboundHandlerAdapter {
    private static final String SEPARATOR = "*_*";
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("/Users/choice/Downloads/20181024162503.csv" + SEPARATOR);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String res = (String)msg;
        log.info("Now time is : " + res + "，the count is ：" + ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info(cause.getMessage());
        ctx.close();
    }
}
