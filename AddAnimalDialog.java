/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class AddAnimalDialog extends JDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private Swimmable objectSwim;
	private JLabel type,color,hoSpeed,veSpeed;
	private JPanel DialogPanel=new JPanel();
	private AquaPanel panel;
	private JCheckBox fish= new JCheckBox("Fish");
	private JCheckBox jellyFish= new JCheckBox("Jelly-Fish");
	private String[] names = {"Fish","Jelly Fish"};  
	private String [] labelsnum={"1","2","3","4","5","6","7","8","9","10"};
	private JComboBox<String> cmb_AnimalsTypes; //ComboBox animals types 
	private JCheckBox red,green,black,orange,yellow;
	private JButton addButton,cancelButton;
	private JComboBox<String> veSpeedBox,hoSpeedBox;
	private JSlider sizeOfFish= new JSlider(20,320);
	private boolean flagColor=false;
	private int horSpeedInt,verSpeedInt,size;
	private Color col;
	private String[] index={"4","3","2","1"};
	private JComboBox<String> cmb_EatTime; //ComboBox animals types 




	public AddAnimalDialog(AquaPanel panel)
	{
		this.panel=panel;		
		setSize(500,500);		//size of the new panel
		setLocationRelativeTo(null);
		settingPanel(); //setting the panel of JDialog
		ListenerColors(); 		//add actionListeners to colors
		add(DialogPanel);//add the panel to north side of the frame
		DialogPanel.setVisible(true);
		setVisible(true);
	}

	public AddAnimalDialog(String type1,Color col,int size,int hor,int ver,AquaPanel panel,int EatTime)
	{
		this.panel=panel;		
		setSize(500,500);		//size of the new panel
		setLocationRelativeTo(null);

		type = new JLabel("type of fish:");
		DialogPanel.setLayout(new GridLayout(9,4,0,0));
		DialogPanel.add(type);			//add the text
		String[] name={"Fish"};
		String[] name1={"Jelly-Fish"};

		if(type1=="fish")
		{
			cmb_AnimalsTypes=new JComboBox<String>(name);
		}
		else
		{
			cmb_AnimalsTypes=new JComboBox<String>(name1);

		}
		DialogPanel.add(cmb_AnimalsTypes); 		//add combobox

		color = new JLabel("choose color:");	//color choose text
		color.setFont(new Font(null,1,12));	//size of text
		DialogPanel.add(color);	
		red= new JCheckBox("Red");
		red.setBackground(Color.red);	//create backgrount color to the button
		orange= new JCheckBox("Orange");
		orange.setBackground(Color.orange);
		black= new JCheckBox("Black");
		black.setBackground(Color.black);
		yellow= new JCheckBox("Yellow");
		yellow.setBackground(Color.yellow);
		green= new JCheckBox("Green");
		green.setBackground(Color.green);

		DialogPanel.add(orange);	//add all the colors
		DialogPanel.add(black);
		DialogPanel.add(yellow);
		DialogPanel.add(green);
		DialogPanel.add(red);
		//horizontal speed part->text+combo box+add
		hoSpeed = new JLabel(" choose horizontal speed:");
		hoSpeed.setFont(new Font(null,1,12));
		DialogPanel.add(hoSpeed,BorderLayout.SOUTH);
		hoSpeedBox = new JComboBox<String>(labelsnum);
		hoSpeedBox.setMaximumRowCount(10);
		hoSpeedBox.setSelectedIndex(hor-1);
		
		DialogPanel.add(hoSpeedBox);
		//vertical speed part->text+combo box +add
		veSpeed = new JLabel(" choose vertical speed:");
		hoSpeed.setFont(new Font(null,1,12));
		DialogPanel.add(veSpeed,BorderLayout.WEST);
		veSpeedBox = new JComboBox<String>(labelsnum);
		veSpeedBox.setMaximumRowCount(10);
		veSpeedBox.setSelectedIndex(ver-1);
		DialogPanel.add(veSpeedBox);
		//size of the fish, by slider 
		final JLabel sizeFish = new JLabel(" choose size of the fish:");
		sizeFish.setFont(new Font(null,1,12));
		DialogPanel.add(sizeFish);
		sizeOfFish.setMinorTickSpacing(1);
		sizeOfFish.setMajorTickSpacing(1);
		sizeOfFish.setPaintTicks(true);
		DialogPanel.add(sizeOfFish);
		sizeOfFish.setValue(size);
		sizeOfFish.addChangeListener(new ChangeListener()			//listiner when we move the slider
		{															//get the live size
			public void stateChanged(ChangeEvent event)
			{
				int current = sizeOfFish.getValue();
				sizeFish.setText(" choose size of the fish:   "+String.valueOf(current));
			}
		});				
		JLabel TimeEat = new JLabel(" choose time to eat");
		DialogPanel.add(TimeEat);
		cmb_EatTime = new JComboBox<String>(index);
		cmb_EatTime.setSelectedItem(EatTime);
		DialogPanel.add(cmb_EatTime);
	
		
		
		if(col==Color.red)
		{
			red.setSelected(true);
			orange.setEnabled(false);
			black.setEnabled(false);
			yellow.setEnabled(false);
			green.setEnabled(false);
		}
		if(col==Color.orange)
		{
			orange.setSelected(true);
			red.setEnabled(false);
			black.setEnabled(false);
			yellow.setEnabled(false);
			green.setEnabled(false);
		}
		if(col==Color.black)
		{
			black.setSelected(true);
			orange.setEnabled(false);
			red.setEnabled(false);
			yellow.setEnabled(false);
			green.setEnabled(false);
		}
		if(col==Color.yellow)
		{
			yellow.setSelected(true);
			orange.setEnabled(false);
			black.setEnabled(false);
			red.setEnabled(false);
			green.setEnabled(false);
		}
		if(col==Color.green)
		{
			green.setSelected(true);
			orange.setEnabled(false);
			black.setEnabled(false);
			yellow.setEnabled(false);
			red.setEnabled(false);
		}
		ListenerColors(); 		//add actionListeners to colors

		addButton=new JButton("add!");
		DialogPanel.add(addButton);
		cancelButton=new JButton("cancel!");
		DialogPanel.add(cancelButton);

		cancelButton.addActionListener(new ActionListener(){			//cancel option
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		addButton.addActionListener(this);

		add(DialogPanel);//add the panel to north side of the frame
		DialogPanel.setVisible(true);
		setVisible(true);
		

	}


	public void  settingPanel()
	{
		type = new JLabel("type of fish:");
		DialogPanel.setLayout(new GridLayout(9,4,0,0));
		DialogPanel.add(type);			//add the text
		cmb_AnimalsTypes=new JComboBox<String>(names);
		DialogPanel.add(cmb_AnimalsTypes); 		//add combobox

		color = new JLabel("choose color:");	//color choose text
		color.setFont(new Font(null,1,12));	//size of text
		DialogPanel.add(color);	
		red= new JCheckBox("Red");
		red.setBackground(Color.red);	//create backgrount color to the button
		orange= new JCheckBox("Orange");
		orange.setBackground(Color.orange);
		black= new JCheckBox("Black");
		black.setBackground(Color.black);
		yellow= new JCheckBox("Yellow");
		yellow.setBackground(Color.yellow);
		green= new JCheckBox("Green");
		green.setBackground(Color.green);

		DialogPanel.add(orange);	//add all the colors
		DialogPanel.add(black);
		DialogPanel.add(yellow);
		DialogPanel.add(green);
		DialogPanel.add(red);
		//horizontal speed part->text+combo box+add
		hoSpeed = new JLabel(" choose horizontal speed:");
		hoSpeed.setFont(new Font(null,1,12));
		DialogPanel.add(hoSpeed,BorderLayout.SOUTH);
		hoSpeedBox = new JComboBox<String>(labelsnum);
		hoSpeedBox.setMaximumRowCount(10);
		DialogPanel.add(hoSpeedBox);
		//vertical speed part->text+combo box +add
		veSpeed = new JLabel(" choose vertical speed:");
		hoSpeed.setFont(new Font(null,1,12));
		DialogPanel.add(veSpeed,BorderLayout.WEST);
		veSpeedBox = new JComboBox<String>(labelsnum);
		veSpeedBox.setMaximumRowCount(10);
		DialogPanel.add(veSpeedBox);
		//size of the fish, by slider 
		final JLabel sizeFish = new JLabel(" choose size of the fish:");
		sizeFish.setFont(new Font(null,1,12));
		DialogPanel.add(sizeFish);
		sizeOfFish.setMinorTickSpacing(1);
		sizeOfFish.setMajorTickSpacing(1);
		sizeOfFish.setPaintTicks(true);
		DialogPanel.add(sizeOfFish);


		sizeOfFish.addChangeListener(new ChangeListener()			//listiner when we move the slider
		{				
			//+ String.valueOf(current) //get the live size
			public void stateChanged(ChangeEvent event)
			{
				int current = sizeOfFish.getValue();
				sizeFish.setText(" choose size of the fish:"+String.valueOf(current));
			}
		});					//add and cancel button
		JLabel TimeEat = new JLabel(" choose time to eat");
		DialogPanel.add(TimeEat);
		cmb_EatTime = new JComboBox<String>(index);
		DialogPanel.add(cmb_EatTime);
		addButton=new JButton("add!");
		DialogPanel.add(addButton);
		cancelButton=new JButton("cancel!");
		DialogPanel.add(cancelButton);

		cancelButton.addActionListener(new ActionListener(){			//cancel option
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		addButton.addActionListener(this);
	}

	//listener func for the color


	public void ListenerColors()
	{
		orange.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if (orange.isSelected()) 
				{							//if we choose i lock all the other
					black.setEnabled(false);
					yellow.setEnabled(false);
					green.setEnabled(false);
					red.setEnabled(false);
				}
				else
				{							//if we cancel our choose i open all the option
					black.setEnabled(true);
					yellow.setEnabled(true);
					green.setEnabled(true);
					red.setEnabled(true);		
				}
			}
		});
		black.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if (black.isSelected()) 
				{
					orange.setEnabled(false);
					yellow.setEnabled(false);
					green.setEnabled(false);
					red.setEnabled(false);
				}
				else
				{
					orange.setEnabled(true);
					yellow.setEnabled(true);
					green.setEnabled(true);
					red.setEnabled(true);		
				}
			}
		});
		yellow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if (yellow.isSelected()) 
				{
					orange.setEnabled(false);
					black.setEnabled(false);
					green.setEnabled(false);
					red.setEnabled(false);
				}

				else
				{
					orange.setEnabled(true);
					black.setEnabled(true);
					green.setEnabled(true);
					red.setEnabled(true);		
				}
			}
		});
		green.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if (green.isSelected()) 
				{
					orange.setEnabled(false);
					black.setEnabled(false);
					yellow.setEnabled(false);
					red.setEnabled(false);
				}

				else
				{
					orange.setEnabled(true);
					black.setEnabled(true);
					yellow.setEnabled(true);
					red.setEnabled(true);		
				}
			}
		});
		red.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if (red.isSelected()) 
				{
					orange.setEnabled(false);
					black.setEnabled(false);
					yellow.setEnabled(false);
					green.setEnabled(false);
				}

				else
				{
					orange.setEnabled(true);
					black.setEnabled(true);
					yellow.setEnabled(true);
					green.setEnabled(true);		
				}
			}
		});
	}
	public void actionPerformed(ActionEvent e) 			//action performed when we add our choose
	{
		if(e.getSource()==addButton)
		{
			String animalType,horSpeed,verSpeed;
			
			if(cmb_AnimalsTypes.getSelectedItem().equals("Fish"))
				animalType="Fish";
			else animalType="Jelly-Fish";
			horSpeed= hoSpeedBox.getItemAt(hoSpeedBox.getSelectedIndex()); // get the selectedItem 
			verSpeed= veSpeedBox.getItemAt(veSpeedBox.getSelectedIndex()); // get the selectedItem 
			horSpeedInt=Integer.parseInt(horSpeed);
			verSpeedInt=Integer.parseInt(verSpeed);

			if (orange.isSelected()) 	//get the color
			{
				col=Color.orange;
				flagColor=true;
			}
			else if(black.isSelected())
			{
				col=Color.black;
				flagColor=true;
			}
			else if(yellow.isSelected())
			{
				col=Color.yellow;
				flagColor=true;
			}
			else if(green.isSelected())
			{
				col=Color.green;
				flagColor=true;
			}
			else if(red.isSelected())
			{
				col=Color.red;
				flagColor=true;
			}
			else
			{			//if we didnt have color! we most
				JOptionPane.showMessageDialog(this, "You need choose color!","Error!",JOptionPane.ERROR_MESSAGE);
			}

			if(flagColor==true)
			{
				setVisible(false);
				size=sizeOfFish.getValue();
				AbstractSeaFactory animal = new AnimalFactory(col,size, horSpeedInt, verSpeedInt, this.panel,Integer.valueOf(cmb_EatTime.getSelectedItem().toString()));
				SeaCreature swim = animal.produceSeaCreature(animalType);
				panel.addToAnimalHashSet(swim);
				repaint();
			}
		}
		if(flagColor==true)		//if we choose color and we finish to choose all
		{
			setVisible(false);
		}
	}

}
