package com.risk.controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.risk.model.Continent;
import com.risk.model.Map;
import com.risk.utility.staticApplicationVariables;

public class RemoveContinentListener extends JFrame implements ActionListener{
	static JFrame frame;
	JLabel lblContinentNametoberemoved;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame = new JFrame("Remove Continent Frame");
        frame.setSize(350, 200);
        
        
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(20, 10, 10, 10));
        
        lblContinentNametoberemoved = new JLabel("Select the Continent: ");
        JPanel panelComboBox = new JPanel();
        panelComboBox.setLayout(new FlowLayout());
        
        String[] continentString = new String[staticApplicationVariables.gb.map.GetContinents().size()];
        
        int i=0;
        for(Object o: staticApplicationVariables.gb.map.GetContinents()){
        	continentString[i] = ((Continent)o).GetName();
        	i++;
		
	    }
        JComboBox<String> comboBoxList = new JComboBox<>(continentString);
        JButton buttonOK = new JButton("OK");
        buttonOK.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	  System.out.println("OK pressed:");   
            	  String name = comboBoxList.getSelectedItem().toString(); 
                 // int ID = staticApplicationVariables.gb.map.GetContinentIdByName(name);
                  Continent cont1 = new Continent(name,25);
                  System.out.println(cont1.GetName());
                  
                  System.out.println("Before" + staticApplicationVariables.gb.map.GetContinents().size());
                 // System.out.println("Before once again" + staticApplicationVariables.gb.map.GetContinents().size());     	  
        	   staticApplicationVariables.gb.map.RemoveContinent(staticApplicationVariables.gb.map.GetContinentByName(name));
                JOptionPane.showMessageDialog(null, cont1.GetName() + " Continent was deleted!"); 
                System.out.println("After " + staticApplicationVariables.gb.map.GetContinents().size());	        
        
               
                
                         
                             
          
            	
            
            	frame.dispose();
            }
          });
       
        JButton buttonCancel = new JButton("Cancel");
        
        buttonCancel.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {   
            frame.dispose();
          }
        });
      
        
        panelComboBox.add(comboBoxList);
        panel.add(panelComboBox);
        panel.add(buttonOK);
        panel.add(buttonCancel);
        
        frame.add(panel);
        
       
        panelComboBox.add(comboBoxList);
        panel.add(lblContinentNametoberemoved);
        panel.add(panelComboBox);
        panel.add(buttonOK);
        panel.add(buttonCancel);
        frame.setVisible(true);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
	}