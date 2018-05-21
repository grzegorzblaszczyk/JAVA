import java.util.Scanner;
import java.lang.Math;

public class ex_1
{
	public static int getValue()
	{
		Scanner read = new Scanner(System.in);
		System.out.print("Please, enter number: ");
		String numberString = read.nextLine();
		int numberInt = Integer.parseInt(numberString);
		return numberInt;
	}

	public static void main(String[] args)
	{
		int A, B, C;
		A = getValue();
		B = getValue();
		C = getValue();
		double x1, x2, delta;
		delta = Math.pow((double)B,2.0) - 4.0*A*C;
		if(delta<0) 
			System.out.println("Equation: "+A+"x^2 + "+B+"x + "+C+" has no roots");
		else if(delta==0)
		{
			System.out.println("Equation: "+A+"x^2 + "+B+"x + "+C+" has one root" );
			x1 = -(B/(2.0*A));
			System.out.println("X = "+x1);
		}
		else
		{
			System.out.println("Equation: "+A+"x^2 + "+B+"x + "+C+" has two roots" );
			x1 = ((-B) - Math.sqrt(delta))/(2*A);
			x2 = ((-B) + Math.sqrt(delta))/(2*A);
			System.out.println("X1 = "+x1+" X2 = "+x2);
		}

	}
}
