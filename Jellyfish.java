/*
 * tomer marzok
 * 203396809
 * ashdod
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JColorChooser;
import javax.swing.JPanel;


public class Jellyfish extends Swimmable implements MarineAnimal
{
	private Color col;
	private int size;
	private int x_front;
	private int y_front;
	private int x_dir;
	private int y_dir;
	private AquaPanel panel;
	private Boolean suspend;
	private int counterEat;
	private CyclicBarrier barr;
	private boolean wonFlag,food;
	private int EatTime;
	private int eatTimeCounter;
	private HungerState state;

	public Jellyfish(Color col,int size,int hors,int vers, AquaPanel panel,int EatTime)
	{
		super(hors,vers);		//build the dad class
		this.col=col;
		this.size=size;
		this.x_front=1;
		this.y_front=1;
		this.x_dir=1;
		this.panel = panel;
		this.suspend = false;
		this.barr=null;
		this.wonFlag=false;
		this.food=false;
		this.EatTime=EatTime;
		this.start();
	}


	public void drawCreature(Graphics g)
	{    
		int numLegs;
		if(size<40)
		{
			numLegs = 5;
		}
		else if(size<80)
		{
			numLegs = 9;
		}
		else numLegs = 12;  
		g.setColor(col);
		g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);
		for(int i=0; i<numLegs; i++)
		{
			g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front, x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front+size/3);
		}  
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

	public boolean ifWon()
	{
		return this.wonFlag;		//if won return true else false
	}

	public String getAnimalName()
	{
		return "Jelly-Fish";		//return name of fish
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
		this.barr=b;	//set the barrier
	}

	public int getSize()
	{			//size of the fish
		return size;
	}

	public void eatInc()
	{		//counter of the eat time
		counterEat+=1;
	}

	public int getEatCount()
	{
		return counterEat;
	}

	public void setEat(boolean x)
	{
		this.food=x;
	}

	public String getColor()
	{			//get color
		return "("+col.getRed()+","+col.getGreen()+","+col.getBlue()+")";

	}


	public Color getColorCol() {
		// TODO Auto-generated method stub
		return this.col;
	}




	@Override
	public Jellyfish cloneJelly() {
		// TODO Auto-generated method stub
		return this;
	}


	@Override
	public Fish cloneFish() {
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

	@Override
	public int GetX()
	{
		// TODO Auto-generated method stub
		return this.x_front;
	}

	@Override
	public int GetY()
	{
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