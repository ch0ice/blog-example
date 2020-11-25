package cn.com.onlinetool.questions.array;

import java.util.Arrays;

public class MaxContinuousSeqTest {
    public static void main(String[] args) {
        int[] src = new int[]{-10, 3,10,-8,11,6};
        int len = 2;
        System.out.println("测试数据为：" + Arrays.toString(src));
        MaxContinuousSeq.findMaxSequence1(src, len);
        MaxContinuousSeq.findMaxSequence2(src, len);
        MaxContinuousSeq.findMaxSequence3(src);

    }
}
