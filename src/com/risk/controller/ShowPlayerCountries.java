package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JComboBox;

import com.risk.model.Country;
import com.risk.utility.staticApplicationVariables;

/**
 * This class shows player Countries
 * 
 * @author Sandeep Swainch
 * @version 1.0
 */
public class ShowPlayerCountries implements ActionListener {
	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer.
	 * 
	 * @param actionEvent
	 *            Not used.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("The number of countries :" + staticApplicationVariables.gb.map.GetCountries().size());
		System.out
				.println("The current player Id :" + staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
		System.out.println("The list of countriesper player is : ");
		System.out.println(
				(staticApplicationVariables.gb.map.GetCountriesByPlayerId(staticApplicationVariables.gb.players.size()))
						.size());
	}
}
