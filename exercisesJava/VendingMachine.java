import java.util.Scanner;
import java.util.Locale;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
*/

public class VendingMachine {
	
	public static double round(double value, int places) //for decimal rounding
	{
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public static void zapiszDoPliku(String filename, double kwota, int[]transakcja, String OKorERR)
	{
		PrintWriter outputStream = null;
		try 
		{
			outputStream = new PrintWriter (new FileWriter(filename,true));
		}
		catch (Exception e) 
		{
				System.out.println ("ERROR podczas tworzenia" +filename);
				System.exit (0); 
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		outputStream.printf(dateFormat.format(date)+"\t%.2f\t", kwota);
		for (int i=0;i<15;i++)
		{                       
			outputStream.printf("%d, ", transakcja[i]);
		}
		outputStream.printf("%s\n",OKorERR);
		System.out.println("Transakcja zapisana do pliku " +filename);
		outputStream.close();			
	}
    
	public static boolean akcja (int[] vmachine, double kwota)
    {            
		double kwotaZapis = kwota; //zmienna do zapisu do pliku
    	final double[] nom = {500.0, 200.0,100.0,50.0,20.0,10.0,5.0,2.0,1.0,0.5,0.2,0.1,0.05,0.02,0.01};
    	int [] trans = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    	int index=0;
    	System.out.println("Start :"+kwota);
    	while(kwota!=0.0)
    	{
    		if(vmachine[index]!=0 && (kwota-nom[index])>=0)
    		{
    			kwota = kwota - nom[index];
    			kwota = round(kwota,2); //bo tak. (by uniknąć rozwinięcia dziesiętnego)
    			vmachine[index]= vmachine[index] - 1;
    			trans[index] = trans[index]+1;
    			//System.out.println("index = "+index+"");
    			//System.out.println("vmachine["+index+"] = "+vmachine[index]);
    			//System.out.println("trans["+index+"] = "+trans[index]);
    		}
    		else
    		{
    			index++;
    		}
    		if(index==15 && kwota!=0.0) 
    		{
    			System.out.println("ERROR: brak banknotów.");
    			System.out.println("Pozostała kwota = "+kwota);
    			VendingMachine.zapiszDoPliku("heheszki.txt", kwotaZapis, trans," ERR");
    			return false;
    			//save
    		}
    	}
    	String [] wart = {"500zł", "200zł", "100zł","50zł","20zł","10zł","5zł","2zł","1zł","50gr","20gr","10gr","5gr","2gr","1gr"};
		System.out.print("Reszta: ");
		for(int i =0; i <15;i++) 
		{
			if(trans[i] != 0)
				System.out.println(wart[i]+" x "+trans[i]);
		}
		VendingMachine.zapiszDoPliku("heheszki.txt", kwotaZapis, trans," OK");
    	return true;
    }
	
	public static void main(String[] args) 
	{	
		Locale.setDefault(new Locale("en", "US")); //zczytywanie wartości w formacie #.#
		int [] vmachine= {1,5,5,5,5,5,5,5,5,5,5,5,5,5,5}; //nasz bankomat - start
		//int [] transakcja = new int[15];
		boolean wenduj = true;
		double liczba=0.0;
		Scanner odczyt = new Scanner(System.in);	
		do 
		{
			System.out.print("Podaj kwotę: ");
			liczba = odczyt.nextDouble();
			wenduj = akcja(vmachine,liczba);
		}
		while(wenduj);
		odczyt.close();
	}

}
