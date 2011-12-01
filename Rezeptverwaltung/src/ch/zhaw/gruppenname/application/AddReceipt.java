package ch.zhaw.gruppenname.application;

import java.awt.Container;
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
		frame.setSize(700,700);
		
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
		//contenPane.add()
		
		
		frame.setVisible(true);
	}
}
