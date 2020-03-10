/*
 * tomer marzok
 * 203396809
 * ashdod
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.CyclicBarrier;


public class Zostera extends Immobile 
{
	private Color color;
	private int size;
	private int x;
	private int y;
	private AquaPanel panel;
	private String name;

	
	public Zostera(Color color,int size,int x,int y, AquaPanel panel,String name)
	{
		super(name);
		this.color=color;
		this.size=size;
		this.x=x;
		this.y=y;
		this.panel=panel;

	}
	public void drawCreature(Graphics g)
	{
		Graphics2D g2=(Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(color);
		g.drawLine(x, y, x, y-size);
		g.drawLine(x-2, y, x-10, y-size*9/10);
		g.drawLine(x+2, y, x+10, y-size*9/10);
		g.drawLine(x-4, y, x-20, y-size*4/5);
		g.drawLine(x+4, y, x+20, y-size*4/5);
		g.drawLine(x-6, y, x-30, y-size*7/10);
		g.drawLine(x+6, y, x+30, y-size*7/10);
		g.drawLine(x-8, y, x-40, y-size*4/7);
		g.drawLine(x+8, y, x+40, y-size*4/7);
		g2.setStroke(new BasicStroke(1));
		panel.repaint();

	}
	public String GetName()
	{
		return "Zostera";
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
