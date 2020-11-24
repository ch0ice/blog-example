package cn.com.onlinetool;

import java.util.Calendar;
import java.util.Date;

public class B extends A{
    public void b(){
        System.out.println("bbb");
    }


    public void az() throws Exception {
        throw new Exception("123");
    }

    public static void main(String[] args) {
        String str = "a";
        System.out.println(str.compareTo("1"));
        System.out.println(str.compareTo("b"));
        System.out.println(str.compareTo("a"));

        char c = 'a';
        char c1 = '1';
        System.out.println((int)c);
        System.out.println((int)c1);

        System.out.println("123456789".substring(0));


        StringBuffer buffer = new StringBuffer("123");
        str = null;
        System.out.println(buffer.append(str));
        System.out.println(buffer.insert(3,str));

        System.out.println(new Date().toString());
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
    }


}