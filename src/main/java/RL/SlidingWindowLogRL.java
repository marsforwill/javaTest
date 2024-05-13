package RL;

import java.util.LinkedList;

public class SlidingWindowLogRL {
    private final long intervalWindow;
    private final long maxWindowSize;
    private LinkedList<Long> list = new LinkedList<>();

    public SlidingWindowLogRL(long intervalWindow, long maxWindowSize){
        this.intervalWindow = intervalWindow;
        this.maxWindowSize = maxWindowSize;
    }

    public boolean tryAcquire(){
        long now = System.currentTimeMillis();
        
        while (list.size() > 0 && list.getFirst() < now - intervalWindow) {
            list.removeFirst();
        }

        if(list.size() < maxWindowSize){
            list.add(now);
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        SlidingWindowLogRL slidingWindowLogRL = new SlidingWindowLogRL(1000, 5);
        for (int i = 0; i < 10; i++) {
            System.out.println(slidingWindowLogRL.tryAcquire());
        }
    }
}
