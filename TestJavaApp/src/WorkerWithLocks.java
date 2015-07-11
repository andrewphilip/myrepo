import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class WorkerWithLocks {

	Random rnum = new Random();
	
	Object oLock1= new Object();
	Object oLock2= new Object();
	
	List<Double> iList1=new ArrayList<Double>();
	List<Double> iList2=new ArrayList<Double>();
	
	public void methodOne(String name){
		// do some computation here
		synchronized (oLock1) {
			System.out.println("Thread in methodOne() is "+ name);
			iList1.add(Math.pow(rnum.nextInt(10),2));
			try {
				System.out.println(name+" is sleeping for 100ms");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void methodTwo(String name){
		// do some computation here
		synchronized (oLock2) {
			System.out.println("Thread in methodTwo() is "+ name);
			iList2.add(Math.pow(rnum.nextInt(10),3));
			try {
				System.out.println(name+" is sleeping for 100ms");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void process(String thread){
		for(int i=0; i<5; i++){
			methodOne(thread);
			methodTwo(thread);
		}
	}
	
	public void runProcess(){
		
		Thread t1= new Thread(new Runnable() {
			public void run() {
				process("ThreadOne");
			}
		});
		Thread t2= new Thread(new Runnable() {
			
			public void run() {
				process("ThreadTwo");
			}
		});
		
		System.out.println("Starting the runProcess....");
		long start= System.currentTimeMillis();
		
		t1.start();
		t2.start();
		
		try{
			t1.join();
			t2.join();
		}catch(InterruptedException ie){ie.printStackTrace();}
		long end = System.currentTimeMillis();
		
		System.out.println("List1 :"+Arrays.toString(iList1.toArray()));
		System.out.println("List2 :"+Arrays.toString(iList2.toArray()));
		
		System.out.println("Time taken:"+(end-start)+" milliseconds.");
		
	}
}
