package cn.com.onlinetool.aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

/**
 * @author choice
 * @date create in 2020/3/23 19:39
 */
public class AioTimeClientConnectCompletionHandler implements CompletionHandler<Void,AioTimeClientHandler> {

    @Override
    public void completed(Void result, AioTimeClientHandler attachment) {
        byte[] bytes = "QUERY".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        attachment.channel.write(writeBuffer, writeBuffer, new AioTimeClientWriteCompletionHandler(attachment.channel, attachment.latch));
    }

    @Override
    public void failed(Throwable exc, AioTimeClientHandler attachment) {
        try {
            attachment.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            attachment.latch.countDown();
        }
    }
}
