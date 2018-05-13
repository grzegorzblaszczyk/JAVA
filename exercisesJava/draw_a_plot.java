import javax.swing.JApplet;
import java.awt.Color;
//Program rysuje dowolną funkcje y o zmiennej f o argumentach od x do width-15.

public class draw_a_plot extends JApplet{
	int width, height;
	
	   public void init () {
	       //getContentPane().setLayout(null);
	       width = 900;
	       height = 600;
	       setSize(width+100, height+200); //+100 i +200 żeby okienko bylo ładniejsze
	}
	public void paint(java.awt.Graphics tlo) {

		//tło
		super.paint(tlo);
		setVisible(true);
		setBackground(Color.black);
		setLayout(null);
		
		 /* Linia pionowa od punktu x=15 y=0 do x=15 y=600.
		  * Wartości 15 jest dodane dla x, aby linia
		  * nie była rysowana w rogu okienka, tylko lekko obok krawędzi
		  */
		tlo.setColor(Color.BLACK);
		tlo.drawLine(15, 600, 15, 0 ); //oś y
		tlo.drawLine(15, 0, 5, 20 );  //strzałka 
		tlo.drawLine(15, 0, 25, 20 ); //strzałka cd
 
  //x+ to prawo
  //x- to lewo 
  //y+ to dół
  //y- to góra
  

  int x=0; //zmienna x dla funkcji
  int x0; //zmienna dla rysowania x
  double y; //zmienna y dla funkcji
  double y0; //zmienna dla rysowania y
  
  double range = 3*2*Math.PI; //N*2*PI = 2*PI zmieści się N razy na osi X; można dać np 3.2 zamiast 3
  double t, f, A, offset;
  // t - zmienna aby skalować funkcje na osi X
  // A - zmienna aby skalować funkcje na osi Y
  // f - zmienna aby skalować funkcje dla argumentu funkcji (czyli f to jest np. takie x w cos(x))
  //offset - offset dla rysowania y
	 
 
  A = (double) height / 2;  
  offset = (double) height / 2; 
  x0 = 15;
  
  tlo.drawLine (x0, (int) offset, width+30, (int) offset); //oś x
  tlo.drawLine (width+30, (int) offset, width+15, (int) offset+10); //strzałka
  tlo.drawLine (width+30, (int) offset, width+15, (int) offset-10); //strzałka cd
  tlo.drawString("0", 9, (int)offset);
  tlo.drawString("2\u03c0", 300, (int)offset); // \u03c0 to kod na symbol "PI"
  tlo.drawString("4\u03c0", 600, (int)offset);
  tlo.drawString("6\u03c0", 900, (int)offset);


  
//zamiast tych trzech linijek poniżej można dać po prostu y0 = offset;
// są one poto by nie bylo paskudnej pinowej niebieskiej linii na x=0
  t = (double) x / ((double) width);
  f = range * t;
  y0 = offset - A*0.25*Math.cos(f);

  
  tlo.setColor(Color.GREEN); //g(x)
  for (x0=15; x0<width; x0++) {
	  	t = (double) x / ((double) width);
	  	f = range * t; 
         y = offset - A*0.25*Math.cos(f);
         tlo.drawLine (x0, (int) y0, (x0+1), (int) y);
         y0 = y;//rysowanie na y
         x++;        
}
 
  x=0;
//zamiast tych trzech linijek poniżej można dać po prostu y0 = offset;
//są one poto by nie bylo paskudnej pinowej zielonej linii na x=0
  t = (double) x / ((double) width);
  f = range * t;
  y0 = offset - A * Math.cos(f)*Math.exp(f*(-0.2));
  
//f(x)
tlo.setColor(Color.BLUE);
for (x0=15; x0<width; x0++) {
         t = (double) x / ((double) width);
         f = range * t;
         y = offset - A * Math.cos(f)*Math.exp(f*(-0.2));
         tlo.drawLine (x0, (int) y0, (x0+1), (int) y);
         y0 = y;
          
         x++;
}
}
}

