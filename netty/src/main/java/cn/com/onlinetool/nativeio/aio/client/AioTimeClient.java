package cn.com.onlinetool.nativeio.aio.client;

/**
 * @author choice
 * @date create in 2020/3/23 18:58
 */
public class AioTimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException ne){
                port = 8080;
            }
        }
        new Thread(new AioTimeClientHandler("127.0.0.1", port), "aio-time-client-pool-%d").start();
    }
}
