package jdk.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

	public static void main(String[] args) {
		
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1));
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=1; i<=100; i++) {
			executor.execute(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + "线程启动");
					Thread.sleep(1000000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		
	}
}
