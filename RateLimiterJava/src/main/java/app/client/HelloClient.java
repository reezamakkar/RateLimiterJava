package app.client;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloClient {
	
	public static int makeRequest(int id) {
		URL url;
		try {
			url = new URL("http://localhost:8080/hello/" + id);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			return connection.getResponseCode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 500;
		}
	}
	
	public static void makeNRequests(int id, int n) {
		int okCount = 0;
		int limitedCount = 0;
		for (int i = 0; i < n; i++) {
			int s = makeRequest(id);
			if (s == 200) {
				okCount++;
			}
			else {
				limitedCount++;
			}

		}
		System.out.println("OK: " + okCount + "\nRATE_LIMITED:" + limitedCount);
	}
	
	public static void makeNRequestsWithMThreadsForUser(int id, int n, int m) {
		ExecutorService e = Executors.newFixedThreadPool(m);
		// Assuming n is divisible by m
		int k = n/m;
		for (int i = 0; i < m; i++) {
			e.submit(() -> {
/*				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}*/
				makeNRequests(id, k);});
		}
		e.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		// Check 10 sequential requests for one user id
		makeNRequests(123, 3);
		Thread.sleep(900);
		makeNRequests(123, 4);
		Thread.sleep(200);
		makeNRequests(123, 5);
//		Thread.sleep(2000);
//		makeNRequests(123, 10);
//		makeNRequests(125, 10);

//		makeNRequestsWithMThreadsForUser(123, 1000, 2);
	}
}
