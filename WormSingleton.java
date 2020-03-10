/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/*
 * tomer marzok
 * 203396809
 * ashdod
 */

public class WormSingleton {



	private static WormSingleton instance=null;

	public WormSingleton()
	{

	}


	public static WormSingleton getInstance()
	{
		if(instance==null)
		{
			instance=new WormSingleton();

		}
		return instance;
	}

	public void setSingleton()
	{
		this.instance=null;	
	}
	public void drawWorm(Graphics g)
	{
		
		Graphics2D g2=(Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.red);
		g2.drawArc(600, 345, 10, 10, 30, 210);
		g2.drawArc(600, 355, 10, 10, 180, 270);
		g2.setStroke(new BasicStroke(1));
	
	}



}
