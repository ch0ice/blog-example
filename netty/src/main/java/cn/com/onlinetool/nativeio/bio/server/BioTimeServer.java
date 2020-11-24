package cn.com.onlinetool.nativeio.bio.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 经典BIO --- 同步阻塞IO
 * 客户端必须等待前一个客户端的请求被服务器处理完毕，新的请求才会被接受并处理。
 * @author choice
 * @date create in 2020/3/23 14:22
 */
@Slf4j
public class BioTimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException ne){
                ne.printStackTrace();
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            log.info("The time server is start in port :" + port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new BioTimeServerHandler(socket), "bio-time-server-pool-%d").start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != server){
                    log.info("The time server is closed");
                    server.close();
                    server = null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
