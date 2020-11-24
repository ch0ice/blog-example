package cn.com.onlinetool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IOTest {
    public IOTest(){

    }

    public static int aaa = 1;
    private int a;
    public void setA(int a){
        this.a = a;
    }
    public int getA(){
        return this.a;
    }

    @Override
    public void finalize(){
        System.out.println("IOTest对象被回收");
    }
    public static void main(String[] args) throws IOException {
//        String str = "";
//        int n;
//        System.out.println("请输入整数");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        str = reader.readLine();
//        System.out.println("输入的字符串为：" + str);

//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入学生的姓名：");
//        String name = sc.nextLine();
//
//        System.out.println("请输入学生的年龄：");
//        Integer age = sc.nextInt();
//
//        System.out.println("学生姓名为：" + name + "，学生年龄为：" + age);


        int num = 9;
        for (int i = 1; i <= num; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(i + " * " + j + " = " + (i * j) + "  ");
            }
            System.out.println();
            double d = 0.7e-3;
        }
    }



}
