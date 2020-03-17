import java.applet.*;
import java.awt.*;
public class Draw extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillRect(50,50,5,150);
		int i=0,j=0;
		for(int k=0;k<5;k++) {
		int x1[]= {55,100+i,70+i,120+i,55};
		int y1[]= {55-j,105+j,105+j,155+j,155+j};
		g.drawPolygon(x1,y1,5);
		i+=2;
		j+=1;
		}
		int x2[]= {55,100,70,120,55};
		int y2[]= {55,105,105,155,155};
		g.setColor(Color.red);
		g.fillPolygon(x2,y2,5);
		g.setColor(Color.white);
		g.drawArc(60,80, 20, 20,180, 180);
		g.drawArc(60,79, 20, 20,180, 180);
		g.drawArc(60,81, 20, 20,180, 180);
		g.drawArc(60,78, 20, 20,180, 180);
		g.fillOval(60,125,20,20);
		g.fillOval(65,91,10,10);
		//Star drawing starts
		g.setColor(Color.red);
		int x3[]= {300,330,360,330,360,300,240,270,240,270};
		int y3[]= {250,280,280,310,340,325,340,310,280,280};	
		g.fillPolygon(x3,y3,10);
		//upper lower triangle
		int x4[]= {500,550,450};
		int y4[]= {300,400,400};
		g.fillPolygon(x4,y4,3);
		g.setColor(Color.blue);
		int x5[]= {450,550,500};
		int y5[]= {330,330,430};
		g.fillPolygon(x5, y5,3);
		
	}

}

