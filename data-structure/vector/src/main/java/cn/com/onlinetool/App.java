package cn.com.onlinetool;

import java.util.Vector;

/**
 * @author choice
 * @description:
 * @date 2018/8/2 下午7:56
 *
 */
public class App {
    public static void main(String[] args){
        VectorTemp<String> myVector = new MyVector<String>(2);
        System.out.println("增加元素,当前大小：" + myVector.size());
        myVector.add("A");
        myVector.add("B");
        myVector.add("C");
        for (int i = 0; i < myVector.size(); i++){
            System.out.println(myVector.get(i));
        }
        System.out.println("插入元素,当前大小：" + myVector.size());
        myVector.insert(2,"I");
        for (int i = 0; i < myVector.size(); i++){
            System.out.println(myVector.get(i));
        }
        System.out.println("删除元素,当前大小：" + myVector.size());
        myVector.remove(3);
        for (int i = 0; i < myVector.size(); i++){
            System.out.println(myVector.get(i));
        }
        System.out.println("替换元素,当前大小：" + myVector.size());
        myVector.set(1,"R");
        for (int i = 0; i < myVector.size(); i++){
            System.out.println(myVector.get(i));
        }


        Vector vector = new Vector();




    }
}
