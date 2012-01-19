package ch.zhaw.gruppenname.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import ch.zhaw.gruppenname.application.IngredientStrategy;
import ch.zhaw.gruppenname.application.RecipeStrategy;
import ch.zhaw.gruppenname.interfaces.ISearchStrategy;

public class SearchReceipe {
	private JFrame frame;
	private JMenuBar menubar;
	private Container contentPane;
	private JTextField name;
	private String search;
	
	public SearchReceipe(){
		frame = new JFrame("Rezept suchen");
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
	
		//Datei-Menü erstellen
		JMenu dateiMenu = new JMenu("Datei");
		JMenu suchenMenu = new JMenu("Suchen nach");
	
		JMenuItem exit = new JMenuItem("Beenden");
		JMenuItem zutat = new JMenuItem("Zutat");
		JMenuItem rezept = new JMenuItem("Name");
	
	
		//Aktion festlegen, welche beim Schliessen durch Datei>Beenden ausgeführt wird
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//Fenster zerstören und nicht komplettes Programm schliessen
				frame.dispose();
			}
		});
		
		zutat.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				ISearchStrategy strategy = new IngredientStrategy();
				search = strategy.getRecipe(name.getText());
				
			}
			
		});
		rezept.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ISearchStrategy strategy = new RecipeStrategy();
				search = strategy.getRecipe(name.getText());
			}
			
		});
		dateiMenu.add(exit);
		suchenMenu.add(rezept);
		suchenMenu.add(zutat);
		
		menubar.add(dateiMenu);
		menubar.add(suchenMenu);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new SearchReceipe();
	}
}
