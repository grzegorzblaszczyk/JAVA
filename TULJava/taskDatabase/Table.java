package taskDatabase;

import java.util.ArrayList;

public class Table 
{
	private ArrayList<Record> dataBase = new ArrayList<Record>();
	
	public Table()
	{
		super();
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
	
}
