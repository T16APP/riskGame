package com.risk.model;

import java.util.List;

/**this class simulate the attack phase
 * 
 * @author Kourosh
 *@version 1.0.0.0
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

/**this constructor build the object based on two parameters
 * 
 * @param prm_ackerCountId is the id of the attacker country
 * @param prm_defenderCountId is the id of the defender country
 */
public Attack(Country prm_attackerCountry, Country prm_defenderCountry,Player prm_defenderPlayer){
	this.attackerCountry=prm_attackerCountry;
	this.defenderCountry = prm_defenderCountry;
	this.defenderPlayer = prm_defenderPlayer;
	isCaptured=false;
	isOccupied=false;
}

}
