package cn.com.onlinetool.array;


import java.util.Arrays;

/**
 * 获取整数数组中最大连续序列
 */
public class MaxContinuousSeq {

    /**
     * 单指针
     * @param src
     * @param size
     * @return
     */
    public static int[] findBigSequence1(int[] src, int size) {
        System.out.println("取数组中指定长度为：" + size + "的最大连续序列，单指针方式，循环次数：" + (src.length - size + 1));
        int[] res = null;
        int currSum = 0;
        int sum = 0;
        for (int i = 0; i <= src.length - size; i++) {
            currSum = incr(src,i,i+size);
            if (currSum >= sum) {
                res = Arrays.copyOfRange(src, i, i + size);;
                sum = currSum;
            }
        }
        System.out.println("最大连续序列为：" + Arrays.toString(res));
        return res;
    }
    /**
     * 双指针
     * @param src
     * @param size
     * @return
     */
    public static int[] findBigSequence2(int[] src, int size) {
        System.out.println("取数组中指定长度为：" + size + "的最大连续序列，双指针方式，循环次数：" + (src.length / size));
        int[] res = null;
        int currBeforeSum = 0;
        int currBehindSum = 0;
        int beforeSum = 0;
        for (int i = 0, j = src.length; i < src.length / size; i++, j--) {
            currBeforeSum = incr(src,i,i+size);
            currBehindSum = incr(src,j-size,j);
            if (currBeforeSum >= currBehindSum && currBeforeSum > beforeSum) {
                res = Arrays.copyOfRange(src, i, i + size);
                beforeSum = currBeforeSum;
            } else if (currBehindSum > beforeSum) {
                res = Arrays.copyOfRange(src, j - size, j);
                beforeSum = currBehindSum;
            }
        }
        System.out.println("最大连续序列为：" + Arrays.toString(res));
        return res;
    }


    /**
     * 不指定长度
     * @param src
     * @return
     */
    public static int[] findBigSequence3(int[] src) {
        System.out.println("取数组中随机长度的最大连续序列");
        //最终结果
        int[] res = null;
        //保存每个长度的最大值
        int max = incr(src,0,src.length);
        for(int size = 1; size <= src.length; size++){
            int currBeforeSum = 0;
            int currBehindSum = 0;
            int currSum = 0;
            int[] currRes = null;
            for (int i = 0, j = src.length; i < src.length / size; i++, j--) {
                currBeforeSum = incr(src,i,i + size);
                currBehindSum = incr(src,j - size, j);
                if (currBeforeSum >= currBehindSum && currBeforeSum > currSum) {
                    currRes = Arrays.copyOfRange(src, i, i + size);
                    currSum = currBeforeSum;
                } else if (currBehindSum > currSum) {
                    currRes = Arrays.copyOfRange(src, j - size, j);
                    currSum = currBehindSum;
                }
                if(currSum > max){
                    res = currRes;
                    max = currSum;
                }

            }
            System.out.println("长度为" + size + "的最大连续序列为：" + Arrays.toString(currRes));
        }
        System.out.println("唯一最大连续序列为：" + Arrays.toString(res));
        return res;
    }


    /**
     * 求数组中 开始下标 到 结束下标 元素之和
     * @param src
     * @return
     */
    private static int incr(int[] src,int sIdx,int eIdx) {
        int res = 0;
        for (int i = sIdx; i < eIdx; i++) {
            res += src[i];
        }
        return res;
    }

}
