//WORK IN PROGRESS!!!

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class load_and_save
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
		outputStream.printf("0.000\t\t0.000\t\t%.1f\n", Double.parseDouble(table[0][4])); 
		for (int ix=0;ix<(index-1);ix++)
		{                       
			time = load_and_save.convertTime(ix+1,table)-load_and_save.convertTime(ix,table);
			distance = (Double.parseDouble(table[ix+1][5]) - Double.parseDouble(table[ix][5]))*1000;
			velocity = time*distance;
			hight = Double.parseDouble(table[ix+1][4]);
			outputStream.printf("%.3f\t\t%.3f\t\t%.1f\n",time,velocity,hight);
		}
		System.out.println("Data saved to " +fileName);
		outputStream.close();			
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException 
	{	
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
			System.out.println(chooser.getSelectedFile().getName() + " chosen.");
		else 
			System.exit(0);
		
		int index = 0;
		String[][] tablica = new String[2300][6]; //[x][0]date, [x][1]time, [x][2]lati, [x][3]longi, [x][4]alti, [x][5]dist;
		
		index = load_and_save.load(chooser, index, tablica); // method 'load' must be int so it can return index value
		load_and_save.save("velocity.csv", index, tablica);
	}
	
	

	

}
	
	
	
