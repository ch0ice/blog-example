package cn.com.onlinetool;

import cn.com.onlinetool.chainTable.list.MyLinkedList;
import org.junit.Test;

/**
 * @author choice
 * @description:
 * @date 2019-09-04 16:53
 *
 */
public class MyLinkedListTest {
    private static MyLinkedList linkedList = new MyLinkedList();
    @Test
    public void linkedListTest(){
        linkedList.addTail(1);
        linkedList.addTail(3);
        linkedList.addHead(0);

        linkedList.insert(2,2);
        linkedList.traverse();

        System.out.println(linkedList.get(1));

        linkedList.delete(1);
        linkedList.traverse();

        linkedList.revers();
        linkedList.traverse();

        System.out.println(linkedList.getMid().value);

        MyLinkedList testMerge = new MyLinkedList();
        testMerge.addHead(9);
        testMerge.addHead(8);
        testMerge.addHead(7);
        linkedList.mergeList(testMerge);
        linkedList.traverse();

        MyLinkedList[] testBreak = linkedList.getOddNumberAndEvenNumber();
        testBreak[0].traverse();
        testBreak[1].traverse();

    }
}
