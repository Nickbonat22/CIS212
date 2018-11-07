import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {


	private static final int port = 8001;
	
	
	public static void main(String[] args) {
	
		ObjectOutputStream output = null;
		
		ObjectInputStream input = null;
		
		Socket connection;
		
		
		try {
		
			InetAddress address = InetAddress.getLocalHost();
			
			connection = new Socket(address,port);
			
			output = new ObjectOutputStream(connection.getOutputStream());
			
			output.flush();
			
			output.writeObject(userInput());
			
			output.flush();
			
			input = new ObjectInputStream(connection.getInputStream());		
			
			ArrayList<Integer> primes;
					
			try {
			
				primes =  (ArrayList<Integer>) input.readObject();
				
				System.out.printf("Received: %s%n",primes);
			
			} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			
			}
			
			
			input.close();
			
			output.close();
			
			connection.close();
		
		} catch (IOException e) {
		
			e.printStackTrace();
		
		}
	
	}// end main
	
	
	
	public static ArrayList<Integer> userInput(){
	
		ArrayList<Integer> inputs = new ArrayList<Integer>();
		
		Scanner reader = new Scanner(System.in);
		
		boolean flag = false; 
		
		while (flag != true){
		
			System.out.println("Enter an integer (Press '!' to send): ");
			
			String in = reader.nextLine();
			
			if (in.equals("!")){
			
				flag = true;
			
				System.out.printf("Sent: %s%n",inputs);
			
				reader.close();
			}
			
			else{
			
				inputs.add(Integer.valueOf(in));
			
			}
		
		}
		
		return inputs;
	
	}

}

