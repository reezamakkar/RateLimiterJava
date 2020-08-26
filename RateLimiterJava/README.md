### Rate Limiter

#### Problem statement:
Write a rate limiter which ensures that 5 requests per second can be made for a particular user.

This is a simple Dropwizard application which has a resource `app.resource.HelloResource` whose `greet` endpoint can only be hit 5 times every second for a particular user id.
You need to modify `app.ratelimiter.RateLimiter.java` to implement the rate-limiter.
The application can be run from command line using `./buildAndDeployServer.sh`.
You can also use the `Run -> Run Configurations` from Eclipse/IntelliJ to run the main class `App.java` with `server` as a program argument.


#### Test cases

We have created a test file `app.ratelimiter.RateLimiterTest.java` which runs unit tests for `RateLimiter.java`.
Make sure that your RateLimiter implementations pass the tests.
You can run the tests from command line using `./runTests.sh` or from Eclipse/IntelliJ Run configurations.
