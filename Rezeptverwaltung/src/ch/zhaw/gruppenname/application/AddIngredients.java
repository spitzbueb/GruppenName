/**
 * Klasse für Hinzufügen von Zutaten in Datenbank
 * 
 * @author Philipp Schalcher
 * @version 23.12.2011
 */
package ch.zhaw.gruppenname.application;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ch.zhaw.gruppenname.database.Database;

public class AddIngredients {
	private JFrame frame;
	private JMenuBar menubar;
	private Container contentPane;
	private JTextField name,bewertung,zutaten;
	private JTextArea beschreibung,vorgehen;
//---------------------------------------------------------------------------------
	/**
	 * Main-Klasse um Maske zu starten
	 */
	public static void main(String[] args) {
		new AddIngredients();
	}
//---------------------------------------------------------------------------------
	/**
	 * Konstruktor: Zeichnet das Eingabefenster
	 */
	public AddIngredients()
	{
		frame = new JFrame("Zutat erfassen");
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		
		//Datei-Menü erstellen
		JMenu dateiMenu = new JMenu("Datei");
		
		JMenuItem exit = new JMenuItem("Beenden");
		JMenuItem safe = new JMenuItem("Speichern");
		
		//Aktion festlegen, welche beim Schliessen durch Datei>Beenden ausgeführt wird
		exit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Fenster zerstören und nicht komplettes Programm schliessen
				frame.dispose();
			}
		});
		
		//Aktion festlegen, beim Drücken von Datei>Speichern
		safe.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Temporäres DB-Objekt generieren um Einträge zu speichern.
				Database add = new Database();
				try {
					add.addIngredients(name.getText());
					JOptionPane.showMessageDialog(frame, "Eintrag gespeichert.");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Eintrag wurde nicht in Datenbank geschrieben!");
				}
				
			}
		});
		
		dateiMenu.add(safe);
		dateiMenu.add(exit);
		
		menubar.add(dateiMenu);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Zeichne das Fenster
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
		
		frame.pack();
		frame.setVisible(true);
	}
//-----------------------------------------------------------------------------------
	/**
	 * Methode um das Namesfeld zu zeichnen
	 * @return JTextField
	 */
	private JTextField nameTextField()
	{
		name = new JTextField(30);
		name.setBorder(BorderFactory.createLineBorder(Color.black));
		return name;
	}
//----------------------------------------------------------------------------------
	/**
	 * Methode um den Titel zu zeichnen
	 * @return JLabel
	 */
	private JLabel title()
	{
		
		JLabel title = new JLabel("Zutat aufnehmen");
		title.setFont(new Font("Arial",1,20));
		return title;
	}
//----------------------------------------------------------------------------------
}
