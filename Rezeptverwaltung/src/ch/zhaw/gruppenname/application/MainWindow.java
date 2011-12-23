package ch.zhaw.gruppenname.application;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ch.zhaw.gruppenname.database.Database;

public class MainWindow {
	
	private JFrame frame;
	private JMenuBar menubar;
	private Container pane;
	private JComboBox dropdown;
	private JTextField ingredientsfield,votefield;
	private JTextArea descriptionfield,procedurefield;
	private JButton load;
	private ArrayList<String> allTitles;
	
	public static void main(String[] args) {
		new MainWindow();
	}
//---------------------------------------------------------------------------------------
	/**
	 * Konstruktor
	 */
	public MainWindow()
	{
		frame = new JFrame("Rezepteverwaltung");
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		
		Database db = new Database();				//Datenbank-Objekt für Operationen
		
		//Dropdownbox und Button werden generiert
		load = new JButton("Laden");
		dropdown = new JComboBox();
		
		//Dropdownbox abfüllen mit Werten aus Datenbank
		for (String string:db.getAllTitles())
		{
			dropdown.addItem(string);
		}
		
		//Funktion des Buttons definieren
		load.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//Schreibe die Werte in der Datenbank in die richtigen Felder
				Database temp = new Database();
				String tempstring = dropdown.getSelectedItem().toString();
				System.out.println(temp.getReceiptId("Pommes"));
			}
		});
		
		
		
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
		editmenu.add(deleteRecipe);
		editmenu.add(addingIngredients);
		editmenu.add(deleteIngredients);
		
		menubar.add(dateimenu);
		menubar.add(editmenu);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Zeichne das Fenster
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gdc = new GridBagConstraints();
		
		gdc.fill = GridBagConstraints.HORIZONTAL;
		gdc.anchor = GridBagConstraints.NORTH;
		gdc.gridx = 0;
		gdc.gridy = 0;
		gdc.insets = new Insets(5,7,20,0);
		pane.add(title(),gdc);
		
		gdc.gridx = 0;
		gdc.gridy = 1;
		gdc.insets = new Insets(0,10,5,0);
		pane.add(dropdown,gdc);
		
		gdc.gridx = 1;
		gdc.gridy = 1;
		gdc.insets = new Insets(0,0,5,10);
		pane.add(load,gdc);
		
		gdc.gridx = 0;
		gdc.gridy = 2;
		gdc.insets = new Insets(0,10,5,0);
		pane.add(new JLabel("Zutaten"),gdc);
		
		gdc.gridx = 1;
		gdc.gridy = 2;
		gdc.insets = new Insets(0,0,5,10);
		pane.add(ingredientsfield(),gdc);
		
		gdc.gridx = 0;
		gdc.gridy = 3;
		gdc.insets = new Insets(0,10,5,0);
		pane.add(new JLabel("Bewertung"),gdc);
		
		gdc.gridx = 1;
		gdc.gridy = 3;
		gdc.insets = new Insets(0,0,5,10);
		pane.add(votefield(),gdc);
		
		gdc.gridx = 0;
		gdc.gridy = 4;
		gdc.insets = new Insets(0,10,5,0);
		pane.add(new JLabel("Beschreibung"),gdc);
		
		gdc.gridx = 1;
		gdc.gridy = 4;
		gdc.insets = new Insets(0,0,5,10);
		pane.add(descriptionfield(),gdc);
		
		gdc.gridx = 0;
		gdc.gridy = 5;
		gdc.insets = new Insets(0,10,5,0);
		pane.add(new JLabel("Vorgehen"),gdc);
		
		gdc.gridx = 1;
		gdc.gridy = 5;
		gdc.insets = new Insets(0,0,5,10);
		pane.add(procedurefield(),gdc);
		
		
		frame.pack();
		frame.setVisible(true);
	}
//----------------------------------------------------------------------------------------
	/**
	 * Liesst aus der Datenbank die Zutatenliste aus und schreibt sie als komma-getrennte Liste
	 * in das Zutaten-Textfeld
	 * @param i
	 */
	public void setIngredientsField(int i)
	{
		Database temp = new Database();
		ArrayList<String> Stringlist = new ArrayList<String>();
		String liste = "";
		Stringlist = temp.getIngredients(i);
		
		for (String string : Stringlist)
		{
			liste = liste + "," + string;
		}
		
		ingredientsfield.setText(liste);
	}
//----------------------------------------------------------------------------------------
	/**
	 * Methode zeichnet gesperrte Textfeld für Anleitung
	 * @return JTextArea
	 */
	public JTextArea procedurefield()
	{
		procedurefield = new JTextArea(10,30);
		procedurefield.setEditable(false);
		return procedurefield;
	}
//----------------------------------------------------------------------------------------
	/**
	 * Methode zeichnet gesperrtes Textfeld für Beschreibung
	 * @return JTextArea
	 */
	public JTextArea descriptionfield()
	{
		descriptionfield = new JTextArea(10,30);
		descriptionfield.setEditable(false);
		return descriptionfield;
	}
//----------------------------------------------------------------------------------------
	/**
	 * Methode zeichnet gesperrtes Textfeld für Bewertung
	 * @return JTextField
	 */
	public JTextField votefield()
	{
		votefield = new JTextField(20);
		votefield.setEditable(false);
		return votefield;
	}
//----------------------------------------------------------------------------------------
	/**
	 * Methode zeichnet gesperrtes Textfeld für Zutaten
	 * @return JTextField
	 */
	public JTextField ingredientsfield()
	{
		ingredientsfield = new JTextField(20);
		ingredientsfield.setEditable(false);
		return ingredientsfield;
	}
//-----------------------------------------------------------------------------------------
	/**
	 * Methode zeichnet den Titel
	 * @return JLabel
	 */
	public JLabel title()
	{
		JLabel title = new JLabel("Rezeptverwaltung");
		title.setFont(new Font("Arial",1,20));
		return title;
	}
//-----------------------------------------------------------------------------------------
}
