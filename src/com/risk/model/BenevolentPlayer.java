package com.risk.model;

import java.io.IOException;

import com.risk.utility.LoggingWindow;

public class BenevolentPlayer implements StrategyPlayer{
	public BenevolentPlayer(){
		
	}

	@Override
	public String Reinforcement(Player prm_currentPlayer) throws Exception {
		// TODO Auto-generated method stub
		int numWeakCountries = prm_currentPlayer.map.GetWeakCountriesByPlayerId(prm_currentPlayer.GetId()).size();
		int totArmies = prm_currentPlayer.GetArmies();
		if(numWeakCountries>0){
			for(Country c : prm_currentPlayer.map.GetWeakCountriesByPlayerId(prm_currentPlayer.GetId())){
				c.AddArmies(totArmies/numWeakCountries);
				if (prm_currentPlayer.GetArmies() >= totArmies/numWeakCountries) {
					prm_currentPlayer.map.AddArmiesToCountry(c.GetId(), totArmies/numWeakCountries);
					prm_currentPlayer.AddArmies(-1 * totArmies/numWeakCountries);
				}
				else if(prm_currentPlayer.GetArmies()>0){
					prm_currentPlayer.map.AddArmiesToCountry(c.GetId(), prm_currentPlayer.GetArmies());
					prm_currentPlayer.AddArmies(-1 * prm_currentPlayer.GetArmies());
				}
			}
		}
		prm_currentPlayer.DoCardExchange();
		prm_currentPlayer.EndReinforcementPhase();
		Attack(prm_currentPlayer);
		return null;
	}

	@Override
	public String Attack(Player prm_currentPlayer) throws Exception {
		// TODO Auto-generated method stub
		LoggingWindow.Log("Benevolent player skips Attack phase");
		prm_currentPlayer.EndAttackPhase();
		Fortification(prm_currentPlayer);
		return null;
	}

	@Override
	public String Fortification(Player prm_currentPlayer)
			throws IOException, Exception {
		// TODO Auto-generated method stub
		Country weakestC = prm_currentPlayer.map.GetWeakestCountry(prm_currentPlayer.GetId());
		Country highestC = prm_currentPlayer.map.GetHighArmiesCountry(prm_currentPlayer.GetId());
		if(weakestC!=null && highestC!=null && highestC.GetArmies()>2){
			prm_currentPlayer.MoveArmiesToCountryFromCountry(highestC.GetId(), weakestC.GetId(), highestC.GetArmies()-2);
		}
		prm_currentPlayer.EndFortificationPhase();
		prm_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
		return null;
	}

}
