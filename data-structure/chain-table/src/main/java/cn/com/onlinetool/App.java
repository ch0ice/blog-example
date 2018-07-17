package cn.com.onlinetool;

import java.sql.SQLOutput;
import java.util.*;

/**
 * @author choice
 * @description:
 * @date 2018/7/12 17:42
 */
public class App {
    public static void main(String[] args){
        List<Integer> l1 = new ArrayList<Integer>(4);
        for(int i = 0; i < 10; i++){
            l1.add(i);
        }
        for(int i = 0; i < 10; i++){
            System.out.println(l1.get(i));
        }


        List<Integer> l2 = new LinkedList<Integer>();
        for(int i = 0; i < 10; i++){
            l2.add(i);
        }
        for(int i = 0; i < 10; i++){
            System.out.println(l2.get(i));
        }

        Map<String,String> stringMap = new HashMap();



         int [] aa = new int[1024];
        aa[1] = 1;
        System.out.println(aa);
    }
}
