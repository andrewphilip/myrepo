import java.util.Random;


public class ProducerConsumer {
	private int val ;
	private  volatile boolean flag=true;
	
	public void producer() throws InterruptedException{
		
		synchronized (this) {
			while(!flag){
				wait();
			}
			val=new Random().nextInt();
			System.out.println("Emitting a number :"+ val);
			flag=false;
			notify();
			
		}
	}
	
	public void consumer() throws InterruptedException{
		
		synchronized (this) {
			while(flag){
				wait();
			}
			System.out.println("Got "+val);
			flag=true;
			notify();
			
		}
	}
}
