import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDownLatchApp {

	
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);

		CountDownLatchApp app= new CountDownLatchApp();
		ExecutorService exec= Executors.newFixedThreadPool(3);
		
		for(int i=0; i<5; i++){
			exec.submit(app.new CustomTask(i, latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finish Line.");
		
	}

	class CustomTask implements Runnable{
		private int id;
		private CountDownLatch latch;
		
		public CustomTask(int id, CountDownLatch latch){
			this.id= id;
			this.latch=latch;
		}
		
		public void run() {
			System.out.println("Starting task "+id);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			latch.countDown();
		}
		
	}
}
