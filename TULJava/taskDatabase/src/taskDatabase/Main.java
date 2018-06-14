package taskDatabase;
import java.io.*;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) throws IOException
	{
		/*
		Table database = new Table("tab");
		String inq = "UPDATE tab SET type = dziala WHERE serialNumber = 111111";
		database.addRecord(new Record("piano",111111,"edgar",1996,"vienn",2000.0));
		Query q = new Query(inq);
		System.out.println(q.isMatchingPattern()); //true
		System.out.println(q.getMatchingPatternId()); // 3
		System.out.println(q.convertMatcherToMap(q.pregMatchAllById(q.getMatchingPatternId())).get("condition")); //serialNumber = 111111
		Condition cond = new Condition(q.convertMatcherToMap(q.pregMatchAllById(q.getMatchingPatternId())).get("condition"));
		System.out.println(database.getRecord(0)); //piano 111111 edgar 1996 vienn 2000.0$
		System.out.println(cond.inquiry[0]+""); // serialNumber = 111111
		System.out.println(cond.isMatchingPattern(cond.inquiry[0])); //true
		System.out.println(cond.getMatchingPatternId(cond.inquiry[0])+""); //0
		System.out.println(cond.isMatchingCondition(database.getRecord(0))); //true
		System.out.println(cond.getMatchingColumnValue("serialNumber", database.getRecord(0))); //111111
		*/
		
		ProgramInterface PF = new ProgramInterface();
		Scanner scanner = new Scanner(System.in);
		Responser interpreter = new Responser();
		Table database = new Table();
		database.load();
		
		PF.printProgramInterface();
		while( true )
		{
			System.out.print(">>");
			String command = scanner.nextLine();
			
			if( command.equals("quit") || command.equals("q") || command.equals("Q") || command.equals("QUIT") )
			{
				scanner.close();
				break;
			}
			else if(command.equals("SHOW TABLE NAME") || command.equals("show table name") || command.equals("stn"))
			{
				System.out.println(database.getTableName());
			}
			else
			{
				try
				{
					interpreter.interpret(command, database);
				}
				catch( Exception e )
				{
					System.out.println( e.getMessage() );
				}
			}
		} database.save();
	}
}
