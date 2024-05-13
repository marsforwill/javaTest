package RL;

public class TokenBucketRL {
    private final long bucketSize;
    private final long putRate;
    private long preTime = System.currentTimeMillis();
    private long curBucket = 0;

    public TokenBucketRL(long bucketSize, long putRate){
        this.bucketSize = bucketSize;
        this.putRate = putRate;
    }

    public boolean tryAcquire(){
        long now = System.currentTimeMillis();

        curBucket = Math.min(bucketSize, curBucket + (now - preTime)/1000*putRate);
        preTime = now;
        if(curBucket == 0){
            return false;
        }
        curBucket--;
        return true;
    }
    public static void main(String[] args) throws InterruptedException {
        TokenBucketRL tokenBucketRL = new TokenBucketRL(10, 3);
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(tokenBucketRL.tryAcquire());
        }
    }
}
