package cn.com.onlinetool.nativeio.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author choice
 * @date create in 2020/3/23 19:32
 */
public class AioTimeClientHandler implements Runnable {
    private String host;
    private int port;
    AsynchronousSocketChannel channel;
    CountDownLatch latch;


    public AioTimeClientHandler(String host, int port){
        this.host = host;
        this.port = port;
        try {
            channel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        channel.connect(new InetSocketAddress(host,port), this, new AioTimeClientConnectCompletionHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
