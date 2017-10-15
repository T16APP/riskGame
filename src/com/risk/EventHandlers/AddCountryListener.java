package com.risk.EventHandlers;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.lang.Thread.State;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.utility.staticApplicationVariables;
import com.risk.view.applicationWindow;

/**
 * Class that listens to user presses of the "Add Country" menu item
 * in the "edit" menu.
 */
public class AddCountryListener extends JFrame implements ActionListener {

	
   
    //Color color;
	JPanel panel;
	JFrame frame;
    JLabel lblContinentName, lblCountryName, banner;
    JTextField  txtCountryName,txtContinentName;
    JButton buttonOK,buttonCancel ;

    /**
     * Constructor.
     * @param gui The user interface that holds the graph to be altered.
     */
 

    /**
     * Causes a new window to Pop-up. This window then asks
     * the user to indicate the name, the number of states, and
     * the color of the country. The names of individual states must be
     * set later.
     * @param actionEvent Not used.
     */
    public void actionPerformed(ActionEvent actionEvent) {
    	frame = new JFrame("Add Country Frame");
        frame.setSize(450, 200);
        Container content = getContentPane();
        content.removeAll();
       
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(20, 10, 10, 10));
        
        JPanel panelComboBox = new JPanel();
        panelComboBox.setLayout(new FlowLayout());
        
        lblContinentName = new JLabel("Select Continent: ");
        String[] continentString = new String[staticApplicationVariables.gb.map.GetContinents().size()];
        int i=0;
        for(Object o: staticApplicationVariables.gb.map.GetContinents()){
       	continentString[i] = ((Continent)o).GetName();
       	i++;
               }
        JComboBox<String> comboBoxList = new JComboBox<>(continentString);
        
        JLabel CountryNametoberemoved = new JLabel("Country Name:");
        JTextField CountryField = new JTextField(10);
       
        
       
       
       JButton buttonOK = new JButton("ADD COUNTRY ");
       
       buttonOK.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
           	//System.out.println("OK pressed:");
        	   String name = comboBoxList.getSelectedItem().toString();
        	   int id = staticApplicationVariables.gb.map.GetContinentIdByName(name);
        	   Continent c = new Continent(name,id);
 //  List<Country> countries= staticApplicationVariables.gb.map.GetCountriesByContinentId(staticApplicationVariables.gb.map.GetContinentIdByName(c.GetName()));
   //System.out.println(staticApplicationVariables.gb.map.DoesExistCountry(staticApplicationVariables.gb.map.GetCountryIdByName(CountryField.getText())));
    
    if( staticApplicationVariables.gb.map.DoesExistCountry(staticApplicationVariables.gb.map.GetCountryIdByName(CountryField.getText()))== true)
     {JOptionPane.showMessageDialog(null, CountryField.getText() + " Already Exists");
     }
        	
             else
        {
        staticApplicationVariables.gb.map.AddCountry(CountryField.getText(), staticApplicationVariables.gb.map.GetContinentIdByName(CountryField.getText()), 999, 999) ; 	 
    	JOptionPane.showMessageDialog(null, CountryField.getText()+ " Country Added to " + c.GetName()); 
    	}
         
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
       
       panelComboBox.add(lblContinentName);
        panelComboBox.add(comboBoxList);
        panelComboBox.add(comboBoxList);
        panelComboBox.add(CountryNametoberemoved);
        panelComboBox.add(CountryField);
        panel.add(panelComboBox);
        panel.add(buttonOK);
        panel.add(buttonCancel);
  
        frame.add(panel);

        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
    }
  
    public String[] getCountryListinStringForCombobox(int id){
		List<Country> countryList = staticApplicationVariables.gb.map.GetCountriesByContinentId(id);
        String[] countryString = new String[countryList.size()];
        
        for(int j=0; j<countryList.size();  j++){
        	countryString[j] = countryList.get(j).GetName();
                                                 }
        return countryString;
	                                                         }

  
}
