package cn.com.onlinetool;

/**
 * @author choice
 * @description: 抽象数据类型向量的简单定义
 * 下面的秩为rank，和数组中的下标或指针一样，为了区别开两者，这里叫rank
 * @date 2018/8/2 下午8:10
 *
 */
public interface Vector {
    /**
     * 返回向量中元素数目
     * @return
     */
    int size();

    /**
     * 判断向量是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 取秩为r的元素
     * @param r
     * @return
     */
    Object get(int r) throws VectorViolationException;

    /**
     * 将秩为r的元素替换为obj
     * @param r
     * @param obj
     * @return
     */
    Object replace(int r, Object obj) throws VectorViolationException;

    /**
     * 插入obj，作为秩为r的元素；返回该元素
     * @param r
     * @param obj
     * @return
     */
    Object insert(int r, Object obj) throws VectorViolationException;

    /**
     * 添加元素
     * @param obj
     * @return
     */
    Object put(Object obj);

    /**
     * 删除秩为r的元素
     * @param r
     * @return
     */
    Object remove(int r) throws VectorViolationException;

}
