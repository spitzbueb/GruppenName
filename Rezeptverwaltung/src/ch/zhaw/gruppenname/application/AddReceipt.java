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

import javax.swing.*;

public class AddReceipt{
	
	private JFrame frame;
	private JMenuBar menubar;
	private Container contentPane;
	
	public static void main(String[] args) {
		new AddReceipt();
	}
	
	public AddReceipt()
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
				//Klasse für Datenbankzugriff
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
		JTextField bewertung = new JTextField(30);
		bewertung.setBorder(BorderFactory.createLineBorder(Color.black));
		return bewertung;
	}
	
	private JTextArea vorgehenTextArea()
	{
		JTextArea vorgehen = new JTextArea(10,30);
		vorgehen.setBorder(BorderFactory.createLineBorder(Color.black));
		return vorgehen;
	}
	
	private JTextArea beschreibungTextArea()
	{
		JTextArea beschreibung = new JTextArea(10,30);
		beschreibung.setBorder(BorderFactory.createLineBorder(Color.black));
		return beschreibung;
	}
	
	private JTextField zutatenTextField()
	{
		JTextField zutaten = new JTextField(30);
		zutaten.setBorder(BorderFactory.createLineBorder(Color.black));
		return zutaten;
	}
	
	private JTextField nameTextField()
	{
		JTextField name = new JTextField(30);
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