package com.risk.model;

import java.util.List;

/**
 * this class simulate the attack phase
 * 
 * @author Kourosh
 * @version 1.0.0.0
 */
public class Attack {
	public List<Dice> attackerDices;
	public List<Dice> defenderDices;
	private static Attack instance;
	public Country attackerCountry;
	public Country defenderCountry;
	public Player defenderPlayer;
	public boolean isCaptured;
	public boolean isOccupied;

	/**
	 * This constructor creates the object
	 * 
	 * @param prm_attackerCountry
	 *            is the country that launches attack
	 * @param prm_defenderCountry
	 *            is defender country
	 * @param prm_defenderPlayer
	 *            is defender player
	 */
	public Attack(Country prm_attackerCountry, Country prm_defenderCountry, Player prm_defenderPlayer) {
		this.attackerCountry = prm_attackerCountry;
		this.defenderCountry = prm_defenderCountry;
		this.defenderPlayer = prm_defenderPlayer;
		isCaptured = false;
		isOccupied = false;
	}

}
