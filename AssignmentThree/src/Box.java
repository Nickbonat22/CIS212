
public class Box extends Rectangle{
	
	private double height;

	public Box(double length, double width, double height) {
		super(length, width);
		
		if(height < 0.0){
			throw new IllegalArgumentException("depth must be greater than 0");
		}
		
		this.height = height;
	}
	
	public double getArea(){
		double area = 0;
		
		try{
			
			area = 2 * (length * width + length * height + width * height);
	
		}
		catch (Exception e) {
            System.out.println("An error occurred");
       
		}
		return area;
	
	}// end getArea
	
	public double getHeight(){
		return height;
	}
}
