package cn.com.onlinetool;

/**
 * @author choice
 * @description:
 * @date 2018/8/2 下午7:56
 *
 */
public class App {
    public static void main(String[] args){
        Vector vector = new MyVector();
        System.out.println("增加元素");
        vector.put(1);
        vector.put(2);
        vector.put(3);
        for (int i = 0; i < vector.size(); i++){
            System.out.println(vector.get(i));
        }
        System.out.println("插入元素");
        vector.insert(2,5);
        for (int i = 0; i < vector.size(); i++){
            System.out.println(vector.get(i));
        }
        System.out.println("删除元素");


    }
}
