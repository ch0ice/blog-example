package cn.com.onlinetool.aio.server;

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
