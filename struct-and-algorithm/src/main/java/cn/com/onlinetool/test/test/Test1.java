package cn.com.onlinetool.test.test;

public class Test1 {
    public static void main(String[] args) {
        int[] price = {1,5,9,4,6,8,1,7};
        int sum = 0;
        for (int i = 0,j = 1; j < price.length; i++,j++){
            int val = price[j] - price[i];
            sum += Math.max(val, 0);
            System.out.println(price[j] + " - " + price[i] + "=" + val);
        }
        System.out.println("total：" + sum);
    }
}
