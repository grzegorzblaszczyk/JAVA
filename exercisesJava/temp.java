import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.util.Locale;
import java.io.File;

import java.awt.*;
import javax.swing.*;
import java.applet.*;
import javax.swing.JApplet;
import java.awt.Color;

public class magic extends JApplet
{   
	public static int loadData(JFileChooser chooser, int index, String[][] table) 
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
	public static int loadVelocityCsv(String filename, int index, Double[][] table) 
	{
		Scanner odczyt=null;
		try 
		{ 
			odczyt= new Scanner(new File(filename)); 
			odczyt.nextLine();
		
		odczyt.nextLine();
		while(odczyt.hasNextLine())
		{
			table[index][0] = Double.parseDouble(odczyt.next()); //time
			table[index][1] = Double.parseDouble(odczyt.next()); //velocity
			table[index][2] = Double.parseDouble(odczyt.next()); //height
			System.out.println(table[index][2] + " " + index);
			index++;
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception thrown.");
		}	
		
		System.out.println("Data loaded from " + filename);
		odczyt.close();
		return index;
	}
	public static double timeToSec(int index, String[][] table) 
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
	public static void saveVelocityCsv(String fileName, int index, String[][] table) 
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
			time = magic.timeToSec(ix+1,table)-magic.timeToSec(ix,table);
			distance = (Double.parseDouble(table[ix+1][5]) - Double.parseDouble(table[ix][5]))*1000;
			velocity = distance/time;
			hight = Double.parseDouble(table[ix+1][4]);
			outputStream.printf("%.3f\t\t%.3f\t\t%.3f\n",time, velocity, hight);
		}
		System.out.println("Data saved to " +fileName);
		outputStream.close();			
	}
	public static double maxHeight(Double[][] tab, int index)
	{
		double max = tab[0][2];
		for(int i=0;i<index;i++)
		{
			if(max>=tab[i][2])
				continue;
			else
			{
				max = tab[i][2];
			}
		}
		return max;
	}

	public void paint(java.awt.Graphics g) 
	{
		
		super.paint(g);
		Double[][] tab = new Double[2300][3];
		int index = 0;
		index = magic.loadVelocityCsv("velocity.csv", index, tab);
		double max = magic.maxHeight(tab,index);
		
		int width, height;
		width = index; 
		height=0;
		while(max>height) //height scaling
			height+=100;
		
		
		//Y axis
		g.setColor(Color.BLACK);
		g.drawLine(15, 400, 15, 0 ); 
		g.drawLine(15, 0, 5, 20 );  
		g.drawLine(15, 0, 25, 20 ); 

		//X axis
		for(int i=0;i<5;i++) 
		{
			g.drawLine (15, 0+i*100, width+15, 0+i*100); 
			g.drawString((height-i*100)+"m", 15, 10+i*100); 
		}
		g.drawLine (width+30, 400, width+15, 400+10); 
		g.drawLine (width+30, 400, width+15, 400-10);
		
		//drawing
		double y,y0;
		index = 0;
		y0 = height - tab[index][2]; //tab[x][2] is height
		g.setColor(Color.RED);
		for(int i=15;i<(width-(15+1));i++)
		{
			y = height - tab[index][2];
			g.drawLine(i,(int)y0,i+1,(int)y);
			y0=y;
			index++;
		}														
	}
	public static void main(String[] args) throws FileNotFoundException 
	{	
		
		Locale.setDefault(new Locale("en", "US")); // for #.# format
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
			System.out.println(chooser.getSelectedFile().getName() + " chosen.");
		else 
			System.exit(0);
		
		int index = 0;
		String[][] tablica = new String[2300][6]; //[x][0]date, [x][1]time, [x][2]lati, [x][3]longi, [x][4]alti, [x][5]dist;
		
		index = magic.loadData(chooser, index, tablica); // loadData must be int so it can return index 
		magic.saveVelocityCsv("velocity.csv", index, tablica);
		
		JFrame jp1 = new JFrame("Wykres wysokości od czasu"); //create frame
		magic a = new magic(); //create object a
		jp1.getContentPane().add(a); //draw object a on the frame
        jp1.setSize(new Dimension(index+50,450)); //frame size
        jp1.setVisible(true); 
	}


}
