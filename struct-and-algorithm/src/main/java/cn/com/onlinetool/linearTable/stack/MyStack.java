package cn.com.onlinetool.linearTable.stack;

import cn.com.onlinetool.linearTable.list.ListNode;

/**
 * @author choice
 * @description: 链表实现 动态栈
 * @date 2019-08-28 16:31
 *
 */
public class MyStack {
    public ListNode stackTop;
    public ListNode stackBottom;

    public MyStack(ListNode stackTop,ListNode stackBottom){
        this.stackTop = stackTop;
        this.stackBottom = stackBottom;
    }

    /**
     * 进栈
     * @param myStack
     * @param value
     */
    public static void pushStack(MyStack myStack, int value){
        System.out.println("进栈");
        ListNode node = new ListNode(value);
        node.next = myStack.stackTop;
        myStack.stackTop = node;
        System.out.println(value + "\n");
    }

    /**
     * 遍历
     * 栈顶元素指针不指向栈低
     */
    public static void traverse(MyStack myStack){
        System.out.println("遍历");
        ListNode top = myStack.stackTop;
        while (top != myStack.stackBottom){
            System.out.println(top.value );
            top = top.next;
        }
        System.out.println();
    }

    /**
     * 判断栈是否为空
     * @param myStack
     * @return
     */
    public static boolean isEmpty(MyStack myStack){
        if(myStack.stackTop == myStack.stackBottom){
            System.out.println("栈为空");
            return true;
        }
        System.out.println("栈不为空");
        return false;
    }

    /**
     * 出栈
     * @param myStack
     */
    public static void popStack(MyStack myStack){
        System.out.println("出栈");
        if(!isEmpty(myStack)){
            ListNode top = myStack.stackTop;
            myStack.stackTop = top.next;
            System.out.println(top.value + "\n");
        }
    }

    /**
     * 清空栈
     * @param myStack
     */
    public static void clearStack(MyStack myStack){
        System.out.println("清空栈");
        myStack.stackTop = null;
        myStack.stackBottom = myStack.stackTop;
    }


    public static void main(String[] args) {
        MyStack myStack = new MyStack(new ListNode(0), new ListNode(0));
        myStack.stackBottom = myStack.stackTop;
        isEmpty(myStack);

        pushStack(myStack,1);
        pushStack(myStack,2);
        pushStack(myStack,3);
        isEmpty(myStack);
        traverse(myStack);

        popStack(myStack);
        traverse(myStack);

        clearStack(myStack);
        isEmpty(myStack);
    }
}
