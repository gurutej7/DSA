package MonotonicStack.Hard;

import java.util.HashMap;

// https://leetcode.com/problems/lfu-cache/description/
class LFUCache{
    int maxSize ;
    int minFreq ;
    HashMap<Integer,Node> keyNodeMap; // keys point to their corresponding nodes
    HashMap<Integer,DLL> freqListMap;
    /* How above freqListMap works
    * freq as key and DLL as a value
    * if key 1 , key 2 and key 3 are occuring only once then,
    * freqListMap is as follows
    * 1 -> {head<->node key1 <-> node key 2 <-> node key 3}
    * 
    * let`s assume a operation is performed on key 1 then
    * 1 -> {head<-> node key 2 <-> node key 3}
    * 2 -> {head<-> node key 1} 
    */
    public LFUCache(int capacity) {
        this.maxSize = capacity;
        this.keyNodeMap = new HashMap<>();
        this.freqListMap = new HashMap<>();
        this.minFreq = 0 ;
    }

    public void updateFreqListMap(Node node){
        keyNodeMap.remove(node.key); // remove it from the keyNode , because the node  address for that key will be updated
        freqListMap.get(node.cnt).remove(node); // also remove from the previous freq list , as the fre of that node will be increased
        if(node.cnt == minFreq && freqListMap.get(node.cnt).size == 0){// if we have removed a guy from the least freq guy`s list and , the least freq guy`s list is empty
            minFreq++;
        }

        // check if there already exixts a list for the next Higher freq
        DLL nextHigherFreqList = new DLL();
        if(freqListMap.containsKey(node.cnt+1)){
            nextHigherFreqList = freqListMap.get(node.cnt+1);
        }

        node.cnt = node.cnt + 1 ; // increase the freq inside the node
        nextHigherFreqList.addFirst(node); // move the node to the new frequencyList
        // make the respective changes in the both the maps
        freqListMap.put(node.cnt,nextHigherFreqList);
        keyNodeMap.put(node.key,node);


    }
    
    public int get(int key) {
        if(keyNodeMap.containsKey(key)){
            Node node = keyNodeMap.get(key);
            // this node`s freq will be changed and it will be moved to other list of corresponding freq
            int value = node.val;
            updateFreqListMap(node); 
            return value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        assert this.maxSize > 0 ;
        
        // case 1 :
        if(keyNodeMap.containsKey(key)){
            Node node = keyNodeMap.get(key);
            node.val = value;
            // if exists already then there will direct impact on frequency aka count map
			// update count map. Move the node from old frequency key and associate it with new frequency key 
			// new frequency key may or may not exists already
            updateFreqListMap(node);
        }
        // case 2 
        else{
            if(keyNodeMap.size() == maxSize){
                DLL minFreqList = freqListMap.get(minFreq); // get the list of the minimal freq
                keyNodeMap.remove(minFreqList.tail.prev.key);
                minFreqList.remove(minFreqList.tail.prev); // delete the LRU in the miniFreqList
            }
            // case 3 : new node has to be added , who is not previously there
            Node node = new Node(key,value);
            keyNodeMap.put(key,node);
            minFreq = 1 ; // new element will always go into freq=1
            DLL newList = freqListMap.getOrDefault(minFreq,new DLL());
            newList.addFirst(node);
            freqListMap.put(minFreq,newList);
        }
    }
}

public class LFUcacheImplementation {
    
    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2,2);
        System.out.println(lfu.get(1));
        lfu.put(3,3);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(3));
        lfu.put(4,4);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(4));

    }
    
}

class Node{
    int key,val,cnt;
    Node prev,next;
    Node(int key,int val){
        this.val = val;
        this.key = key;
        this.cnt = 1 ;
    }
}

class DLL{
    Node head,tail;
    int size;
    public DLL(){
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.size = 0 ;
    }

    public void addFirst(Node node){
        Node currNextOfHead = head.next;
        head.next = node;
        node.prev = head;
        node.next = currNextOfHead;
        currNextOfHead.prev = node;
        this.size++;
    }

    public void remove(Node node){
        Node delPrev = node.prev;
        Node delNext = node.next;
        delPrev.next = delNext;
        delNext.prev = delPrev;
        this.size--;
    }
}
