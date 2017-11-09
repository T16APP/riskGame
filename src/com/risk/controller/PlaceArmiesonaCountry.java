package com.risk.controller;

import java.awt.FlowLayout;
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

import com.risk.model.Country;
import com.risk.utility.staticApplicationVariables;

/**
 * This class belongs to a listener which Adds a country to the map file based
 * on the events captured from the user interface.
 * 
 * @author Sandeep Swainch
 * @version 1.0
 */
public class PlaceArmiesonaCountry implements ActionListener {
	static JFrame frame;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate
	 * the Continent name, the Control value. The control value must be set as
	 * Integer
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		frame = new JFrame("Place Armies On a Country");
		frame.setSize(350, 200);
		JLabel listofcountriesofplayer = new JLabel("Select My countries:");
		int currentplayerid = staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId();
		JComboBox<String> comboBoxList2 = new JComboBox<>(getCountryListStringForaplayerCombobox(currentplayerid));
		comboBoxList2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = ((JComboBox) e.getSource()).getSelectedItem().toString();
				JOptionPane.showMessageDialog(null, " Country Selected " + name + "Enter Armies now :");
			}
		});
		JLabel numberofarmies = new JLabel("Number Of Armies:");
		JTextField numberofarmiesTField = new JTextField(7);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		JButton buttonOK = new JButton("OK");
		buttonOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, " Country Allocated  Successfully");
				frame.dispose();

			}
		});
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(listofcountriesofplayer);
		panel.add(comboBoxList2);
		panel.add(numberofarmies);
		panel.add(numberofarmiesTField);
		panel.add(buttonOK);
		panel.add(buttonCancel);
		frame.add(panel);
		frame.setVisible(true);
	}

	String[] getCountryListStringForaplayerCombobox(int playerid) {
		List<Country> countryList = staticApplicationVariables.gb.map.GetCountriesByPlayerId(playerid);
		String[] countryString = new String[countryList.size()];

		for (int j = 0; j < countryList.size(); j++) {
			countryString[j] = countryList.get(j).GetName();
		}
		return countryString;
	}
}
