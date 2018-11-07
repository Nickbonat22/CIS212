import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private int intId;
	private BlockingQueue<String> queue;
	int count = 0;

	public Consumer(BlockingQueue<String> blockingQueue, int intId){

		this.queue = blockingQueue;
		
		this.intId = intId;
		
	}// end Consumer

	@Override
	public void run() {
	
		String check;
			
		do{
		
		long rndm = (long) (Math.random()*10);
		
		check = queue.poll();
		
		
		if (check != null){
		
		count++;
		
		if (count % 100 == 0){
		
		System.out.printf("\"Consumer %s\": %d events consumed\n",this.toString(), count);
		
		            }
		}
			
		try {
		
		Thread.sleep(rndm);
		
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		
			}
		
		}// end do
		
		while (!queue.isEmpty() || !SynchronizeddBuffer.done);

	}// end run
	
	public int getCount(){
		
		return count;
	}// end getCount

	@Override
	public String toString(){
	
		return String.valueOf(intId);
	
	}// end toString

}// end class