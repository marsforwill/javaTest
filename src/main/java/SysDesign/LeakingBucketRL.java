package SysDesign;

public class LeakingBucketRL {
    private final long bucketSize;
    private final long consumeRate;
    private long curWater = 0;
    private long preTime = System.currentTimeMillis();

    public LeakingBucketRL(long bucketSize, long consumeRate){
        this.bucketSize = bucketSize;
        this.consumeRate = consumeRate;
    }

    public boolean tryAcquire(){
        long now = System.currentTimeMillis();

        curWater = Math.max(0, curWater - (now - preTime)/1000*consumeRate);
        if(curWater < bucketSize){
            curWater++;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LeakingBucketRL leakingBucketRL = new LeakingBucketRL(5, 3);
        for (int i = 0; i < 10; i++) {
            System.out.println(leakingBucketRL.tryAcquire());
        }
    }

}
