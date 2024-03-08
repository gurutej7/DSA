package MonotonicStack.Hard;

import java.util.HashMap;

// https://leetcode.com/problems/lru-cache/description/
class LRUCache {
    Node head , tail ;
    HashMap<Integer,Node> map ;
    int maxCapacity ;

    public LRUCache(int capacity) {
        this.head = new Node(-1,-1); // dummy head
        this.tail = new Node(-1,-1); // dummy tail
        this.map = new HashMap<>();
        this.maxCapacity = capacity;
        // form doubly-LinkedList
        // DLL purpose , it stores the cache in the order
        // left to right => corresponds to last (most recently) used to least recently  used
        head.next = tail ;
        head.prev = null; // just for clearance , by default they are null only

        tail.prev = head;
        tail.next = null;

    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            delete(node); // delete it from it`s previous position
            insert(node); // put it in the front , this is the lastly used
            return node.val;
        }
        else return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) delete(map.get(key));
        else if(map.size() == maxCapacity) delete(tail.prev);

        insert(new Node(key,value));
    }

    private void delete(Node node){
        // play with the links , to remove the current node
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node node){
        map.put(node.key , node);
        Node currNextOfHead = head.next;
        // insert , curr node after head
        head.next = node;
        node.prev = head;
        // now link this with the previous next of head
        currNextOfHead.prev = node;
        node.next = currNextOfHead;
    }
}

public class LRUcacheImplementation {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println( lRUCache.get(1));   // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));;    // return -1 (not found)
        System.out.println(lRUCache.get(3));;    // return 3
        System.out.println(lRUCache.get(4));;    // return 4
    }
    
}
