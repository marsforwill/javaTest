package SysDesign;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import java.util.*;

public class FixWindowCounterRL {
    
    // mill second
    private final long intervalWindow;

    private final long maxWindowSize;

    private Map<String, Pair<AtomicInteger, Long>> counterMap;

    // private AtomicInteger counter = new AtomicInteger();

    // private long windowStartTimestamp = System.currentTimeMillis();

    public FixWindowCounterRL(long intervalWindow, long maxWindowSize){
        this.intervalWindow = intervalWindow;
        this.maxWindowSize = maxWindowSize;
        counterMap = new HashMap<>();
    }

    public boolean acquire(String key) {
        long now = System.currentTimeMillis();
        Pair<AtomicInteger, Long> value = counterMap.get(key);
        if (now > value.getRight() + intervalWindow){
            Pair<AtomicInteger, Long> newValue =  new ImmutablePair<AtomicInteger, Long>(new AtomicInteger(1), System.currentTimeMillis());
            counterMap.put(key, newValue);
            return true;
        } 

        if (value.getLeft().get() < maxWindowSize){
            value.getLeft().incrementAndGet();
            return true;
        }
        return false;
    } 

    public boolean tryAcquireByKey(String key){
        if (counterMap.containsKey(key)){
            return acquire(key);
        } else {
            counterMap.put(key, new ImmutablePair<AtomicInteger, Long>(new AtomicInteger(1), System.currentTimeMillis()));
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
