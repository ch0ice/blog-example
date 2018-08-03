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
        System.out.println("增加元素,当前大小：" + vector.size());
        vector.put(1);
        vector.put(2);
        vector.put(3);
        for (int i = 0; i < vector.size(); i++){
            System.out.println(vector.get(i));
        }
        System.out.println("插入元素,当前大小：" + vector.size());
        vector.insert(2,5);
        for (int i = 0; i < vector.size(); i++){
            System.out.println(vector.get(i));
        }
        System.out.println("删除元素,当前大小：" + vector.size());
        vector.remove(3);
        for (int i = 0; i < vector.size(); i++){
            System.out.println(vector.get(i));
        }
        System.out.println("替换元素,当前大小：" + vector.size());
        vector.replace(1,9);
        for (int i = 0; i < vector.size(); i++){
            System.out.println(vector.get(i));
        }



    }
}
