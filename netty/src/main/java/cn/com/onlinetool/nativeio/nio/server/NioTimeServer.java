package cn.com.onlinetool.nativeio.nio.server;

/**
 * NIO --- 同步非阻塞IO
 * @author choice
 * @date create in 2020/3/23 15:45
 */
public class NioTimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException ne){
                ne.printStackTrace();
            }
        }
        new Thread(new NioTimeServerHandler(port), "nio-time-server-pool-%d").start();

    }
}
