package cn.com.onlinetool.struct.chain.queue;

/**
 * @author choice
 * @description:
 * @date 2019-08-28 17:25
 *
 */
public interface MyQueue {
    /**
     * 判断是否满
     * @return
     */
    boolean isFull();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();


    /**
     * 入队
     * @param value
     */
    void enQueue(int value);

    /**
     * 遍历
     */
    void traverse();
    /**
     * 出队
     */
    void outQueue();
}
