package cn.com.onlinetool.singleton;

/**
 * 双检锁（DCL），懒加载
 * 线程安全
 * 增加 volatile 的原因：
 *      正常创建一个对象简述为三步：
 *          1.读取Class对象并分配内存
 *          2.将对象初始化
 *          3.将符号引用指向堆内的对象内存地址
 *      Class在被创建时有可能（概率极低）出现指令重排，会出现上述第2步和第3步发生错位，可能导致引用不为null，但对象并没有初始化完成，单线程下没有问题，
 *      但在多线程并发读取对象时，可能会导致程序崩溃，而被 volatile 标注的引用不会发生指令重排，从而避免了这个问题。
 *      但 JDK <= 1.5 volatile 并没有添加避免指令重排的语义，所以依然会出现上述概率极低的多线程访问问题，解决方法就是不使用懒加载。
 */
public class SingletonTest3 {
    private SingletonTest3(){};
    private volatile static SingletonTest3 INSTANCE = null;

    public static SingletonTest3 getInstance(){
        if(null == INSTANCE){
            synchronized (SingletonTest3.class){
                if(null == INSTANCE){
                    INSTANCE = new SingletonTest3();
                }
            }
        }
        return INSTANCE;
    }
}
