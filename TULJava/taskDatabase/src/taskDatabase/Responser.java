package taskDatabase;

import java.util.Map;

public class Responser 
{
	public Responser(){}
	
	public void interpret(String command, Table database) throws Exception
	{
		Query inquiry = new Query(command);
		if(inquiry.isMatchingPattern()==true)
		{
			int id = inquiry.getMatchingPatternId();
			Map<String,String> extract = inquiry.convertMatcherToMap(inquiry.pregMatchAllById(id));
			actionMenu(database, id, extract);
		}
		else
		{
			System.out.println("Nie rozumiem komendy: " + command );
			//throw new CommandNotFoundException( "Nie rozumiem komendy: " + command );
		}
	}
	public int getMatchingPatternId(Table database, String field )
	{
		for(int i = 0; i < database.columns.length; i++)
			if(field.matches(database.columns[i]))
				return i;
		
		return -1;
	}
	
	public void actionMenu(Table database, int id, Map<String,String> extract ) throws Exception 
	{
		switch(id) 
		{
			case 0: 
				actionCreate(database, extract.get("tableName"));
				break;
			case 1:
				actionSelect(database, extract.get("columns"), extract.get("tableName"));
				break;
			case 2: 
				actionInsert(database, extract.get("values"), extract.get("tableName"));
				break;
			case 3: 
				actionUpdate(database, extract.get("set"), extract.get("condition"), extract.get("tableName"));
				break;
			case 4: 
				actionDelete(database, extract.get("condition"), extract.get("tableName"));
				break;
			case 5:
				actionShowInterface();
				break;
			default:
				break;
		}
	}
	public void actionCreate(Table database, String name) throws Exception
	{
		database.clear();
		database.setTableName(name);
		database.save();
	}
	public void actionSelect(Table database, String columns, String tableName)
	{
		int id=0;
		if(!database.getTableName().equals(tableName))
		{
			System.out.println("Nie znaleziono tablicy!");
			return;
		}
		//else
		//{
		//	System.out.println("Tablica: "+database.getTableName());
		//}
		String [] columnList;
		if(columns.equals("*"))
		{
			database.printTable();
		}
		else
		{
			columnList = columns.split(", ");
			for(int i = 0; i < database.getSize(); i++)
			{
				for(int j = 0; j<columnList.length;j++)
				{
					id = getMatchingPatternId(database, columnList[j]);
					switch(id)
					{
						case 0: 
							System.out.print(database.getRecord(i).getType() + " ");
							break;
						case 1: 
							System.out.print(String.format("%06d", database.getRecord(i).getSerialNumber()));
							System.out.print(" ");
							break;
						case 2: 
							System.out.print(database.getRecord(i).getManufacturer() + " ");
							break;
						case 3: 
							System.out.print(database.getRecord(i).getProductionYear() + " ");
							break;
						case 4: 
							System.out.print(database.getRecord(i).getMechanics() + " ");
							break;
						case 5: 
							System.out.print(database.getRecord(i).getValuation() + "$ ");
							break;
						default: 
							System.out.print("???");
							break;
					}	
				}System.out.println();
			}
		}
	}
	public void actionInsert(Table database, String values, String tableName)
	{
		if(!database.getTableName().equals(tableName))
		{
			System.out.println("Nie znaleziono tablicy!");
			return;
		}
		else
		{
			database.addRecord(new Record());
		}
		String [] valueList = values.split(", ");
		for(int i = 0; i<valueList.length;i++)
		{
			switch(i) 
			{
				case 0:
					database.getRecord(database.getSize()-1).setType(valueList[0]);
					break;
				case 1:
					database.getRecord(database.getSize()-1).setSerialNumber(Integer.parseInt(valueList[1]));
					break;
				case 2:
					database.getRecord(database.getSize()-1).setManufacturer(valueList[2]);
					break;
				case 3:
					database.getRecord(database.getSize()-1).setProductionYear(Integer.parseInt(valueList[3]));
					break;
				case 4:
					database.getRecord(database.getSize()-1).setMechanics(valueList[4]);
					break;
				case 5:
					database.getRecord(database.getSize()-1).setValuation(Double.parseDouble(valueList[5]));
					break;
				default: break;
			}
		}
	}
	public void actionUpdate(Table database, String set, String condition, String tableName)
	{
		if(!database.getTableName().equals(tableName))
		{
			System.out.println("Nie znaleziono tablicy!");
			return;
		}
		Condition cond = new Condition(condition);
		for(int i = 0; i<database.getSize();i++)
		{
			if(cond.isMatchingCondition(database.getRecord(i)))
			{
				String [] setColumnValue = set.split(", ");
				for(int j = 0; j<setColumnValue.length;j++) {
					String [] columnAndValue = setColumnValue[j].split(" = ");
					int id = getMatchingPatternId(database, columnAndValue[0]);
					switch(id) 
					{
						case 0:
							database.getRecord(i).setType(columnAndValue[1]);
							break;
						case 1:
							database.getRecord(i).setSerialNumber(Integer.parseInt(columnAndValue[1]));
							break;
						case 2:
							database.getRecord(i).setManufacturer(columnAndValue[1]);
							break;
						case 3:
							database.getRecord(i).setProductionYear(Integer.parseInt(columnAndValue[1]));
							break;
						case 4:
							database.getRecord(i).setMechanics(columnAndValue[1]);
							break;
						case 5:
							database.getRecord(i).setValuation(Double.parseDouble(columnAndValue[1]));
							break;
						default: 
							System.out.println("Setting value ERROR, Switch threw default");
							break;
					}
				}
			}
		}
		
	}
	public void delete(Table database, String condition, String tableName)
	{
		if(!database.getTableName().equals(tableName))
		{
			System.out.println("Nie znaleziono tablicy!");
			return;
		}
		Condition cond = new Condition(condition);
		for(int i = 0; i<database.getSize();i++)
		{
			if(cond.isMatchingCondition(database.getRecord(i)))
			{
				database.deleteRecord(i);
			}
		}
		
	}
	public void actionShowInterface()
	{
		new ProgramInterface().printProgramInterface();
	}
}
