/*
 * tomer marzok
 * 203396809
 * ashdod
 */ 
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.concurrent.CyclicBarrier;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;


public class AquaPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID=1L;
	private int eatCounter=0;
	private boolean wormFlag=false;
	private JButton addAnimal,addPlant,sleep,wakeUp,reset,food,info,exit,Duplicate,Decorator;
	public JPanel panelButton,panelInfo;
	private Graphics g;
	public static HashSet <SeaCreature> setAnimals=new HashSet<SeaCreature>();
	public static HashSet <SeaPlant> setPlants=new HashSet<SeaPlant>();
	private WormSingleton worm;
	private JTable table;
	JLabel picLabel;
	private int click;
	private Image img;
	private Boolean FishFlag=false,JellyFlag=false;
	DuplicateAnimal d;
	private JFrame DecoratorColor;
	private Swimmable[] arr;
	private JComboBox<String> Cmb_Index;
	private JButton Change=new JButton("Change Color");

	final JButton button = new JButton("");

	final JColorChooser colorChooser = new JColorChooser();


	////////////////////


	public AquaPanel()
	{
		super();
		setLayout(new BorderLayout());
		panelButton = new JPanel();
		panelButton.setLayout(new GridLayout());
		panelInfo=new JPanel();			//for the table
		panelInfo.setLayout(new GridLayout());
		panelInfo.setVisible(false); //we dont need to see it now
		click=0;		//for the table show
		add(panelInfo,BorderLayout.NORTH);
		//add the button of the down button
		addAnimal=new JButton("add animal");
		addPlant=new JButton("add plant");
		sleep=new JButton("sleep");
		wakeUp=new JButton("wake Up");
		reset=new JButton("Reset");
		food=new JButton("Food");
		info=new JButton("Info");
		exit=new JButton("Exit");
		Decorator=new JButton("Decorator");
		Duplicate=new JButton("Duplicate Animal");
		picLabel = new JLabel();
		add(picLabel);

		panelButton.add(addAnimal);
		panelButton.add(addPlant);
		panelButton.add(Duplicate);
		panelButton.add(Decorator);
		panelButton.add(sleep);
		panelButton.add(wakeUp);
		panelButton.add(reset);
		panelButton.add(food);
		panelButton.add(info);
		panelButton.add(exit);
		//add the choose click option
		addAnimal.addActionListener(this);
		addPlant.addActionListener(this);
		sleep.addActionListener(this);
		wakeUp.addActionListener(this);
		reset.addActionListener(this);
		food.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);
		Duplicate.addActionListener(this);
		Decorator.addActionListener(this);
		Change.addActionListener(this);

		this.add(panelButton,BorderLayout.SOUTH);
	}




	public void backGraound(String s)		//set the image on background or cancl

	{
		if (s=="image")
		{
			ImageIcon i = new ImageIcon("aquarium_background.jpg");
			img = i.getImage();
		}
		else img=null;
	}

	public void paintComponent(Graphics g)		
	{ 
		super.paintComponent(g);

		if (img != null)// if we need the image background
		{
			g.drawImage(img,0,0,1300,650,this);		
		}
		if(wormFlag==true)	//if we have a worm/food
		{			
			worm.drawWorm(g);
		}
		for(SeaPlant s: setPlants)
		{
			s.drawCreature(g);
		}
		for(SeaCreature s:setAnimals)		//draw all the animals
		{
			s.drawCreature(g);
			if(wormFlag==true)	//if there is a food we set in all the fish -> have a food
			{
				s.setEat(true);
			}
			else if(wormFlag==false)		//cancl the food
			{
				s.setEat(false);
			}
			else if(s.ifWon()==true)		//if somebody won ->there is no food now
			{
				s.setEat(false);
				wormFlag=false;
			}
		}
		repaint();
	}
	public void tableInfo()
	{			//create the table
		String[] colValue={"Animal","Color","Size","Hor speed","Ver speed","Eat counter"};	//the column value name
		String name,color,size,horSpeed,verSpeed,counter;	//for the details of fish
		DefaultTableModel model=new DefaultTableModel();	//defualt table
		for (int i=0;i<colValue.length;i++)		//set the col value
		{
			model.addColumn(colValue[i]);
		}

		for (SeaCreature s : setAnimals)		//loop all over the fish and put a string value in the model row
		{
			name=s.getAnimalName();
			color=s.getColor();
			size=String.valueOf(s.getSize());
			horSpeed=String.valueOf(s.getHorSpeed());
			verSpeed=String.valueOf(s.getVerSpeed());
			counter=String.valueOf(s.getEatCount());
			//eatCounter+=s.getEatCount();		//counter eat for the total
			String[] row={name,color,size,horSpeed,verSpeed,counter};
			model.addRow(row);		//add the row

		}
		String[] countRow={"Total","","","","",String.valueOf(eatCounter)}; //add total row
		model.addRow(countRow);

		table=new JTable(model);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panelInfo.add(scrollPane);		//add to the panel info
		add(panelInfo);	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit)
		{
			System.exit(0);			//for exit
		}
		else if (e.getSource()==addAnimal)
		{
			AddAnimalDialog a=new AddAnimalDialog(this);
		}
		else if(e.getSource()==addPlant)
		{
			AddPlantDialog b=new AddPlantDialog(this);
		}
		else if(e.getSource()==Duplicate)
		{
			if (setAnimals.size()>=1)
			{
				for(SeaCreature s:setAnimals)		//draw all the animals
				{
					if(s.getAnimalName()=="Fish")
					{
						FishFlag=true;
					}
					if(s.getAnimalName()=="Jelly-Fish")
					{
						JellyFlag=true;
					}
				}
				if(FishFlag==false && JellyFlag==false)
				{
					JOptionPane.showMessageDialog(this, "There is no any type of animal to duplicate","Error!",JOptionPane.ERROR_MESSAGE);

				}
				else
				{
					d = new DuplicateAnimal(this);
				}
			} else JOptionPane.showMessageDialog(this, "There is no any type of animal to duplicate","Error!",JOptionPane.ERROR_MESSAGE);

		}
		else if(e.getSource()==Decorator)
		{
			if(this.setAnimals.size()!=0)
			{

				DecoratorColor=new JFrame();
				DecoratorColor.setSize(400,400);
				DecoratorColor.setLayout(new GridLayout(8,4));
				DecoratorColor.setLocationRelativeTo(null);
				DecoratorColor.setVisible(true);

				JLabel type = new JLabel(" choose the fish you wont to Change Color:");
				DecoratorColor.add(type);	
				int counter=0;
				for(SeaCreature s:setAnimals)		//draw all the animals
				{

					counter++;

				}
				arr=new Swimmable[counter];
				int i=0;
				for(SeaCreature s:setAnimals)		//draw all the animals
				{

					JLabel type3 = new JLabel(i+1+") "+s.getAnimalName()+" size: " +s.getSize()+", color:"+s.getColor()+", verSpeed: "+s.getVerSpeed()+", horSpeed: "+s.getHorSpeed());
					DecoratorColor.add(type3);
					arr[i]=(Swimmable) s;
					i++;

				}
				String[] index;
				index=new String[counter];
				for(int j=0;j<counter;j++)
					index[j]=Integer.toString(j+1);

				Cmb_Index=new JComboBox<String>(index);

				DecoratorColor.add(Cmb_Index);
				DecoratorColor.add(Change);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "there is no animal!! ","Error",JOptionPane.ERROR_MESSAGE);

			}

		}
		else if (e.getSource()==sleep)
		{
			for(SeaCreature swim:setAnimals)		//set a sleep all the animals
			{
				swim.setSuspend();
			}
		}
		else if(e.getSource()==Change)
		{
			DecoratorColor.setVisible(false);

			MarineAnimalDecorator m= new MarineAnimalDecorator(arr[Cmb_Index.getSelectedIndex()]);
			m.PaintFish();
			return;

		}
		else if (e.getSource()==wakeUp)
		{
			for(SeaCreature s:setAnimals)		//wake up  all the animal
			{
				s.setResume();
			}
		}
		else if (e.getSource()==reset)
		{
			reset();	//clear the screen
		}
		else if (e.getSource()==food)
		{

			if(setAnimals.size()!=0)
			{
				if (worm!=null && wormFlag==true)
				{
					JOptionPane.showMessageDialog(this, "there is alredy one worm! ","Error",JOptionPane.ERROR_MESSAGE);
				}
				else{
					worm=WormSingleton.getInstance();

					if(worm!=null)
					{
						wormFlag=true;		//if we have food set barrier by the num of fish
						//worm.setSingleton();

					}
					else
					{
						JOptionPane.showMessageDialog(this, "there is alredy one worm! ","Error",JOptionPane.ERROR_MESSAGE);

					}

					CyclicBarrier barr=new CyclicBarrier(setAnimals.size()) ;
					for(SeaCreature s:setAnimals)
					{
						s.setBarrier(barr);
					}
					repaint();
				}
			}
			else		//if there is no fish we cant give a food
			{
				JOptionPane.showMessageDialog(this, "its empty aquarium! ","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource()==info)	//for table info
		{
			//show if we have odd click
			click+=1;
			if(click%2==1)
			{
				tableInfo();
				panelInfo.setVisible(true);
			}
			else		//even remove to the main screen with fish
			{
				panelInfo.removeAll();
				panelInfo.setVisible(false);
			}
		}
	}


	public void addToAnimalHashSet(SeaCreature obj)
	{
		if(setAnimals.size()<5)			//add a fish to the hash set
		{
			setAnimals.add(obj);
			repaint();
		}
		else
			JOptionPane.showMessageDialog(this, "You can't add more then 5 animals","Error!",JOptionPane.ERROR_MESSAGE);	
	}
	public void addToPlantsHashSet(SeaPlant swim)
	{
		if(setPlants.size()<5)			//add a fish to the hash set
		{
			setPlants.add(swim);
			repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(this, "You can't add more then 5 plants","Error!",JOptionPane.ERROR_MESSAGE);	
		}
	}


	public void reset()
	{						
		setAnimals.clear();		//clear the hashset and the food
		wormFlag=false;
		setPlants.clear();
		this.backGraound(null);
		this.setBackground(null);
		

	}


	public void AddImage(Image image)
	{
		if(picLabel != null)			//add the image to the screen
		{	
			picLabel.setIcon(new ImageIcon(image.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(),Image.SCALE_SMOOTH)));
			add(picLabel);
		}
		repaint();
	}
	public void RemoveImage()		//remove the image
	{
		remove(picLabel);
		repaint();
	}
	public boolean getWormFlag()
	{
		return this.wormFlag;	//if there is a food
	}
	public void setWormFlag(boolean x)
	{
		this.wormFlag=x;	//set if we have a food
	}

	public void GetNoticeFromObservable()
	{
		
		if (wormFlag != true)
		{
			Thread message = new Thread(new Runnable()
			{
				public void run() 
				{
					JOptionPane.showMessageDialog(null, "Feed me!","I need Foood!",JOptionPane.ERROR_MESSAGE);	
				}		
			});
			message.start();
		}
	}
	
}




