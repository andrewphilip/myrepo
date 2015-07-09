
public class ProducerConsumerApp {

	public static void main(String[] args) {

		final ProducerConsumer obj= new ProducerConsumer();
		
		Thread runner1=new Thread(new Runnable() {
			public void run() {
					try {
						for(int i=0; i<10; i++){
							obj.producer();
							Thread.sleep(1000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});

		Thread runner2=new Thread(new Runnable() {
			public void run() {
					try {
						for(int i=0; i<10; i++){
							obj.consumer();
							Thread.sleep(1000);
						}	
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});
		
		runner1.start();
		runner2.start();
		
		try {
			runner1.join();
			runner2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Finished.");
	}

}
