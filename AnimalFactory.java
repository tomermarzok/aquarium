/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.Color;
import java.awt.Panel;


public class AnimalFactory extends AbstractSeaFactory
{
	Color c;
	int size;
	int hors;
	int vers;
	AquaPanel panel;
	int EatTime;
	
	public AnimalFactory(Color col, int size, int hors, int vers, AquaPanel p,int EatTime)
	{
		this.c = col;
		this.size = size;
		this.hors = hors;
		this.vers = vers;
		this.panel = p;
		this.EatTime=EatTime;
	}

	@Override
	public SeaCreature produceSeaCreature(String type)
	{
		if (type.equalsIgnoreCase("Fish")){
			return new Fish(this.c, size, hors, vers, panel,EatTime);
		}
		if (type.equalsIgnoreCase("Jelly-Fish")){
			return new Jellyfish(this.c, size, hors, vers, panel,EatTime);
		}
		return null;
	}

	@Override
	public SeaPlant produceSeaPlant(String type) {
		// TODO Auto-generated method stub
		return null;
	}
}
