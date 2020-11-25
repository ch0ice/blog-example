package cn.com.onlinetool.chainTable.queue;

/**
 * @author choice
 * @description: 数组实现 静态队列
 * @date 2019-08-28 16:55
 *
 */
public class ArrayQueue implements MyQueue{
    public int[] data;
    /**
     * 指向第一个有效元素
     */
    public int front;
    /**
     * 直线最后一个有有效元素的下一个元素
     */
    public int rear;

    public ArrayQueue(int[] data, int front, int rear) {
        this.data = data;
        this.front = front;
        this.rear = rear;
    }

    /**
     * 判断是否满
     * @return
     */
    @Override
    public boolean isFull(){
        if((this.rear + 1) % this.data.length == this.front){
            System.out.println("队列已满");
            return true;
        }
        System.out.println("队列没满");
        return false;
    }

    /**
     * 判断是否为空
     * @return
     */
    @Override
    public boolean isEmpty(){
        if(this.rear == this.front){
            System.out.println("空队列");
            return true;
        }
        System.out.println("非空队列");
        return false;
    }


    /**
     * 入队
     * @param value
     */
    @Override
    public void enQueue( int value){
        System.out.println("入队");
        if(!isFull()){
            this.data[this.rear] = value;
            this.rear = (this.rear + 1) % this.data.length;
        }
        System.out.println();
    }

    /**
     * 遍历
     */
    @Override
    public void traverse(){
        System.out.println("遍历");
        int i = this.front;
        while(i != this.rear){
            System.out.println(this.data[i]);
            i = (i + 1) % this.data.length;
        }
        System.out.println();
    }

    /**
     * 出队
     */
    @Override
    public void outQueue(){
        System.out.println("出队");
        if(!isEmpty()){
            int value = this.data[this.front];
            System.out.println(value + "\n");
            this.front = (this.front + 1) % this.data.length;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(new int[6],0, 0);
        arrayQueue.isEmpty();

        arrayQueue.enQueue(1);
        arrayQueue.enQueue(2);
        arrayQueue.enQueue(3);
        arrayQueue.enQueue(4);
        arrayQueue.enQueue(5);
        arrayQueue.traverse();
        arrayQueue.isFull();

        arrayQueue.outQueue();
        arrayQueue.isFull();
        arrayQueue.traverse();




    }
}
