package cn.com.onlinetool.questions.chain;

import cn.com.onlinetool.struct.chain.list.ListNode;

public class ChainOptions {

    public ChainOptions() {
    }

    /**
     * 打印链表中的数据
     * @param listNode
     * @return
     */
    public static String print(ListNode listNode) {
        ListNode curr = listNode;
        StringBuilder res = new StringBuilder();
        while (curr != null) {
            res.append(curr.value);
            System.out.print(curr.value);
            curr = curr.next;
        }
        System.out.println();
        return res.toString();
    }

    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }


    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        print(next);
        return next;
    }


    public static ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode left = head;
        ListNode right = head;
        head = head.next;
        while (right != null && right.next != null) {
            left.next = right.next;
            left = right.next;
            right.next = left.next;
            left.next = right;
            left = right;
            right = right.next;
        }
        return head;
    }

    public static boolean hasCycle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(slow != null && fast != null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

}
