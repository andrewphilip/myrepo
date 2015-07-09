import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class CallableApp {

	public static void main(String[] args) {

		ExecutorService exec=Executors.newCachedThreadPool();
		Future<Integer> future=exec.submit(new Callable<Integer>() {
			
			public Integer call() throws Exception {
				Random num=new Random();
				int sleepTime= num.nextInt(5000);
				
				if(sleepTime > 4000){
					throw new IOException("Thread is trying to sleep more than 4 seconds...["+sleepTime+"ms].");
				}
				
				System.out.println("Thread starting to sleep within allowed period...");
				try{
					Thread.sleep(sleepTime);
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
				System.out.println("Thread awakened...");
				return sleepTime;
			}
		
		});
		
		exec.shutdown();
		try {
			System.out.println("Thread slept for "+future.get()+" milliseconds.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
