package cn.com.onlinetool.nativeio.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author choice
 * @date create in 2020/3/23 19:07
 */
public class AioTimeServerAcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AioTimeServerHandler> {

    /**
     *
     * @author choice
     * @date 2020/3/25 10:31
     * @param result
     * @param attachment 异步channel携带的附件，通知回调函数的时候作为入参
     * @return void
     */
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
