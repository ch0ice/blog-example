package cn.com.onlinetool.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author choice
 * @date create in 2020/3/23 19:07
 */
public class AioTimeServerAcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AioTimeServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result, AioTimeServerHandler attachment) {
        attachment.channel.accept(attachment, this);
        ByteBuffer buff = ByteBuffer.allocate(1024);
        result.read(buff,buff,new AioTimeServerReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AioTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
