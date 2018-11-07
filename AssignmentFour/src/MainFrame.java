import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame{
	
	private final PaintPanel paintPanel;

    public MainFrame(){
    	
    		//creating the panel
            super("Main");
            setLayout(new BorderLayout());

            paintPanel = new PaintPanel();
            paintPanel.setPreferredSize(new Dimension(500, 500));
            paintPanel.setBackground(Color.WHITE);
            add(paintPanel, BorderLayout.CENTER);

            JButton largeButton = new JButton("Large");
            largeButton.addActionListener(new ActionListener(){
            	
                    @Override
                    public void actionPerformed(ActionEvent event){
                    	
                            paintPanel.setPenSize(12);
                    }
            });

            JButton mediumButton = new JButton("Medium");
            mediumButton.addActionListener(new ActionListener(){
            	
                    @Override
                    public void actionPerformed(ActionEvent event){
                    	
                            paintPanel.setPenSize(8);
                    }
            });

            JButton smallButton = new JButton("Small");
            smallButton.addActionListener(new ActionListener(){
            	
                    @Override
                    public void actionPerformed(ActionEvent event){
                    	
                            paintPanel.setPenSize(4);
                    }
            });

            JButton clear = new JButton("Clear");
            clear.addActionListener(new ActionListener(){
            	
                    @Override
                    public void actionPerformed(ActionEvent event){
                    	
                            paintPanel.clear();
                            repaint();
                    }
            });
            
            //setting size options
            JPanel buttonPanel1 = new JPanel(new GridLayout(4, 1, 5, 5));            
            buttonPanel1.add(smallButton);
            buttonPanel1.add(mediumButton);
            buttonPanel1.add(largeButton);
            buttonPanel1.add(clear);
            add(buttonPanel1, BorderLayout.EAST);

            JButton redButton = new JButton("Red");
            redButton.addActionListener(new ActionListener(){
            	
                    @Override
                    public void actionPerformed(ActionEvent event){
                    	
                            paintPanel.setPenColor(Color.RED);
                    }
            });

            JButton blueButton = new JButton("Blue");
            blueButton.addActionListener(new ActionListener(){
            	
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                            paintPanel.setPenColor(Color.BLUE);
                    }
            });

            JButton greenButton = new JButton("Green");
            greenButton.addActionListener(new ActionListener(){
            	
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                            paintPanel.setPenColor(Color.GREEN);
                    }
            });

            JButton yellowButton = new JButton("Yellow");
            yellowButton.addActionListener(new ActionListener(){
            	
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                            paintPanel.setPenColor(Color.YELLOW);
                    }
            });

            //setting color options
            JPanel buttonPanel2 = new JPanel(new GridLayout(4, 1, 5, 5));
            buttonPanel2.add(greenButton);
            buttonPanel2.add(yellowButton);            
            buttonPanel2.add(blueButton);
            buttonPanel2.add(redButton);
            add(buttonPanel2, BorderLayout.WEST);
    }


}// end class MainFrame
