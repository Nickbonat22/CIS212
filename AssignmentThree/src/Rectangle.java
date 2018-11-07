
public class Rectangle implements Measurable {
	
	protected double length;
	protected double width;
	
	public Rectangle(double length, double width){
		
		if(length < 0.0){
			throw new IllegalArgumentException("Height must be greater than 0");			
		}
		
		if(width < 0.0){
			throw new IllegalArgumentException("Width must be greater than 0");
		}
		
		this.length = length;
		this.width = width;
	}
	
	public double getArea(){
		double area = 0;
		
		try{
			area = length * width;
		
		}
		catch (Exception e) {
            System.out.println("An error occurred");
       
		}
		return area;

		
	}// end getArea
	
	
	// getter methods
	public double getLength(){
		return length;
		
	}
	
	public double getWidth(){
		return width;
		
	}	
}// end class Rectangle
