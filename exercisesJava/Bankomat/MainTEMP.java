package temp;

import javax.swing.*;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Scanner;

public class Main
{
	public static  int [] zeruj(int [] tab)
	{
		for(int i=0; i<8; i++)
			tab[i] = 0;
		return tab;
	}
	
	public static void main(String[] args) 
	{
		int [] vmachine= {999,999,999,999,999,999,999,999}; 
		//set Frame
		JFrame f = new JFrame("SUmthing");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = f.getContentPane();
		
		//variables
		int [] transakcja = {0,0,0,0,0,0,0,0};
		DrawMoney rysujHajs;
		//transakcja = zeruj(transakcja);
		rysujHajs = new DrawMoney(200,15,transakcja);
		f.getContentPane().add(rysujHajs);
		f.setSize(800,400);
		f.setVisible(true);
	
		
		
		
		
		
		
		
		
		
		
		
		
		//DrawMoney rysujHajs = new DrawMoney(15,15,vmachine);
		//f.add(rysujHajs);
		//f.setSize(600,200);
		//f.setVisible(true);
		
		
	}
}
