package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.risk.utility.staticApplicationVariables;

/**
 * This class shows current player on the events captured from the user
 * interface.
 * 
 * @author Sandeep Swainch
 * @version 1.0
 */
public class ShowCurrentPlayer implements ActionListener {

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
		System.out.println(staticApplicationVariables.gb.turnOrganizer.GetCurrentPhase());
	}
}
