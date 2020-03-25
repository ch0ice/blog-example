package cn.com.onlinetool.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author choice
 * @date create in 2020/3/23 19:11
 */
public class AioTimeServerReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public AioTimeServerReadCompletionHandler(AsynchronousSocketChannel channel){
        if(channel != null){
            this.channel = channel;
        }
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
        attachment.flip();
        byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
        String req = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("The time server receive :" + req);
        String currentTime = "QUERY".equalsIgnoreCase(req) ? new Date(System.currentTimeMillis()).toString() : "QUERY FAIL";
        doWrite(currentTime);

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void doWrite(String currentTime){
        if(currentTime != null && currentTime.trim().length() > 0){
            byte[] bytes = currentTime.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer, new AioTimeServerWriteCompletionHandler(currentTime, channel));
        }
    }
}
