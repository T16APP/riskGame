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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.risk.model.GameBoard;
import com.risk.utility.staticApplicationVariables;

public class Numberofplayers implements ActionListener{
	
	JPanel panel;
	JFrame frame;
    JLabel selectplayerlabel;
    JTextField  txtCountryName,txtContinentName;
    JButton buttonOK,buttonCancel ;
  
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frame = new JFrame("Set Number Of players ");
		frame.setSize(400, 150);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(20, 10, 10, 10));
        
		JPanel panelComboBox = new JPanel();
        panelComboBox.setLayout(new FlowLayout());
        
         JLabel selectplayerlabel = new JLabel("Select Number of  Players:");
         int maxnumberofplayers=5;
         
         JComboBox comboBoxlist = new JComboBox();
         for (int i=0;i<=maxnumberofplayers;i++)
         { 
        	 comboBoxlist.addItem(i);
         
         }
         JButton buttonOK = new JButton("OK ");
         buttonOK.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {       
        int numberofplayerselected=Integer.parseInt(comboBoxlist.getSelectedItem().toString());
         if(numberofplayerselected<2)
         {
        	 JOptionPane.showMessageDialog(null, "Minimum Number of Players Must be : 2  " ); 
        	 frame.dispose();
         }
         else
         { 
         try {
			staticApplicationVariables.gb.StartupGame(numberofplayerselected);
			//staticApplicationVariables.gb.players.size() = numberofplayerselected;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	 
         JOptionPane.showMessageDialog(null, "Number of Players Selected  " + numberofplayerselected);
           
         } 
         
             }});
         JButton buttonCancel = new JButton("Cancel");
         buttonCancel.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {       
         
           frame.dispose();
             }});
         
         panelComboBox.add(selectplayerlabel);
         panelComboBox.add(comboBoxlist);
         panel.add(panelComboBox);
         panel.add(buttonOK);
         panel.add(buttonCancel);
         
         
   
         frame.add(panel);

         
         frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
	}

}
