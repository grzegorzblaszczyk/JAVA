import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class Bankomat_JApplet extends JApplet implements ActionListener
{ 
	void dodajClickMe(String nazwaGuzika, int x, int y, int size, TextField tf) 
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
		
	}
	
	public void init()
	{
		setSize(800,400);
		setVisible(true);
		setLayout (null);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Container c = this.getContentPane();
		TextField tf = new TextField();
		tf.setBounds(30,60,145,30); 
		add(tf);
		dodajKlawiature(tf);
		
		JButton clickMeEnter = new JButton("e");
		clickMeEnter.setBounds(130,280,60,60);
		clickMeEnter.addActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent whatsUp)
					{	
						
						/*	
						int [] transakcja = {1,0,0,0,0,0,0,0}; //8
						int x = 200;
						int y = 15;
						int kwota = 100;
						String [] nominals_US= {"  $500","  $200","  $100","   $50","   $20","   $10","    $5","    $1"};
						final int[] nominals = {500, 200, 100, 50, 20, 10, 5, 1};
					    int index=0;
					    kwota = Integer.parseInt(tf.getText());
					    System.out.println("Start: $"+kwota);
					    while(kwota!=0.0)
					    {
					    	if((kwota-nominals[index])>=0)
					    	{
					    		kwota = kwota - nominals[index];
					    		transakcja[index] = transakcja[index]+1;
					    	}
					    	else
					    	{
					    		index++;
					    	}
					    }
					    for(int i : transakcja)
								System.out.println(transakcja[i]);
							
						int start_x = x;
						index=0;
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
						repaint();
					*/}
				});
		add(clickMeEnter);	
	
	
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
