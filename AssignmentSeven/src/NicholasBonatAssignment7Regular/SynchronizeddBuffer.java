import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class SynchronizeddBuffer {
	
	static boolean done = false;
	
	public static void main(String[] args) throws InterruptedException {
	
		BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>(100);

		Producer producer1 = new Producer(blockingQueue,1);
		Consumer consumer1 = new Consumer(blockingQueue,1);
		Consumer consumer2 = new Consumer(blockingQueue,2);
		Consumer consumer3 = new Consumer(blockingQueue,3);
		Consumer consumer4 = new Consumer(blockingQueue,4);

		Thread t1 = new Thread(producer1);
		Thread t2 = new Thread(consumer1);
		Thread t3 = new Thread(consumer2);
		Thread t4 = new Thread(consumer3);
		Thread t5 = new Thread(consumer4);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();

		System.out.println("Summary:");
		System.out.printf("\"Producer %s\" produced %d events\n", producer1.toString(), producer1.getCount());
		System.out.printf("\"Consumer %s\" consumed %d events\n", consumer1.toString(), consumer1.getCount());
		System.out.printf("\"Consumer %s\" consumed %d events\n", consumer2.toString(), consumer2.getCount());
		System.out.printf("\"Consumer %s\" consumed %d events\n", consumer3.toString(), consumer3.getCount());
		System.out.printf("\"Consumer %s\" consumed %d events\n", consumer4.toString(), consumer4.getCount());

	}

}
