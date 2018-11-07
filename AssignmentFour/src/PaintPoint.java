import java.awt.Color;
import java.awt.Graphics;

public class PaintPoint {
	
	private final int size;
    private final int xpos;
    private final int ypos;
    private final Color color;

    public PaintPoint(int x, int y, int _size, Color _color)
    {
            xpos = x;
            ypos = y;
            size = _size;
            color = _color;
    }

    public int getX()
    {
            return xpos;
    }

    public int getY()
    {
            return ypos;
    }

    public int getSize()
    {
            return size;
    }

    public Color getColor()
    {
            return color;
    }

    public void draw(Graphics g){
    	
            g.setColor(getColor());
            g.fillOval(getX(),  getY(),  getSize(),  getSize());
    }

}// end class PaintPoint
