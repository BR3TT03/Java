import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
public class KeyEventDemo extends Applet implements KeyListener,Runnable {
 int x,y,h,w,bx,by,Score=0,BncrStrt,BncrEnd;
 int down=1;
 String GameOver=" ";
 Thread t;
	public void init() {
		x=490;
		y=530;
		h=10;
		w=100;
		bx=300;
		by=150;
		addKeyListener(this);
		this.setFocusable(true);
		t=new Thread(this);
		t.start();
	}
	public void run()
	{
		for(;;)
		{
		if(down==1) {
			by++;
			}
			if(by==150) {
				down=1;
			}
			if(down==0)
			{
				by--;
			}
			if(bx>(BncrStrt-20) && bx<BncrEnd && by==y-20 )        //Collision Detection
			{
				Score++;
				down=0;
			}
			if(by>y-20)
			{
				for(;;) {
					GameOver="GAME OVER";
					repaint();
					try {Thread.sleep(1000);}
					catch(Exception e) {}

				}
					
			}
			try {Thread.sleep(5);}
			catch(Exception e) {}

			repaint();
					}
	}
	public void keyPressed(KeyEvent e) {
		 
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(x>200){
			
			x-=20;
			repaint();
		}
		}
		 
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(x<990) {
			x+=20;
			repaint();
		}
			
		}
		BncrStrt=x;
		BncrEnd=x+100;

	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void paint(Graphics g) {  
		g.setColor(Color.red);
		g.fillRect(190, 150, 900, 400);  //GameCanvas
		g.setColor(Color.black);
		g.fillRect(x, y, w,h );         //Bouncer
		g.setColor(Color.blue);
		g.fillOval(bx, by, 20, 20);		//Bouncing Ball
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Score: ", 980, 140);
		g.drawString(Integer.toString(Score), 1050, 140);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.setFont(new Font("medieval",Font.BOLD,100));
		g.drawString(GameOver, 350, 400);
		
	}
	}
