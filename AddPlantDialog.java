/*
 * tomer marzok
 * 203396809
 * ashdod
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class AddPlantDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private SeaCreature objectPlant;
	private JLabel type,x_coord,y_coord,size;
	private JPanel DialogPanel=new JPanel();
	private AquaPanel panel;
	private JCheckBox laminaria= new JCheckBox("Laminaria");
	private JCheckBox zostera= new JCheckBox("Zostera");
	private String[] names = {"Laminaria","Zostera"};  
	private JComboBox<String> cmb_plantsTypes; //ComboBox animals types 
	private JButton addButton,cancelButton;
	private JSlider x_Coordinate = new JSlider(0,1200);
	private JSlider y_Coordinate = new JSlider(0,700);
	private JSlider sizeOfPlant= new JSlider(20,320);
	
	public AddPlantDialog(AquaPanel panel)
	{
		this.panel=panel;		
		setSize(500,500);		//size of the new panel
		setLocationRelativeTo(null);
		settingPanel(); //setting the panel of JDialog
		add(DialogPanel);//add the panel to north side of the frame
		DialogPanel.setVisible(true);
		setVisible(true);
	}

	public void  settingPanel()
	{
		type = new JLabel("type of plant:");
		DialogPanel.setLayout(new GridLayout(6,2,0,0));
		DialogPanel.add(type);	
		//add the text
		cmb_plantsTypes=new JComboBox<String>(names);
		DialogPanel.add(cmb_plantsTypes); 		//add combobox
	
		final JLabel sizePlant = new JLabel(" choose size of the plant:    170");
		sizePlant.setFont(new Font(null,1,12));
		DialogPanel.add(sizePlant);
		sizeOfPlant.setMinorTickSpacing(1);
		sizeOfPlant.setMajorTickSpacing(1);
		sizeOfPlant.setPaintTicks(true);
		DialogPanel.add(sizeOfPlant);
				
		
		sizeOfPlant.addChangeListener(new ChangeListener()			//listiner when we move the slider
		{															//get the live size
			public void stateChanged(ChangeEvent event)
			{
				int current = sizeOfPlant.getValue();
				sizePlant.setText(" choose size of the plant:    "+ String.valueOf(current));
			}
		});	
		final JLabel x= new JLabel(" choose x Coordinate :    350");
		sizePlant.setFont(new Font(null,1,12));
		DialogPanel.add(x);
		x_Coordinate.setMinorTickSpacing(1);
		x_Coordinate.setMajorTickSpacing(1);
		x_Coordinate.setPaintTicks(true);
		DialogPanel.add(x_Coordinate);
		x_Coordinate.addChangeListener(new ChangeListener()			//listiner when we move the slider
		{															//get the live size
			public void stateChanged(ChangeEvent event)
			{
				int current = x_Coordinate.getValue();
				x.setText(" choose x Coordinate :    "+ String.valueOf(current));
			}
		});	
		
		final JLabel y= new JLabel(" choose y Coordinate :    350");
		sizePlant.setFont(new Font(null,1,12));
		DialogPanel.add(y);
		y_Coordinate.setMinorTickSpacing(1);
		y_Coordinate.setMajorTickSpacing(1);
		y_Coordinate.setPaintTicks(true);
		DialogPanel.add(y_Coordinate);
		y_Coordinate.addChangeListener(new ChangeListener()			//listiner when we move the slider
		{															//get the live size
			public void stateChanged(ChangeEvent event)
			{
				int current = y_Coordinate.getValue();
				y.setText(" choose y Coordinate :    "+ String.valueOf(current));
			}
		});	
		final JLabel label2 = new JLabel();
		DialogPanel.add(label2);
		final JLabel label3 = new JLabel();
		DialogPanel.add(label3);
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

	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource()==addButton)
		{
			setVisible(false);

			String plantType;
			int x,y,size;
			plantType=cmb_plantsTypes.getItemAt(cmb_plantsTypes.getSelectedIndex()); // get the selectedItem 
			size=sizeOfPlant.getValue();
			x=x_Coordinate.getValue();
			y=y_Coordinate.getValue();
			AbstractSeaFactory plant = new PlantFactory(Color.green, size, x, y, this.panel);
			SeaPlant swim = plant.produceSeaPlant(plantType);
			panel.addToPlantsHashSet(swim);
			repaint();
			

		}

	}

}
