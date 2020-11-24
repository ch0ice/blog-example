package cn.com.onlinetool;

public class C extends B {
    public void c() {
        System.out.println("ccc");
    }

    public static void main(String[] args) {
        C a = new C() {
            @Override
            public void b() {
                System.out.println("123123132");
            }
        };
        a.b();

    }
}
