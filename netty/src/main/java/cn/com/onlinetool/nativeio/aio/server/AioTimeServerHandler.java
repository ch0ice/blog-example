package cn.com.onlinetool.nativeio.aio.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author choice
 * @date create in 2020/3/23 19:02
 */
@Slf4j
public class AioTimeServerHandler implements Runnable {
    CountDownLatch latch;
    AsynchronousServerSocketChannel channel;

    public AioTimeServerHandler(int port){
        try {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress(port));
            log.info("The time server is start in port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept(){
        channel.accept(this, new AioTimeServerAcceptCompletionHandler());
    }
}
