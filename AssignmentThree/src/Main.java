// Main class

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int rectsCount = 0; int boxesCount = 0;
		int circlesCount = 0; int spheresCount = 0;
		double randNum;
		
		ArrayList<Measurable> sumMeasurable = new ArrayList<Measurable>(1000);

		for(int i= 1; i <= 1000; i++) {
			
            randNum = nextDouble();
            if (randNum <= .25){
            	
            	Rectangle rect = new Rectangle(nextDouble(), nextDouble());
            	sumMeasurable.add(rect);
            	rectsCount += 1;
            }
            else if (randNum > .25 && randNum <= .50){
            	
            	Box box = new Box(nextDouble(), nextDouble(), nextDouble());
            	sumMeasurable.add(box);
            	boxesCount += 1;
            }
            else if (randNum > .50 && randNum <= .75){
            	
            	Circle circ = new Circle(nextDouble());
            	sumMeasurable.add(circ);
            	circlesCount += 1;
            }
            else
            {
            	Sphere sphere = new Sphere(nextDouble());
            	sumMeasurable.add(sphere);
            	spheresCount += 1;
            }
            
        }// end for loop
		
		double areaSum = calculateSum(sumMeasurable);
		System.out.printf("%s: %s %s: %s %s: %s %s: %s %n%s: %s",
				"rects", rectsCount, "boxes", boxesCount, "circles", circlesCount,
				"spheres", spheresCount, "sum", areaSum);
	
	}// end main

	private static double nextDouble(){
        Random rand = new Random();
        double randNum = rand.nextDouble() + Double.MIN_VALUE;
        return randNum;
        
	}// end nextDouble
	
	private static double calculateSum(ArrayList<Measurable> areaList){
		double areaSum = 0;
		int i;
		
		for(i = 0; i < areaList.size(); i++){
			areaSum += areaList.get(i).getArea();
			
		}
		return areaSum;	

	}// end calculateSum

}// end main
