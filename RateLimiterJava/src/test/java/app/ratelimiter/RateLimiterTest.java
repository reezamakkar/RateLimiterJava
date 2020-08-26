package app.ratelimiter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RateLimiterTest {

    // Some utility methods to reduce boilerplate
    
    public static int makeNRequestsInOneSecond(RateLimiter limiter, int id, int n, long start) {
        int okCount = 0;
        for (int i = 0; i < n && (System.currentTimeMillis() - start) <= 1000; i++) {
            if (limiter.acquire(id)) {
                okCount++;
            }
        }
        return okCount;        
    }

    @Test
    public void testSimpleSingleUser() {
        // Test that rate limiter with 5 requests per second can make 5 requests
        RateLimiter limiter = new RateLimiter(5);
        int successCount = makeNRequestsInOneSecond(limiter, 0, 5, System.currentTimeMillis());
        assertEquals(5, successCount);
    }
    
    @Test
    public void testSimpleMultipleUsers() {
     // Test that rate limiter with 5 requests per second can make 5 requests each for 5 users
        RateLimiter limiter = new RateLimiter(5);
        for (int i = 0; i < 5; i++) {            
            assertEquals(5, makeNRequestsInOneSecond(limiter, i, 5, System.currentTimeMillis()));
        }
    }
    
    @Test
    public void testSimpleWithWait() throws Exception {
        // Test that rate limiter with 5 requests per second is able to make 10 requests in 2 seconds
        RateLimiter limiter = new RateLimiter(5);
        int okCount = makeNRequestsInOneSecond(limiter, 0, 5, System.currentTimeMillis());
        Thread.sleep(1000);
        okCount += makeNRequestsInOneSecond(limiter, 0, 5, System.currentTimeMillis());
        assertEquals(10, okCount);
      }
    
    @Test
    public void testTimedSingleUser() {
     // Test that rate limiter with 5 requests per second can make only 5 requests in 1 second
        RateLimiter limiter = new RateLimiter(5);
        assertEquals(5, makeNRequestsInOneSecond(limiter, 0, 50, System.currentTimeMillis()));
    }
    
    @Test
    public void testTimedMultipleUsers() {
     // Test that rate limiter with 5 requests per second can make only 5 requests in 1 second for 5 users
        RateLimiter limiter = new RateLimiter(5);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {            
            assertEquals(5, makeNRequestsInOneSecond(limiter, i, 50, startTime));
        }
    }
    
    @Test
    public void testTimedSingleUserBulk() {
        // Test that rate limiter with 5000 requests per second can make 5000 requests in one second
        RateLimiter limiter = new RateLimiter(5000);
        assertEquals(5000, makeNRequestsInOneSecond(limiter, 0, 5000, System.currentTimeMillis()));
    }
    
    @Test
    public void testTimedMultipleUsersBulk() {
     // Test that rate limiter with 5000 requests per second can make 5000 requests in one second for 5 users
        RateLimiter limiter = new RateLimiter(5000);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {            
            assertEquals(5000, makeNRequestsInOneSecond(limiter, i, 5000, startTime));
        }
    }
}
