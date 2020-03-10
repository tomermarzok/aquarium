/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;


public abstract class Swimmable extends Thread implements SeaCreature ,MarineAnimal, Observer
{								//build class 
	protected int horSpeed;
	protected int verSpeed;
	public Swimmable()
	{
		horSpeed = 0;
		verSpeed = 0;
	}
	public Swimmable(int hor, int ver)
	{
		horSpeed = hor;
		verSpeed = ver;
	}
	public int getHorSpeed()
	{
		return (int) Math.abs (horSpeed);
	}
	public int getVerSpeed()
	{
		return (int) Math.abs (verSpeed);
	}
	public void setHorSpeed(int hor)
	{
		horSpeed  = hor;
	}
	public void setVerSpeed(int ver)
	{
		verSpeed  = ver;
	}  
	abstract public String getAnimalName();
	abstract public void drawCreature(Graphics g);
	abstract public void setSuspend();
	abstract public void setResume();
	abstract public void setBarrier(CyclicBarrier b);
	abstract public int getSize();
	abstract public void eatInc();
	abstract public int getEatCount();
	abstract public String getColor(); 
	abstract public void setEat(boolean x);	//func if we have a food
	abstract public boolean ifWon();		//func if some fish won
	abstract public Fish cloneFish();
	abstract public Jellyfish cloneJelly();
	abstract public int GetX();
	abstract public int GetY();
	abstract public int GetEatTime();



}
