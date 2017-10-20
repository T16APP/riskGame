package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.risk.utility.staticApplicationVariables;

/**
 * This class belongs to a listener which Adds a country to the map file based
 * on the events captured from the user interface.
 * 
 * @author Sandeep Swainch
 * @version 1.0
 */
public class SavetofileListener implements ActionListener {
	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			staticApplicationVariables.gb.SaveMapToFile("output.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
