package ch.zhaw.gruppenname.application;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

public class Search {
	private JFrame frame;
	private JMenuBar menubar;
	private Container contentPane;
	private JTextField field;
	private JButton searchButton;
	
	public static void main(String[] args) {
		new Search();
	}
	
	public Search()
	{
		frame = new JFrame("Suche");
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		
		JMenu SearchMenu = new JMenu("Suchen");
		
		JRadioButtonMenuItem recipe = new JRadioButtonMenuItem("Nach Titel");
		JRadioButtonMenuItem ingredients = new JRadioButtonMenuItem("Nach Zutaten");
		recipe.setSelected(true);
		ButtonGroup strategy = new ButtonGroup();
		
		strategy.add(recipe);
		strategy.add(ingredients);
		
		SearchMenu.add(recipe);
		SearchMenu.add(ingredients);
		
		menubar.add(SearchMenu);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,10,5,0);
		contentPane.add(new JLabel("Suche nach:"),c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 10;
		c.insets = new Insets(0,0,5,10);  
		contentPane.add(searchField(),c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0,10,5,0);
		contentPane.add(searchButton(),c);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private JButton searchButton()
	{
		searchButton = new JButton("Suche");
		return searchButton;
	}
	
	private JTextField searchField()
	{
		field = new JTextField(30);
		field.setBorder(BorderFactory.createLineBorder(Color.black));
		return field;
	}
	
	private JLabel title()
	{
		JLabel title = new JLabel("Rezept suchen");
		title.setFont(new Font("Arial",1,20));
		return title;
	}
}
