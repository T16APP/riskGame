package com.risk.model;

import java.io.IOException;

/**this an interface that all concrete strategies should implement it
 * 
 * @author Kourosh
 *
 */
public interface StrategyPlayer {
	String Reinforcement(Player prm_currentPlayer) throws IOException, Exception;
	String Attack(Player prm_currentPlayer) throws IOException, Exception;
	String Fortification(Player prm_currentPlayer) throws IOException, Exception;

}
