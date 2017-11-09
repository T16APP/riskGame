package com.risk.model;

import java.util.Observable;
import java.util.Observer;

/**
 * this class represents a class to show world domination percentage it uses map
 * observable to show the percentage
 * 
 * @author Kourosh
 * @version 1.0.0.0
 */
public class WorldDominationObserver implements Observer {

	public String worldDomination = "No country distributed";

	public WorldDominationObserver() {

	}

	/**
	 * this method updates the status as per observable changes
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Map map = (Map) o;
		worldDomination = map.GetWorldDomination();
		System.out.println("_____World Domination View_____");
		System.out.println(worldDomination);
		System.out.println("_____End of World Domination View_____");
	}

	public void GetWorldDomination() {
		System.out.println(worldDomination);
	}

}
