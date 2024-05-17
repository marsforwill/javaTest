package CodeTest;

/**
 * popularityTracker.increasePopularity(7);
  popularityTracker.increasePopularity(7);
  popularityTracker.increasePopularity(8);
  popularityTracker.mostPopular();        // returns 7
  popularityTracker.increasePopularity(8);
  popularityTracker.increasePopularity(8);
  popularityTracker.mostPopular();        // returns 8
  popularityTracker.decreasePopularity(8);
  popularityTracker.decreasePopularity(8);
  popularityTracker.mostPopular();        // returns 7
  popularityTracker.decreasePopularity(7);
  popularityTracker.decreasePopularity(7);
  popularityTracker.decreasePopularity(8);
  popularityTracker.mostPopular();        // returns -1 since there is no content with popularity greater than 0
 */
public interface MostPopular {
    void increasePopularity(Integer contentId);
    Integer mostPopular();
    void decreasePopularity(Integer contentId);
}
