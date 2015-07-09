import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class BlockingQueueApp {

	//private static BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);
	
	public int value;
	public boolean consume=false;
	
	public static void main(String[] args) throws InterruptedException {

		/*
		Thread t1=new Thread(new Runnable() {
			public void run() {
					try {
						producer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});

		Thread t2=new Thread(new Runnable() {
			public void run() {
					try {
							consumer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});
			System.out.println("Starting the producer...");
			t1.start();
			System.out.println("Starting the consumer...");
			t2.start();
			
			t1.join();
			t2.join();
		*/
		
		// Mulithreaded process using wait and notify
		final BlockingQueueApp app= new BlockingQueueApp();

		Thread t1=new Thread(new Runnable() {
			public void run() {
					try {
							app.runProducer();
						} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			public void run() {
					try {
							app.runConumser();
						} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});
		
		System.out.println("Starting the producer...");
		t1.start();
		System.out.println("Starting the consumer...");
		t2.start();
		
		t1.join();
		t2.join();
		
	}
/*
	private static void producer() throws InterruptedException{
		Random num= new Random();
		while(true){
			queue.put(num.nextInt(1000));
			System.out.println("Produced number "+ queue.peek());
		}
	}
	
	private static void consumer() throws InterruptedException{
		Random num=new Random();
		while(true){
			Thread.sleep(100);
			if(num.nextInt(5) == 1){
				Integer val=queue.take();
				System.out.println("Consumed value : "+val+"  from the queue. Queue size is "+queue.size());
			}
		}
	}
*/	
	
	public void runProducer() throws InterruptedException{
		while(!consume){
			producerWithWait();
		}
	}
	
	public void runConumser() throws InterruptedException{
		while(consume){consumerWithNotify();}
	}
	
	public  void producerWithWait() throws InterruptedException{
		//Random num= new Random();
		synchronized (this) {
			//queue.put(num.nextInt(1000));
			value=new Random().nextInt(1000);
			System.out.println("Produced ...:"+value);
			consume=true;
			wait();
		}
	}
	
	public void consumerWithNotify() throws InterruptedException{
		synchronized (this) {
			//Integer val=queue.take();
			System.out.println("Consumed value : "+value);
			consume=false;
			notify();
			//Thread.sleep(1000);
		}
	}
	
}
