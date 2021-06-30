package cn.com.onlinetool.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author choice
 * @date 2021-06-30 14:40
 */
public class LotteryGenerateUtil {
    /**
     * 快乐8
     * 同一天相同参数重复执行结果不变
     *
     * @param size  一注几个号码
     * @param count 生成几注号码
     */
    public static void happy8(int size, int count) {
        final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
        int seed = Integer.parseInt(SDF.format(new Date()));
        Random random = new Random(seed);
        List<Integer> list = new ArrayList<>();
        int bound = 80;
        while (count > 0) {
            for (int i = 0; i < size; i++) {
                int num = random.nextInt(bound);
                if (num == 0) {
                    num = bound;
                }
                if (list.contains(num)) {
                    num++;
                }
                list.add(num);
            }
            Integer[] arr = list.toArray(new Integer[0]);
            int temp;
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j + 1] < arr[j]) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
            count--;
            list = new ArrayList<>();
        }
    }


    public static void main(String[] args) {
        happy8(10, 5);
    }
}
