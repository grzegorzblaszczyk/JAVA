//WORK IN PROGRESS!!!
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.util.Locale;

import java.awt.*;
import javax.swing.*;
import java.applet.*;
//import javax.swing.JApplet;
//import java.awt.Color;

public class magic extends JApplet
{   
	//BRAKUJE OBSŁUGI WYJĄTKÓW
	//POPRAW LOAD
	public static int load(JFileChooser chooser, int index, String[][] table) 
	{
		Scanner odczyt=null;
		try 
		{ 
			odczyt= new Scanner ( chooser.getSelectedFile() ); 
			odczyt.nextLine();
			
			while(odczyt.hasNextLine())
			{
				odczyt.next();
				table[index][0] = odczyt.next(); // date
				//System.out.print(table[index][0] + " ");	
				table[index][1] = odczyt.next(); // time
				//System.out.print(table[index][1] + " ");	
				table[index][2] = odczyt.next(); // latitude
				//System.out.print(table[index][2] + " ");	
				table[index][3] = odczyt.next(); // longitude
				//System.out.print(table[index][3] + " ");	
				table[index][4] = odczyt.next(); // altitude
				//System.out.print(table[index][4] + " ");	
				table[index][5] = odczyt.next(); // distance
				//System.out.print(table[index][5] + " ");	
				/*System.out.println("Is good. " + ++index);*/ index++;
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception thrown.");
		}
		System.out.println("Data loaded from " + chooser.getSelectedFile());
		odczyt.close();
		return index;
	}
	public static double convertTime(int index, String[][] table) 
	{
		double ms, s, m, h;
		String[] parts = table[index][1].split("[.]");
		String[] parts2 = parts[0].split(":");
		h = Double.parseDouble(parts2[0])*3600;
		m = Double.parseDouble(parts2[1])*60;
		s = Double.parseDouble(parts2[2]);
		ms = Double.parseDouble(parts[1])/1000;
		return h+m+s+ms;
	}
	public static void save(String fileName, int index, String[][] table) 
	{   
		double time = 0.0;
		double distance = 0.0;
		double velocity = 0.0;
		double hight = 0.0;
		PrintWriter outputStream = null;
		
		try 
		{
			outputStream = new PrintWriter (new FileOutputStream(fileName, false));
		}
		catch (FileNotFoundException e) 
		{
			System.out.println ("Error while creating " +fileName);
			System.exit (0); 
		}
		outputStream.printf("T[s]\t\tV[m/s]\t\tH[m]\n"); 
		outputStream.printf("0.000\t\t0.000\t\t%.3f\n", Double.parseDouble(table[0][4])); 
		for (int ix=0;ix<(index-1);ix++)
		{                       
			time = magic.convertTime(ix+1,table)-magic.convertTime(ix,table);
			distance = (Double.parseDouble(table[ix+1][5]) - Double.parseDouble(table[ix][5]))*1000;
			velocity = time*distance;
			hight = Double.parseDouble(table[ix+1][4]);
			outputStream.printf("%.3f\t\t%.3f\t\t%.3f\n",time, velocity, hight);
		}
		System.out.println("Data saved to " +fileName);
		outputStream.close();			
	}
	
	public void paint(java.awt.Graphics g) 
	{
		int width, height;
		width = 400;
		height = 800;
		// Y axis
		g.setColor(Color.BLACK);
		g.drawLine(15, 800, 15, 0 ); 
		g.drawLine(15, 0, 5, 20 );  
		g.drawLine(15, 0, 25, 20 ); 

		//X axis
		for(int i=0;i<9;i++)
  		g.drawLine (15, 0+i*100, 915, 0+i*100); 
		g.drawLine (width+30, 800, width+15, 800+10); 
		g.drawLine (width+30, 800, width+15, 800-10);
		
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
			System.out.println(chooser.getSelectedFile().getName() + " chosen.");
		else 
			System.exit(0);
		
		Double[][] tab = new Double[2300][2];
		int index = 0;
		index = magic.load2(chooser, index, tab);
		/*
		int x=0; //function variable 'x' 
  		int x0=15; //variable for drawing 'x'
  		double y; //function values f(x)
  		double y0; //variable for drawing 'y'
  
  		double range = index; //N*2*PI = 2*PI will fit X axis N times.
  		double t, f, A, offset;
  		// t - X Axis subsidiary variable for fitting the plot
  		// A - Y Axis subsidiary variable for fitting the plot
  		// f - 'function argument' variable for fitting the plot (f is like f.e. an 'x' in cos(x))
 		//offset - offset for drawing y (to fit X axis)
	 
  		A = (double) height;  
  		offset = (double) height;
  		//fun_1 start point x,f(x)
	  	t = (double) x / ((double) width);
 		f = range * t;
  		y0 = offset - tab[index][1];
  		//draw fun_1
  		g.setColor(Color.GREEN); //g(x)
  		for (x0=15; x0<width; x0++) 
		{
	  		t = (double) x / ((double) width);
	  		f = range * t; 
        	y = offset - A*tab[index][1];
         	g.drawLine (x0, (int) y0, (x0+1), (int) y);
         	y0 = y;
         	x++;
               
		}*/  
	}
	
	public static void main(String[] args) throws FileNotFoundException 
	{	
		Locale.setDefault(new Locale("en", "US"));
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
			System.out.println(chooser.getSelectedFile().getName() + " chosen.");
		else 
			System.exit(0);
		
		int index = 0;
		String[][] tablica = new String[2300][6]; //[x][0]date, [x][1]time, [x][2]lati, [x][3]longi, [x][4]alti, [x][5]dist;
		
		index = magic.load(chooser, index, tablica); // method 'load' must be int so it can return index value
		magic.save("velocity.csv", index, tablica);
		
		JFrame jp1 = new JFrame();
		magic a = new magic();
		jp1.getContentPane().add(a);
        jp1.setSize(new Dimension(400,800));
        jp1.setVisible(true);
		}
	
	public static int load2(JFileChooser chooser, int index, Double[][] table) 
	{
		Scanner odczyt=null;
		try 
		{ 
			odczyt= new Scanner ( chooser.getSelectedFile() ); 
			odczyt.nextLine();
		
		odczyt.nextLine();
		while(odczyt.hasNextLine())
		{
			table[index][0] = Double.parseDouble(odczyt.next()); //time	
			System.out.println(table[index][0] + " " + index);
			table[index][1] = Double.parseDouble(odczyt.next()); //velocity	
			odczyt.next();	
			index++;
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception thrown.");
		}	
		
		System.out.println("Data loaded from " + chooser.getSelectedFile());
		odczyt.close();
		return index;
	}
	
	

}
