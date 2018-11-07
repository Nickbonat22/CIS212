import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	int intId;
	int count = 0;
	BlockingQueue<String> queue; 
	
	public Producer(BlockingQueue<String> blockingQueue,int intId){
	
		this.queue = blockingQueue;
		
		this.intId = intId;
	
	}
	
	@Override
	public void run() {
	
		for(int i = 0; i< 1000;i++){
		
		double rndm = Math.random();

		String s = Double.toString(rndm);
		
		try{
		
			queue.put(s);
		
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		count++;
		
		if (count%100 == 0){
		
		System.out.printf("\"Producer %s\": %d events produced\n",this.toString(),count);
		
			}
	
		}// end for loop

		SynchronizeddBuffer.done = true;

	}// end run
	
	public int getCount(){
		
		return count;
	
	}// end getCount
	
	@Override
	public String toString(){
	
		return String.valueOf(intId);
	
	}// end toString
	
}// end class
