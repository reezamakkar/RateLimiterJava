package app.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import app.ratelimiter.RateLimiter;

@Path("/hello/")
public class HelloResource {
    
    // TODO : Feel free to initialize it in a different way
    public static final RateLimiter RATE_LIMITER = new RateLimiter(5);
    
    // TODO : Guard this endpoint using a rate-limiter so that a single id can only be hit 5 times / second
    @GET
    @Path("{id}")
    public Response greet(@PathParam("id") int id) {
        // Check with rate-limiter here to allow request else return http status code 429
        if (RATE_LIMITER.acquire(id)) {
            String greeting = String.format("Received request for id: %d", id);
            return Response.status(200).entity(greeting).build();
        } else {
            // Rate limit exceeded, return 429
            return Response.status(429).build();
        }
    }
}
