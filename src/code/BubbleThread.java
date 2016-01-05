package code;
import java.awt.*;
import java.applet.Applet;
public class BubbleThread extends Thread
{
	char ch;
	
	int x,y,dx,dy,r=55;
	Applet a;
	Image i;
	boolean b;
	Color c;
	public BubbleThread(int x,int y,Color c,Image i,int dx,int dy,Applet aa)
	{
		this.x=x;
		this.y=y;	
		
		a=aa;
		this.i=i;
		this.dx=dx;
		this.dy=dy;
		this.c=c;
		start();
	}
	public BubbleThread(int x,int y,Color c,int dx,int dy,Applet aa)
	{
		this.x=x;
		this.y=y;	
		
		a=aa;
		this.i=null;
		this.dx=dx;
		this.dy=dy;
		this.c=c;
		start();
	}
	public void run()
	{
		try
		{
			while(true)
			{
				if(y<=0)
					b=true;
				move();
				a.repaint();
				Thread.sleep(100);
			}
		}
		catch (Exception e)
		{
		}
	}
	public void move()
	{
		if(x-r<0||x+r>a.getWidth())
			dx=-dx;
		if(y+r>a.getHeight())
			dy=-dy;
		x+=dx;
		y+=dy;
	}
	
}
