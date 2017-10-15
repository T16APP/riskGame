
package com.risk.EventHandlers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.GameBoard;
import com.risk.utility.staticApplicationVariables;
import com.risk.view.applicationWindow;

public class RemoveCountryListener implements ActionListener {
	static JFrame frame;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    frame = new JFrame("Remove Country Frame");
        frame.setSize(350, 200);
        
        
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(20, 10, 10, 10));
        
        
        JPanel panelComboBox = new JPanel();
        panelComboBox.setLayout(new FlowLayout());
        
        String[] continentString = new String[staticApplicationVariables.gb.map.GetContinents().size()];
        
        int i=0;
        for(Object o: staticApplicationVariables.gb.map.GetContinents()){
        	continentString[i] = ((Continent)o).GetName();
        	i++;
        }
        
        JComboBox<String> comboBoxList = new JComboBox<>(continentString);
        
        
        
       
        
        JComboBox<String> comboBoxList2 = new JComboBox<>(getCountryListStringForCombobox(staticApplicationVariables.gb.map.GetContinents().get(0).GetId()));
        
        int idCountrySelected = 0;
        
        comboBoxList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	
            	String name = ((JComboBox)e.getSource()).getSelectedItem().toString();
            	int id = staticApplicationVariables.gb.map.GetContinentIdByName(name);
            	System.out.println("selected: "+ name);
            	DefaultComboBoxModel model = new DefaultComboBoxModel(getCountryListStringForCombobox(id));
            	comboBoxList2.setModel(model);
            }
          });
        
        JButton buttonOK = new JButton("OK");
        
        buttonOK.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	//System.out.println("OK pressed:");
            	String name = comboBoxList2.getSelectedItem().toString(); 
            			
            	staticApplicationVariables.gb.map.RemoveCountry(staticApplicationVariables.gb.map.GetCountryByName(name));
            	JOptionPane.showMessageDialog(null, name+ " country was deleted!");
            	
            	System.out.println("try to get country which was removed : "+ staticApplicationVariables.gb.map.GetCountryIdByName(name));
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
        panelComboBox.add(comboBoxList2);
        panel.add(panelComboBox);
        panel.add(buttonOK);
        panel.add(buttonCancel);
        
        frame.add(panel);

        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String[] getCountryListStringForCombobox(int id){
		List<Country> countryList = staticApplicationVariables.gb.map.GetCountriesByContinentId(id);
        String[] countryString = new String[countryList.size()];
        
        for(int j=0; j<countryList.size();  j++){
        	countryString[j] = countryList.get(j).GetName();
        }
        return countryString;
	}

}
