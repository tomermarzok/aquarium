/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;


public class Laminaria extends Immobile 
{
	private Color color;
	private int size;
	private int x;
	private int y;
	private AquaPanel panel;
	private String name;

	
	public Laminaria(Color color,int size,int x,int y, AquaPanel panel,String name)
	{
		super(name);
		this.color=color;
		this.size=size;
		this.x=x;
		this.y=y;
		this.panel=panel;
		this.name=name;
	}
	
	public void drawCreature(Graphics g)
	{
		g.setColor(this.color);
		g.fillArc(x-size/20,y-size , size/10,size*4/5, 0, 360);
		g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.fillArc(x+size/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.drawLine(x, y, x, y-size/5);
		g.drawLine(x, y, x-size/10, y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);
		panel.repaint();

	}
	public String GetName()
	{
		return "Laminaria";
	}
	public int GetSize()
	{
		return this.size;
	}

	public int GetX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	public int GetY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	public void SetSize(Immobile i)
	{
		// TODO Auto-generated method stub
		this.size=i.GetSize();
		
	}
	public void SetX(Immobile i)
	{
		// TODO Auto-generated method stub
		this.x=i.GetX();
		
	}
	public void SetY(Immobile i)
	{
		// TODO Auto-generated method stub
		this.y=i.GetY();
		
	}
	

}
