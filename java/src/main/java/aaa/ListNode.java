package aaa;

public class ListNode {
    private int val;
    private ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public static void print(ListNode listNode) {
        ListNode curr = listNode;
        while (curr != null) {
            System.out.print(curr.val);
            curr = curr.next;
        }
        System.out.println();
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


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        print(listNode);
//        ListNode reverseList = reverse(listNode);
//        print(reverseList);
        ListNode swapList = swapPairs1(listNode);
        print(swapList);

    }
}
