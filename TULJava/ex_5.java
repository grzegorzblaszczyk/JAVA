import java.util.*; //ResourceBundle for getLozalizedMessage
import java.io.*;
import java.util.Scanner;

//Is it better to use Throws IOException or catch(IOExcepion e)????


public class ex_5 {

	public static void main(String[] args) throws IOException
	{
		//try 
		//{
			Scanner read = new Scanner(System.in);
			Vector<Double> A = null;
			Vector<Double> B = null;
			Vector<Double> C = null;
			boolean vectorsAreEqual = true;
			
			do 
			{
				vectorsAreEqual = true;
				System.out.print("Enter first vector: ");
				A = readVec(read.nextLine());
				
				System.out.print("Enter second vector: ");
				B = readVec(read.nextLine());
				
				try 
				{
					C = addVector(A, B);
				} 
				catch(WektoryRoznejDlugosciException e) 
				{
					System.out.println("ERROR: " + e.getMessage() + " " + e.A + " != " + e.B);
					System.out.println("Re-enter the vectors.");
					A.clear();
					B.clear();
					vectorsAreEqual = false;
				}
			} while(!vectorsAreEqual);
			
			save(C, "vector.txt");
			System.out.println("Vector C saved to vector.txt .");
			read.close();
			
		//} catch(IOException e) {
		//	System.out.println("Application error" + e.getLocalizedMessage()); // e.getMessage();
		//}
	}
	
	static Vector<Double> readVec(String l) {
		Scanner scan = new Scanner(l);
		Vector<Double> vec = new Vector<Double>();
		while(scan.hasNext()) 
		{
			if(scan.hasNextDouble())
				vec.add(scan.nextDouble());
			else
				scan.next();
		}
		scan.close();
		return vec;
	}
	
	static Vector<Double> addVector(Vector<Double> A, Vector<Double> B) throws WektoryRoznejDlugosciException 
	{
		if(A.size() != B.size())
			throw new WektoryRoznejDlugosciException(A.size(), B.size());
		Vector<Double> C = new Vector<Double>(A.size());
		for(int i = 0; i < A.size(); ++i)
			C.add(A.elementAt(i) + B.elementAt(i));
		
		return C;
	}
	
	static void save(Vector<Double> v, String fileName) throws IOException 
	{
		PrintWriter out = new PrintWriter (new FileOutputStream(fileName));
		for(int i = 0; i < v.size(); ++i)
			out.print(v.elementAt(i) + " ");
		out.close();
	}
}


class WektoryRoznejDlugosciException extends Exception {

	private static final long serialVersionUID = 1L; //for class matching???... Thank you eclipse
	public int A, B;
	
	public WektoryRoznejDlugosciException(int A, int B) 
	{
		super("Vectors do not match.");
		/*
		 * In the case of custom exceptions, it is common to use super to initialize the exception's error message; 
		 * by passing the message into the base class constructor, the base class will take care of the work of setting the message up correctly.
		 */
		this.A = A;
		this.B = B;
	}
	
}
