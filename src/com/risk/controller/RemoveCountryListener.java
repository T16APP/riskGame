
package com.risk.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

/**
 * This class belongs to a listener which Adds a country to the map file based
 * on the events captured from the user interface.
 * 
 * @author Sandeep Swainch
 * @version 1.0
 */
public class RemoveCountryListener implements ActionListener {
	static JFrame frame;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		frame = new JFrame("Remove Country Frame");
		frame.setSize(400, 220);
		JPanel panel = new JPanel();
		JLabel ContinentNametoberemoved = new JLabel("Continent Name:");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(20, 10, 10, 10));
		JLabel CountryNametoberemoved = new JLabel("Country  Name:");
		JPanel panelComboBox = new JPanel();
		panelComboBox.setLayout(new FlowLayout());
		String[] continentString = new String[staticApplicationVariables.gb.map.GetContinents().size()];
		int i = 0;
		for (Object o : staticApplicationVariables.gb.map.GetContinents()) {
			continentString[i] = ((Continent) o).GetName();
			i++;
		}
		JComboBox<String> comboBoxList = new JComboBox(continentString);
		JComboBox<String> comboBoxList2 = new JComboBox(
				staticApplicationVariables.gb.map.getCountryListStringForCombobox(
						staticApplicationVariables.gb.map.GetContinents().get(0).GetContinentId()));
		int idCountrySelected = 0;
		comboBoxList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = ((JComboBox) e.getSource()).getSelectedItem().toString();
				int id = staticApplicationVariables.gb.map.GetContinentIdByName(name);
				System.out.println("selected: " + name);
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel(
						staticApplicationVariables.gb.map.GetCountriesByContinentIdInStrings(id));
				comboBoxList2.setModel(model);
				String[] temp = staticApplicationVariables.gb.map.GetCountriesByContinentIdInStrings(id);
				String temp2 = "";
				for (int i = 0; i < temp.length; i++) {
					temp2 += temp[i];
				}
				System.out.println("The list populated is : " + temp2);
			}
		});

		JButton buttonOK = new JButton("OK");
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBoxList2.getSelectedItem().toString();
				staticApplicationVariables.gb.map
						.RemoveCountry(staticApplicationVariables.gb.map.GetCountryByName(name));
				JOptionPane.showMessageDialog(null, name + " country was deleted!");
				try {
					staticApplicationVariables.gb.SaveMapToFile("output.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println("try to get country which was removed : "
						+ staticApplicationVariables.gb.map.GetCountryIdByName(name));
				frame.dispose();
			}
		});
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panelComboBox.add(ContinentNametoberemoved);
		panelComboBox.add(comboBoxList);
		panelComboBox.add(CountryNametoberemoved);
		panelComboBox.add(comboBoxList2);
		panel.add(panelComboBox);
		panel.add(buttonOK);
		panel.add(buttonCancel);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
