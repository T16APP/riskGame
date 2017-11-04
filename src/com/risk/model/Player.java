package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.risk.utility.LoggingWindow;
import com.risk.utility.TurnPhases;

/**
 * This class represents a player it maintains id and name of the player also it
 * has different methods to change the status of the object
 * 
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */

public class Player {
	private int id;
	private String name;
	private int armies;
	private int armiesFromCards;
	private Attack attack;
    private Map map;
    private List<Card> deck;
    public TurnOrganizer turnOrganizer;
	/**
	 * this is the constructor of the class it takes id and name and will asign them
	 * to the player
	 * 
	 * @param prm_id
	 *            , which its type is integer, will br the id of the player
	 * @param prm_name,
	 *            which its type is string, will be the name of the player
	 */
	public Player(int prm_id, String prm_name, Map prm_map, List<Card> prm_deck, TurnOrganizer prm_turnOrganizer) {
		this.id = prm_id;
		this.name = prm_name;
		armies = 0;
		armiesFromCards = 0;
		this.map=prm_map;
		this.deck=prm_deck;
		this.turnOrganizer=prm_turnOrganizer;
	}

	/**
	 * this method returns the id of the player
	 * 
	 * @return, which its type is integer, is the id of the player
	 */
	public int GetId() {
		return this.id;
	}

	/**
	 * this method returns the name of the player
	 * 
	 * @return, which its type is string, is the name of the player
	 */
	public String GetName() {
		return this.name;
	}

	
	/**
	 * this method sets the number of armies of the player
	 * 
	 * @param prm_armies,
	 *            which its type is integer, will assign to the player
	 */
	public void SetArmiesToplayer(int prm_armies) {
		this.armies = prm_armies;
	}

	/**
	 * this method returns the number of armies that the player has
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return the number of armies of the player
	 */
	public int GetArmies() {
		return this.armies;
	}

	/**
	 * this method sets the number of armies
	 * 
	 * @param prm_armies
	 *            is the number of new armies
	 */
	public void SetArmies(int prm_armies) {
		this.armies = prm_armies;
	}

	/**
	 * this methos adds the armies exchanged with cards
	 * 
	 * @param prm_armies
	 *            which is the number of armies that will be added to the player
	 */
	public void AddArmiesFromCards(int prm_armies) {
		this.armiesFromCards += prm_armies;
		// tbd
		this.armies += prm_armies;
	}

	/**
	 * this method returns the number of armiesFromCards that the player has
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return the number of armies of the player
	 */
	public int GetArmiesFromCards() {
		return this.armiesFromCards;
	}
	/**this method decalres the attacke by taking the attacker and the defender countries' ids
	 * it creates the attack object
	 * @param prm_ackerCountId the id of the attacker country
	 * @param prm_defenderCountId the id of the defender country
	 * @return the instance of the attack object
	 * @throws IOException 
	 */
	public String DeclareAttack(int prm_attackerCountId, int prm_defenderCountId, Player defenderPlayer) throws IOException{
		//tbd check connectivity of two countries
		if(attack!=null){
			return "Attack declaration already done!";
		}
		else{
			Country attackerCountry = this.map.GetCountryById(prm_attackerCountId);
			LoggingWindow.Log("check if the attacker country belongs to the attacker player");
			if(attackerCountry==null) return "The attacker country does not belong to the attacker player!";
			Country defenderCountry = this.map.GetNeighborOpponentById(prm_attackerCountId,prm_defenderCountId);
			//verify the defender country is neighbor of attacker one
			if(defenderCountry==null) return "the defender country is not neighbor!";
			//apply rules on the number of dices before rolling dices
			if(attackerCountry.GetArmies()<2) return "The attacker country does not have more than two armies";
			attack=new Attack( attackerCountry,defenderCountry,  defenderPlayer);
		     return "Attack Declaration successfully done!";
		}
		
	}
	/**this method takes the number of dices for attacker and defender
	 * @param prm_attackerDices is the number of dices for attacker
	 * @param prm_defenderDices is the number of dices for defender
	 */
	public String Attack(int prm_attackerDices,int prm_defenderDices){
		//apply rules on the number of dices before rolling dices
		if(prm_attackerDices>3 || prm_attackerDices<1 ) return "The number of dices requested by attacker is more than 3 or less than 1!";
		if(prm_defenderDices>2 || prm_attackerDices<1 ) return "The number of dices requested by defender is more than 2 or less than 1!";
		if(prm_attackerDices>=this.attack.attackerCountry.GetArmies()) return "The number of dices requested by attacker is more than or equal to its armies!";
		if(prm_defenderDices>this.attack.attackerCountry.GetArmies()) return "The number of dices requested by defender is more than its armies!";
		//create dices for the attacker and the defender
		this.attack.attackerDices = new ArrayList<Dice>(prm_attackerDices);
		this.attack.defenderDices = new ArrayList<Dice>(prm_defenderDices);
		//roll dices
		for(Dice d : this.attack.attackerDices) d.RollDice();
		for(Dice d : this.attack.defenderDices) d.RollDice();
		//sort dices for each of attacker and defender country
		Collections.sort(this.attack.attackerDices);
		Collections.reverse(this.attack.attackerDices);
		Collections.sort(this.attack.defenderDices);
		Collections.reverse(this.attack.defenderDices);
		DeductionArmiesFromAttck();
		if(this.attack.defenderCountry.GetArmies()==0){
			this.map.ConquerCountry(this.attack.defenderCountry.GetId(),this.id);
			if(this.map.IsContinentCaptured(this.id, this.attack.defenderCountry.GetContinentId())){
				
			}
			if(this.map.IsWorldCaptured(this.id)){
				//end of the game
			}
		}
		return "Attack is done";
	}
	/** this method performs the attack action
	 * 
	 * @return the successful message if the attack is done
	 */
	public String DeductionArmiesFromAttck(){
		//decision the attack for the first roll
		if(this.attack.attackerDices.get(0).GetDice()>this.attack.defenderDices.get(0).GetDice()){
			this.attack.attackerCountry.AddArmies(1);
			this.attack.defenderCountry.AddArmies(-1);
		}
		else{
			this.attack.attackerCountry.AddArmies(-1);
			this.attack.defenderCountry.AddArmies(1);
		}
		//the second decision if each has two dices
		if(this.attack.attackerDices.size()>=2 && this.attack.defenderDices.size()>=2){
			if(this.attack.attackerDices.get(1).GetDice()>this.attack.defenderDices.get(1).GetDice()){
				this.attack.attackerCountry.AddArmies(1);
			    this.attack.defenderCountry.AddArmies(-1);
			}
		    else{
                this.attack.attackerCountry.AddArmies(-1);
			    this.attack.defenderCountry.AddArmies(1);
		    }
		}
		return "The attack successfully is done!";
	}
	
	
	
	/**this method performs conquest of the continent
	 * 
	 * @param map
	 * @return
	 */
	public String ConquerContinent(){
		Continent conqContinent = this.map.GetContinentById(this.attack.defenderCountry.GetContinentId());
		boolean isContinentCaptured = true;
		for(Country c : this.map.GetCountriesByContinentId(conqContinent.GetId())){
			if(c.GetPlayerId()!=this.id) isContinentCaptured=false;
		}
		if(isContinentCaptured){
			this.map.TakeControlOfContinent(conqContinent.GetId(),this.id);
			return "The continent" +conqContinent.GetName()+" captured by"+this.name+CaptureWorld();
		}
		return "";
	}
	/**this method performs conquest the world by the player
	 * 
	 * @param prm_map is the map containing all countries and continents
	 * @return the domination message
	 */
	public String CaptureWorld(){
		boolean isWorldCaptured = true;
		for(Continent c : this.map.GetContinents()){
			if(c.GetPlayerId()!=this.id) isWorldCaptured=false;
		}
		if(isWorldCaptured){
			return "World is dominated by player: "+this.name;
		}
		return "";
	}
	/**this method performs card exchange
	 * during this action if the player has proper cards he can excange them with armies according the RISK rules
	 * @param prm_card1 the first card
	 * @param prm_card2 the second card
	 * @param prm_card3 the third card
	 * @return succesful message
	 */
	public String ExchangeCards(Card prm_card1, Card prm_card2, Card prm_card3){
		if(IsThreeSameCards( prm_card1,  prm_card2,  prm_card3)){
			DeckCard(prm_card1);
			DeckCard(prm_card2);
			DeckCard(prm_card3);
			this.AddArmiesFromCards(5);
			return "Successfully three same cards exchanged!";
		}
		else if(IsThreeDifferentCards( prm_card1,  prm_card2,  prm_card3)){
			DeckCard(prm_card1);
			DeckCard(prm_card2);
			DeckCard(prm_card3);
			this.AddArmiesFromCards(5);
			return "Successfully three different cards exchanged!";
		}
		else{
			
			return "Failed exchange!";
		}
	}
	/**
	 * this method checks if the three cards are the same type
	 * 
	 * @param prm_card1Id the first card to be exchange
	 * @param prm_card2Id the second card to be exchange
	 * @param prm_card3Id the third card to be exchange
	 * @return true if the three cards are the same type 
	 * otherwise false
	 */
	public boolean IsThreeSameCards(Card prm_card1Id, Card prm_card2Id, Card prm_card3Id){
		if(prm_card1Id.GetType().equals(prm_card2Id) && prm_card1Id.GetType().equals(prm_card3Id))
			return true;
		return false;
	}
	/**
	 * this method checks if the three cards are different type
	 * 
	 * @param prm_card1Id the first card to be exchange
	 * @param prm_card2Id the second card to be exchange
	 * @param prm_card3Id the third card to be exchange
	 * @return true if the three cards are different type 
	 * otherwise false
	 */
	public boolean IsThreeDifferentCards(Card prm_card1Id, Card prm_card2Id, Card prm_card3Id){
		if(!prm_card1Id.GetType().equals(prm_card2Id) && !prm_card1Id.GetType().equals(prm_card3Id) && !prm_card2Id.GetType().equals(prm_card3Id))
			return true;
		return false;
	}
	/**this method performs decking of the card that is to be exchanged
	 * 
	 * @param prm_card the card to be decked
	 * @return successful message
	 */
	public String DeckCard(Card prm_card){
		for(Card c : this.deck){
			if(c.GetId()==prm_card.GetId()){
				c.SetPlayerId(-1);
				return "The card"+prm_card.GetId()+"Successfully decked!";
			}
		}
		return "Failed decking the card"+prm_card.GetId();
	}
	/**
	 * this method places armies on a country
	 * 
	 * @param prm_countryId
	 *            is the id of the country on which armies are placed
	 * @param prm_armies
	 *            the number of armies to be placed
	 * @return 1 if it is succesful otherwise 0
	 * @throws Exception
	 *             if the current phase not Reinforcement
	 */
	public String ReinforcementCountry(int prm_countryId, int prm_armies) throws Exception {
		String result = "";
		// tbd
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Reinforcement)
			return "PhaseNotValid";
		int countryArmies = map.GetCountryById(prm_countryId).GetArmies();
		if (this.GetArmies() >= prm_armies) {
			map.AddArmiesToCountry(prm_countryId, prm_armies);
			this.AddArmies(-1*prm_armies);
			return "SuccessFullyArmiesAdded";
		} else
			return "FailedNotEnoughArmies";
	}
	/**
	 * this method calculate the number of armies that the player can have
	 * 
	 * @param ,
	 *            which its type is playerId, is the id of the player
	 * 
	 */
	public void CalculateReinforcementArmies() {
		int armiesFromCountries = map.GetCountriesByPlayerId(this.id).size();
		armiesFromCountries /= 3;
		int armiesFromContinents=Calculate_ArmiesFromContinentControl(this.id);
		int totArmies=armiesFromCountries+armiesFromContinents+this.GetArmiesFromCards();
		this.SetArmiesToplayer(totArmies <= 3 ? 3 : totArmies);

	}
	/**this method calculates the number of armies from
	 * the continent control if the player has the whol continent
	 * @param prm_playerId is the id of the player under questio
	 * @return is the number of calulated srmies from continent controls
	 */
	public int Calculate_ArmiesFromContinentControl(int prm_playerId){
		boolean doesBelong = true;;
		int totArmies=0;
		for(Continent c: this.map.GetContinents()){
			doesBelong=true;
			for(Country cy : this.map.GetCountriesByContinentId(c.GetContinentId())){
				if(cy.GetPlayerId()!=prm_playerId)
					doesBelong=false;
			}
			if(doesBelong)
				totArmies+=c.GetControl();
		}
		return totArmies;		
	}
	public String AddArmies(int prm_armies){
		this.armies += prm_armies;
		return "ArmiesSuccessfullyAdded";
	}
	/**
	 * this method ends the reinforcement phase this set the turn-organizer to
	 * reinforcement phase also it causes the turn to change also it recalculate the
	 * armies for the current player
	 * 
	 * @throws Exception
	 *             if there is now card left
	 */
	public void EndFortificationPhase() throws Exception {
		// this is only for build one to show cards flow and it is supposed that a
		// successful attack is done
		if (turnOrganizer.IsAttackSuccessful()) {
			DrawACard(turnOrganizer.GetCurrentPlayerId());
		}
		turnOrganizer.SetCurrentPhase(TurnPhases.Reinforcement);
		turnOrganizer.GetNextPlayerId();
		// tbd
		// CalculateArmies(GetPlayerById(turnOrganizer.GetCurrentPlayerId()));
	}
	/**
	 * this method draws a card from deck to the player with specific id
	 * 
	 * @param prm_playerId
	 * @throws Exception
	 *             if there is no card to draw
	 */
	public int DrawACard(int prm_playerId) throws Exception {
		int result = 0;
		if (GetUnassignedCards().get(0) != null) {
			GetUnassignedCards().get(0).playerId = prm_playerId;
			return result = 1;
		} else
			throw new Exception("DeckHasNoCard");
	}
	/**
	 * this method returns unassigned cards
	 * 
	 */
	public List<Card> GetUnassignedCards() {
		List<Card> cards = new ArrayList<Card>();
		for (Card c : deck) {
			if (c.playerId == -1)
				cards.add(c);
		}
		return cards;
	}
	/**
	 * this method places armies on a country
	 * 
	 * @param prm_countryId
	 *            is the id of the country on which armies are placed
	 * @param prm_armies
	 *            the number of armies to be placed
	 * @return 1 if it is succesful otherwise 0
	 * @throws Exception
	 *             if the current phase not Reinforcement
	 */
	public String PlaceReinforcedArmiesOnCountry(int prm_countryId, int prm_armies) {
		String result = "";
		// tbd
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Reinforcement)
			return "PhaseNotValid";
		int countryArmies = map.GetCountryById(prm_countryId).GetArmies();
		if (this.armies >= prm_armies) {
			map.GetCountryById(prm_countryId).SetArmies(countryArmies + prm_armies);
			this.AddArmies(-1*prm_armies);
			result = "SuccessfullReinforcement";
		} else
			return "FailedNotEnoughArmies";
		return result;
	}

	
}
