package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.risk.utility.LoggingWindow;

public class CheaterPlayer implements StrategyPlayer{

	@Override
	public String Reinforcement(Player prm_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		for(Country c : prm_currentPlayer.map.GetCountriesByPlayerId(prm_currentPlayer.GetId())){
			c.AddArmies(c.GetArmies());
		}
		prm_currentPlayer.DoCardExchange();
		prm_currentPlayer.EndReinforcementPhase();
		Attack(prm_currentPlayer);
		return "";
	}

	@Override
	public String Attack(Player prm_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		List<Country> neighbors = new ArrayList<Country>();
		for(Country c : prm_currentPlayer.map.GetCountriesByPlayerId(prm_currentPlayer.GetId())){
			for(Country neighbor : prm_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(c.GetId())){
				neighbors.add(neighbor);
			}
		}
		for(Country c : neighbors){
			prm_currentPlayer.map.ConquerCountry(c.GetId(), prm_currentPlayer.GetId());
			LoggingWindow.Log("country: "+c.GetName()+" occupied by player"+prm_currentPlayer.GetName());
		}
		boolean isWorldCaptured = prm_currentPlayer.map.IsWorldCaptured(prm_currentPlayer.GetId());
		if(isWorldCaptured){
			System.out.println("Game is ended the winner is: " + prm_currentPlayer.GetName());
			prm_currentPlayer.turnOrganizer.winner=prm_currentPlayer.GetName();
			return "";
		}
		prm_currentPlayer.EndAttackPhase();
		prm_currentPlayer.turnOrganizer.isAttackSuccessfull=true;
		Fortification(prm_currentPlayer);
		return null;
	}

	@Override
	public String Fortification(Player prm_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		for(Country c: prm_currentPlayer.map.GetBorderCountriesByPlayerId(prm_currentPlayer.GetId())){
			c.AddArmies(c.GetArmies());
		}
		prm_currentPlayer.EndFortificationPhase();
		prm_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
		return "";
	}

}
