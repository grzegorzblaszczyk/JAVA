//WORK IN PROGRESS

import javax.swing.JApplet;
import java.awt.Color;
//The program draws function plot y(f); (arguments from x to width-15).
//atm for 0-6PI;

public class draw_a_plot extends JApplet
{
	int width, height;
	public void init () 
	{
	       width = 900;
	       height = 600;
	       setSize(width+100, height+200); //+100 i +200 to make
	}
	
	public void paint(java.awt.Graphics g) 
	{

		//background
		super.paint(g);
		setVisible(true);
		setBackground(Color.black);
		setLayout(null);
		
		// Y axis
		tlo.setColor(Color.BLACK);
		tlo.drawLine(15, 600, 15, 0 ); 
		tlo.drawLine(15, 0, 5, 20 );  
		tlo.drawLine(15, 0, 25, 20 ); 

  		int x=0; //function variable 'x' 
  		int x0=15; //variable for drawing 'x'
  		double y; //function values f(x)
  		double y0; //variable for drawing 'y'
  
  		double range = 3*2*Math.PI; //N*2*PI = 2*PI will fit X axis N times.
  		double t, f, A, offset;
  		// t - X Axis subsidiary variable for fitting the plot
  		// A - Y Axis subsidiary variable for fitting the plot
  		// f - 'function argument' variable for fitting the plot (f is like f.e. an 'x' in cos(x))
 		//offset - offset for drawing y (to fit X axis)
	 
  		A = (double) height / 2;  
  		offset = (double) height / 2;
  		
		//X axis
  		tlo.drawLine (x0, (int) offset, width+30, (int) offset); 
  		tlo.drawLine (width+30, (int) offset, width+15, (int) offset+10); //X axis arrow
 		tlo.drawLine (width+30, (int) offset, width+15, (int) offset-10); //X axis arrow 
  		tlo.drawString("0", 9, (int)offset);
  		tlo.drawString("2\u03c0", 300, (int)offset); // \u03c0 to kod na symbol "PI"
  		tlo.drawString("4\u03c0", 600, (int)offset);
 		tlo.drawString("6\u03c0", 900, (int)offset);
		
		//fun_1 start point x,f(x)
	  	t = (double) x / ((double) width);
 		f = range * t;
  		y0 = offset - A*0.25*Math.cos(f);

  		//draw fun_1
  		tlo.setColor(Color.GREEN); //g(x)
  		for (x0=15; x0<width; x0++) 
		{
	  		t = (double) x / ((double) width);
	  		f = range * t; 
        		y = offset - A*0.25*Math.cos(f);
         		tlo.drawLine (x0, (int) y0, (x0+1), (int) y);
         		y0 = y;
         		x++;        
		}
 		
		//fun_2 start point x,f(x)
  		x=0;
  		t = (double) x / ((double) width);
  		f = range * t;
  		y0 = offset - A * Math.cos(f)*Math.exp(f*(-0.2));
  
		//draw fun_2		
		tlo.setColor(Color.BLUE);
		for (x0=15; x0<width; x0++) 
		{
      			t = (double) x / ((double) width);
         		f = range * t;
         		y = offset - A * Math.cos(f)*Math.exp(f*(-0.2));
        		tlo.drawLine (x0, (int) y0, (x0+1), (int) y);
        		y0 = y;
        		x++;
		}
	}
}

