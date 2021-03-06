package cn.com.onlinetool.nativeio.bio.bioPlusServer;

import cn.com.onlinetool.nativeio.bio.server.BioTimeServerHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步IO --- BIO + 线程池
 * 简单来说就是在传统BIO基础上增加线程池以达到异步的假象，
 * 这样服务端可以同时接收并处理多个客户端请求
 * 但是其读写操作没有任何改变，依旧是同步阻塞式。
 * @author choice
 * @date create in 2020/3/23 14:22
 */
@Slf4j
public class BioPlusTimeServer {
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
            BioPlusTimeServerHandlerExecutePoll executor = new BioPlusTimeServerHandlerExecutePoll(10, 20, 1024);
            Socket socket = null;
            while (true){
                socket = server.accept();
                executor.execute(new BioTimeServerHandler(socket));
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
