import java.util.LinkedList;
import java.util.Random;

public class Consumer implements Runnable
{
        Random random = new Random();
        private int intID;
        int count = 0;
        private final LinkedList<String> sharedLocation;

        public Consumer(LinkedList<String> sharedLocation, int intID)
        {
                this.sharedLocation = sharedLocation;
                this.intID = intID;
        }

        @Override
        public void run()
        {
                while (Main.finished == false)
                {
                        try
                        {
                                synchronized(Main.lock)
                                {
                                        while (sharedLocation.size() == 0)
                                        {
                                                Main.lock.wait();
                                        }

                                        String check;

                                        do
                                        {
                                                check = sharedLocation.poll();

                                                if (check != null)
                                                {
                                                        count++;

                                                        if (count % 100 == 0)
                                                        {
                                                                System.out.printf("\"Consumer %s\": %d events consumed\n", this.toString(), count);
                                                        }
                                                }

                                                try
                                                {
                                                        double sleepTime = random.nextDouble() * 10;
                                                        Thread.sleep((long) sleepTime);
                                                }

                                                catch (InterruptedException e)
                                                {
                                                        e.printStackTrace();
                                                }
                                        }

                                        while (!sharedLocation.isEmpty());

                                        Main.lock.notifyAll();
                                }
                        }

                        catch (InterruptedException e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        @Override
        public String toString()
        {
                return String.valueOf(intID);
        }

        public int getCount()
        {
                return count;
        }
}