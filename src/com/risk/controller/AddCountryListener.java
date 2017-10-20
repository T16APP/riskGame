package com.risk.controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.GameBoard;
import com.risk.utility.MapParser;
import com.risk.utility.staticApplicationVariables;
import com.risk.view.applicationWindow;


/**
 * This class belongs to a listener which Adds a country to the map file based on the 
 * events captured from the user interface.
 * @author SSS
 *@version 1.0.0.0
 */
public class AddCountryListener extends JFrame implements ActionListener {

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
	 * the user to select the name of Continent from a list, the X and Y cordinates, and
	 * the country name to be added. 
	 * 
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

		JLabel CountryNametobeadded = new JLabel("Country Name:");
		JTextField CountryField = new JTextField(10);
		JLabel enterXcordinates= new JLabel("X:");
		JTextField xField = new JTextField(5);

		JLabel enterYcordinates= new JLabel("Y:");
		JTextField yField = new JTextField(5);



		JButton buttonOK = new JButton("ADD COUNTRY ");
		/**
		 * Causes addition of a country to the continent selected by user on press of OK button.
		 * @param actionEvent Not used.
		 */
		buttonOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{

				String name = comboBoxList.getSelectedItem().toString();
				int id = staticApplicationVariables.gb.map.GetContinentIdByName(name);
				//Continent c = new Continent(name,id);
				


				if( staticApplicationVariables.gb.map.DoesExistCountry(staticApplicationVariables.gb.map.GetCountryIdByName(CountryField.getText()))== true)
				{JOptionPane.showMessageDialog(null, CountryField.getText() + " Already Exists");
				}

				else
				{  
					
					int before = staticApplicationVariables.gb.map.GetCountries().size();
					System.out.println("Before is :" + before);

					String output =staticApplicationVariables.gb.map.AddCountry(CountryField.getText(), id,Integer.parseInt(xField.getText()),Integer.parseInt(yField.getText())) ; 	 
					System.out.println("HERE1: "+ output);
					int after = staticApplicationVariables.gb.map.GetCountries().size();
					System.out.println(after + "After");
					
					try {
						staticApplicationVariables.gb.SaveMapToFile("output.txt");
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, CountryField.getText()+ " Country Added to " + name); 


					try {
						staticApplicationVariables.gb.SaveMapToFile("output.txt");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				
			}

		});

		/**
		 * Causes frame to be closed on press of cancel button.
		 * @param actionEvent Not used.
		 */
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
		panelComboBox.add(CountryNametobeadded);
		panelComboBox.add(CountryField);
		panelComboBox.add(enterXcordinates);
		panelComboBox.add(xField);
		panelComboBox.add(enterYcordinates);
		panelComboBox.add(yField);

		panel.add(panelComboBox);
		panel.add(buttonOK);
		panel.add(buttonCancel);

		frame.add(panel);


		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	


}
