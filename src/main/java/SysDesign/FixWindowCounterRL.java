package SysDesign;

import java.util.concurrent.atomic.AtomicInteger;

public class FixWindowCounterRL {
    
    // mill second
    private final long intervalWindow;

    private final long maxWindowSize;

    private AtomicInteger counter = new AtomicInteger();

    private long windowStartTimestamp = System.currentTimeMillis();

    public FixWindowCounterRL(long intervalWindow, long maxWindowSize){
        this.intervalWindow = intervalWindow;
        this.maxWindowSize = maxWindowSize;
    }

    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now > windowStartTimestamp + intervalWindow){
            windowStartTimestamp = now;
            counter = new AtomicInteger(1);
            return true;
        } 

        if (counter.get() < maxWindowSize){
            counter.incrementAndGet();
            return true;
        }
        return false;
    } 

    public static void main(String[] args) {
        FixWindowCounterRL fixWindowCounterRL = new FixWindowCounterRL(1000, 4);
        for (int i = 0; i < 20; i++) {
            System.out.println(fixWindowCounterRL.tryAcquire());
            if (i == 10){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
