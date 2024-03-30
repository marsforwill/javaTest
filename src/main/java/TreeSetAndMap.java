import java.util.*;
// 
public class TreeSetAndMap {
    // 基于红黑树的有序集合：
    /**
     * add(E e): 添加元素到 TreeSet 中，如果元素已存在则不会添加。
        remove(Object o): 从 TreeSet 中移除指定的元素。
        contains(Object o): 检查 TreeSet 是否包含指定的元素。
        first(): 获取 TreeSet 中的第一个（最低）元素。
        last(): 获取 TreeSet 中的最后一个（最高）元素。
        higher(E e): 返回 TreeSet 中大于给定元素的最小元素。
        lower(E e): 返回 TreeSet 中小于给定元素的最大元素。
     */
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(30);
        System.out.println(treeSet.higher(20));
        System.out.println(treeSet.first());

        // 自定义比较函数
        TreeMap<String, Integer> treeMap = new TreeMap<>((str1, str2) -> str1.length() - str2.length());
        treeMap.put("apple", 50);
        treeMap.put("banana", 30);
        treeMap.put("cherry", 20);
        System.out.println(treeMap.higherKey("apple")); 
        
    }
}
