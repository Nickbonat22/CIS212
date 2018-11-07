
public class Circle implements Measurable {
	
	protected double radius;
	
	public Circle(double radius){
		
		if(radius < 0.0){
			throw new IllegalArgumentException("Height must be greater than 0");			
		}
		
		this.radius = radius;
		
	}// end circle
	
	public double getArea(){
		double area = 0;
		
		try{
			area = Math.PI * (radius * radius);
		
		}
		catch (Exception e) {
            System.out.println("An error occurred");
       
		}
		return area;

		
	}// end getArea
	
	public double getRadius(){
		return radius;
	}

}
