package CodeTest;

import java.util.*;

public class MostPopularImp implements MostPopular {

    public TreeMap<Integer, Set<Integer>> contentPopularToIdMap;
    // 
    public Map<Integer, Integer> contentIdToPopularMap;

    public MostPopularImp(){
        contentPopularToIdMap = new TreeMap<>();
        contentIdToPopularMap = new HashMap<>();
    }

    public void increasePopularity(Integer contentId){
        // 1. get content item from id (map get)
        int popularity = contentIdToPopularMap.getOrDefault(contentId, 0);
        // 2. increase (update item count) (map put)
        // remove from last set
        Set<Integer> lastSet = contentPopularToIdMap.getOrDefault(popularity, new HashSet<>());
        lastSet.remove(contentId);
        contentPopularToIdMap.putIfAbsent(popularity, lastSet);
        
        popularity++;
        contentIdToPopularMap.put(contentId, popularity);
        // add to new set
        Set<Integer> newSet = contentPopularToIdMap.getOrDefault(popularity, new HashSet<>());
        newSet.add(contentId);
        contentPopularToIdMap.putIfAbsent(popularity, newSet);
        return;
    }

    public Integer mostPopular(){
        // get top(sorted get by O(1) O(log))
        if (contentPopularToIdMap.size() == 0){
            return -1;
        }

        Set<Integer> s = contentPopularToIdMap.lastEntry().getValue();
        return s.iterator().next();
    }

    public void decreasePopularity(Integer contentId){
        // 1. get content item from id (map get)
        int popularity = contentIdToPopularMap.getOrDefault(contentId, 0);
        if (popularity <= 0){
            contentIdToPopularMap.remove(contentId);
            return;
        }
        
        // 2. increase (update item count) (map put)
        // remove from last set
        Set<Integer> lastSet = contentPopularToIdMap.getOrDefault(popularity, new HashSet<>());
        lastSet.remove(contentId);
        contentPopularToIdMap.putIfAbsent(popularity, lastSet);

        popularity--;
        if (popularity > 0){
            // add to new set
            contentIdToPopularMap.put(contentId, popularity);
            Set<Integer> newSet = contentPopularToIdMap.getOrDefault(popularity, new HashSet<>());
            newSet.add(contentId);
            contentPopularToIdMap.putIfAbsent(popularity, newSet);
        }
    }

    public static void main(String[] args) {
        MostPopularImp popularityTracker = new MostPopularImp();
        // mostPopularImp.increasePopularity(7);
        // mostPopularImp.increasePopularity(8);
        // mostPopularImp.increasePopularity(8);
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        System.out.println(popularityTracker.mostPopular()); // returns 7
        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(8);
        System.out.println(popularityTracker.mostPopular()); // returns 8
        popularityTracker.decreasePopularity(8);
        popularityTracker.decreasePopularity(8);
        System.out.println(popularityTracker.mostPopular()); // returns 7
        popularityTracker.decreasePopularity(7);
        popularityTracker.decreasePopularity(7);
        popularityTracker.decreasePopularity(8);
      // returns -1 since there is no content with popularity greater than 0
        System.out.println(popularityTracker.mostPopular());
    }
}
