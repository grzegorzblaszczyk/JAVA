//Invalid number of arguemnts ERROR_1
//*2* and *3* argument not integer ERROR_2
//*2* arguemnt range ERROR_3
//*3* arguemnt range ERROR_4

/*class ERROR_2 extends Exception {}
class ERROR_3 extends Exception 
{
	System.out.println("Argument *2* RangeError");
	System.exit(1);
}
class ERROR_4 extends Exception 
{
	System.out.println("Argument *3* RangeError");
	System.exit(1);
}*/

public class ex_2
{
	
	public static void main(String[] args)
	{
		if(args.length != 3)
		{
			System.out.println("Invalid number of arguments");
			System.exit(1);
		}
		String String1 = args[0];
		int int1=0, int2=0; 
		try
		{
			int1 = Integer.parseInt(args[1]);
			int2 = Integer.parseInt(args[2]);
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Second and third argument must be an integer.");
			System.exit(1);
		}
		/*try{
		if(int1<0 || int1>String1.length()) throws ERROR_3;
		if(int2<int1 || int2>String1.length()) throws ERROR_4;}
		catch(ERROR_3 c){}
		catch(ERROR_4 d){}*/

		if(int1<0 || int1>String1.length())
		{
			System.out.println("Argument *2* RangeError");
			System.exit(1);
		}
		if(int2<int1 || int2>String1.length())
		{
			System.out.println("Argument *3* RangeError");
			System.exit(1);
		}

		System.out.println(String1.substring(int1,int2));
		
	}
}
