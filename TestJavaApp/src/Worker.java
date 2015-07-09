
public class Worker{

	private  int counter=0;
	
	public static void main(String[] args) {
		Worker w=new Worker();
		w.process();
	}
	
	public void process(){
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				increment();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				increment();
			}
		});
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				increment();
			}
		});
		
		System.out.println("Starting threads...");
		long start=System.currentTimeMillis();
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end= System.currentTimeMillis();
		
		System.out.println("After firing up the threads...");
		System.out.println("Counter:"+counter);
		System.out.println("Time taken :"+ (end-start) +" milliseconds.");
	}
	
	public synchronized void increment(){
		for(int n=0; n < 1000000; n++){
			counter++;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
