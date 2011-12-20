package ch.zhaw.gruppenname.application;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow {
	
	private JFrame frame;
	private JMenuBar menubar;
	private Container contentPane;
	//private JTextField name,bewertung,zutaten;
	//private JTextArea beschreibung,vorgehen;
	
	public static void main(String[] args) {
		new MainWindow();
	}
	
	public MainWindow()
	{
		frame = new JFrame("Rezepteverwaltung");
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		
		//Datei-Menü erstellen
		JMenu dateimenu = new JMenu("Datei");
		JMenu editmenu = new JMenu("Bearbeiten");
		
		JMenuItem exit = new JMenuItem("Schliessen");
		
		exit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				System.exit(0);
			}			
		});
		
		JMenuItem addingRecipe = new JMenuItem("Rezept erfassen");
		JMenuItem addingIngredients = new JMenuItem("Zutat erfassen");
		JMenuItem deleteRecipe = new JMenuItem("Rezept löschen");
		JMenuItem deleteIngredients = new JMenuItem("Zutat löschen");
		
		addingRecipe.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				AddRecipe.main(null);
			}
		});
		
		addingIngredients.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				AddIngredients.main(null);
			}
		});
		
		dateimenu.add(exit);
		editmenu.add(addingRecipe);
		editmenu.add(addingIngredients);
		editmenu.add(deleteRecipe);
		editmenu.add(deleteIngredients);
		
		menubar.add(dateimenu);
		menubar.add(editmenu);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = frame.getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,7,20,0);
		contentPane.add(title(),c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0,10,5,0);
		contentPane.add(dropdown(),c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0,0,5,10);  
		contentPane.add(load(),c);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public JButton load()
	{
		JButton load = new JButton("Laden");
		return load;
	}
	
	public JComboBox dropdown()
	{
		JComboBox dropdown = new JComboBox();
		dropdown.addItem("Test");
		return dropdown();
	}
	
	public JLabel title()
	{
		JLabel title = new JLabel("Rezeptverwaltung");
		title.setFont(new Font("Arial",1,20));
		return title;
	}
}
