package com.risk.controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

/**
 * This class belongs to a listener which Adds a country to the map file based
 * on the events captured from the user interface.
 * 
 * @author Sandeep Swainch
 * @version 1.0
 */
public class RemoveContinentListener extends JFrame implements ActionListener {
	static JFrame frame;
	JLabel lblContinentNametoberemoved;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		frame = new JFrame("Remove Continent Frame");
		frame.setSize(350, 200);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(20, 10, 10, 10));
		lblContinentNametoberemoved = new JLabel("Select the Continent: ");
		JPanel panelComboBox = new JPanel();
		panelComboBox.setLayout(new FlowLayout());
		String[] continentString = new String[staticApplicationVariables.gb.map.GetContinents().size()];
		int i = 0;
		for (Object o : staticApplicationVariables.gb.map.GetContinents()) {
			continentString[i] = ((Continent) o).GetName();
			i++;
		}
		JComboBox<String> comboBoxList = new JComboBox<>(continentString);
		JButton buttonOK = new JButton("OK");
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK pressed:");
				String name = comboBoxList.getSelectedItem().toString();
				Continent cont1 = new Continent(name, 25);
				System.out.println(cont1.GetName());
				System.out.println("Before" + staticApplicationVariables.gb.map.GetContinents().size());
				staticApplicationVariables.gb.map
						.RemoveContinent(staticApplicationVariables.gb.map.GetContinentByName(name));
				try {
					staticApplicationVariables.gb.SaveMapToFile("output.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, cont1.GetName() + " Continent was deleted!");
				System.out.println("After " + staticApplicationVariables.gb.map.GetContinents().size());
				frame.dispose();
			}
		});
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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