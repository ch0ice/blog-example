package cn.com.onlinetool.nativeio.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author choice
 * @date create in 2020/3/23 19:20
 */
public class AioTimeServerWriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private String currentTime;
    private AsynchronousSocketChannel channel;

    public AioTimeServerWriteCompletionHandler(String currentTime, AsynchronousSocketChannel channel){
        this.currentTime = currentTime;
        this.channel = channel;
    }

    /**
     *
     * @author choice
     * @date 2020/3/25 10:32
     * @param result
     * @param attachment 异步channel携带的附件，通知回调函数的时候作为入参
     * @return void
     */
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        if(attachment.hasRemaining()){
            channel.write(attachment, attachment, this);
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
