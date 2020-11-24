package aaa;


/**
 * 算法复杂度
 */
public class Complexity {

    /**
     * O(1)复杂度代码
     */
    private void O1(){
        int i = 1000;
        System.out.println(i);
    }

    /**
     * O(N)复杂度代码
     */
    private void On(){
        int n = 10;
        for (int i = 0; i <= n; i++) {
            System.out.println(i);
        }
    }

    /**
     * O(N^2)复杂度代码
     */
    private void On2(){
        int n = 10;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i);
            }
        }
    }

    /**
     * O(log(N))复杂度代码
     */
    private void Ologn(){
        int n = 10;
        for (int i = 0; i < n; i *= 2) {
            System.out.println(i);
        }
    }

    /**
     * O(k^n)复杂度代码
     */
    private void Okn(){
        int n = 10;
        for (int i = 0; i <= Math.pow(2,n); i++) {
            System.out.println(i);
        }
    }

//    /**
//     * O(n!)复杂度代码
//     */
//    private void On1(){
//        for (int i = 0; i <=  ; i++) {
//
//        }
//    }

    public static void main(String[] args) {
        //O(n)
        int n = 100;
        int r = 0;
        for (int i = 1; i <= n ; i++) {
            r += i;
        }
        System.out.println(r);

        //O(1)
        n = n * (n + 1) /2;
        System.out.println(n * (n + 1) /2);


    }



}
