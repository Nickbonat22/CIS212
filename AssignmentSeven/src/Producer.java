import java.util.LinkedList;
import java.util.Random;

public class Producer implements Runnable
{
        Random random = new Random();
        private int intID;
        int count = 0;
        private final LinkedList<String> sharedLocation;

        public Producer(LinkedList<String> sharedLocation, int intID)
        {
                this.sharedLocation = sharedLocation;
                this.intID = intID;
        }

        @Override
        public void run(){
        	
                while (Main.producerCount < 1000){
                	
                        try{
                                synchronized(Main.lock){
                                        while (sharedLocation.size() == 100){
                                                Main.lock.wait();
                                        }

                                        double element = Math.random();
                                        String value = Double.toString(element);
                                        sharedLocation.add(value);
                                        count++;
                                        Main.producerCount++;

                                        if (count % 100 == 0){
                                                System.out.printf("\"Producer %s\": %d events produced\n", 
                                                		this.toString(), count);
                                        }

                                        Main.lock.notifyAll();
                                }
                        }

                        catch (InterruptedException e){
                                e.printStackTrace();
                        }
                }

                Main.finished = true;
        }

        @Override
        public String toString(){
                return String.valueOf(intID);
        }

        public int getCount(){
                return count;
        }
}