import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
        private static final int PORT = 1337;

        public static void main(String[] args)
        {
                System.out.println("Running Server!");

                ServerSocket server = null;
                Socket connection = null;
                ObjectOutputStream output = null;
                ObjectInputStream input = null;

                try
                {
                        server = new ServerSocket(PORT);

                        System.out.println("Server Socket created.");

                        connection = server.accept(); //Wait for connection

                        System.out.println("Got Socket: " + connection);

                        //Get input/output stream
                        output = new ObjectOutputStream(connection.getOutputStream());
                        output.flush();

                        input = new ObjectInputStream(connection.getInputStream());

                        while (true)
                        {
                                int num = input.readInt();

                                if (isPrime(num))
                                        output.writeInt(num);

                                output.flush();
                        }
                }

                catch (IOException e)
                {
                        e.printStackTrace();
                }

                finally //Close connection
                {
                        try
                        {
                                if (server != null) {
                                        server.close();
                                }
                                if (connection != null) {
                                        connection.close();
                                }
                                if (output != null) {
                                        output.close();
                                }
                                if (input != null) {
                                        input.close();
                                }
                        }

                        catch (IOException e)
                        {
                                e.printStackTrace();
                        }
                }

                System.out.println("Server Done!");
        }

        public static boolean isPrime(int n)
        {
                for (int i = 2; i < n; i++)
                {
                        if (n % i == 0)
                                return false;
                }

                return true;
        }
}