package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.risk.utility.staticApplicationVariables;

public class ExchangeCardsforAplayerListener extends JFrame implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			int output = staticApplicationVariables.gb.ExchangeCards(staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
			if(output==1)
			JOptionPane.showMessageDialog(null, "Cards Excahnged");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
