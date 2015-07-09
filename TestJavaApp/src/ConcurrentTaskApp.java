import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ConcurrentTaskApp {

	class WorkerTask implements Runnable{

		private int taskId;
		private int sumOfN;
		
		public WorkerTask(int id, int n){
			this.taskId= id;
			this.sumOfN= n;
		}
		
		public WorkerTask(int id){
			this.taskId=id;
		}
		
		public void run() {
			System.out.println("Begining task "+taskId);
			//we can do computation here
			// sum of the square from 1 to 10
			int sum=0;
			for(int i=1; i<=sumOfN; i++){
				sum = sum + (i*i);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Finished task "+ taskId + "  and the sum is "+ sum);
		}
		
	}
	
	public static void main(String[] args) {
		
		ConcurrentTaskApp app= new ConcurrentTaskApp();
		ExecutorService exec= Executors.newFixedThreadPool(3);
		Random rnum= new Random();
		
		System.out.println("Starting....");
		for(int i=0; i<10; i++){
			exec.submit(app.new WorkerTask(i, rnum.nextInt(100)));
		}
		System.out.println("All the tasks submitted....");
		exec.shutdown();
		
		try {
			exec.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All the tasks completed.");
	}

}
