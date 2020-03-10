/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Fish extends Swimmable implements MarineAnimal
{
	private Color col;
	private int size;
	private int x_front;
	private int y_front;
	private int x_dir,y_dir;
	private AquaPanel panel;
	private Boolean suspend;
	private int counterEat;
	private CyclicBarrier barr;
	private boolean wonFlag,food;
	private int EatTime;
	private int eatTimeCounter;
	private HungerState state;




	public Fish(Color col,int size,int hors,int vers, AquaPanel panel,int EatTime)
	{
		super(hors,vers);			//build the dad class
		this.col=col;
		this.size=size;
		this.x_front=1;
		this.y_front=1;
		this.x_dir=1;	
		this.y_dir=1;
		this.panel = panel;
		this.suspend = false;
		this.barr=null;
		this.counterEat=0;	//for eat counter
		this.wonFlag=false;		//if we won
		this.EatTime=EatTime;
		state = new Satiated();
		eatTimeCounter = 0;
		this.start();
	}

	public void drawCreature(Graphics g) 
	{
		g.setColor(col);
		if(x_dir==1)// fish swims to right side
		{  // Body of fish
			g.fillOval(x_front - size, y_front - size/4, size, size/2); // Tail of fish  
			int[] x_t={x_front-size-size/4,x_front-size-size/4,x_front-size};
			int [] y_t = {y_front - size/4, y_front + size/4, y_front};
			Polygon t = new Polygon(x_t,y_t,3);
			g.fillPolygon(t);  
			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255- col.getBlue()));
			g2.fillOval(x_front-size/5, y_front-size/10, size/10, size/10);
			// Mouth of fish
			if(size>70)
			{
				g2.setStroke(new BasicStroke(3));
			}
			else if(size>30)
			{
				g2.setStroke(new BasicStroke(2));
			}
			else g2.setStroke(new BasicStroke(1));
			g2.drawLine(x_front, y_front, x_front-size/10, y_front+size/10);
			g2.setStroke(new BasicStroke(1));
		}
		else // fish swims to left side
		{  // Body of fish  
			g.fillOval(x_front, y_front - size/4, size, size/2);  

			// Tail of fish
			int[] x_t={x_front+size+size/4,x_front+size+size/4,x_front+size};
			int [] y_t = {y_front - size/4, y_front + size/4, y_front};
			Polygon t = new Polygon(x_t,y_t,3);
			g.fillPolygon(t); 
			// Eye of fish  
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-col.getBlue()));
			g2.fillOval(x_front+size/10, y_front-size/10, size/10, size/10);
			// Mouth of fish
			if(size>70)
			{
				g2.setStroke(new BasicStroke(3));
			}
			else if(size>30)
			{
				g2.setStroke(new BasicStroke(2));
			}
			else g2.setStroke(new BasicStroke(1));
			g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10);
			g2.setStroke(new BasicStroke(1));
		}		
		panel.repaint();
	}

	public void run() 
	{
		super.run();
		while(true)
		{

			try 
			{
				Thread.sleep(10);
				if(this.suspend)
				{
					synchronized(this)	//synchronized between the fishes
					{
						wait();
					}
				}
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			if(this.suspend==false)
			{
				if(eatTimeCounter == EatTime)
				{
					this.state = new Hungry();
				}
				if(panel.getWormFlag() == true && barr != null)
				{
					try 
					{
						barr.await();
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					} catch (BrokenBarrierException e) 
					{
						e.printStackTrace();
					}
				}
				
				if(panel.getWormFlag() && eatTimeCounter >= EatTime)		//if we have food ->change the speed and dir
				{
					int []x_y_front = {x_front, y_front};
					this.state.doAction(horSpeed,verSpeed,x_y_front, x_dir, y_dir, panel);
					x_front = x_y_front[0];
					y_front = x_y_front[1];
					if(((Math.abs(x_front-650)<=5)&&(Math.abs(y_front-350)<=5))) //if the fish won! and under 5 pixsel
					{
						panel.setWormFlag(false);	
						this.eatInc();	//add to counter
						this.wonFlag=false;	//there is no food now
						this.food=false;
						panel.repaint();
						eatTimeCounter = 0;
					}
				}
				else		//if there is no food
				{			//normal behavior and speed

					if(this.x_front > this.panel.getWidth()-50)	//if the fish in the end of the left side
					{
						this.horSpeed *=-1;
						if(this.x_dir == 1)		//change size
						{
							this.x_dir =-1;
							eatTimeCounter++;
							if(eatTimeCounter == EatTime)
								this.notifyObserver();
						}
					}

					if(this.x_front <= 0)	//if the fish in the start side change size
					{
						this.horSpeed *=-1;
						if(this.x_dir != 1)
						{
							this.x_dir =1;
							eatTimeCounter++;
							if(eatTimeCounter == EatTime)
								this.notifyObserver();
						}

					}
					if(this.y_front >= this.panel.getHeight()-50 || this.y_front <= 0)
					{			//if the fish at top or down
						this.verSpeed*=-1;
					}

					this.x_front+= this.horSpeed;
					this.y_front+=this.verSpeed;
					this.panel.repaint();
					synchronized(this)
					{
						while(this.suspend == true)
						{
							try 
							{
								this.wait();
							} 
							catch (InterruptedException e) 
							{
								e.printStackTrace();
							}
						}
					}
					
				}
			}
		}
	}

	public String getAnimalName() 
	{
		return "Fish";	//return name of fish
	}

	public void setSuspend() 
	{
		this.suspend = true;
	}

	public synchronized void setResume() 
	{
		this.suspend = false;
		notify();
	}

	public void setBarrier(CyclicBarrier b)
	{
		this.barr=b;
	}

	public int getSize() 
	{
		return size;	//get size
	}

	public void eatInc() 
	{
		this.counterEat+=1;
	}

	public int getEatCount() 
	{
		return this.counterEat;
	}

	public String getColor() 
	{			//color chack
		return "("+col.getRed()+","+col.getGreen()+","+col.getBlue()+")";
	}

	public boolean ifWon() {	//if won return true else false
		return this.wonFlag;
	}

	public void setEat(boolean x)
	{
		this.food=x;
	}
	public Fish cloneFish()//this function return the clone
	{
		return this;
	}
	public Color getColorCol()
	{
		return this.col;
	}

	@Override
	public Jellyfish cloneJelly() {
		// TODO Auto-generated method stub
		return null;
	}

	public void PaintFish()
	{
		Color col = null;
		col= JColorChooser.showDialog(null,"Choose the color you want", this.col);
		if(col!=null)
		{
			this.col=col;
		}
	}	


	public void SetColor(Color c)
	{
		this.col=c;

	
	}

	@Override
	public int GetX() {
		// TODO Auto-generated method stub
		return this.x_front;
	}

	@Override
	public int GetY() {
		// TODO Auto-generated method stub
		return this.y_front;
	}

	public void SetSize(SeaCreature x)
	{
		// TODO Auto-generated method stub
		this.size=x.getSize();
		
	}

	public void SetX(SeaCreature x)
	{
		// TODO Auto-generated method stub
		this.x_front=x.GetX();
	}

	public void SetY(SeaCreature x)
	{
		// TODO Auto-generated method stub
		this.y_front=x.GetY();
		
	}

	public void SetColor(SeaCreature x)
	{
		// TODO Auto-generated method stub
		this.col=x.getColorCol();
		
	}

	public void SetVer(SeaCreature x)
	{
		// TODO Auto-generated method stub
		this.verSpeed=x.getVerSpeed();
		
	}

	public void SetHor(SeaCreature x)
	{
		// TODO Auto-generated method stub
		this.horSpeed=x.getHorSpeed();
		
	}

	@Override
	public int GetEatTime() {
		// TODO Auto-generated method stub
		return this.EatTime;
	}

	public void notifyObserver() 
	{
		this.panel.GetNoticeFromObservable();	
	}
}
