package cn.com.onlinetool;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author choice
 * @description: 向量的简单实现
 * @date 2018/8/2 下午8:15
 *
 */
public class MyVector<E> implements VectorTemp<E> {

    /**
     * 向量的容量
     */
    private int capacityIncrement;

    /**
     * 向量的实际大小
     */
    private int elementCount;

    /**
     * 向量的数据类型
     */
    private Object[] elementData;


    /**
     * 给定初始大小
     * @author choice
     * @param initialCapacity
     */
    public MyVector(int initialCapacity) {
        if (initialCapacity < 0){
            throw new IllegalArgumentException("Illegal Capacity: "+
                initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = initialCapacity;
    }

    /**
     * 无参构造，默认初始大小10
     * @author choice
     */
    public MyVector(){
        this(10);
    }

    /**
     * Collection to Vector
     * @author choice
     * @param c
     */
    public MyVector(Collection<? extends E> c) {
        elementData = c.toArray();
        elementCount = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class){
            elementData = Arrays.copyOf(elementData, elementCount, Object[].class);
        }
    }

    /**
     * 返回向量的大小/元素数量
     * @author choice
     * @return
     */
    @Override
    public synchronized int size() {
        return elementCount;
    }

    /**
     * 检查向量是否为空
     * @author choice
     * @return
     */
    @Override
    public synchronized boolean isEmpty() {
        return elementCount == 0;
    }

    /**
     * 检查一个对象是否存在
     * @author choice
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        return indexOf(e, 0) >= 0;
    }

    /**
     * 从指定下标开始轮训检查一个对象是否存在
     * @author choice
     * @param o
     * @param index
     * @return
     */
    @Override
    public synchronized int indexOf(Object o, int index) {
        if (o == null) {
            for (int i = index ; i < elementCount ; i++){
                if (elementData[i]==null) {
                    return i;
                }
            }
        } else {
            for (int i = index ; i < elementCount ; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回一个元素在向量中最后一次出现的位置
     * @author choice
     * @param o
     * @return
     */
    @Override
    public synchronized int lastIndexOf(Object o) {
        return lastIndexOf(o, elementCount-1);
    }

    /**
     * 从指定下标开始轮训返回一个元素在向量中最后一次出现的位置
     * @author choice
     * @param o
     * @return
     */
    @Override
    public synchronized int lastIndexOf(Object o, int index) {
        if (index >= elementCount) {
            throw new IndexOutOfBoundsException(index + " >= " + elementCount);
        }

        if (o == null) {
            for (int i = index; i >= 0; i--) {
                if (elementData[i] == null){
                    return i;
                }
            }

        } else {
            for (int i = index; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 获取给定下标的元素
     * @author choice
     * @param index
     * @return
     */
    @Override
    public synchronized E get(int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return elementData(index);
    }

    /**
     * 替换 下标为index 的元素
     * @author choice
     * @param index
     * @param element
     * @return
     * @throws VectorViolationException
     */
    @Override
    public synchronized E set(int index, Object element) throws VectorViolationException {
        rankValid(index);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 追加元素
     * @author choice
     * @param e
     * @return
     */
    @Override
    public synchronized boolean add(Object e) {
        if(elementCount + 1 >= capacityIncrement){
            capacity();
        }
        elementData[elementCount] = e;
        elementCount++;
        return true;
    }


    /**
     * 将元素插入到给定位置
     * @author choice
     * @param index
     * @param obj
     * @return
     * @throws VectorViolationException
     */
    @Override
    public synchronized E insert(int index, E obj) {
        if(index < 0 || index > elementCount){
            throw new VectorViolationException("rankOutOfBound");
        }
        if(elementCount + 1 >= capacityIncrement){
            capacity();
        }
        for (int i = elementCount; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = obj;
        elementCount++;
        return obj;
    }


    /**
     * 取给定下标元素
     * @author choice
     * @param index
     * @return
     */
    private E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 删除给定下标的元素
     * @author choice
     * @param index
     * @return
     * @throws VectorViolationException
     */
    @Override
    public synchronized E remove(int index) throws VectorViolationException {
        rankValid(index);
        E bak = elementData(index);
        for (int i = index; i < elementCount - 1; i++){
            elementData[i] = elementData[i + 1];
        }
        elementCount--;
        return bak;
    }

    /**
     * 数组扩容，将以前的数据copy到给定新数组中
     * @author choice
     */
    public synchronized void capacity() {
        capacityIncrement *= 2;
        Object[] arrObj = new Object[capacityIncrement];
        System.arraycopy(elementData, 0, arrObj, 0, elementCount);
        elementData = arrObj;
    }

    /**
     * 检查rank是否合法
     * @author choice
     * @param index
     */
    private void rankValid(int index){
        if(index < 0 || index >= capacityIncrement){
            throw new VectorViolationException("rankOutOfBound");
        }
    }


}
