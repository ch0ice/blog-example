package test;

public class Printing {


    public static void main(String[] args) {
        String target = "bcd";
        String block = "bcxbdcd";
        System.out.println(print(target,block));
        System.out.println(print1(target,block));

    }

    private static int print(String target,String block){
        StringBuilder sb = new StringBuilder();
        char[] b = block.toCharArray();
        for(int i = 0; i < b.length; i++){
            String s = Character.toString(b[i]);
            if(target.contains(s)){
                sb.append(s);
            }
        }
        return show(sb.toString(),target);
    }

    private static int show(String str, String key) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(key, index)) != -1) {
            index = index + key.length();
            count++;
        }
        return count;
    }

    private static int print1(String target,String block){
        char[] t = target.toCharArray();
        char[] b = block.toCharArray();
        int num = 0;
        int tidx = 0;
        for(int i = 0; i < b.length; i++){
            if(t[tidx] == b[i]){
                tidx++;
            }
            if(tidx == t.length){
                tidx = 0;
                num++;
            }
        }
        return num;
    }

}
