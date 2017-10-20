package com.risk.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

/**
 * This class belongs to a listener which performs action of adding a continent
 * based on the events captured from the front end.
 * @author Sandeep Swainch
 * @version 1.0
 */

public class AddContinentListener implements ActionListener {
	static JFrame frame;

	@Override
	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer.
	 * @param actionEvent Not used.
	 */
	public void actionPerformed(ActionEvent e) {
		frame = new JFrame("Add Continent Frame");
		frame.setSize(350, 200);

		JLabel continentLabel = new JLabel("Continent Name:");

		JTextField nameTField = new JTextField(15);

		JLabel continentcontrolLabel = new JLabel("Control Value:");

		JTextField continentfield = new JTextField(10);

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JButton buttonOK = new JButton("OK");

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (staticApplicationVariables.gb.map.DoesExistContinent(nameTField.getText()) == true) {
					JOptionPane.showMessageDialog(null, "Continent with name " + nameTField.getText() + " Exists!");
				} else {
					String output = staticApplicationVariables.gb.map.AddContinent(nameTField.getText(),
							Integer.parseInt(continentfield.getText()));
					System.out.println(output);
					System.out.println(staticApplicationVariables.gb.map.lands.size());
					JOptionPane.showMessageDialog(null, "Continent with name " + nameTField.getText()
							+ ",Control value : " + continentfield.getText() + " is successfully added!");

					try {
						staticApplicationVariables.gb.SaveMapToFile("output.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					frame.dispose();
				}

			}
		});

		JButton buttonCancel = new JButton("Cancel");

		/**
		 * Causes frame to be closed on press of cancel button.
		 * 
		 * @param actionEvent
		 *            Not used.
		 */
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
