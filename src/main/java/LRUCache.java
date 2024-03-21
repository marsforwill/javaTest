import java.util.*;

public class LRUCache {
    int capacity;
    Map<Integer, Integer> lruCache;
    // coding version: linkedList + hashmap => linkedHashMap
    LinkedList<Integer> bList;
    Map<Integer, Integer> m;

    // 依赖jdk里面的LinkedHashMap
    public LRUCache(int n){
        this.capacity = n;
        this.lruCache = new LinkedHashMap<Integer, Integer>(n, 0.75f, true) {
            /**
             * 移除元素条件方法，因为在 LinkedHashMap 中是空方法
             * 所以我们需要重写方法，定义自己的逻辑
             * 这里就是当容量不够时进行移除元素
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
        this.bList = new LinkedList<Integer>();
        this.m = new HashMap<>(n);
    }

    public int get(int key){
        // return lruCache.getOrDefault(key, -1);
        // coding version 
        if (m.containsKey(key)){
            bList.remove(Integer.valueOf(key));
            bList.addFirst(key);
            return m.get(key);
        }
        return -1;
    }

    public void set(int key, int value){
        this.lruCache.put(key, value);

        // coding version
        if (m.size() >= this.capacity){
            Integer last = bList.removeLast();
            System.out.println(bList.size());
            m.remove(last);
            System.out.println(m.size());
        }
        m.put(key, value);
        bList.addFirst(key);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.set(2, 10);
        cache.set(1, 5);
        System.out.println(cache.get(1));
        cache.set(0, 20);
        System.out.println(cache.get(2));
    }
}
