package taskDatabase;
import java.io.*;
import java.util.*;

public class Main 
{
	public static void main(String[] args) throws IOException
	{
		
		//Scanner user = new Scanner(System.in);
		//char createNewTable;
		//Table tableZero = new Table();
		//
		//System.out.println("Do you wish to create new Table? Y/N");
		//do
		//{
		//	createNewTable = user.next().charAt(0);
		//	switch(createNewTable)
		//	{
		//		case 'Y': {/*create*/ break;}
		//		case 'N': {/*load*/ break;}
		//		default: break;
		//	}
		//}while(createNewTable!='Y' && createNewTable!='N');
		//user.close();
		Table tableSave = new Table();
		Record recordZero = new Record("Grandpiano", 202323, "Steinway", 1969, "Viennese", 250000.00);
		Record recordFirst = new Record("Electric", 312119, "Calisia", 2001, "N/A", 11999.99);
		tableSave.addRecord(recordZero);
		tableSave.addRecord(recordFirst);
		tableSave.addRecord(new Record());
		System.out.println("tableZero.getSize() = " +tableSave.getSize());
		Main.save(tableSave);
		Main.printTable(tableSave);
		System.out.println();
		Table tableLoad = new Table();
		Main.load(tableLoad);
		Main.printTable(tableLoad);
		System.out.println();
		System.out.println(tableLoad.getRecord(0).getManufacturer()+" "+tableLoad.getRecord(0).getSerialNumber());
		System.out.println();
		System.out.println(tableSave.getRecord(0).getManufacturer()+" "+tableSave.getRecord(0).getSerialNumber());
	}
	
	public static void load(Table table) throws IOException
	{
		table.clear();
		Scanner file = null;
        //int counter = 0;
        try 
        {
	            file = new Scanner(new File("database.csv"));
	            while (file.hasNextLine()) //Returns: The character read, or -1 if the end of the stream has been reached
	            {	
	            	table.addRecord(new Record(file.nextLine()));
	            	/*
	            	table.getRecord(counter).setType(file.next());
	            	table.getRecord(counter).setSerialNumber(Integer.parseInt(file.next()));
	            	table.getRecord(counter).setManufacturer(file.next());
	            	table.getRecord(counter).setProductionYear(Integer.parseInt(file.next()));
	            	table.getRecord(counter).setMechanics(file.next());
	            	table.getRecord(counter).setValuation(Double.parseDouble(file.next()));
	            	counter++;*/
	            }
        } 
        finally 
        {
        	if (file != null)
        		file.close();
        }
	}
	public static void save(Table table) throws IOException
	{   
		PrintWriter outputStream = new PrintWriter (new FileOutputStream("database.csv",false)); 
		for (int counter = 0; counter<table.getSize();counter++)
		{   /*                  
			outputStream.printf("%s \t", table.getRecord(counter).getType());
			outputStream.printf("%d \t", table.getRecord(counter).getSerialNumber());
			outputStream.printf("%s \t", table.getRecord(counter).getManufacturer());
			outputStream.printf("%d \t", table.getRecord(counter).getProductionYear());
			outputStream.printf("%s \t", table.getRecord(counter).getMechanics());
			outputStream.printf("%.2f\n", table.getRecord(counter).getValuation());
			*/
			outputStream.println(table.getRecord(counter));
		}
		System.out.println("Data saved to database.csv");
		outputStream.close();			
	}
	public static void printTable(Table table)
	{
		for(int counter=0; counter<table.getSize();counter++) 
		{
			System.out.println(table.getRecord(counter));
		}
		
	}
}
