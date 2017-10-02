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

import org.jgraph.JGraph;

import com.risk.listeners.*;

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
	// private JMenu viewMenu;
	private JMenu helpMenu;
	// private JMenu simulationMenu;

	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem addCountry;
	private JMenuItem addPlayer;
	private JMenuItem addEdge;
	private JMenuItem delete;
	// private JMenuItem showDetails;
	private JMenuItem help;
	private JGraph graph;
	private JMenuItem start, pause;
	// private JMenuItem showGlobalLog;
	// private JMenuItem showStateLog;
	// private JMenuItem showCountryDetails;
	// private JMenuItem showCountriesDetails;

	public static applicationWindow getInstance() {
		if (instance == null) {
			instance = new applicationWindow();
		}
		return instance;
	}

	public JGraph getGraph() {
		return graph;
	}

	public void open() {
		System.out.println("opening Application Window");

		// CREATING FRAME and Setting
		JFrame mainFrame = new JFrame("Map Editor and Game Board");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		staticApplicationVariables.SCREEN_WIDTH = (int) (dim.width * 0.9);
		staticApplicationVariables.SCREEN_HEIGHT = (int) (dim.height * 0.9);

		mainFrame.setSize(staticApplicationVariables.SCREEN_WIDTH, staticApplicationVariables.SCREEN_HEIGHT);

		mainFrame.setLocation(dim.width / 2 - staticApplicationVariables.SCREEN_WIDTH / 2,
				dim.height / 2 - staticApplicationVariables.SCREEN_HEIGHT / 2);

		mainFrame.setLayout(new FlowLayout());
		// instance = this;
		/*
		 * JPanel panel = new JPanel(); panel.setLayout(new BorderLayout());
		 * this.add(panel);
		 */

		/**
		 * Returns the singleton instance of GraphicalUserInterface
		 * 
		 * @return
		 */

		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		playerMenu = new JMenu("Player");
		// viewMenu = new JMenu("View");
		helpMenu = new JMenu("Help");
		// simulationMenu = new JMenu("Simulation");

		open = new JMenuItem("Open");
		save = new JMenuItem("Save");

		addCountry = new JMenuItem("Add Country");
		addEdge = new JMenuItem("Add Edge");
		delete = new JMenuItem("Delete");
		addPlayer = new JMenuItem("Add New Player");
		// showDetails = new JMenuItem("Show State Details");

		// showGlobalLog = new JMenuItem("Show Global Log");
		// showStateLog = new JMenuItem("Show State Log");
		// showCountriesDetails = new JMenuItem("Show Countries Details");

		/*
		 * Create the menu items for the simulation menu.
		 */
		help = new JMenuItem("Help");
		start = new JMenuItem("Start");
		pause = new JMenuItem("Pause");

		// open.addActionListener(new OpenListener());
		// save.addActionListener(new SaveListener());

		// addCountry.addActionListener(new AddCountryListener());
		// addEdge.addActionListener(new AddEdgeListener());
		// delete.addActionListener(new DeleteListener());
		// showDetails.addActionListener(new PopupListener());

		// showGlobalLog.addActionListener(new ShowGlobalLogListener());
		// showStateLog.addActionListener(new ShowStateLogListener());
		// showCountriesDetails.addActionListener(new ShowCountriesDetailsListener());

		fileMenu.add(open);
		fileMenu.addSeparator();
		fileMenu.add(save);

		editMenu.add(addCountry);
		editMenu.add(addEdge);
		editMenu.addSeparator();
		editMenu.add(delete);
		// editMenu.addSeparator();
		// editMenu.add(showDetails);
		// editMenu.add(showCountryDetails);

		// viewMenu.add(showGlobalLog);
		// viewMenu.add(showStateLog);
		// viewMenu.add(showCountriesDetails);

		// simulationMenu.add(start);
		// simulationMenu.add(pause);
		playerMenu.add(addPlayer);

		helpMenu.add(help);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		// menuBar.add(viewMenu);
		menuBar.add(playerMenu);
		// menuBar.add(simulationMenu);
		menuBar.add(helpMenu);
		open.addActionListener(new OpenListener());
		mainFrame.setJMenuBar(menuBar);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setVisible(true);

		mainFrame.setVisible(true);

	}
}