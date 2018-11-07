import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	
	private final ArrayList<PaintPoint> points;
    private Color paintColor;
    private int penSize;

    public PaintPanel(){
    		
    		// setting starting color to black and size to 8
            points = new ArrayList<>();
            paintColor = Color.BLACK;
            penSize = 8;

            MouseAdapter adapter = new MouseAdapter(){
            	
                    @Override
                    public void mouseDragged(MouseEvent event){
                    	
                            PaintPoint point = new PaintPoint(event.getX(), event.getY(), 
                            		penSize, paintColor);
                            points.add(point);
                            repaint();
                    }
            };

            addMouseListener(adapter);
            addMouseMotionListener(adapter);
    }

    @Override
    public void paintComponent(Graphics g){
    	
            super.paintComponent(g);
            for (PaintPoint point : points)
                    point.draw(g);
    }

    public void setPenColor(Color color){
            paintColor = color;
    }

    public void setPenSize(int size){
    	
            penSize = size;
    }

    public void clear(){
    	
            points.clear();

    }

}// end class PaintPanel
