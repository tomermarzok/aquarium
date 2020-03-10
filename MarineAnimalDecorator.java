/*
 * tomer marzok
 * 203396809
 * ashdod
 */

public class MarineAnimalDecorator implements MarineAnimal
{
	public MarineAnimal animal;
	
	public MarineAnimalDecorator(MarineAnimal animal)
	{
		this.animal=animal;
	}

	public void PaintFish() {
		// TODO Auto-generated method stub
		this.animal.PaintFish();
	}

}
