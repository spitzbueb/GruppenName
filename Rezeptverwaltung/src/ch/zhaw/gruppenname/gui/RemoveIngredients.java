/**
 * Klasse um Zutaten aus der Datenbank zu löschen
 * 
 * @author GruppenName
 * @version 18.01.12
 */
package ch.zhaw.gruppenname.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ch.zhaw.gruppenname.application.Ingredient;

public class RemoveIngredients {
	private JFrame frame;
	private JMenuBar menubar;
	private Ingredient ingredient;
	private JComboBox dropdown;
	
	/**
	 * Konstruktor
	 */
	public RemoveIngredients(){
		frame = new JFrame("Zutat löschen");
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		dropdown = new JComboBox();
		for (String string:Ingredient.getAllNames()){
			dropdown.addItem(string);
		}
		
		//Datei-MenÃ¼ erstellen
		JMenu dateiMenu = new JMenu("Datei");
		
		JMenuItem exit = new JMenuItem("Beenden");
		JMenuItem delete = new JMenuItem("Löschen");
		
		//Aktion festlegen, welche beim Schliessen durch Datei>Beenden ausgefÃ¼hrt wird
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//Fenster zerstÃ¶ren und nicht komplettes Programm schliessen
				frame.dispose();
			}
		});
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ingredient = new Ingredient(dropdown.getSelectedItem().toString());
				if (ingredient.remove()){
					JOptionPane.showMessageDialog(null, dropdown.getSelectedItem() + "wurde gelöscht", "Löschvorgang", JOptionPane.INFORMATION_MESSAGE);
					dropdown.removeItem(dropdown.getSelectedItem());
				}
				else {
					JOptionPane.showMessageDialog(null, dropdown.getSelectedItem() + "wurde nicht gelöscht, da es sich mit einem Rezept verlinkt ist", "Löschvorgang", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		dateiMenu.add(delete);
		dateiMenu.add(exit);
		
		menubar.add(dateiMenu);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane;
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
		contentPane.add(dropdown,c);
		
		frame.pack();
		frame.setVisible(true);
	}
	//----------------------------------------------------------------------------------
	/**
	 * Methode um den Titel zu zeichnen
	 * @return JLabel
	 */
	private JLabel title(){		
		JLabel title = new JLabel("Zutat löschen");
		title.setFont(new Font("Arial",1,20));
		return title;
	}
//----------------------------------------------------------------------------------
}
