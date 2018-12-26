package cn.com.onlinetool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author choice
 * @description:
 * @date 2018-12-25 20:58
 *
 */
public class InputUtil {
    private static final BufferedReader KEYBORAD_INPUT = new BufferedReader(new InputStreamReader(System.in));
    private InputUtil(){

    }

    /**
     * 实现键盘数据的输入操作，可以返回的数据类型为String
     * @param prompt
     * @return
     */
    public static String getString(String prompt){
        boolean flag = true;
        String str = null;
        while (flag){
            System.out.println(prompt);
            try {
                str = KEYBORAD_INPUT.readLine();//读取一行数据
                if(null == str || "".equals(str)){
                    System.out.println("数据输入错误，该内容不允许为空");

                }else {
                    flag = false;
                }
            }catch (IOException e){
                System.out.println("数据输入错误，该内容不允许为空");
            }
        }
        return str;
    }
}
