//By Sujata Regoti
//Date: 15 March 2015
package code;

import java.awt.*;
import javax.swing.JButton;
import java.awt.event.*;
import java.util.*;
/*<applet code=TypingGame width=400 height=600></applet>*/
public class TypingGame extends java.applet.Applet  implements KeyListener,MouseListener,MouseMotionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//implementing both KeyListener and ActionListener does not work
		BubbleThread t[];
		boolean b[];
		int limit=10;
		Button btn=new Button("restart");
		long score=0;
		long cnt=0;
		long loss=0;
		boolean mouseIn=false;
		private Image ii;
		Image bk,star;;
		int r=0,gg=0,bb=0;
		private Graphics doubleG;
		Random rad;
		int j=0,i;
		JButton jb;
		char ch[];
		public void init()
		{
			star=getImage(getDocumentBase(),"images/star.gif");
			setLayout(null);
			bk=getImage(getDocumentBase(),"images/bak.jpg");
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();
			this.setSize(width, height);
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			//add(btn);
			//btn.setBounds(getWidth()/2-100,getHeight()/2+100,100,30);
			/*btn.addActionListener(new ActionListener(){
					
				public void actionPerformed(ActionEvent e)
				{
					initilize();
				}
			});*/
			//btn.setVisible(false);

		}
		public void start()
		{
			loss=0;
		

			initilize();
			
		}
		public void paint(Graphics g)
		{
			
			boolean flg=true;
			rad=new Random();
			g.drawImage(bk,0,0,getWidth(),getHeight(),this);
			
			g.drawImage(star,5,5,100,100,this);
			g.setFont(new Font("Arial",Font.BOLD,40));	
					g.setColor(Color.white);
			g.drawString(""+score,150,50);
			for(int k=0;k<=i;k++)
			{
				cnt=0;
				if(t[k]!=null&&loss<=limit)
				{
					
					g.setFont(new Font("Arial",Font.BOLD,35));	
					
					
						
					g.setColor(t[k].c);
					//g.fillOval(t[k].x-t[k].r,t[k].y-t[k].r,2*t[k].r,2*t[k].r);
					
					
					//g.drawOval(t[k].x-t[k].r,t[k].y-t[k].r,2*t[k].r,2*t[k].r);
					g.drawImage(t[k].i,t[k].x-t[k].r,t[k].y-t[k].r,2*t[k].r,2*t[k].r,this);
				
					//g.setColor(Color.white);
					g.setColor(Color.black);
					g.drawString(""+ch[k],t[k].x-10,t[k].y+5);
					flg=false;
					if(t[k].y<=0)
					{
						b[k]=true;
						t[k]=null;
					}
				}
				
			}
			
			
			for(int j=0;j<t.length;j++)
			{
						if(t[j]==null&&b[j]==true)
						{
							cnt++;
							
						}
			}
			if((loss+cnt)>=limit)
			{
				flg=true;
			}
			if(flg)
			{
				loss+=cnt;
				if(loss>=limit)
				{
						g.setColor(Color.white);
						g.drawString("Game Over",getWidth()/2-100,getHeight()/2);
						g.drawString("Score:"+score,getWidth()/2-100,getHeight()/2+50);
						for(int j=0;j<t.length;j++)
							t[j]=null;
						try
						{
							Thread.sleep(1000);
						}
						catch (Exception e)
						{
						}
						btn.setVisible(true);
						/*btn.addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e)
					{
						start();
					}
				});*/
					
					g.drawRect(getWidth()/2-100-20,getHeight()/2+100-30,250,40);
					if(mouseIn)
					{
						g.setColor(Color.red);
						g.drawString("Play Again?",getWidth()/2-100,getHeight()/2+100);
					}
					else
					{
						g.setColor(Color.white);
						g.drawString("Play Again?",getWidth()/2-100,getHeight()/2+100);
					}

				}
				else
				initilize();
			}
			
				try
				{
					j++;
					if(loss<limit)
					{
						if(i<(t.length-1)&&j>50)
						{
								i++;
								r=rad.nextInt(256);
								gg=rad.nextInt(256);
								bb=rad.nextInt(256);
								ch[i]=(char)(rad.nextInt(74)+48);
								Image img=getImage(getDocumentBase(),"images/"+(rad.nextInt(8)+1)+"t.png");
								t[i]=new BubbleThread(rad.nextInt(1200),getHeight()-50,new Color(r,gg,bb),img,0,5+2*i,this);

								j=0;
						}
					
						long tot=loss+cnt;
						g.setColor(Color.red);
						g.drawString("Loose:"+tot,150,90);
					}
				}catch(Exception e)
				{
					e.printStackTrace();		
				}
			
		}
		public void update(Graphics g)
		{
			if(ii==null)
			{
				ii=createImage(this.getSize().width,this.getSize().height);
				doubleG=ii.getGraphics();
			}

			doubleG.setColor(getBackground());
			doubleG.fillRect(0,0,getSize().width,getSize().height);
			doubleG.setColor(getForeground());
			paint(doubleG);
			g.drawImage(ii,0,0,this);
		}
		
		public void keyTyped(KeyEvent e){
			char c=e.getKeyChar();
			if(c!=KeyEvent.CHAR_UNDEFINED)
			{
				for(int l=0;l<ch.length;l++)
				{
					if(ch[l]==c&&ch[l]!=0)
					{
						ch[l]=0;
						t[l]=null;
						score+=10;
					}
				}
			}
		}
		public void keyPressed(KeyEvent e)
		{
		}
		public void keyReleased(KeyEvent e){}
		/*public void paint(Graphics g)
		{
			paint(g);
		}*/
		public void initilize()
		{
			i=0;

			t=new BubbleThread[10];
			b=new boolean[10];
			Arrays.fill(b,false);
			ch=new char[10];
			rad=new Random();
			ch[i]=(char)(rad.nextInt(42)+48);
			r=rad.nextInt(256);
			gg=rad.nextInt(256);
			bb=rad.nextInt(256);
			Image img=getImage(getDocumentBase(),"images/"+(rad.nextInt(8)+1)+"t.png");
			t[i]=new BubbleThread(rad.nextInt(1200),getHeight()-50,new Color(r,gg,bb),img,0,(5+2*i),this);
			btn.setVisible(false);
			setBackground(Color.black);
		}
		public void mouseClicked(MouseEvent e) 
		{
			if(mouseIn)
				start();
			
		}     
		public void mouseEntered(MouseEvent e){}
	 
		public void mouseExited(MouseEvent e) {}
	          
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseReleased(MouseEvent e) {
					

		}
		public void mouseDragged(MouseEvent me){}
		public void mouseMoved(MouseEvent e)
		{
			//getWidth()/2-100-20,getHeight()/2+100-30,250,40
			if(e.getX()>getWidth()/2-100-20&&e.getX()<(getWidth()/2-100-20+250))
			{
				if(e.getY()>getHeight()/2+100-30&&e.getY()<(getHeight()/2+100-30+40))
					mouseIn=true;
			}
			else
				mouseIn=false;
			/*if(e.getX()<290||e.getX()>400)
				mouseIn=false;
			if(e.getY()>320||e.getY()<360)
				mouseIn=false;*/
				repaint();
		}

		
	}
