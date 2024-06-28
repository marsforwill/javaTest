package CodeTest;

import java.util.*;

public class ContentSortedSetImp  implements MostPopular{
    
    public SortedSet<Content> sortedSet;
    public Map<Integer, Content> idToContent;

    public ContentSortedSetImp(){
        sortedSet = new TreeSet<Content>();
        idToContent = new HashMap<>();
    }

    public void increasePopularity(Integer contentId){
        Content content = idToContent.get(contentId);
        if (content != null) {
            sortedSet.remove(content); // Remove old entry
            content.popularity++;
            sortedSet.add(content); // Add updated entry
        } else {
            content = new Content(contentId, 1);
            idToContent.put(contentId, content);
            sortedSet.add(content);
        }
        return;
    }

    public Integer mostPopular(){
        if(sortedSet.size() == 0){
            return -1;
        }
        return sortedSet.first().contentId;
    }

    public void decreasePopularity(Integer contentId){
        Content content = idToContent.get(contentId);
        if (content != null) {
            sortedSet.remove(content); // Remove old entry
            content.popularity--;
            if (content.popularity <= 0) {
                idToContent.remove(contentId);
            } else {
                sortedSet.add(content); // Add updated entry
            }
        }
        return;
    }

    /**
     * 问题主要出在TreeSet的行为上。TreeSet是基于排序来管理元素的，如果元素的字段发生变化，TreeSet不会自动调整其位置。
     * 因此，当你增加或减少Content的受欢迎度（popularity）时，TreeSet并不会重新排序，导致mostPopular方法可能返回不正确的结果。
     */
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
