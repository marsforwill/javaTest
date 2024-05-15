package SysDesign;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// sliding window to answer
/**
 * Code Design – Rate Limiter. 
 * Here is the exact question I received. Problem Title: Rate Limiter Problem Description: 
 * Imagine we are building an application that is used by many different customers. We want to avoid one customer being able 
 * to overload the system by sending too many requests, so we enforce a per-customer rate limit. The rate limit is defined 
 * as: “Each customer can make X requests per Y seconds” Assuming that customer ID is extracted somehow from the request, 
 * implement the following function. // Perform rate limiting logic for provided customer ID. Return true if the 
 * // request is allowed, and false if it is not. boolean rateLimit(int customerId)
 */
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
