import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Server {


	private static final int port = 8001;
	
	
	public static void main(String[] args){
	
		ObjectInputStream input = null;
		
		ObjectOutputStream output = null;
		
		Socket connection = null;
		
		try {
		
			ServerSocket sSocket = new ServerSocket(port);
			
			connection = sSocket.accept();
			
			System.out.println("CONNECTED");
			
			output = new ObjectOutputStream(connection.getOutputStream());
			
			output.flush();
			
			input = new ObjectInputStream(connection.getInputStream());
			
			
			try {
			
				ArrayList<Integer> in = (ArrayList<Integer>) input.readObject();
				
				output.writeObject(primes(in));
				
				output.flush();
			
			} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			
			}
			
			
			output.close();
			
			input.close();
			
			connection.close();
			
			sSocket.close();
		
		
		
		} catch (IOException e) {
		
			e.printStackTrace();
		
		}
		
	}
	
	public static ArrayList<Integer> primes(ArrayList<Integer> arr){
	
		ArrayList<Integer> prms = new ArrayList<Integer>();
		
		boolean isPrime;
		
		for( int current:arr){
		
			isPrime = true;
			
			for(int j = 2;j<=(current/2);j++){
			
				if (current%j == 0)
				
				isPrime = false;
			
			}
			
			if (isPrime == true){
			
				prms.add(current);
				
			}
			
		}
		
		return prms;
	}
	

}//end class
