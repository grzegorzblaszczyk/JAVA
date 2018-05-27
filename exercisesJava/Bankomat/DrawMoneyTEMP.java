package temp;

import java.awt.*;
import javax.swing.*;


import javax.swing.JApplet;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class DrawMoney extends JPanel implements ActionListener
{ 
	private int [] transakcja = {0,0,0,0,0,0,0,0}; //8
	private int x;
	private int y;
	private int kwota;
	
	private String [] nominals_US= {"  $500","  $200","  $100","   $50","   $20","   $10","    $5","    $1"};
	//private String [] nominals_PLN= {"500PLN","200PLN","100PLN","50PLN","20PLN","10PLN"};
	
	public DrawMoney(int x, int y, int [] vmachine)
	{	
		this.transakcja = vmachine;
		this.x = x;
		this.y = y;
	}
	
	void dodajClickMe(String nazwaGuzika, int x, int y, int size/*, ActionListener action*/, TextField tf) 
	{
		JButton clickMe = new JButton(nazwaGuzika);
		setLayout(null);
		clickMe.setBounds(x,y,size,size);
		clickMe.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent whatsUp){
			String blabla = tf.getText();
			tf.setText(blabla + nazwaGuzika);
		}});

		add(clickMe);
	}
	
	void dodajKlawiature(TextField tf)
	{
		dodajClickMe("1", 10, 100, 60, tf);
		dodajClickMe("2", 70, 100, 60, tf);
		dodajClickMe("3", 130, 100, 60, tf);
		
		dodajClickMe("4", 10, 160, 60, tf);
		dodajClickMe("5", 70, 160, 60, tf);
		dodajClickMe("6", 130, 160, 60, tf);
		
		dodajClickMe("7", 10, 220, 60, tf);
		dodajClickMe("8", 70, 220, 60, tf);
		dodajClickMe("9", 130, 220, 60, tf);
		
		dodajClickMe("0", 70, 280, 60, tf);
		
		JButton clickMeHash = new JButton("#");
		setLayout(null);
		clickMeHash.setBounds(10,280,60,60);
		clickMeHash.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent whatsUp){
			tf.setText("");
		}});
		add(clickMeHash);
		
		JButton clickMeEnter = new JButton("e");
		setLayout(null);
		clickMeEnter.setBounds(130,280,60,60);
		clickMeEnter.addActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent whatsUp)
					{
						        
					    	final int[] nom = {500, 200, 100, 50, 20, 10, 5, 1};
					    	int index=0;
					    	kwota = Integer.parseInt(tf.getText());
					    	System.out.println("Start :"+kwota);
					    	while(kwota!=0.0)
					    	{
					    		if((kwota-nom[index])>=0)
					    		{
					    			kwota = kwota - nom[index];
					    			transakcja[index] = transakcja[index]+1;
					    		}
					    		else
					    		{
					    			index++;
					    		}
					    	}
					    
					}
				});
		add(clickMeEnter);
		
	}
	
	public void paint/*Component*/(Graphics g)
	{
		super.paint/*Component*/(g);
		this.setBackground(Color.YELLOW);
		TextField tf = new TextField();
		tf.setBounds(200,280,150,20); 
		add(tf);
		dodajKlawiature(tf);
		
		for(int x : transakcja)
			System.out.println(transakcja[x]);
		
		int start_x = x;
		int index=0;
		for(index=0;index<8;index++)
		{
			for(int i = 0; i<transakcja[index];i++) 
			{
				g.setColor(Color.GREEN);
				g.fillRect(x+(i*1), y+(i*10), 40, 20);
				g.setColor(Color.BLACK);
				g.drawRect(x+(i*1), y+(i*10), 40, 20);
				g.drawString(nominals_US[index],x+(i*1),y+(15+i*10));
			
			}
			x+=60;
			index++;
		}
		x = start_x; // u can also use "x-=(60*index) otherwise the program will start drawing dollars from x = 475 
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	

}
