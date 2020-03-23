package cn.com.onlinetool;

/**
 * @author choice
 * @description:
 * @date 2018-12-25 22:35
 *
 */
public class TestSeparator {
    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println(System.getProperties().getProperty("line.separator"));
    }
}
