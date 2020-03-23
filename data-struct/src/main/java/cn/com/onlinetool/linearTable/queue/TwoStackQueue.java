package cn.com.onlinetool.linearTable.queue;

import java.util.Stack;

/**
 * @author choice
 * @description: 两个栈实现一个队列
 * @date 2019-08-28 17:22
 *
 */
public class TwoStackQueue{
    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStackQueue(Stack<Integer> stackPush, Stack<Integer> stackPop) {
        this.stackPush = stackPush;
        this.stackPop = stackPop;
    }


}
