package cn.com.onlinetool.aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @author choice
 * @date create in 2020/3/23 19:50
 */
public class AioTimeClientReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel channel;
    private CountDownLatch latch;
    public AioTimeClientReadCompletionHandler(AsynchronousSocketChannel channel, CountDownLatch latch){
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
        attachment.flip();
        byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
        String body;
        body = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("Now time is :" + body);
        latch.countDown();
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
