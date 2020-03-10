/*
 * tomer marzok
 * 203396809
 * ashdod
 */

public class Hungry implements HungerState
{

	public void doAction(int horSpeed, int verSpeed, int[] x_y_front, int x_dir, int y_dir, AquaPanel panel) 
	{
		double v_old,k,v_hor_new,v_ver_new;
		v_old=Math.sqrt(horSpeed*horSpeed+verSpeed*verSpeed);
		if((x_y_front[0]-650)!=0)		//check if we div with ziro
		{
			k=Math.abs((x_y_front[1]-350)/(x_y_front[0]-650));
		}
		else k=1;
		v_hor_new=v_old/(Math.sqrt(k*k+1));
		v_ver_new=v_hor_new*k;

		if(x_y_front[0]>650)		//change dir of the fish  for the center
			x_dir=-1;
		else x_dir=1;
		if(x_y_front[1]>350)
			y_dir=-1;
		else y_dir=1;

		x_y_front[0]+= (int)v_hor_new*x_dir;
		x_y_front[1]+= (int)v_ver_new*y_dir;
	}

}
