package cn.com.onlinetool.interview.array;


import java.util.Arrays;

/**
 * 获取整数数组中最大连续序列
 */
public class MaxContinuousSeqTest {

    public static void main(String[] args) {

        int[] src = new int[]{-10, 3,10,-8,11,6};
        int len = 2;
        System.out.println("测试数据为：" + Arrays.toString(src));
        MaxContinuousSeq.findBigSequence1(src, len);
        MaxContinuousSeq.findBigSequence2(src, len);
        MaxContinuousSeq.findBigSequence3(src);

    }
}
