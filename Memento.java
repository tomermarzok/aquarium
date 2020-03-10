import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import sun.security.util.Length;


public class Memento implements ActionListener
{
	private Color col;
	private int size;
	private int x;
	private int y;
	private int ver;
	private int hor;
	private int index1;
	public static Swimmable[] SwimArr;
	public static Immobile[] PlantArr;
	private JFrame MemoOption;
	private Swimmable[] arr;
	private Immobile[] arr2;
	private JComboBox<String> Cmb_Index;
	private JComboBox<String> Cmb_Index2;

	private JButton Save=new JButton("Save");
	private JButton Next=new JButton("Next");
	private JButton finish=new JButton("Finish");
	private AquaPanel panel;
	public int counter1=0, counter2=0;
	public static int Fcounter=0;
	public static int Pcounter=0;
	private String[] index;
	private String[] index2;

	public Memento( AquaPanel panel)
	{
		this.panel=panel;
		if(panel.setAnimals.size()<1 && panel.setPlants.size()<1)
		{
			JOptionPane.showMessageDialog(null, "you dont have what to Save! ","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{

			MemoOption=new JFrame();
			MemoOption.setSize(600,400);
			MemoOption.setLayout(new GridLayout(8,4));
			MemoOption.setLocationRelativeTo(null);
			JLabel type = new JLabel(" choose the fish do you wont to Save:");
			MemoOption.add(type);	
			Next.addActionListener(this);
			finish.addActionListener(this);
			for(SeaCreature s:panel.setAnimals)		//draw all the animals
			{

				counter1++;

			}
			for(SeaPlant s:panel.setPlants)
			{
				counter2++;
			}
			arr=new Swimmable[counter1];
			arr2=new Immobile[counter2];
			int i=0;
			for(SeaCreature s:panel.setAnimals)		//draw all the animals
			{
				JLabel type3 = new JLabel(i+1+") "+s.getAnimalName()+" size: " +s.getSize()+", color:"+s.getColor()+", verSpeed: "+s.getVerSpeed()+", horSpeed: "+s.getHorSpeed()+",X: "+s.GetX()+", Y: "+s.GetY());
				MemoOption.add(type3);
				arr[i]=(Swimmable) s;
				i++;
			}
			int j=0;
			for(SeaPlant s:panel.setPlants)
			{
				JLabel type3 = new JLabel(i+1+") "+s.GetName() +" size: " +s.GetSize()+", color: Green , x: "+s.GetX()+" , y: "+s.GetY());
				MemoOption.add(type3);
				arr2[j]=(Immobile) s;
				j++;
			}
			index=new String[counter1+counter2];

			for(int x=0;x<counter2+counter1;x++)
				index[x]=Integer.toString(x+1);

			Cmb_Index=new JComboBox<String>(index);
			MemoOption.add(Cmb_Index);
			MemoOption.add(Save);
			MemoOption.setVisible(true);
			Save.addActionListener(this);

			panel.repaint();


		}
	}




	public void MementoFish(Swimmable s,int index)//this is for fish
	{
		if(index!=0)
		{
			Swimmable[] temp=new Swimmable[index+1];
			int i=0;

			for(Swimmable sw:SwimArr)
			{
				temp[i]=sw;
				i++;
			}
			temp[i]=s;

			SwimArr = null;
			SwimArr=new Swimmable[index+1];
			int j=0;
			for(Swimmable swim:temp)
			{
				SwimArr[j]=swim;
				j++;
			}
		}
		else
		{
			SwimArr=new Swimmable[1];
			SwimArr[0]=s;
		}
		Fcounter++;


	}

	public void MementoPlant(Immobile im,int index)
	{
		if(index!=0)
		{
			Immobile[] temp=new Immobile[index+1];
			int i=0;
			for(Swimmable sw:SwimArr)
			{
				temp[i]=PlantArr[i];
				i++;
			}
			temp[i]=im;
			PlantArr=new Immobile[index+1];
			int j=0;
			for(Immobile swim:temp)
			{
				PlantArr[j]=swim;
				j++;
			}

		}
		else
		{
			PlantArr=new Immobile[1];
			PlantArr[0]=im;
		}
		Pcounter++;

	}
	public boolean CheckSize()
	{
		int i=0;
		for(SeaCreature s:panel.setAnimals)		//draw all the animals
		{

			i++;
		}
		for(SeaPlant s:panel.setPlants)
		{
			i++;
		}
		if (i==0)
		{
			return true;
		}
		return false;
	}

	public void Res()
	{
		if((Fcounter==0 &&  Pcounter==0))
		{
			JOptionPane.showMessageDialog(null, "you dont have what to Restore ! ","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(CheckSize()==true)
		{
			JOptionPane.showMessageDialog(null, "there's nothing in aquarium ! ","Error",JOptionPane.ERROR_MESSAGE);

		}
		else
		{
			MemoOption=new JFrame();
			MemoOption.setSize(600,400);
			MemoOption.setLayout(new GridLayout(10,4));
			MemoOption.setLocationRelativeTo(null);
			MemoOption.setVisible(true);
			JLabel type = new JLabel(" choose the fish do you wont to Restore:");
			MemoOption.add(type);
			int i=0;
			if( this.Fcounter!=0)
			{
				for(Swimmable s: SwimArr)
				{
					JLabel type3 = new JLabel(i+1+") "+s.getAnimalName()+" size: " +s.getSize()+", color:"+s.getColor()+", verSpeed: "+s.getVerSpeed()+", horSpeed: "+s.getHorSpeed()+",X: "+s.GetX()+", Y: "+s.GetY());
					MemoOption.add(type3);
					i++;
				}
			}
			if(this.Pcounter!=0)
			{
				for(Immobile im: PlantArr)
				{
					JLabel type3 = new JLabel(i+1+") "+im.GetName() +" size: " +im.GetSize()+", color: Green , x: "+im.GetX()+" , y: "+im.GetY());
					MemoOption.add(type3);
					i++;
				}

			}
			index=new String[Fcounter+Pcounter];
			for(int x=0;x<Pcounter+Fcounter;x++)
				index[x]=Integer.toString(x+1);

			Cmb_Index=new JComboBox<String>(index);
			MemoOption.add(Cmb_Index);
			MemoOption.add(Next);


		}
	}

	public Swimmable GetFish(int index)
	{
		return SwimArr[index];

	}
	public Immobile GetPlant(int index)
	{
		return PlantArr[index];
	}
	public Color getCol() {
		return col;
	}

	public int getSize() {
		return size;
	}

	public int GetX() {
		return x;
	}

	public int GetY() {
		return y;
	}

	public int GetVer() {
		return this.ver;
	}

	public int GetHor() {
		return this.hor;
	}

	public int GetFcounter()
	{
		return Fcounter;
	}
	public int GetPcounter()
	{
		return this.Pcounter;
	}

	public void FinishWin()
	{
		MemoOption.dispose();
		if(Cmb_Index.getSelectedIndex()<Fcounter)
		{
			DoRestore(arr[Cmb_Index2.getSelectedIndex()],SwimArr[Cmb_Index.getSelectedIndex()]);

		}
		else
		{
			DoRestore(arr2[Cmb_Index.getSelectedIndex()-counter1],PlantArr[Cmb_Index.getSelectedIndex()]);
		}

	}
	public void NextWin()
	{
		MemoOption.dispose();
		MemoOption=new JFrame();
		MemoOption.setSize(600,400);
		MemoOption.setLayout(new GridLayout(10,4));
		MemoOption.setLocationRelativeTo(null);
		MemoOption.setVisible(true);
		if(Cmb_Index.getSelectedIndex()<Fcounter)
		{
			JLabel type = new JLabel(" choose the destination fish");
			MemoOption.add(type);
			int i=0;
			for(Swimmable s:arr)
			{
				JLabel type3 = new JLabel(i+1+") "+s.getAnimalName()+" size: " +s.getSize()+", color:"+s.getColor()+", verSpeed: "+s.getVerSpeed()+", horSpeed: "+s.getHorSpeed()+",X: "+s.GetX()+", Y: "+s.GetY());
				MemoOption.add(type3);
				i++;
			}
			index2=new String[counter1];
			for(int x=0;x<counter1;x++)
				index2[x]=Integer.toString(x+1);
			Cmb_Index2=new JComboBox<String>(index2);
			MemoOption.add(Cmb_Index2);
		}
		else
		{
			int i=0;
			JLabel type = new JLabel(" choose the destination Plant");
			MemoOption.add(type);
			for(Immobile im:arr2)
			{
				JLabel type3 = new JLabel(i+1+") "+im.GetName() +" size: " +im.GetSize()+", color: Green , x: "+im.GetX()+" , y: "+im.GetY());
				MemoOption.add(type3);
				i++;
			}
			index2=new String[counter2];

			for(int x=0;x<counter2;x++)
				index2[x]=Integer.toString(x+1);

			Cmb_Index2=new JComboBox<String>(index2);
			MemoOption.add(Cmb_Index2);
		}

		MemoOption.add(finish);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==Next)
		{
			NextWin();
		}
		else if(e.getSource()==finish)
		{
			FinishWin();
		}
		else if(e.getSource()==Save)
		{

			MemoOption.setVisible(false);
			if(Cmb_Index.getSelectedIndex()<counter1)
			{
				MementoFish(arr[Cmb_Index.getSelectedIndex()],Fcounter);
			}
			else
			{
				MementoPlant(arr2[Cmb_Index.getSelectedIndex()-counter1],Pcounter);
			}

		}

	}

	public void DoRestore(Swimmable to,Swimmable from)
	{
		if(to.getAnimalName()==from.getAnimalName())
		{
			to.SetSize(from);
			to.SetVer(from);
			to.SetHor(from);
			to.SetColor(from);
			to.SetX(from);
			to.SetY(from);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "its not the same type of fish!! ","Error",JOptionPane.ERROR_MESSAGE);

		}


	}

	public void DoRestore(Immobile to,Immobile from)
	{
		if(!(to.GetName().equals(from.GetName())))
		{
			to.SetSize(from);

			to.SetX(from);
			to.SetY(from);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "its not the same type of plant!! ","Error",JOptionPane.ERROR_MESSAGE);

		}
	}


}
