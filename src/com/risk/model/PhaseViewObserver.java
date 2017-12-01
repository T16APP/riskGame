package com.risk.model;

import java.util.Observable;
import java.util.Observer;

import com.risk.utility.TurnPhases;

/**
 * this class represents observer for the phase view
 * 
 * @author Kourosh
 * @version 1.0.0.0
 */
public class PhaseViewObserver implements Observer {
	String phaseView = "";
	/**standard constructor for json use
	 * 
	 */
	public  PhaseViewObserver(){
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		TurnOrganizer turnOrganizer = (TurnOrganizer) o;
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Startup) {
			phaseView = "Current Phase: " + turnOrganizer.GetCurrentPhase() + "\n" + "Current Player: "
					+ turnOrganizer.GetCurrentPlayer().GetName();
		} else {
			phaseView = "Current Phase: " + turnOrganizer.GetCurrentPhase();
			if(turnOrganizer.GetCurrentPlayerId()!=-1)
				phaseView+="\n" + "Current Player: " + turnOrganizer.GetCurrentPlayer().GetName();
		}
		System.out.println("_____Phase View_____");
		System.out.println(phaseView);
		System.out.println("_____End of Phase View_____");
	}

	public void GetPhaseView() {
		System.out.println(phaseView);
	}

}
