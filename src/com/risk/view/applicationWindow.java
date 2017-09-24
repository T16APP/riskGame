package com.risk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.risk.utility.staticApplicationVariables;

public class applicationWindow {

	public applicationWindow(){
		
	}
	
	
	
	
	public void open(){
		System.out.println("opening Application Window");
		
		//CREATING FRAME and Setting
		JFrame mainFrame = new JFrame("Java Swing Examples");
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	  
	    staticApplicationVariables.SCREEN_WIDTH = (int)(dim.width*0.9);
	    staticApplicationVariables.SCREEN_HEIGHT = (int)(dim.height*0.9);
	    mainFrame.setSize(staticApplicationVariables.SCREEN_WIDTH, staticApplicationVariables.SCREEN_HEIGHT);
	    
	    mainFrame.setLocation(dim.width/2-staticApplicationVariables.SCREEN_WIDTH/2, dim.height/2-staticApplicationVariables.SCREEN_HEIGHT/2);

	    mainFrame.setLayout(new FlowLayout());
	    
/*	    
	    JPanel gameMenuPanel = new JPanel();
	    gameMenuPanel.setPreferredSize(new Dimension(200, 200));
	    gameMenuPanel.setBackground(Color.WHITE);
	    gameMenuPanel.setLayout(new BoxLayout(gameMenuPanel, BoxLayout.Y_AXIS));
	    mainFrame.add(gameMenuPanel);
	    
	    
	    JButton startNewGameButton = new JButton("Start New Game");
	//    startNewGameButton.setPreferredSize(new Dimension((int)(gameMenuPanel.getSize().getWidth()), (int)(gameMenuPanel.getSize().getHeight())));
	//    startNewGameButton.setMinimumSize(new Dimension(200, 50));
	    gameMenuPanel.add(startNewGameButton);
	    
	    JButton loadGameButton = new JButton("Load Game");
	    gameMenuPanel.add(loadGameButton);
	    
	    JButton quitGameButton = new JButton("Quit");
	    gameMenuPanel.add(quitGameButton);
	    
	    
	    
	*/    
	    
	   /*
	    GameGuiPanel gameGUIPanel = new GameGuiPanel(StaticApplicationVariables.SCREEN_WIDTH,StaticApplicationVariables.SCREEN_HEIGHT);
	    mainFrame.add(gameGUIPanel);
	   */
	    
	    
	    mainFrame.setVisible(true);

	}	
}


