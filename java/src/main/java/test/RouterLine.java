package test;

import java.util.*;

public class RouterLine {
    public static void main(String[] args) {
        int[][] tickets = new int[][]{{4, 6}, {4, 6}, {3, 2}, {6, 3}, {2, 4}, {3, 4}, {6, 1}, {1, 0}};

        System.out.println(routing(tickets).toString());

    }

    /**
     * 路线规划
     * @param tickets
     * @return
     */
    private static List<Integer> routing(int[][] tickets){
        Map<Integer, ArrayList<ArrayList<Integer>>> res = new HashMap<>();
        getPath(0, tickets, res);
        ArrayList<Integer> line = null;
        for(int i = 0; i < res.size(); i++){
            if(res.get(i).size() > 0){
                return res.get(i).get(0);
            }
        }
        return new ArrayList<>();
    }
    /**
     * 获取所有路线
     * @param sIdx
     * @param tickets
     * @param res
     */
    private static void getPath(int sIdx, int[][] tickets, Map<Integer, ArrayList<ArrayList<Integer>>> res) {
        if (sIdx < tickets.length) {
            ArrayList<ArrayList<Integer>> iterms = new ArrayList<>();
            ArrayList<Integer> iterm = new ArrayList<>();
            iterm.add(sIdx);
            iterms.add(iterm);

            for (int j = 0; j < tickets.length; j++) {
                int xx = iterms.size();
                for (int x = 0; x < xx; x++) {
                    ArrayList<Integer> base = (ArrayList<Integer>) iterms.get(x).clone();
                    if (base.size() - 1 == j) {

                        int[] it = tickets[base.get(j)];
                        for (int i = 0; i < tickets.length; i++) {
                            if (!base.contains(i) && it[1] == tickets[i][0]) {
                                if (!iterms.contains(base)) {

                                    ArrayList<Integer> b = (ArrayList<Integer>) base.clone();
                                    b.add(i);
                                    iterms.add(b);
                                } else {
                                    iterms.get(x).add(i);
                                }
                            }
                        }
                    }
                }
            }
            //删除不完整路线
            iterms.removeIf(integers -> integers.size() < tickets.length);

//            res.put(new Integer[]{tickets[sIdx][0], tickets[sIdx][1]}, iterms);
            res.put(sIdx, iterms);
            getPath(++sIdx, tickets, res);
        }
    }

}
