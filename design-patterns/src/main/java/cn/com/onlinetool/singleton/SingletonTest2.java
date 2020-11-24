package cn.com.onlinetool.singleton;

/**
 * 初识加载
 * 线程安全
 */
public class SingletonTest2 {
    private SingletonTest2(){}
    private static SingletonTest2 INSTANCE = new SingletonTest2();

    public static SingletonTest2 getInstance(){
        return SingletonTest2.INSTANCE;
    }
}
