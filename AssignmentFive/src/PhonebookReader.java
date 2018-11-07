import java.io.IOException;
import java.lang.IllegalStateException;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class PhonebookReader {

	private static Scanner input;
	private static String phoneNumber;
	private static String lastName;
	private static String firstName;
	private static ArrayList<String> phonebookList = new ArrayList<String>();
	static ArrayList<String> new_phones = new ArrayList<String>();
	
	public static void main(String[] args) {
		openFile();
		createPhoneList();
		
		// uncomment to test 
		//phonebookOutput(phonebookList,"new");
		
		System.out.print("Time to process with Selection Sort: ");
		long startTime = System.nanoTime();
        selectionSort(phonebookList);
        double endTime = ((System.nanoTime() - startTime) / 1000000.0);
        System.out.println(endTime);

        System.out.print("Time to process with Merge Sort: ");
        long startTime2 = System.nanoTime();
        mergeSort(phonebookList);
        double endTime2 = ((System.nanoTime() - startTime2) / 1000000.0);
        System.out.println(endTime2);
		
		closeFile();
		
	}// end main
	
	// opening file
	public static void openFile(){
		
		try{	
			input = new Scanner(Paths.get("Assignment5_phonebook.txt"));
		}
		catch (IOException ioException){
			System.err.println("Error opening file. Terminating");
			System.exit(1);
		}
	}// end openFile 
	
	// creating an array list with each line in #, last name, first name format
	public static ArrayList<String> createPhoneList(){
		
		try{	
			
			while (input.hasNextLine()){	        
				String row = input.nextLine();
		        String [] tokens = row.split("\\s+");
		        phoneNumber = tokens[0];
		         lastName = tokens[1];
		         firstName = tokens[2];
		         phonebookList.add(phoneNumber +' '+ lastName +' '+ firstName + '\n');
		    }
			}
		catch (NoSuchElementException elementException){
			System.err.println("File improperly found. Terminating");
			System.exit(1);
		}
		catch (IllegalStateException stateException){
			System.err.println("Error reading from file. Terminating");
			System.exit(1);
		}
		
		return phonebookList;
		
		
	}// end createPhoneList
	
	// closing file
	public static void closeFile(){
		if(input != null)
			input.close();
	}
	
	//creates and writes in file
	public static void phonebookOutput(ArrayList<String> list, String wordSearch){
		
		try {
			FileWriter writer = new FileWriter("Output.txt");
			ArrayList<String> listOutput = new ArrayList<String>(); 
	        for (String word : list){
	        	  if (word.contains(wordSearch)){
	        		  listOutput.add(word);
	        	  }
	        	}
	      for(String str: listOutput) {
	        	  writer.write(str);
	        	}
	        	writer.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
		
	}// end phonebookOutput
	
	// sorting alphabetically
	public static ArrayList<String> mergeSort(ArrayList<String> array){
        if (array.size() > 1){
        	
                //Make sure the array is >1, then split the array into 2 parts
                int lSize = (array.size() / 2 );
                int rSize = (array.size() - (array.size() / 2));
                
                //Each list is its own ArrayList of phone books, so it can recurse.
                ArrayList<String> left = new ArrayList<String>(lSize);
                ArrayList<String> right = new ArrayList<String>(rSize);

                for (int i = 0; i < lSize; i++) {
                        left.add(i, array.get(i));

                }
                for (int i = 0; i < rSize; i++) {
                        right.add(i, (array.get(i+lSize)));

                }


                mergeSort(left);
                mergeSort(right);

                new_phones = mergLists(array,left,right);
        }

        	return new_phones;
}
    
    public static ArrayList<String> mergLists(ArrayList<String> array,ArrayList<String> left, ArrayList<String> right){
        int a = 0;
        int b = 0;
        for (int i = 0; i<array.size();i++){
                if (b >= right.size() || (a < left.size() && left.get(a).compareTo(right.get(b)) < 0)){
                        array.set(i,left.get(a));
                        a++;
                }
                else {
                        array.set(i,right.get(b));
                        b++;
                }
        }
        return array;
}
    
    public static ArrayList<String> selectionSort(ArrayList<String> array){
        for (int i = 0; i< array.size() - 1; i++){
                for (int j = i + 1; j<array.size(); j++){
                        if (array.get(i).compareTo(array.get(j)) >= 1) {

                                String temp = array.get(i);
                                array.set(i, array.get(j));
                                array.set(j, temp);
                        }
                }
        }
        return array;
    }// end selectionSort
    
    //check to see if sorted
    public static boolean isSorted(ArrayList<String> array){
        boolean sorted = true;
        for (int i = 1; i<array.size();i++){
                if (array.get(i-1).compareTo(array.get(i)) > 0) 
                	sorted = false;
        }
        return sorted;
    }// end isSorted

}// end class
