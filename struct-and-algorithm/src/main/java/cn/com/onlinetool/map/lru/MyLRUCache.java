package cn.com.onlinetool.map.lru;


import java.util.HashMap;

public class MyLRUCache<K, V> {

    private int capacity;
    private int size;
    private HashMap<K, Node<K, V>> cache;
    private Node<K, V> head;
    private Node<K, V> tail;
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;
    }
    public MyLRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }


    /**
     * key存在返回V并将node移动到头部
     * key不存在返回null
     */
    public V get(K key) {
        Node<K,V> node = cache.get(key);
        if (node == null) return null;
        moveToHead(node);
        return node.value;
    }

    /**
     * key存在，将该node的值更新，并移动到表头
     * key不存在，新建node，将node加入cache头部，size+1
     * size>capacity，要去除表尾节点，并从cache中删除，size-1
     */
    public void put(K key, V value) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            node = new Node<K, V>();
            node.key = key;
            node.value = value;
            cache.put(key, node);
            addToHead(node);
            size++;
            if (size > capacity) {
                Node<K, V> del = popTail();
                cache.remove(del.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public void forEach(){
        Node node = this.tail.prev;
        int cycleSize = this.size;
        while (cycleSize > 0){
            System.out.println("key:" + node.key.toString() + ",value:" + node.value.toString());
            node = node.prev;
            cycleSize--;
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node popTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }


   


}
