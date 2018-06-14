package taskDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Table 
{
	
	String [] columns = 
		{
			"[tT][yY][pP][eE]",
			"[sS][eE][rR][iI][aA][lL][nN][uU][mM][bB][eE][rR]",
			"[mM][aA][nN][uU][fF][aA][cC][tT][uU][rR][eE][rR]",
			"[pP][rR][oO][dD][uU][cC][tT][iI][oO][nN][yY][eE][aA][rR]",
			"[mM][eE][cC][hH][aA][nN][iI][cC][sS]",
			"[vV][aA][lL][uU][aA][tT][iI][oO][nN]",
		};
	private String tableName;
	private ArrayList<Record> dataBase = new ArrayList<Record>();
	
	public Table()
	{
		super();
		this.tableName="basicTable";
	}
	public Table(String tableName)
	{
		super();
		this.tableName = tableName;
	}
	public String [] getColumns()
	{
		return columns;
	}
	public String getTableName() 
	{
		return tableName;
	}
	public void setTableName(String tableName) 
	{
		this.tableName = tableName;
	}
	public void addRecord(Record record)
	{
		this.dataBase.add(record);
	}
	public void deleteRecord(int iterator)
	{
		if(iterator>0)
			this.dataBase.remove(iterator);
		else
			System.out.println("Iterator <=0");;
	}
	public void updateRecord(int iterator, Record record)
	{
		this.dataBase.set(iterator, record);
	}
	public int getSize()
	{
		return dataBase.size();
	}
	public Record getRecord(int iterator)
	{
		return dataBase.get(iterator);
	}
	public void clear()
	{
		this.dataBase.clear();
	}
	
	public void load() throws IOException
	{
		this.dataBase.clear();
		Scanner file = null;
		File f = new File("database.txt");
        try 
        {
        	f.createNewFile();
	        file = new Scanner(new File("database.txt"));
	        if(file.hasNextLine())
	        {
	        	this.tableName = file.nextLine(); file.nextLine();
	        }
	            while (file.hasNextLine()) //Returns: The character read, or -1 if the end of the stream has been reached
	        {
	            	addRecord(new Record(file.nextLine()));
	        }
        } 
        finally 
        {
        	if (file != null)
        		file.close();
        }
	}
	public void save() throws IOException
	{   
		PrintWriter outputStream = new PrintWriter (new FileOutputStream("database.txt",false));
		outputStream.println(tableName);
		outputStream.println();
		for (int counter = 0; counter<getSize();counter++)
		{   
			outputStream.print(getRecord(counter).getType() + " ");
			outputStream.print(getRecord(counter).getSerialNumber() + " ");
			outputStream.print(getRecord(counter).getManufacturer() + " ");
			outputStream.print(getRecord(counter).getProductionYear() + " ");
			outputStream.print(getRecord(counter).getMechanics() + " ");
			outputStream.println(getRecord(counter).getValuation());
		}
		//System.out.println("Data saved to database.csv");
		outputStream.close();			
	}
	public void printTable()
	{
		if(getSize() == 0)
			System.out.println("No records!!");
		for(int counter=0; counter<getSize();counter++) 
		{
			System.out.println(getRecord(counter));
		}	
	}
}
