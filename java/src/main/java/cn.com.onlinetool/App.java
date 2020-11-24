package cn.com.onlinetool;

/**
 * @author choice
 * @date create in 2020/9/9 19:07
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        IOTest ioTest = new IOTest();
        ioTest.setA(1);
        ioTest = null;
        System.gc();
        System.out.println(
                "main end"
        );
        IOTest.aaa = 4;
        IOTest.aaa += 1;
        System.out.println(IOTest.aaa);


        C c = new C();
    }


}
