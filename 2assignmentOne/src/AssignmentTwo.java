// AssignmentTwo
// importing necessary classes
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class AssignmentTwo {
	
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int input_len = 0;
        boolean incorrect = false;
         do {
             try {
            	 
            	 // only accept positive integers
                 System.out.println("Please array length:");
                 input_len = input.nextInt();
                 if (input_len > 0) {
                	 incorrect = true;
                 }
                 else {
                         System.out.println("Please enter a positive integer");
                 }
             }
             catch (Exception e) {
                 System.out.println("The integer you entered is not accepted");
                 input.next();
             }
         }// end do
         while (incorrect == false);

        double density = 0;
        boolean wrong = false;
        
        do {
            try {
            	
            	// density must be between 0 and 1
                System.out.println("Enter density:");
                density = input.nextDouble();
                
                if (0 < density && density < 1) {
                        wrong = true;
                }
                else {
                        System.out.println("Must be between 0 and 1");
                }
            }
            catch (Exception e) {
                System.out.println("The value you entered is not valid:");
                input.next();
            }
        }// end do
        
        // while the inputs are accepted, plug them into methods
        while (wrong == false);
        
        int[] dArray = CreateDense(input_len, density);
        convertToSparse(dArray);
        ArrayList<int[]> sArray = CreateSparse(input_len, density);
        convertToDense(sArray);
        MaxDen(dArray);
        MaxSparse(sArray);
        
        input.close();
    }// end main

    public static int[] CreateDense(int input_len, double density){
        long startTime = System.nanoTime();
        int denseArray[] = new int[input_len];
        Random rand = new Random();
        double randNum;

        for(int i= 0; i < input_len; i++) {
            randNum = rand.nextDouble();
            if(randNum < density) {
                denseArray[i] = rand.nextInt(1000000) + 1;
            }
            else {
                denseArray[i] = 0;
            }
        }

        long end = System.nanoTime();
        double duration = (end - startTime) / 1000000.0;
        System.out.println("create dense length: " + input_len + " time: " + duration);
        return denseArray;
    }// end CreateDense

    public static ArrayList<int[]> CreateSparse(int len, double den) {
        long startTime = System.nanoTime();
        double randNum;
        Random rand = new Random();
        ArrayList<int[]> sArray = new ArrayList<int[]>(len);
        for (int i = 0; i < len; i++) {
            randNum = rand.nextDouble();
            if (randNum < den) {
                int[] list = new int[2];
                list[0] = i;
                list[1] = rand.nextInt(1000000) + 1;
                sArray.add(list);
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("create sparse length: " + sArray.size() + " time: " + duration);
        
        if(sArray.size() == 0){
            return null;
        }
        return sArray;
    }// end CreateSparse

    public static ArrayList<int[]> convertToSparse(int[] dArray) {
        long startTime = System.nanoTime();
        ArrayList<int[]> sArray = new ArrayList<int[]>(dArray.length);

        for(int i = 0; i < dArray.length ; i++) {
            if (dArray[i] != 0) {
                int[] list = new int[2];
                list[0] = i;
                list[1] = dArray[i];
                sArray.add(list);
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("convert to sparse length: " + sArray.size() + " time: " + duration);
        return sArray;
    }// end convertToSparse

    public static int[] convertToDense(ArrayList<int[]> sArray){
        long startTime = System.nanoTime();
        if(sArray == null){
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1000000.0;
            System.out.println("convert to dense length: " + null + " time: " + duration);
            return null;
        }

        int len = sArray.size();
        int[] index = sArray.get(len-1);
        int[] dArray = new int[index[0]+1];
        for(int i = 0; i < len ; i++){
            int [] check = sArray.get(i);
            int newIndex = check[0];
            int app = check[1];
            dArray[newIndex] = app;
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("convert to dense length: " + dArray.length + " time: " + duration);
        return dArray;
    }// end convertToDense

    public static void MaxDen(int[] DenseArray){
        long startTime = System.nanoTime();
        int max = 0;
        int counter = 0;

        for(int i=0; i < DenseArray.length; i++){
            if(DenseArray[i] > max){
                max = DenseArray[i];
                counter = i;
            }
        }

        System.out.println("find max (dense): " + max + " at: " + counter );
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("dense find time: " + duration );
    }// end MaxDen

    public static void MaxSparse(ArrayList<int[]> sparseArray){
        long startTime = System.nanoTime();
        int max = 0;
        int counter = 0;

        if (sparseArray == null) {
            System.out.println("find max (sparse): " + null + " at: " + null );
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1000000.0;
            System.out.println("sparse find time: " + duration);
            return;
        }

        for(int i=0; i < sparseArray.size(); i++){
            int[] current = sparseArray.get(i);
            if(current[1] > max){
                max = current[1];
                counter = current[0];
            }
        }

        System.out.println("find max (sparse): " + max + " at: " + counter );
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("sparse find time: " + duration );
        
    
    }// end MaxSparse
    

}// end class AssignmentTwo