package cn.com.onlinetool;

/**
 * @author choice
 * @description:
 * @date 2018/8/4 下午4:42
 *
 */
public class App {
    public static void main(String[] args){
        //创建无序树
        DisorderlyTree<String> disorderlyTree1 = new DisorderlyTree<String>();
        disorderlyTree1.setData("A");

        DisorderlyTree<String> disorderlyTree2 = new DisorderlyTree<String>();
        disorderlyTree2.setData("B");

        disorderlyTree1.addNode(disorderlyTree2);

        System.out.println(disorderlyTree1.toString());
    }
}
