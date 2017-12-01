package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.risk.utility.LoggingWindow;
import com.risk.utility.TurnPhases;
/**this class represent a human player strategy implemented strategy pattern
 * 
 * @author Kourosh
 * @version 1.0.0.0
 */
public class HumanPlayer implements StrategyPlayer {
	public HumanPlayer(){
		
	}

	@Override
	/**reinforcement for human player
	 * 
	 */
	public String Reinforcement(Player prm_currentPlayer) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		int option = 1;
		Scanner inInteger = new Scanner(System.in); 
		Scanner inString = new Scanner(System.in); 
		Country weakest = new Country();
	    while(option==1 && prm_currentPlayer.GetArmies()>1){
			System.out.println("Enter options: 1:to reinforcement, 2:end reinforcement, 0:to quit the game");
			option = inInteger.nextInt();
			if(option==1){
				System.out.println("An option to reinforcement:");
				weakest = prm_currentPlayer.map.GetWeakestCountry(prm_currentPlayer.GetId());
				System.out.println("An option to reinforcement: candidate country: "+weakest.GetName()+", its armies: "+weakest.GetArmies()+" remaining armies of the player: "+prm_currentPlayer.GetArmies());
				System.out.println("Enter option: 1:To perform suggested reinforcement enter, 2:end reinforcement, 0:to quit");
				option = inInteger.nextInt();
				if(option==1){
					System.out.println("Enter number of armies to reinforcement between 1 and "+prm_currentPlayer.GetArmies());
					int armies = inInteger.nextInt();
					prm_currentPlayer.ReinforcementCountry(weakest.GetId(), armies);
				}
			}
		}
		// tbd
	    prm_currentPlayer.DoCardExchange();
	    prm_currentPlayer.EndReinforcementPhase();
	    Attack(prm_currentPlayer);
	    return "FailedNotEnoughArmies";
	}
	
	@Override
	/**attack for human player
	 * 
	 */
	public String Attack(Player prm_currentPlayer) throws Exception {
		String result = "";
		int quit = -1;
		Scanner inInteger = new Scanner(System.in); 
		Scanner inString = new Scanner(System.in); 
		Country weakest = new Country();
		int option=1;
	    while(option ==1){
	    	System.out.println("Enter options: 1:to attack, 2:end attack, 0:to quit the game");
			option = inInteger.nextInt();
			if(option==1){
				Country attackerC = prm_currentPlayer.map.GetBorderCountryByPlayerId(prm_currentPlayer.GetId());
				Country defenderC = prm_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(attackerC.GetId()).get(0);
				if(attackerC!=null && defenderC!=null){
					System.out.println("Declare attack(specifying the attacker and defender country):");
					System.out.println("A suggested option is: attacker country: "+attackerC.GetName()+" defender country: "+defenderC.GetName());
					System.out.println("Suggested option accepted: 1:yes, 0:no");
					option = inInteger.nextInt();
					if(option==1){
						int result1 = attackerC.GetArmies()>=3 ? 3:attackerC.GetArmies();
						int result2 = attackerC.GetArmies()>=2 ? 2:attackerC.GetArmies();
						System.out.println("Decide dices to roll: attacker dices<="+result1+" defender dices <=" +result2);
						System.out.println("enter attacker dice:");
						int attackerDice = inInteger.nextInt();
						System.out.println("enter defender dice:");
						int defenderDice = inInteger.nextInt();
						if(attackerDice<=result1 && defenderDice<=result2){
							prm_currentPlayer.PerformDeduction(prm_currentPlayer, attackerC, attackerDice, defenderC, defenderDice);
						}
						if(attackerC.GetArmies()<2) option=2;
					}
				}
			}
		}
	    if(option==2){
	    	prm_currentPlayer.EndAttackPhase();
	    	prm_currentPlayer.Fortification();
	    }
		return "";
	}
	
	
	@Override
	/**fortification for human player
	 * 
	 */
	public String Fortification(Player prm_currentPlayer) throws Exception {
		// TODO Auto-generated method stub
		// tbd
				if (prm_currentPlayer.turnOrganizer.GetCurrentPhase() != TurnPhases.Fortification)
					return "PhaseNotValid";
				Country countryS = prm_currentPlayer.map.GetHighArmiesCountry(prm_currentPlayer.GetId());
				if(countryS!=null){
					if(prm_currentPlayer.map.GetNeighborsByCountryIdSamePlayer(countryS.GetId()).size()>0){
						Country countryD = prm_currentPlayer.map.GetNeighborsByCountryIdSamePlayer(countryS.GetId()).get(0);
						int countrySArmies = prm_currentPlayer.map.GetCountryById(countryS.GetId()).GetArmies();
						int countryDArmies = prm_currentPlayer.map.GetCountryById(countryD.GetId()).GetArmies();
						if (!prm_currentPlayer.map.IsNeighborByCountryId(countryS.GetId(), countryD.GetId())) {
							LoggingWindow.Log("The two countries are not connected to move armies between them");
							return "Countries are not connected";
						}
						LoggingWindow.Log("The two countries are connected to move armies between them");
						int armies = countryS.GetArmies()>2?2:0;
						if (countrySArmies < armies-2) {
							LoggingWindow.Log("The source country does not have at least tw0 country after fortification");
							return "The source country should keep one country after fortification";
						}
						LoggingWindow.Log("The source country has at least one country after fortification");
						if (prm_currentPlayer.map.GetCountryById(countryS.GetId()).playerId != prm_currentPlayer.map.GetCountryById(countryD.GetId()).playerId) {
							LoggingWindow.Log("The two country for fortification do not belong the same player");
							return "The two country for fortification do not belong the same player";
						}
						prm_currentPlayer.map.GetCountryById(countryS.GetId()).AddArmies(-1 * armies);
						prm_currentPlayer.map.GetCountryById(countryD.GetId()).AddArmies(armies);
						if(prm_currentPlayer.turnOrganizer.IsAttackSuccessful()){
							LoggingWindow.Log(
									"The fortification is done, one card was drawn because a country was captured from attack and turn is ended");
						}
					}
					
				}
				prm_currentPlayer.EndFortificationPhase();
				prm_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
				return "Fortification is done successfully";
	}

}
