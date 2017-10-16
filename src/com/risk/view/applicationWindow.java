package com.risk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//simport org.jgraph.JGraph;


import com.risk.controller.AddContinentListener;
import com.risk.controller.AddCountryListener;
import com.risk.controller.Numberofplayers;
import com.risk.controller.OpenListener;
import com.risk.controller.RemoveContinentListener;
import com.risk.controller.RemoveCountryListener;
import com.risk.controller.SavetofileListener;
import com.risk.utility.staticApplicationVariables;

/**
 * This class defines the main window of our program. It defines the main
 * components of the user interface. All event handlers registered to components
 * (menu items) of this class are instantiated with a reference to this class.
 */
public class applicationWindow extends JFrame {

	public applicationWindow() {
	}

	private JMenuBar menuBar;
	private static applicationWindow instance = null;
	private JMenu playerMenu;

	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu playMenu;

	private JMenu helpMenu;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem numberofarmiesofcountry;
	private JMenuItem addContinent;
	private JMenuItem addCountry;
	private JMenuItem removeContinent;
	private JMenuItem removeCountry;
	private JMenuItem addPlayer;
	private JMenuItem createMap;
	private JMenuItem adjacentcountries;
	private JMenuItem numberofplayers;
	private JMenuItem startgame;
	private JMenuItem help;
//	private JGraph graph;
	private JMenuItem start, pause;

	public static applicationWindow getInstance() {
		if (instance == null) {
			instance = new applicationWindow();
		}
		return instance;
	}

	//public JGraph getGraph() {
	//	return graph;
	//}

	/**
	 * Returns the singleton instance of GraphicalUserInterface
	 * 
	 * @return
	 */
	public void open() {
		// CREATING FRAME and Setting
		JFrame mainFrame = new JFrame("Map Editor and Game Board");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Uses the specific input screen of the user
		staticApplicationVariables.SCREEN_WIDTH = (int) (dim.width * 0.9);
		staticApplicationVariables.SCREEN_HEIGHT = (int) (dim.height * 0.9);
		mainFrame.setSize(staticApplicationVariables.SCREEN_WIDTH, staticApplicationVariables.SCREEN_HEIGHT);
		mainFrame.setLocation(dim.width / 2 - staticApplicationVariables.SCREEN_WIDTH / 2,
				dim.height / 2 - staticApplicationVariables.SCREEN_HEIGHT / 2);
		mainFrame.setLayout(new FlowLayout());
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		playerMenu = new JMenu("Player");
		helpMenu = new JMenu("Help");
		playMenu = new JMenu("Play");

		open = new JMenuItem("Open");
		save = new JMenuItem("Save to file");
		addContinent = new JMenuItem("Add Continent");
		addCountry = new JMenuItem("Add  Country");
		removeContinent = new JMenuItem("Remove Continent");
		removeCountry = new JMenuItem("Remove Country");
		startgame = new JMenuItem("Start Game");
		numberofplayers= new JMenuItem(" Set Number of Players");
		//addPlayer = new JMenuItem("Add New Player");
		createMap = new JMenuItem("Create Map");
		adjacentcountries= new JMenuItem("Show Adjacent Countries");
		numberofarmiesofcountry= new JMenuItem("Show Armies in a Country");
		/*
		 * Create the menu items for the simulation menu.
		 */
		help = new JMenuItem("Help");
		start = new JMenuItem("Start");
		pause = new JMenuItem("Pause");

		fileMenu.add(open);
		fileMenu.addSeparator();
		fileMenu.add(save);
		fileMenu.add(createMap);
		
		editMenu.add(addContinent);
		editMenu.add(removeContinent);
		editMenu.addSeparator();
		editMenu.add(addCountry);
		editMenu.add(removeCountry);
		
	//	editMenu.add(delete);
		//playerMenu.add(addPlayer);
		playerMenu.add(numberofplayers);
		
		playMenu.add(startgame);
		playMenu.add(adjacentcountries);
		playMenu.add(numberofarmiesofcountry);
		
		helpMenu.add(help);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(playerMenu);
		menuBar.add(playMenu);
		menuBar.add(helpMenu);
		
		open.addActionListener(new OpenListener());
		addContinent.addActionListener(new AddContinentListener());
		removeContinent.addActionListener(new RemoveContinentListener());
		addCountry.addActionListener(new AddCountryListener());
		removeCountry.addActionListener(new RemoveCountryListener());
		save.addActionListener(new SavetofileListener());
		numberofplayers.addActionListener(new Numberofplayers());
		
		
		mainFrame.setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame.setVisible(true);

	}
	
}