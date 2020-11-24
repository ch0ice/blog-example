package cn.com.onlinetool.nativeio.aio.server;

/**
 * @author choice
 * @date create in 2020/3/23 18:58
 */
public class AioTimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException ne){
                port = 8080;
            }
        }
        new Thread(new AioTimeServerHandler(port), "aio-time-server-pool-%d").start();
    }
}
