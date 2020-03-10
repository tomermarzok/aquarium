/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;


public interface SeaCreature
{

	void drawCreature(Graphics g);
	void setEat(boolean b);
	boolean ifWon();
	String getAnimalName();
	String getColor();
	int getSize();
	int getHorSpeed();
	int getVerSpeed();
	int getEatCount();
	int GetX();
	int GetY();
	void setSuspend();
	void setResume();
	void setBarrier(CyclicBarrier barr); 
	Fish cloneFish();
	Jellyfish cloneJelly();
	Color getColorCol();
	void SetSize(SeaCreature x );
	void SetX(SeaCreature x);
	void SetY(SeaCreature x);
	void SetColor(SeaCreature x);
	void SetVer(SeaCreature x);
	void SetHor(SeaCreature x);
	int GetEatTime();
}
