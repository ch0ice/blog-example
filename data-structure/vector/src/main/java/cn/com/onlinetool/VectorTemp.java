package cn.com.onlinetool;

/**
 * @author choice
 * @description: 抽象数据类型向量的简单定义
 * @date 2018/8/2 下午8:10
 *
 */
public interface VectorTemp<E> {
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
     * 检查一个对象是否存在
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     *
     * @param e
     * @param index
     * @return
     */
    int indexOf(E e, int index);

    /**
     * 返回一个元素在向量中最后一次出现的位置
     * @param e
     * @return
     */
    int lastIndexOf(E e);

    /**
     * 从指定下标开始轮训返回一个元素在向量中最后一次出现的位置
     * @param e
     * @param index
     * @return
     */
    int lastIndexOf(E e, int index);

    /**
     * 取秩为r的元素
     * @param index
     * @return
     * @throws VectorViolationException
     */
    E get(int index) throws VectorViolationException;

    /**
     * 插入obj，作为秩为r的元素；返回该元素
     * @param index
     * @param e
     * @return
     * @throws VectorViolationException
     */
    E set(int index, E e) throws VectorViolationException;

    /**
     * 添加元素
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 插入给定元素到给定下标
     * @param index
     * @param obj
     * @return
     */
    E insert(int index, E obj);

    /**
     * 删除秩为r的元素
     * @param index
     * @return
     * @throws VectorViolationException
     */
    E remove(int index) throws VectorViolationException;

}
