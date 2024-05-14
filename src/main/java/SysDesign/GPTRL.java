package SysDesign;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GPTRL {
    private final int maxRequests;
    private final long timeWindow;
    private final Map<Integer, Queue<Long>> customerRequests;

    public GPTRL(int maxRequests, int timeWindowInSeconds) {
        this.maxRequests = maxRequests;
        this.timeWindow = timeWindowInSeconds * 1000L; // Convert seconds to milliseconds
        this.customerRequests = new HashMap<>();
    }

    public synchronized boolean rateLimit(int customerId) {
        long currentTime = System.currentTimeMillis();

        customerRequests.putIfAbsent(customerId, new LinkedList<>());

        Queue<Long> requests = customerRequests.get(customerId);

        // Remove outdated requests
        while (!requests.isEmpty() && (currentTime - requests.peek() > timeWindow)) {
            requests.poll();
        }

        if (requests.size() < maxRequests) {
            requests.add(currentTime);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        GPTRL rateLimiter = new GPTRL(5, 10); // 5 requests per 10 seconds

        int customerId = 123;
        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + (i + 1) + ": " + rateLimiter.rateLimit(customerId));
            try {
                Thread.sleep(1000); // Simulate 1 second between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
