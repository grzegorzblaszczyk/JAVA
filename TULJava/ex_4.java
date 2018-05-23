import java.util.Random;
import java.io.*;

import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class ex_4 
{
	public static void TEST(String method, String fileName, File file, char[] array, int IOorNIO) throws Exception
	{
		System.out.print(method +" Save Time: ");
		long Time = System.currentTimeMillis();
		file.save(array, fileName);
		long TimeEnd = System.currentTimeMillis() - Time;
		System.out.println(TimeEnd);
		
		
		System.out.print(method +" Read Time: ");
		Time = System.currentTimeMillis();
		if(IOorNIO == 0)
			file.readIO(fileName);
		if(IOorNIO == 1)
			file.readNIO(fileName);
		TimeEnd = System.currentTimeMillis() - Time;
		System.out.println(TimeEnd);	
	}
	
	public static void fillArray(char[]array)
	{
		Random rand = new Random();
		for(int i=0;i<array.length;i++)
			array[i] = (char)(rand.nextInt(94) + 32);
	}
	
	public static void main(String[] args) throws Exception
	{
		char[]array = new char[1000];
		ex_4.fillArray(array);
		/*
		File testIO = new IOFILE();
		testIO.save(array, "testIO.txt");
		testIO.readAndPrint("testIO.txt");
		
		File testNIO = new IOFILE();
		testNIO.save(array, "testNIO.txt");
		testNIO.readAndPrint("testNIO.txt");
		*/
		TEST("IO","IOFile.txt", new IOFILE(), array, 0);
		TEST("NIO","NIOFile.txt",new NIOFILE(), array, 1);
	}

}

interface File
{
	void save(char[] array, String fileName) throws Exception;
	char[] readIO(String fileName) throws Exception;
	String[] readNIO(String fileName) throws Exception;
	void readAndPrint(String fileName) throws Exception;

}

class IOFILE implements File
{

	@Override
	public void save(char[] array, String fileName) throws IOException 
	{
		OutputStream out = new FileOutputStream(fileName);
		Writer writeToFile = new OutputStreamWriter(out);
		writeToFile.write(array);
		writeToFile.close();
		out.close();
	}

	@Override
	public char[] readIO(String fileName) throws IOException 
	{
        FileReader file = null;
        char[] readText = new char[1000];
        int counter = 0;
        try 
        {
	            file = new FileReader(fileName);
	            int c;
	            while ((c = file.read()) != -1) //Returns: The character read, or -1 if the end of the stream has been reached
	            	readText[counter++] = ((char)c); //readText[counter] = ((char)c);
	            
        } 
        finally 
        {
        	if (file != null)
        		file.close();
        }
	    return readText;
	}

	@Override
	public String[] readNIO(String fileName) throws Exception 
	{
	
		return null;
	}

	@Override
	public void readAndPrint(String fileName) throws IOException
	{
		//Can i use super(); somehow?
		FileReader file = null;
        char[] readText = new char[1000];
        int counter = 0;
        try 
        {
	            file = new FileReader(fileName);
	            int c;
	            while ((c = file.read()) != -1) //Returns: The character read, or -1 if the end of the stream has been reached
	            	readText[counter++] = ((char)c); //readText[counter] = ((char)c);
	            
        } 
        finally 
        {
        	if (file != null)
        		file.close();
        }
        System.out.println(readText);
	}
}

class NIOFILE implements File
{

	@Override
	public void save(char[] array, String fileName) throws IOException 
	{
		Path path = Paths.get(fileName);
		FileChannel fch = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		ByteBuffer bbuf = ByteBuffer.allocate(array.length * 2);
		CharBuffer cbuf = bbuf.asCharBuffer();
		cbuf.put(array);
		fch.write(bbuf);
		fch.close();
	}

	@Override
	public String[] readNIO(String fileName) throws IOException 
	{
		Path path = Paths.get(fileName);
		FileChannel fc = FileChannel.open(path, StandardOpenOption.READ);
		ByteBuffer bbuf = ByteBuffer.allocate((int)fc.size());
		CharBuffer cbuf = bbuf.asCharBuffer();
		fc.read(bbuf);
		fc.close();
		String[] t = new String[cbuf.length()];
		cbuf.get();
		return t;
	}

	@Override
	public char[] readIO(String fileName) throws Exception 
	{
	
		return null;
	}

	public void readAndPrint(String fileName) throws IOException
	{	
		//Can i use super(); somehow?
		Path path = Paths.get(fileName);
		FileChannel fc = FileChannel.open(path, StandardOpenOption.READ);
		ByteBuffer bbuf = ByteBuffer.allocate((int)fc.size());
		CharBuffer cbuf = bbuf.asCharBuffer();
		fc.read(bbuf);
		fc.close();
		String[] t = new String[cbuf.length()];
		cbuf.get();
		System.out.println(t);
	}
}



