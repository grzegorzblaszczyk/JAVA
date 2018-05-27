package temp;

import java.awt.*;
import javax.swing.*;


import javax.swing.JApplet;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class DrawMoney extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private int [] vmachine = {0,0,0,0,0,0,0,0}; //8
	private int x;
	private int y;
	
	private String [] nominals_US= {"  $500","  $200","  $100","   $50","   $20","   $10","    $5","    $1"};
	//private String [] nominals_PLN= {"500PLN","200PLN","100PLN","50PLN","20PLN","10PLN"};
	
	public DrawMoney(int x, int y, int [] vmachine)
	{	
		this.vmachine = vmachine;
		this.x = x;
		this.y = y;
	}
	
	public DrawMoney()
	{	
		this.vmachine = null;
		this.x = 10;
		this.y = 10;
	}
	
	void dodajClickMe(String name, int x, int y, int size, ActionListener action) 
	{
		JButton clickMe = new JButton(name);
		setLayout(null);
		clickMe.setBounds(x,y,size,size);
		
		//clickMe.addActionListener(action);
		add(clickMe);
	}
	
	void dodajKlawe()
	{
		TextField tf = new TextField();
		tf.setBounds(200,280, 150,20); 
		
		
		dodajClickMe("1",10,100,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		dodajClickMe("1",70,100,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		dodajClickMe("1",130,100,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });

		dodajClickMe("1",10,160,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		dodajClickMe("1",70,160,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		dodajClickMe("1",130,160,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });

		dodajClickMe("1",10,220,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		dodajClickMe("1",70,220,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		dodajClickMe("1",130,220,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });

		dodajClickMe("1",10,280,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		dodajClickMe("1",70,280,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		dodajClickMe("1",130,280,60, new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
	            tf.setText("1");  
	    } });
		
		add(tf);
	}
	
	public void paint/*Component*/(Graphics g)
	{
		super.paint/*Component*/(g);
		this.setBackground(Color.YELLOW);
		dodajKlawe();
		
		int start_x = x;
		int index=0;
		while(index!=8)
		{
			for(int i = 0; i<vmachine[index];i++) 
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
