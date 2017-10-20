package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JComboBox;

import com.risk.model.Country;
import com.risk.utility.staticApplicationVariables;

public class ShowPlayerCountries implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
  
  System.out.println("The number of countries :" + staticApplicationVariables.gb.map.GetCountries().size());
  System.out.println("The current player Id :" + staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
  System.out.println("The list of countriesper player is : ");
  System.out.println((staticApplicationVariables.gb.map.GetCountriesByPlayerId(staticApplicationVariables.gb.players.size())).size());
  
  
  
	}

}
