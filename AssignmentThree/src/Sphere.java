
public class Sphere extends Circle{
	
	public Sphere(double radius){
		super(radius);
		
		if(radius < 0.0){
			throw new IllegalArgumentException("depth must be greater than 0");
		}
		
		this.radius = radius;
	}// end sphere constructor
	
	public double getArea(){
		double area = 0;
		
		try{
			area = 4 * super.getArea();
		
		}
		catch (Exception e) {
            System.out.println("An error occurred");
       
		}
		return area;
	
	}// end getArea

}
