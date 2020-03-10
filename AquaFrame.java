/*
 * tomer marzok
 * 203396809
 * ashdod
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AquaFrame extends JFrame
{
	private static final long serialVersionUID=1L;
	private JFrame aquaFrame;
	private AquaPanel panel;
	Image tileImage;
	JLabel picLabel;
	private JFrame MemoOption;
	private Memento m;







	public static void main(String[] args)
	{
		AquaFrame frame=new AquaFrame();		//create new aqua frame
		frame.BuildAquaFrame();
	}

	public AquaFrame()
	{
		setTitle("My Aquarium- tomer marzok 203396809 ");		//set title to the main frame
		setLayout(new BorderLayout());							//by location
		//aquaFrame.setLayout(new BorderLayout());
		panel=new AquaPanel();
		setSize(1300,700);		//the frame size
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(panel);		//add this pane
		picLabel = new JLabel();		//for the pic background


	}
	
	public void BuildAquaFrame()
	{									//build the main frame
		JMenuBar topMenu=new JMenuBar();	//for the button at the top
		setJMenuBar(topMenu);
		JMenu File=new JMenu("File");		//boutton at top
		JMenu Background=new JMenu("Background");
		JMenu Help=new JMenu("Help");
		JMenu Memento=new JMenu("Memento");
		topMenu.add(File);
		topMenu.add(Background);
		topMenu.add(Memento);
		topMenu.add(Help);
		JMenuItem ExitItem=new JMenuItem("Exit");			//build item of menu bar button
		JMenuItem ImageItem=new JMenuItem("Image");
		JMenuItem BlueItem=new JMenuItem("Blue");
		JMenuItem NoneItem=new JMenuItem("None");
		JMenuItem HelpItem=new JMenuItem("help");
		JMenuItem SaveObj=new JMenuItem("Save Object State"); 
		JMenuItem ResObj=new JMenuItem("Restore Object State");

		File.add(ExitItem);
		ExitItem.addActionListener(new ActionListener()
		{						//if we wont to exit
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		Memento.add(SaveObj);
		SaveObj.addActionListener(new ActionListener()
		{											//if we choose image
			public void actionPerformed(ActionEvent e)
			{
				m =new Memento(panel);
			}
		});
		Memento.add(ResObj);
		ResObj.addActionListener(new ActionListener()
		{											//if we choose image
			public void actionPerformed(ActionEvent e)
			{
				m.Res();
			}
		});
		Background.add(ImageItem);
		ImageItem.addActionListener(new ActionListener()
		{											//if we choose image
			public void actionPerformed(ActionEvent e)
			{
				panel.backGraound("image");
				panel.validate();
			}	
		});
		Background.add(BlueItem);
		BlueItem.addActionListener(new ActionListener()	//for blue backgound
		{
			public void actionPerformed(ActionEvent e)
			{
				panel.backGraound("");	//help func for cancel/add the imahe
				panel.removeAll();
				panel.setBackground(Color.BLUE);
				panel.add(panel.panelButton, BorderLayout.SOUTH);	//add the button again
				panel.validate();
				panel.repaint();	//paint again all
			}
		});
		Background.add(NoneItem);
		NoneItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				panel.backGraound("");
				panel.removeAll();
				panel.setBackground(null);
				panel.add(panel.panelButton, BorderLayout.SOUTH);
				panel.validate();
				panel.repaint();	
			}
		});
		Help.add(HelpItem);
		HelpItem.addActionListener(new ActionListener()
		{				//show the help massage
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Home Work 3 \n GUI@ Therads");
			}
		});
		setVisible(true);   //show all
	}
}
