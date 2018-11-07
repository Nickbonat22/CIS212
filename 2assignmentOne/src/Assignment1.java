import java.util.Scanner;

public class Assignment1 {
	
		public static void main(String[] args) {
	     
	        Scanner input = new Scanner(System.in);
	        
	        int user_input, total, close_app;
	        
	        total = 0;
	        close_app = 0;
	        
	        // run this loop until input of -1
	        while (close_app != 1){
	        	
	            System.out.print("Enter a positive integer (-3 to print, -2 to reset, -1 to quit): ");
	            user_input = input.nextInt();
	             
	             if (user_input == -3){
	                System.out.printf("Sum: %d%n", total);
	                close_app = 0;
	             }
	             
	             else if (user_input == -2){
	                total = 0;
	                close_app = 0;
	             }
	             
	             else if (user_input == -1){
	                System.out.printf("Sum: %d%n", total);
	                close_app = 1;
	            }
	            
	             else if (user_input > 0){
	                total = user_input + total;    
	                close_app = 0;
	                
	            }
	            
	             else if (user_input < -3){
	                close_app = 0;
	            }
	            
	        }
	        
	        
	        input.close();
	        System.exit(0);

		}// end main

}// end class
