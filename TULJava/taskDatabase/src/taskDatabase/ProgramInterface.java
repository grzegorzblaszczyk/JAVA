package taskDatabase;

public class ProgramInterface 
{
	
	public void printProgramInterface()
	{
		System.out.println("           MENU");
		System.out.println("--------------------------------");
		System.out.println("          Hello!!   ");
		System.out.println("--------------------------------");
		System.out.println("0|'CREATE TABLE [tableName]'");
		System.out.println("1|'SELECT [columns] FROM [tableName]'");
		System.out.println("2|'INSERT INTO [tableName] VALUES [values]'");
		System.out.println("3|'UPDATE [tableName] SET [values] WHERE [condition]'");
		System.out.println("-|");
		System.out.println("5|'SHOW TABLE NAME'");
		System.out.println("6|'SHOW INTERFACE'");
		System.out.println("-|'(Q)uit'");

	}
}
