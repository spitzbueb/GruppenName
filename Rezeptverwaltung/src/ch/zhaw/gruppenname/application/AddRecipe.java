/**
 * Klasse um ein Rezept in Datenbank aufzunehmen
 * 
 * @author Philipp Schalcher
 * @version 1
 */

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
//----------------------------------------------------------------------------------------------
	/**
	 * Main-Klasse für die Ausführung. 
	 */
	public static void main(String[] args) {
		new AddRecipe();
	}
//----------------------------------------------------------------------------------------------	
	/**
	 * Konstruktor: Zeichnet das Eingabefenster
	 */
	public AddRecipe()
	{
		frame = new JFrame("Rezept erfassen");
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		
		//Datei-Menü erstellen
		JMenu dateiMenu = new JMenu("Datei");
		
		JMenuItem exit = new JMenuItem("Beenden");
		JMenuItem safe = new JMenuItem("Speichern");
		
		//Definiere die Aktion wenn das Fenster über Datei>Beenden geschlossen wird
		exit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Fenster verschwinden lassen, im Gegensatz zu System.exit(0) wird nicht das komplette Programm beendet
				frame.dispose();
			}
		});
		
		//Aktion für Speicherung der eingetragenen Daten
		safe.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				//temporäres Datenbank-Objekt erstellen, dann die eingetragenen Felder auslesen,
				// in Datenbank eintragen
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
				
				//Zwischentabelle abfüllen mit RezeptID und ZutatenID
				add.matchReceipt_Ingredients(name.getText(),zutaten.getText());

				frame.dispose();
			}
		});

		dateiMenu.add(safe);
		dateiMenu.add(exit);
		
		menubar.add(dateiMenu);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Zeichne das komplette Fenster
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
	
//------------------------------------------------------------------------------------------------
	/**
	 * Erstellt das Textfeld für die Bewertung
	 * @return JTextField
	 */
	private JTextField bewertungTextField()
	{
		bewertung = new JTextField(30);
		bewertung.setBorder(BorderFactory.createLineBorder(Color.black));
		return bewertung;
	}
//------------------------------------------------------------------------------------------------
	/**
	 * Erstellt das Textfeld für die Eingabe des Vorgehens
	 * @return JTextArea
	 */
	private JTextArea vorgehenTextArea()
	{
		vorgehen = new JTextArea(10,30);
		vorgehen.setBorder(BorderFactory.createLineBorder(Color.black));
		return vorgehen;
	}
//------------------------------------------------------------------------------------------------
	/**
	 * Erstellt das Textfeld für die Eingabe der Beschreibung
	 * @return JTextArea
	 */
	private JTextArea beschreibungTextArea()
	{
		beschreibung = new JTextArea(10,30);
		beschreibung.setBorder(BorderFactory.createLineBorder(Color.black));
		return beschreibung;
	}
//------------------------------------------------------------------------------------------------
	/**
	 * Erstellt das Textfeld für die Zutaten.
	 * Zutaten werden als komma-getrennte Liste eingegeben
	 * @return JTextField
	 */
	private JTextField zutatenTextField()
	{
		zutaten = new JTextField(30);
		zutaten.setBorder(BorderFactory.createLineBorder(Color.black));
		return zutaten;
	}
//------------------------------------------------------------------------------------------------
	/**
	 * Erstellt das Textfeld um den Namen des Rezeptes einzugeben
	 * @return JTextField
	 */
	private JTextField nameTextField()
	{
		name = new JTextField(30);
		name.setBorder(BorderFactory.createLineBorder(Color.black));
		return name;
	}
//------------------------------------------------------------------------------------------------	
	/**
	 * Erstellt den Titel des GUI.
	 * @return JLabel
	 */
	private JLabel title()
	{
		
		JLabel title = new JLabel("Rezept aufnehmen");
		title.setFont(new Font("Arial",1,20));
		return title;
	}
//-------------------------------------------------------------------------------------------------
}
