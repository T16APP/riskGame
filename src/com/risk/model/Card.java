package com.risk.model;

import com.risk.utility.*;

/**
 * this class represent a card it has properties such as id, name, playerId
 * 
 * @author Kourosh
 * @version 1.0.0.0
 */
public class Card {
	private static int counter = 0;
	protected int id;
	protected ECards type;
	protected int playerId;
	/**standard constructor for json use
	 * 
	 */
	public Card(){
		
	}

	/**
	 * this is the constructor it takes the name and creates the card object
	 * 
	 * @param prm_name
	 *            is the type of the card
	 */
	public Card(ECards prm_name) {
		counter++;
		this.id = this.counter;
		this.type = prm_name;
		this.playerId = -1;

	}

	/**
	 * this method returns the name of the card
	 * 
	 * @return the type of the object
	 */
	public ECards GetType() {
		return this.type;
	}

	/**
	 * this method sets playerId
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 */
	public void SetPlayerId(int prm_playerId) {
		this.playerId = prm_playerId;
	}

	/**
	 * this method returns the id of the object
	 * 
	 * @return is the id of the card
	 */
	public int GetId() {
		return this.id;
	}
}
