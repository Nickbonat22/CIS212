import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Assignment5{
	
	private static ArrayList<String> phonebookList = new ArrayList<String>();
	
	    // opening file and invoking merge/selection sort all in one method
        public static void main (String [] args) throws IOException{

        	
            	openFile();
    			createPhoneList();

    			// it takes a little over a minute for these to show results as selectionSort is significantly slower
                long start = System.nanoTime(); 
                selectionSort(phonebookList);
                long end = System.nanoTime();
                double time = (end - start) / 1000000000.0;
                time = Math.round(time * 1000.0) / 1000.0;
                System.out.println("Selection Sort: " + time);

                start = System.nanoTime(); 
                mergeSort(phonebookList);
                end = System.nanoTime();
                time = (end - start) / 1000000000.0;
                time = Math.round(time * 1000.0) / 1000.0;
                System.out.println("Merge Sort: " + time);
                  
        }
        public static void openFile(){
        	Scanner input;
    		try{	
    			
    			input = new Scanner(Paths.get("Assignment5_phonebook.txt"));
    		}
    		
    		catch (IOException ioException){
    			System.err.println("Error opening file. Terminating");
    			System.exit(1);
    		}
    	}// end openFile 
    	
    	// creating an array list with each line in #, last name, first name format
    	public static ArrayList<String> createPhoneList() throws FileNotFoundException{
    		Scanner text;
    		try{
                text = new Scanner(new File("Assignment5_phonebook.txt"));
                while (text.hasNext()){
                        String name = text.nextLine();
                        phonebookList.add(name); 
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

        public static void outputFile(ArrayList<String> list, String name) throws IOException {
                        File file = new File("Output.txt");
                        file.createNewFile();
                        FileWriter notepad = new FileWriter (file);

                        for (String word: list){
                                if (getLastName(word).equals(name) || getFirstName(word).equals(name)) notepad.write(word + "\n");
                        }
                        notepad.close();
        }// end outputFile

        public static String getFirstName(String first){
                String firstName;
                int index = first.indexOf(",");
                firstName = first.substring(index + 2);
                return firstName;

        }// end getFirstName

        public static String getLastName(String last){
                String lastName;
                int firstIndex = last.indexOf(" ");
                int lastIndex = last.indexOf(",");
                lastName = last.substring(firstIndex + 1, lastIndex);
                return lastName;

        }// end getLastName


        public static ArrayList <String> selectionSort(ArrayList <String> selectList){

                
                ArrayList <String> selects = new ArrayList <String>(selectList);

                for (int i = 0; i < selects.size() - 1; i++){
                        for (int j = i + 1; j < selects.size(); j++){
                                if (getLastName(selects.get(i)).compareTo(getLastName(selects.get(j))) > 0){
                                 
                                        selects.set(i, selects.get(j));
                                        selects.set(j, selects.get(i));

                                }
                        }
                }
                return selects;

        }// end selectionSort

        public static ArrayList<String> mergeSort(ArrayList <String> mergeList){
                ArrayList <String> mList = new ArrayList <String>(mergeList); 
                ArrayList <String> leftMerge = new ArrayList <String>(); 
                ArrayList<String> rightMerge = new ArrayList <String>();

                if (mList.size() <= 1) return mList; 
                else{
                        int middle = mergeList.size()/2;

                        for (int i = 0; i < middle; i++){
                                leftMerge.add(mList.get(i));
                        }

                        for (int i = middle; i < mList.size(); i++){
                                rightMerge.add(mList.get(i));
                        }

                        rightMerge = mergeSort(rightMerge);
                        leftMerge = mergeSort(leftMerge);
                        merge(leftMerge, rightMerge, mList);

                }

                return mList;

        }// end mergeSort

        public static void merge(ArrayList <String> left, ArrayList <String> right, ArrayList <String> full){

                int leftIndex = 0;
                int rightIndex = 0;
                int fullIndex = 0;

                while (leftIndex < left.size() && rightIndex < right.size()){
                        if ((getLastName(left.get(leftIndex)).compareTo(getLastName(right.get(rightIndex)))) < 0){
                                full.set(fullIndex, left.get(leftIndex));
                                leftIndex ++;

                        }
                        else{
                                full.set(fullIndex, right.get(rightIndex));
                                rightIndex ++;

                        }
                        fullIndex ++;

                }

                ArrayList <String> remains;
                int left_index;

                if (leftIndex >= left.size()){
                        remains = right;
                        left_index = rightIndex;

                }
                else{
                        remains = left;
                        left_index = leftIndex;

                }

                for (int i = left_index; i < remains.size(); i++){
                        full.set(fullIndex, remains.get(i));
                        fullIndex ++;

                }

        }// end merge

        // check to see if sorted
        public static boolean isSorted(ArrayList <String> list) {
                for (int i = 1; i < (list.size()); i++) {
                        if (getLastName(list.get(i-1)).compareTo(getLastName(list.get(i))) > 0) {
                                return false;
                        }
                }
                return true;

        }// end isSorted
}