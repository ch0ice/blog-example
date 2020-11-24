package cn.com.onlinetool.singleton;

/**
 * 懒加载模式
 * 线程不安全
 */
public class SingletonTest1 {
    private SingletonTest1(){}
    private static SingletonTest1 INSTANCE = null;

    public static SingletonTest1 getInstance(){
        if(null == INSTANCE){
            INSTANCE = new SingletonTest1();
        }
        return INSTANCE;
    }
}
