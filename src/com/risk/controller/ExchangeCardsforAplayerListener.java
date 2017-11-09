package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.risk.utility.staticApplicationVariables;

public class ExchangeCardsforAplayerListener extends JFrame implements ActionListener {

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param e is the event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			/*int output = staticApplicationVariables.gb
					.ExchangeCards(staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
			if (output == 1)
				*/JOptionPane.showMessageDialog(null, "Cards Excahnged");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
