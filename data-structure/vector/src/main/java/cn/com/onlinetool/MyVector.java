package cn.com.onlinetool;

/**
 * @author choice
 * @description: 向量的简单实现
 * @date 2018/8/2 下午8:15
 *
 */
public class MyVector implements Vector{
    /**
     * 向量的初始容量
     */
    private static int SIZE = 10;

    /**
     * 向量的实际大小
     */
    private int size;

    /**
     * 向量的数据类型
     */
    private static Object[] ARR;

    public MyVector(){
        size = 0;
        ARR = new Object[SIZE];
    }



    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public Object get(int r) throws VectorViolationException {
        rankValid(r);
        return ARR[r];
    }

    public Object replace(int r, Object obj) throws VectorViolationException {
        rankValid(r);
        Object bak = ARR[r];
        ARR[r] = obj;
        return bak;
    }

    public Object insert(int r, Object obj) throws VectorViolationException {
        if(r < 0 || r > size){
            throw new VectorViolationException("rankOutOfBound");
        }
        if(size + 1 == SIZE){
            extend();
        }
        for (int i = size; i > r; i--) {
            ARR[i] = ARR[i - 1];
        }
        ARR[r] = obj;
        return obj;
    }

    public Object put(Object obj) {
        int rank = size;
        if(size + 1 == SIZE){
            extend();
        }
        ARR[rank] = obj;
        return obj;
    }


    public Object remove(int r) throws VectorViolationException {
        rankValid(r);
        Object bak = ARR[r];
        for (int i = r; i < size - 1; i++){
            ARR[i] = ARR[i + 1];
        }
        size--;
        return bak;
    }

    /**
     * 数组扩容
     * @return
     */
    private Object[] extend() {
        SIZE *= 2;
        Object[] newObjs = new Object[SIZE];
        for(int i = SIZE; i > size; i--){
            newObjs[i] = ARR[i];
        }
        ARR = newObjs;
        return ARR;
    }

    /**
     * 检查rank是否合法
     * @param r
     * @return
     */
    private void rankValid(int r){
        if(r < 0 || r >= SIZE){
            throw new VectorViolationException("rankOutOfBound");
        }
    }
}
