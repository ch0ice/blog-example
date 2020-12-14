package test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

public class RouterLine1 {

    private static void calRout(int[][] tickets, List<Integer> rout) {
        // 路径包含所有节点，证明已经遍历完成
        if (rout.size() == tickets.length) {
            System.out.println(JSON.toJSONString(rout));
            return;
        }

        // 第一次需要找出最小的一个作为起始节点
        if (rout.isEmpty()) {
            int start = 0;
            for (int i = 0; i < tickets.length; i++) {
                if ((tickets[i])[0] < (tickets[start])[0]) {
                    start = i;
                }
            }
            rout.add(start);
        }

        // 找出末尾路径
        int[] tail = tickets[rout.get(rout.size() - 1)];

        // 找出下一个节点
        for (int i = 0; i < tickets.length; i++) {
            // 不是第一个节点，且已经包含在路径中
            if (!rout.get(0).equals(i) && rout.contains(i)) {
                continue;
            }
            int[] next = tickets[i];
            if (tail[1] == next[0]) {
                rout.add(i);
                calRout(tickets, Lists.newArrayList(rout));
            }
        }
    }

    public static void main(String[] args) {
        int[][] tickets = {{3, 1}, {0, 2}, {1, 2}, {2, 3}};
//        int[][] tickets = new int[][]{{4, 6}, {4, 6}, {3, 2}, {6, 3}, {2, 4}, {3, 4}, {6, 1}, {1, 0}};
        List<Integer> rout = Lists.newArrayList();
        calRout(tickets, rout);
        System.out.println(rout.toString());
    }
}
