package cn.com.onlinetool.nativeio.aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @author choice
 * @date create in 2020/3/23 19:46
 */
public class AioTimeClientWriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel channel;
    private CountDownLatch latch;
    public AioTimeClientWriteCompletionHandler(AsynchronousSocketChannel channel, CountDownLatch latch){
        this.channel = channel;
        this.latch = latch;
    }

    /**
     *
     * @author choice
     * @date 2020/3/25 10:30
     * @param result
     * @param attachment 异步channel携带的附件，通知回调函数的时候作为入参
     * @return void
     */
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        if(attachment.hasRemaining()){
            channel.write(attachment, attachment, this);
        } else {
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            channel.read(readBuffer, readBuffer, new AioTimeClientReadCompletionHandler(channel, latch));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
}
