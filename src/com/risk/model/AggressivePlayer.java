package com.risk.model;

import java.io.IOException;

public class AggressivePlayer implements StrategyPlayer{

	@Override
	public String Reinforcement(Player prm_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		Country strongestC = null;
		int armies=0;
		for(Country c : prm_currentPlayer.map.GetBorderCountriesByPlayerId(prm_currentPlayer.GetId())){
			if(c.GetArmies()>=armies){
				strongestC=c;
				armies=c.GetArmies();
			}
		}
		if(strongestC!=null){
			prm_currentPlayer.PlaceReinforcedArmiesOnCountry(strongestC.GetId(), prm_currentPlayer.GetArmies());
		}
		prm_currentPlayer.DoCardExchange();
		prm_currentPlayer.EndReinforcementPhase();
		Attack(prm_currentPlayer);
		return null;
	}

	@Override
	public String Attack(Player prm_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		Country strongestC = new Country();
		Country defenderC = new Country();
		int armies=0;
		for(Country c : prm_currentPlayer.map.GetBorderCountriesByPlayerId(prm_currentPlayer.GetId())){
			if(c.GetArmies()>=armies){
				strongestC=c;
				armies=c.GetArmies();
			}
		}
		if(strongestC!=null){
			while(strongestC.GetArmies()>2){
				armies=100000;
				for(Country defender : prm_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(strongestC.GetId())){
					if(defender.GetArmies()<=armies){
						defenderC=defender;
						armies=defender.GetArmies();
					}
				}
				if(defenderC!=null){
					prm_currentPlayer.PerformDeduction(prm_currentPlayer, strongestC, strongestC.GetArmies()>3?3:strongestC.GetArmies(), defenderC, strongestC.GetArmies()>2?2:strongestC.GetArmies());
				}
			}
		}
		prm_currentPlayer.EndAttackPhase();
    	prm_currentPlayer.Fortification();
		return null;
	}

	@Override
	public String Fortification(Player prm_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		Country strongestC = new Country();
		int armies=0;
		for(Country c : prm_currentPlayer.map.GetBorderCountriesByPlayerId(prm_currentPlayer.GetId())){
			if(c.GetArmies()>=armies){
				strongestC=c;
				armies=c.GetArmies();
			}
		}
		armies=2;
		Country countryS = null;
		if(strongestC!=null){
			for(Country neighbor : prm_currentPlayer.map.GetNeighborsByCountryIdSamePlayer(strongestC.GetId())){
				if(neighbor.GetArmies()>=armies){
					countryS=neighbor;
					armies=countryS.GetArmies();
				}
			}
		}
		if(strongestC!=null && countryS!=null){
			if(countryS.GetArmies()>2){
				prm_currentPlayer.MoveArmiesToCountryFromCountry(countryS.GetId(), strongestC.GetId(), countryS.GetArmies()-2);
			}
		}
		prm_currentPlayer.EndFortificationPhase();
		prm_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
		return "";
	}

}
