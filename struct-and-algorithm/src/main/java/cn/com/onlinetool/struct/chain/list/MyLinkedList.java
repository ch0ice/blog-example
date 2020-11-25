package cn.com.onlinetool.struct.chain.list;

/**
 * @author choice
 * @description: 单链表实现
 * @date 2019-08-21 16:35
 *
 */
public class MyLinkedList {
    private ListNode head;

    /**
     * 头结点插入 头插法
     */
    public void addHead(int value){
        ListNode newNode = new ListNode(value);
        System.out.println("头结点插入");
        if(this.head == null){
            this.head = newNode;
            return;
        }
        newNode.next = this.head;
        this.head = newNode;
    }

    /**
     * 链表添加结点 尾插法
     * @param value
     */
    public void addTail(int value){
        System.out.println("尾结点插入");
        ListNode newNode = new ListNode(value);
        if(this.head == null){
            this.head = newNode;
            return;
        }
        ListNode temp = this.head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * 遍历
     */
    public void traverse(){
        System.out.println("遍历");
        ListNode head = this.head;
        while (head != null){
            System.out.println(head.value);
            head = head.next;
        }
    }

    /**
     * 查找
     */
    public int get(int index){
        System.out.println("查找");
        int res = -1;
        int count = 0;
        ListNode head = this.head;
        while (head != null){
            if(count == index){
                res =  head.value;
                break;
            }
            count ++;
            head = head.next;
        }
        return res;
    }

    /**
     * 插入 指定下标插入
     */
    public void insert(int index, int value){
        System.out.println("插入");
        int count = 0;
        ListNode head = this.head;
        while (head != null){
            if(index == 0){
                this.addHead(value);
                return;
            }
            if(count == index - 1){
                ListNode newNode = new ListNode(value);
                newNode.next = head.next;
                head.next = newNode;
                return;
            }
            count ++;
            head = head.next;
        }
    }

    /**
     * 删除下标元素 后面元素前移
     */
    public void delete(int index){
        System.out.println("删除元素");
        ListNode head = this.head;
        int count = 0;
        while (head != null){
            if(index == 0){
                this.head = head.next;
                break;
            }
            if(count == index - 1){
                head.next = head.next.next;
                return;
            }
            count ++;
            head = head.next;
        }

    }


    /**
     * 反转链表
     * O(n) O(1)
     * @return
     */
    public void revers(){
        System.out.println("反转链表");
        ListNode head = this.head;
        ListNode pre = null;
        ListNode next = null;

        while (null != head){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;

        }
        this.head = pre;
    }


    /**
     * 取中间节点
     * 节点数为偶数 取前一个
     * 节点数为奇数 取中间
     * @return
     */
    public ListNode getMid(){
        System.out.println("取中间节点");
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (null != fast.next && null != fast.next.next){
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;
    }


    /**
     * 链表合并
     * @return
     */
    public void mergeList(MyLinkedList linkedList){
        System.out.println("链表合并");
        if(this.head == null || linkedList == null || linkedList.head == null){
            return;
        }
        ListNode head = linkedList.head;
        while (head != null){
            addTail(head.value);
            head = head.next;
        }
    }

    /**
     * 将一个链表（整型）按照奇数偶数拆分成两个链表
     * @return
     */
    public MyLinkedList[] getOddNumberAndEvenNumber(){
        System.out.println("将一个链表（整型）按照奇数偶数拆分成两个链表");
        ListNode head = this.head;
        MyLinkedList oddList = new MyLinkedList();
        MyLinkedList evenList = new MyLinkedList();
        int count = 1;

        while (null != head){
            if(count % 2 == 1){
                oddList.addTail(head.value);
            }else {
                evenList.addTail(head.value);
            }
            head = head.next;
            count++;
        }
        return new MyLinkedList[]{oddList,evenList};
    }

    /**
     * 归并排序
     * @return
     */
    public MyLinkedList sortList(MyLinkedList linkedList){
        System.out.println("归并排序");

        ListNode mid = getMid();
        ListNode right = mid.next;
        MyLinkedList list = new MyLinkedList();
        list.head = right;

        mid.next = null;
//        return mergeList(linkedList);
        return null;
    }


}
