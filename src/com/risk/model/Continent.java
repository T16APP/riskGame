package com.risk.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a continent it maintains name, id, control number and
 * playerId it has different methods to change the state of the object
 * 
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */

public class Continent extends Land {
	int control;
	int playerId;

	/**
	 * This is the constructor of the Continent class, it inherits partially
	 * from the parent superclass, Land, to set the continentId and the name and
	 * the constructor sets the control value and assign -1 to playerId which
	 * means that in Gameplay phase it will be assigned to a player
	 * 
	 * @param prm_name
	 *            this parameter will be the name of the continent which is
	 *            created
	 * @param prm_control
	 *            this parameter represents the value of the control of the
	 *            continent
	 */
	public Continent(String prm_name, int prm_control) {
		super(prm_name);
		this.control = prm_control;
	}

	/**
	 * This method returns the continentID of the object
	 * 
	 * @return continentID as integer which is the Id of the object
	 */
	public int GetContinentId() {
		return this.id;
	}

	/**
	 * This method returns the value of the control of the object
	 * 
	 * @return control of the object as integer
	 */
	public int GetControl() {
		return control;
	}

	/**
	 * this method sets the playerId of the continent
	 * 
	 * @param prm_playerId
	 *            is the id of the player who owns the continent
	 */
	public void SetPlayerId(int prm_playerId) {
		// TODO Auto-generated method stub
		this.playerId = prm_playerId;
	}

	/**
	 * this method returns the id of the player
	 * 
	 * @return the id of the player who owns the continent
	 */
	public int GetPlayerId() {
		return this.playerId;
	}

}