package cn.com.onlinetool.struct;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListNodeTest {


    private ListNode testData;
    @Before
    public void init(){
        testData = new ListNode(1);
        testData.next = new ListNode(2);
        testData.next.next = new ListNode(3);
        testData.next.next.next = new ListNode(4);
        testData.next.next.next.next = new ListNode(5);
    }

    @Test
    public void reverseTest(){
        ListNode listNode = ListNode.reverse(testData);
        Assert.assertEquals("54321",ListNode.print(listNode));
    }

    @Test
    public void swapParisTest(){
        ListNode listNode = ListNode.swapPairs1(testData);
        Assert.assertEquals("21435",ListNode.print(listNode));
        listNode = ListNode.swapPairs1(listNode);
        Assert.assertEquals("12345",ListNode.print(listNode));
    }


    @Test
    public void hasCycleTest(){
        //链表加环
        testData.next.next.next.next.next = testData.next.next;
        Assert.assertTrue(ListNode.hasCycle(testData));
    }
}
