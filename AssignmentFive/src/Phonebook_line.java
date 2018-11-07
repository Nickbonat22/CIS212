import java.util.ArrayList;

public class Phonebook_line {

	private static String phoneNumber;
	private static String lastName;
	private static String firstName;
	String [] tokens;
	
    public Phonebook_line(String line){
	        tokens = line.split("\\s+");
	        phoneNumber = tokens[0];
	         lastName = tokens[1];
	         firstName = tokens[2];
            }// end Phonebook_line

    public String Getlastname(){
            return lastName;
    }
    public String Getnumber(){
            return phoneNumber;
    }
    public String Getline(){
            return phoneNumber +' '+ lastName +' '+ firstName + '\n';
    }
    
    

}// end class
