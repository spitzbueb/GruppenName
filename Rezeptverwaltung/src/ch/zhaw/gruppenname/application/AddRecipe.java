package ch.zhaw.gruppenname.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.SQLException;
import ch.zhaw.gruppenname.database.Database;

import javax.swing.*;

import ch.zhaw.gruppenname.database.Database;

public class AddRecipe{
	
	private JFrame frame;
	private JMenuBar menubar;
	private Container contentPane;
	private JTextField name,bewertung,zutaten;
	private JTextArea beschreibung,vorgehen;
	
	public static void main(String[] args) {
		new AddRecipe();
	}
	
	public AddRecipe()
	{
		frame = new JFrame("Rezept erfassen");
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		
		//Datei-Menü
		JMenu dateiMenu = new JMenu("Datei");
		
		JMenuItem exit = new JMenuItem("Beenden");
		JMenuItem safe = new JMenuItem("Speichern");
		
		exit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		safe.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				Database add = new Database();
				try {
					add.addReceipt(name.getText(), beschreibung.getText(), "author", Integer.parseInt(bewertung.getText()), vorgehen.getText());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				add.matchReceipt_Ingredients(name.getText(),zutaten.getText());

				System.exit(0);
			}
		});

		dateiMenu.add(safe);
		dateiMenu.add(exit);
		
		menubar.add(dateiMenu);
		
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
		contentPane.add(new JLabel("Name:"),c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 10;
		c.insets = new Insets(0,0,5,10);  
		contentPane.add(nameTextField(),c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0,10,5,0);
		contentPane.add(new JLabel("Zutaten:"),c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0,0,5,10);
		contentPane.add(zutatenTextField(),c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0,10,5,0);
		contentPane.add(new JLabel("Beschreibung:"),c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0,0,5,10);
		contentPane.add(beschreibungTextArea(),c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(0,10,5,0);
		contentPane.add(new JLabel("Vorgehen:"),c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(0,0,5,10);
		contentPane.add(vorgehenTextArea(),c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(0,10,5,0);
		contentPane.add(new JLabel("Bewertung:"),c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(0,0,5,10);
		contentPane.add(bewertungTextField(),c);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private JTextField bewertungTextField()
	{
		bewertung = new JTextField(30);
		bewertung.setBorder(BorderFactory.createLineBorder(Color.black));
		return bewertung;
	}
	
	private JTextArea vorgehenTextArea()
	{
		vorgehen = new JTextArea(10,30);
		vorgehen.setBorder(BorderFactory.createLineBorder(Color.black));
		return vorgehen;
	}
	
	private JTextArea beschreibungTextArea()
	{
		beschreibung = new JTextArea(10,30);
		beschreibung.setBorder(BorderFactory.createLineBorder(Color.black));
		return beschreibung;
	}
	
	private JTextField zutatenTextField()
	{
		zutaten = new JTextField(30);
		zutaten.setBorder(BorderFactory.createLineBorder(Color.black));
		return zutaten;
	}
	
	private JTextField nameTextField()
	{
		name = new JTextField(30);
		name.setBorder(BorderFactory.createLineBorder(Color.black));
		return name;
	}
	
	private JLabel title()
	{
		
		JLabel title = new JLabel("Rezept aufnehmen");
		title.setFont(new Font("Arial",1,20));
		return title;
	}
}
