package cn.com.onlinetool.nio.client;

import cn.com.onlinetool.nio.server.NioTimeServerHandler;

/**
 * @author choice
 * @date create in 2020/3/23 16:34
 */
public class NioTimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException ne){
                ne.printStackTrace();
            }
        }
        new Thread(new NioTimeClientHandler("127.0.0.1",port), "nio-time-client-pool-%d").start();

    }
}
