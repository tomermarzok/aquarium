/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.Color;


public class PlantFactory extends AbstractSeaFactory
{
	Color c;
	int size;
	int x;
	int y;
	AquaPanel panel;
	
	public PlantFactory(Color col, int size, int x, int y, AquaPanel p)
	{
		this.c = col;
		this.size = size;
		this.x = x;
		this.y = y;
		this.panel = p;
	}

	@Override
	public SeaPlant produceSeaPlant(String type)
	{
		if (type.equalsIgnoreCase("Zostera"))
		{
			return new Zostera(this.c, size, x, y, panel,"Zostera");
		}
		if (type.equalsIgnoreCase("Laminaria"))
		{
			return new Laminaria(this.c, size, x, y, panel,"Laminaria");
		}
		return null;
	}

	@Override
	public SeaCreature produceSeaCreature(String type) {
		return null;
	}


}
