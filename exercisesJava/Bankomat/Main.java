package temp;

import javax.swing.*;
import java.util.Scanner;

public class Main
{
	public static  int [] zeruj(int [] tab)
	{
		for(int i=0; i<8; i++)
			tab[i] = 0;
		return tab;
	}
	
	public static boolean akcja (int[] vmachine, int kwota, int[]T)
    {            
    	final int[] nom = {500, 200, 100, 50, 20, 10, 5, 1};// US
    	//int [] T = {0,0,0,0,0,0,0,0}; //8
    	int index=0;
    	System.out.println("Start :"+kwota);
    	while(kwota!=0.0)
    	{
    		if(vmachine[index]!=0 && (kwota-nom[index])>=0)
    		{
    			kwota = kwota - nom[index];
    			vmachine[index]= vmachine[index] - 1;
    			T[index] = T[index]+1;
    		}
    		else
    		{
    			index++;
    		}
    		if(index==8 && kwota!=0) 
    		{
    			System.out.println("ERROR!!! Coś nie poszło z wydawaniem (index == 8, kwota != 0) ");
    			return false;
    		}
    	}
    	return true;
    }
	
	public static void main(String[] args) 
	{
		int [] vmachine= {999,999,999,999,999,999,999,999}; 
		//set Frame
		JFrame f = new JFrame("SUmthing");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//variables
		boolean wenduj = true;
		int [] transakcja = {0,0,0,0,0,0,0,0};
		DrawMoney rysujHajs;
		Scanner read = new Scanner(System.in);
		int kwota;
		
		//program
		do 
		{
			f.repaint();
			
			System.out.print("Podaj kwote: ");
			kwota = read.nextInt();
			transakcja = zeruj(transakcja);
			wenduj = akcja(vmachine,kwota,transakcja);
			/*
			for (int index = 0;index<8;index++)
				System.out.printf("transakcja["+index+"] = "+transakcja[index]+"\n");
			 */
			rysujHajs = new DrawMoney(15,15,transakcja);
			f.add(rysujHajs);
			f.setSize(600,400);
			f.setVisible(true);
			
			
		}while(wenduj);
		
		
		
		
		
		
		
		
		
		
		
		
		//DrawMoney rysujHajs = new DrawMoney(15,15,vmachine);
		//f.add(rysujHajs);
		//f.setSize(600,200);
		//f.setVisible(true);
		
		
	}
}

