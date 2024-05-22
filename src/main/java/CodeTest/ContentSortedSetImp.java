package CodeTest;

import java.util.*;

public class ContentSortedSetImp  implements MostPopular{
    
    public SortedSet<Content> sortedSet;
    public Map<Integer, Content> idToContent;

    public ContentSortedSetImp(){
        sortedSet = new TreeSet<Content>((a, b) -> Integer.compare(b.popularity, a.popularity));
        idToContent = new HashMap<>();
    }

    public void increasePopularity(Integer contentId){
        Content content = idToContent.getOrDefault(contentId, new Content(contentId, 0));
        content.popularity++;
        if (!sortedSet.contains(content)){
            sortedSet.add(content);
        }
        idToContent.put(contentId, content);
        return;
    }

    public Integer mostPopular(){
        if(sortedSet.size() == 0){
            return -1;
        }
        return sortedSet.first().contentId;
    }

    public void decreasePopularity(Integer contentId){
        if (idToContent.containsKey(contentId)){
            Content content = idToContent.get(contentId);
            content.popularity--;
            if (content.popularity <= 0){
                idToContent.remove(contentId);
                sortedSet.remove(content);
            } else {
                idToContent.put(contentId, content);
            }
        }
        return;
    }

    public static void main(String[] args) {
        ContentSortedSetImp popularityTracker = new ContentSortedSetImp();
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
