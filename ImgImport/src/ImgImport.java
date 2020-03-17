import java.awt.*;
import java.applet.*;
import java.util.*;
public class ImgImport extends Applet implements Runnable {

	Graphics bufferGraphics;
	Image offScreen;
	Thread t;
	Image img;
	int x=180,y=100,flagX=0,flagY=0;
	public void init() {
		offScreen=createImage(getSize().width,getSize().height);
		bufferGraphics=offScreen.getGraphics();
		img=getImage(getDocumentBase(),"football_icon.png");
		t=new Thread(this);
		t.start();
	}

	public void run(){
	while (true) {
		//for  X 
		if(flagX==1) {
			x--;
		}
		
		 if(flagX==0) { 
		x++;
		}
	    if(x==863) {
			flagX=1;

	    }
	    if(x==185) {
	    	flagX=0;
	    }
	    //for Y
	  if(flagY==0) {
		  y++;
	  }
	    if(y==255) {
	    	flagY=1;
	    }
	    
	    if(flagY==1)
	    {
	    	y--;
	    }
	    if(y==95)
	    {
	    	flagY=0;
	    }
		repaint();
		try {Thread.sleep(5);}
		catch(Exception e) {}
		
	
		
	}
		
	}
	public void paint(Graphics g) 
	{
		bufferGraphics.clearRect(0, 0, getSize().width,getSize().height);
		bufferGraphics.setColor(Color.green);
		bufferGraphics.fillRect(200, 100, 700, 200);
		bufferGraphics.drawImage(img,x,y,50,50,this);
		bufferGraphics.drawString("Start",0, 10);
	    g.drawImage(offScreen,0,0,this);
	}
	public void update(Graphics g) {
		paint(g);
	}

}