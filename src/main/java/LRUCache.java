import java.util.*;

public class LRUCache {
    int capacity;
    Map<Integer, Integer> lruCache;

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
    }

    public int get(int key){
        return lruCache.getOrDefault(key, -1);
    }

    public int set(int key, int value){
        return lruCache.put(key, value);
    }


    public static void main(String[] args) {
        new LRUCache(10);
    }
}
