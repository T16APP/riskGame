package com.risk.model;

import java.io.IOException;
import java.util.Random;

public class RandomPlayer implements StrategyPlayer{

	@Override
	public String Reinforcement(Player prm_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		int numCountries = prm_currentPlayer.map.GetCountriesByPlayerId(prm_currentPlayer.GetId()).size();
		if(numCountries>0){
			Random rand = new Random();
			int  n = rand.nextInt(numCountries);
			int numReinforcement = prm_currentPlayer.GetArmies();
			int randomReinforcement = rand.nextInt(numReinforcement);
			if(n>0){
				Country randomC = prm_currentPlayer.map.GetCountriesByPlayerId(prm_currentPlayer.GetId()).get(n);
				if(randomC!=null && numReinforcement>0){
					prm_currentPlayer.PlaceReinforcedArmiesOnCountry(randomC.GetId(), randomReinforcement);
				}
				prm_currentPlayer.PlaceReinforcedArmiesOnCountry(randomC.GetId(), randomReinforcement);
			}
		}
		prm_currentPlayer.DoCardExchange();
		prm_currentPlayer.EndReinforcementPhase();
		Attack(prm_currentPlayer);
		return null;
	}

	@Override
	public String Attack(Player prm_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		Random randomNum = new Random();
		int numCountries = prm_currentPlayer.map.GetBorderCountriesByPlayerId(prm_currentPlayer.GetId()).size();
		if(numCountries>0){
			Random rand = new Random();
			int  n = rand.nextInt(numCountries);
			if(n>0){
				Country randomC = prm_currentPlayer.map.GetBorderCountriesByPlayerId(prm_currentPlayer.GetId()).get(n);
				int nDefender = rand.nextInt(prm_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(randomC.GetId()).size());
				Country defenderC = prm_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(randomC.GetId()).get(nDefender);
				while(randomC.GetArmies()>2 && defenderC.playerId!=prm_currentPlayer.GetId() && randomNum.nextInt(2)==1){
					prm_currentPlayer.PerformDeduction(prm_currentPlayer, randomC, randomC.GetArmies()>3?3:randomC.GetArmies(), defenderC, randomC.GetArmies()>2?2:randomC.GetArmies());
					
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
		int numCountries = prm_currentPlayer.map.GetCountriesByPlayerId(prm_currentPlayer.GetId()).size();
		if(numCountries>0){
			Random rand = new Random();
			int  n = rand.nextInt(numCountries);
			int numReinforcement = prm_currentPlayer.GetArmies();
			int randomReinforcement = rand.nextInt(numReinforcement);
			Country randomC = prm_currentPlayer.map.GetCountriesByPlayerId(prm_currentPlayer.GetId()).get(n);
			prm_currentPlayer.PlaceReinforcedArmiesOnCountry(randomC.GetId(), randomReinforcement);
		}
		prm_currentPlayer.EndFortificationPhase();
		prm_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
		return null;
	}

}
