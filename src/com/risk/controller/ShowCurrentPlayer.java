package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.risk.utility.staticApplicationVariables;

public class ShowCurrentPlayer implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
		System.out.println(staticApplicationVariables.gb.turnOrganizer.GetCurrentPhase());
		//System.out.println(staticApplicationVariables.gb.turnOrganizer.GetNextPhase() );
	
		
	}

}
