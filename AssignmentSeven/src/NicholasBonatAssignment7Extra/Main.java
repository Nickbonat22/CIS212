import java.util.LinkedList;

public class Main
{
        static final Object lock = new Object();
        static boolean finished = false;
        static int producerCount = 0;

        public static void main(String[] args) throws InterruptedException
        {
                LinkedList<String> sharedLocation = new LinkedList<>();

                Producer producer1 = new Producer(sharedLocation, 1);
                Producer producer2 = new Producer(sharedLocation, 2);
                Consumer consumer1 = new Consumer(sharedLocation, 1);
                Consumer consumer2 = new Consumer(sharedLocation, 2);

                Thread t1 = new Thread(producer1);
                Thread t2 = new Thread(producer2);
                Thread t3 = new Thread(consumer1);
                Thread t4 = new Thread(consumer2);

                t1.start();
                t2.start();
                t3.start();
                t4.start();

                t1.join();
                t2.join();
                t3.join();
                t4.join();

                System.out.println("Summary:");
                System.out.printf("\"Producer %s\" produced %d events\n", producer1.toString(), producer1.getCount());
                System.out.printf("\"Producer %s\" produced %d events\n", producer2.toString(), producer2.getCount());
                System.out.printf("\"Consumer %s\" consumed %d events\n", consumer1.toString(), consumer1.getCount());
                System.out.printf("\"Consumer %s\" consumed %d events\n", consumer2.toString(), consumer2.getCount());
        }
}