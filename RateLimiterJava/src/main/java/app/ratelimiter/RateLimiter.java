package app.ratelimiter;
import java.util.*;

public class RateLimiter {

    public RateLimiter(int n) {
        
    }

    // TODO : ensure that it only accepts at most 5 requests per second for a particular id

    HashMap<Integer, Integer> rateMap= new HashMap<>();
    HashMap<Integer, Long> startTimeMap= new HashMap<>();

    public boolean acquire(int id) {
       // This should only return true if given id has received < 5 requests in last second
        final long expiration = 1000;

        if (!rateMap.containsKey(id)) {
            startTimeMap.put(id, System.currentTimeMillis());
            rateMap.put(id, 5);
        }

        else {
            int counter = rateMap.get(id) - 1;
            System.out.print("Counter value: " + counter);
            long current_time = System.currentTimeMillis();
            System.out.print("Current Time: " + current_time);
            long startTime = startTimeMap.get(id);
            System.out.println( "Start Time: " + startTime);

            if (counter<=0) {
                if (counter<0) {
                    startTimeMap.put(id, System.currentTimeMillis());
                    rateMap.put(id, 4);
                }
                if ((current_time - startTime) < expiration) {
                    return false;
                }
            }
            rateMap.put(id, counter);
        }
        return true;
    }
}
