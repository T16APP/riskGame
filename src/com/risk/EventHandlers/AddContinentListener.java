package com.risk.EventHandlers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.GameBoard;
import com.risk.model.Map;
import com.risk.utility.staticApplicationVariables;
import com.risk.view.applicationWindow;

public class AddContinentListener implements ActionListener {
	static JFrame frame;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    frame = new JFrame("Add Continent Frame");
        frame.setSize(350, 200);
        
        
        JLabel continentLabel = new JLabel("Continent Name:");
        
        JTextField nameTField = new JTextField(15);
        
        JLabel continentcontrolLabel = new JLabel("Control Value:");
        
        JTextField continentfield = new JTextField(15);
        
        
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JButton buttonOK = new JButton("OK");
        
        buttonOK.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	
          
          GameBoard gameBo = staticApplicationVariables.gb;
         	Continent cont1 = new Continent(nameTField.getText(),Integer.parseInt(continentfield.getText()));
            	
            	
            if(staticApplicationVariables.gb.map.DoesExistContinent(cont1)==true){
            JOptionPane.showMessageDialog(null, "Continent with name "+nameTField.getText()+" Exists!");
           }else{
        	   staticApplicationVariables.gb.map.GetContinents().add(cont1);
            JOptionPane.showMessageDialog(null, "Continent with name "+nameTField.getText()+" is successfulyy added!");
                }
            	 	
            }
          });
        
        JButton buttonCancel = new JButton("Cancel");
        
        buttonCancel.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {   
            frame.dispose();
          }
        });
        
        panel.add(continentLabel);
        panel.add(nameTField);
        panel.add(continentcontrolLabel);
        panel.add(continentfield);
        panel.add(buttonOK);
        panel.add(buttonCancel);
        
        frame.add(panel);

        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
