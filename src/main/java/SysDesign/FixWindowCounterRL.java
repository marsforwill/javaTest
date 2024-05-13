package SysDesign;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;;

public class FixWindowCounterRL {
    
    // mill second
    private final long intervalWindow;

    private final long maxWindowSize;

    private Map<String, AtomicInteger> counterMap;

    // private AtomicInteger counter = new AtomicInteger();

    private long windowStartTimestamp = System.currentTimeMillis();

    public FixWindowCounterRL(long intervalWindow, long maxWindowSize){
        this.intervalWindow = intervalWindow;
        this.maxWindowSize = maxWindowSize;
        counterMap = new HashMap<>();
    }

    public boolean tryAcquire(String key) {
        long now = System.currentTimeMillis();
        AtomicInteger counter = counterMap.get(key);
        if (now > windowStartTimestamp + intervalWindow){
            windowStartTimestamp = now;
            counter = new AtomicInteger(1);
            counterMap.put(key, counter);
            return true;
        } 

        if (counter.get() < maxWindowSize){
            counter.incrementAndGet();
            counterMap.put(key, counter);
            return true;
        }
        return false;
    } 

    public boolean tryAcquireByKey(String key){
        if (counterMap.containsKey(key)){
            return tryAcquire(key);
        } else {
            counterMap.put(key, new AtomicInteger(1));
            return true;
        }
    }

    public static void main(String[] args) {
        FixWindowCounterRL fixWindowCounterRL = new FixWindowCounterRL(1000, 4);
        for (int i = 0; i < 10; i++) {
            if (i < 5){
                System.out.println(fixWindowCounterRL.tryAcquireByKey("shimin"));
                System.out.println(fixWindowCounterRL.tryAcquireByKey("shimin3"));
            } else {
                System.out.println(fixWindowCounterRL.tryAcquireByKey("shimin2"));
            }
        }
    }
}
