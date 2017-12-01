package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observer;

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
	public Attack attack;
	public Map map;
	public List<Card> deck;
	public TurnOrganizer turnOrganizer;
	public CardExchange cardExchange;
	public StrategyPlayer strategy;
	/**standard constructor for json use
	 * 
	 */
	public Player(){
		
	}

	/**
	 * this is the constructor of the class it takes id and name and will asign
	 * them to the player
	 * 
	 * @param prm_id
	 *            , which its type is integer, will br the id of the player
	 * @param prm_name
	 *            ,which its type is string, will be the name of the player
	 * @param prm_map
	 *            the map
	 * @param prm_deck
	 *            the deck
	 * @param prm_turnOrganizer
	 *            the turn organizer
	 * @param strategy type of strategy
	 */
	public Player(int prm_id, String prm_name, Map prm_map, List<Card> prm_deck, TurnOrganizer prm_turnOrganizer, String strategy) {
		this.id = prm_id;
		this.name = prm_name;
		armies = 0;
		armiesFromCards = 0;
		this.map = prm_map;
		this.deck = prm_deck;
		this.turnOrganizer = prm_turnOrganizer;
		setStrategy(strategy);
	}
	/**constrauctor with human as default
	 * this is the constructor of the class it takes id and name and will asign
	 * them to the player
	 * 
	 * @param prm_id
	 *            , which its type is integer, will br the id of the player
	 * @param prm_name
	 *            ,which its type is string, will be the name of the player
	 * @param prm_map
	 *            the map
	 * @param prm_deck
	 *            the deck
	 * @param prm_turnOrganizer
	 *            the turn organizer
	 */
	public Player(int prm_id, String prm_name, Map prm_map, List<Card> prm_deck, TurnOrganizer prm_turnOrganizer) {
		this.id = prm_id;
		this.name = prm_name;
		armies = 0;
		armiesFromCards = 0;
		this.map = prm_map;
		this.deck = prm_deck;
		this.turnOrganizer = prm_turnOrganizer;
		setStrategy("Human");
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
	 * is the id of the player
	 * 
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
	 * is the id of the player
	 * 
	 * @return the number of armies of the player
	 */
	public int GetArmiesFromCards() {
		return this.armiesFromCards;
	}

	/**
	 * this method decalres the attacke by taking the attacker and the defender
	 * countries' ids it creates the attack object
	 * 
	 * @param prm_attackerCountId
	 *            the id of the attacker country
	 * @param prm_defenderCountId
	 *            the id of the defender country
	 * @param defenderPlayer
	 *            is the defender player
	 * @return the instance of the attack object
	 * @throws IOException
	 *             if the game started
	 */
	public String DeclareAttack(int prm_attackerCountId, int prm_defenderCountId, Player defenderPlayer)
			throws IOException {
		// apply rules on the number of dices before rolling dices
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Attack)
			return "PhaseNotValid";
		// tbd check connectivity of two countries
		if (!this.map.IsAttackPossibleByPlayerId(this.id)) {
			LoggingWindow.Log("Attack is impossible, the attack phase is ended");
			EndAttackPhase();
			return "Attack is impossible, the attack phase is ended";
		}
		if (this.map.GetCountryById(prm_attackerCountId).GetArmies() < 2) {
			LoggingWindow.Log("The attacker country does not has equal or more than 2 armies");
			return "The attacker country does not have equal or more than two armies";
		}
		LoggingWindow.Log("The attacker country has equal or more than 2 armies");
		Country attackerCountry = this.map.GetCountryById(prm_attackerCountId);
		LoggingWindow.Log("check if the attacker country belongs to the attacker player");
		if (attackerCountry == null) {
			LoggingWindow.Log("The attacker country does not belong to the attacker player!");
			return "The attacker country does not belong to the attacker player";
		}
		Country defenderCountry = this.map.GetNeighborOpponentById(prm_attackerCountId, prm_defenderCountId);
		// verify the defender country is neighbor of attacker one
		if (defenderCountry == null) {
			LoggingWindow.Log("The defender country is not neighbor");
			return "the defender country is not neighbor";
		}
		// apply rules on the number of dices before rolling dices
		attack = new Attack(attackerCountry, defenderCountry, defenderPlayer);
		LoggingWindow.Log("Attack Declaration successfully done");
		return "Attack Declaration successfully done";
	}

	/**
	 * this method takes the number of dices for attacker and defender
	 * 
	 * @param prm_attackerDices
	 *            is the number of dices for attacker
	 * @param prm_defenderDices
	 *            is the number of dices for defender
	 * @return a successfull message
	 * @throws IOException
	 *             if the game started
	 */
	public String Attack(int prm_attackerDices, int prm_defenderDices) throws IOException {
		// apply rules on the number of dices before rolling dices
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Attack)
			return "PhaseNotValid";
		if (prm_attackerDices > 3 || prm_attackerDices < 1) {
			LoggingWindow.Log("The number of dices requested by attacker is more than 3 or less than 1");
			return "The number of dices requested by attacker is more than 3 or less than 1";
		}
		LoggingWindow.Log("The number of dices requested by attacker is in the range of 1 and 3");
		if (prm_defenderDices > 2 || prm_attackerDices < 1) {
			LoggingWindow.Log("The number of dices requested by defender is more than 2 or less than 1");
			return "The number of dices requested by defender is more than 2 or less than 1";
		}
		LoggingWindow.Log("The number of dices requested by defender is in the range 1 and 2");
		if (prm_attackerDices >= this.attack.attackerCountry.GetArmies()) {
			LoggingWindow.Log("The number of dices requested by attacker is more than or equal to its armies");
			return "The number of dices requested by attacker is more than or equal to its armies";
		}
		LoggingWindow.Log("The number of dices requested by attacker is less than  its armies");
		if (prm_defenderDices > this.attack.attackerCountry.GetArmies()) {
			LoggingWindow.Log("The number of dices requested by defender is more than its armies");
			return "The number of dices requested by defender is more than its armies";
		}
		LoggingWindow.Log("The number of dices requested by defender is less or equal than its armies");
		// create dices for the attacker and the defender
		this.attack.attackerDices = new ArrayList<Dice>(prm_attackerDices);
		for (int i = 1; i <= prm_attackerDices; i++) {
			this.attack.attackerDices.add(new Dice());
		}
		this.attack.defenderDices = new ArrayList<Dice>(prm_defenderDices);
		for (int i = 1; i <= prm_defenderDices; i++) {
			this.attack.defenderDices.add(new Dice());
		}
		// roll dices
		for (Dice d : this.attack.attackerDices)
			d.RollDice();
		for (Dice d : this.attack.defenderDices)
			d.RollDice();
		// sort dices for each of attacker and defender country
		Collections.sort(this.attack.attackerDices);
		Collections.reverse(this.attack.attackerDices);
		Collections.sort(this.attack.defenderDices);
		Collections.reverse(this.attack.defenderDices);
		String attackResult = DeductionArmiesFromAttck();
		if (attackResult.contains("AttackWon")) {
			LoggingWindow.Log("Propper number of armies deducted from defender and that added to the attacker country");
		} else {
			LoggingWindow.Log("Propper number of armies deducted from attacker and that added to the defender country");
		}
		if (this.attack.defenderCountry.GetArmies() == 0) {
			this.attack.isCaptured = true;
			this.map.ConquerCountry(this.attack.defenderCountry.GetId(), this.id);
			this.turnOrganizer.SetAttackSuccessful(true);
			LoggingWindow.Log("The defender country captured");
			if (this.map.IsContinentCaptured(this.id, this.attack.defenderCountry.GetContinentId())) {
				LoggingWindow.Log("The continent " + this.attack.defenderCountry.GetContinentId() + " is captured");
			}
			if (this.map.IsWorldCaptured(this.id)) {
				// end of the game
				EndGame();
				LoggingWindow.Log("The game is over and the player: " + this.id + " is winner");
				return "the game is over you won";
			} else {
				return "you captured the defender country now you should occupy it";
			}
		}
		if (!this.map.IsAttackPossibleByPlayerId(this.id)) {
			EndAttackPhase();
			LoggingWindow.Log("More attack is not possible and attack phase is ended");
			return "More ttack is not possible";
		}
		return "Attack is done";
	}

	public String OccupyCountry(int prm_armies) throws IOException {
		// validate occupation armies
		if (this.turnOrganizer.GetCurrentPhase() != TurnPhases.Attack) {
			return "the current phase is not attack";
		}
		if (this.attack == null) {
			return "Occupation is not valid";
		}
		if (prm_armies < 1) {
			return "The armies for occupation should be equal or more than 1";
		}
		if (prm_armies >= this.attack.attackerCountry.GetArmies()) {
			LoggingWindow.Log("At least one army should be left in attacker country after occupation");
			return "the number of occupied armies should be less than attacker country armies ";
		}
		if (prm_armies < this.attack.attackerDices.size()) {
			LoggingWindow.Log(
					"The number of armies to occupy captured country should not be less than the number of dice you rolled");
			return "the number of occupied armies should be more than attacker rolled dices";
		}
		LoggingWindow.Log(
				"The number of armies to occupy captured country is according the rules(equal or more than attacker dices) and at least one army left at the attacker country");
		// add armies to the captured country and take out the attacker country
		this.map.GetCountryByCountryId(attack.attackerCountry.GetId()).AddArmies(-1 * prm_armies);
		this.map.GetCountryByCountryId(attack.defenderCountry.GetId()).AddArmies(prm_armies);
		this.attack = null;
		LoggingWindow.Log("The captured country was occupied");
		if (!this.map.IsAttackPossibleByPlayerId(this.id)) {
			EndAttackPhase();
			LoggingWindow.Log("More attack is not possible and attack phase is ended");
			return "More ttack is not possible";
		}
		return "Occupation is done";
	}

	/**
	 * this method performs the attack action
	 * 
	 * @return the successful message if the attack is done
	 */
	public String DeductionArmiesFromAttck() {
		// decision the attack for the first roll
		String result = "";
		if (this.attack.attackerDices.get(0).GetDice() > this.attack.defenderDices.get(0).GetDice()) {
			this.map.AddArmiesToCountry(this.attack.attackerCountry.GetId(), 1);
			this.map.AddArmiesToCountry(this.attack.defenderCountry.GetId(), -1);
			result = "AttackWon";
		} else {
			this.map.AddArmiesToCountry(this.attack.attackerCountry.GetId(), -1);
			this.map.AddArmiesToCountry(this.attack.defenderCountry.GetId(), 1);
			result = "AttackLost";
		}
		// the second decision if each has two dices
		if (this.attack.attackerDices.size() >= 2 && this.attack.defenderDices.size() >= 2
				&& attack.defenderCountry.GetArmies() > 0) {
			if (this.attack.attackerDices.get(1).GetDice() > this.attack.defenderDices.get(1).GetDice()) {
				this.map.AddArmiesToCountry(this.attack.attackerCountry.GetId(), 1);
				this.map.AddArmiesToCountry(this.attack.defenderCountry.GetId(), -1);
				result = "AttackWon";
			} else if (attack.defenderCountry.GetArmies() > 0) {
				this.map.AddArmiesToCountry(this.attack.attackerCountry.GetId(), -1);
				this.map.AddArmiesToCountry(this.attack.defenderCountry.GetId(), 1);
				result = "AttackLost";
			}
		}
		return result;
	}

	public String EndAttackPhase() {
		this.attack = null;
		this.turnOrganizer.SetCurrentPhase(TurnPhases.Fortification);
		return "Attach is ended";
	}

	/**
	 * this method performs conquest the world by the player
	 * 
	 * @return the domination message
	 */
	public String EndGame() {
		turnOrganizer.SetCurrentPhase(TurnPhases.GameOver);
		turnOrganizer.SetCurrentPlayerId(this.id);
		return "World is dominated by player: " + this.name;
	}

	/**
	 * this method prepare the cardexchange observable for view
	 * 
	 * @param prm_o
	 *            is the view observer
	 */
	public void PrepareExchangeCards(Object prm_o) {
		this.cardExchange = new CardExchange(GetCardsByPlayerId(this.id));
		this.cardExchange.addObserver((Observer) prm_o);
	}

	/**
	 * this method destries the observable for card exchange view
	 * 
	 */
	public void EndExchangeCards() {
		this.cardExchange.deleteObservers();
		this.cardExchange = null;
	}

	/**
	 * this method returns the cards of a given player in his hand
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return list of the cards in hand
	 */
	public List<Card> GetCardsByPlayerId(int prm_playerId) {
		List<Card> cards = new ArrayList<Card>();
		for (Card c : deck) {
			if (c.playerId == prm_playerId)
				cards.add(c);
		}
		return cards;
	}

	/**
	 * this method performs card exchange during this action if the player has
	 * proper cards he can excange them with armies according the RISK rules
	 * 
	 * @param prm_card1
	 *            the first card
	 * @param prm_card2
	 *            the second card
	 * @param prm_card3
	 *            the third card
	 * @return succesful message
	 * @throws IOException
	 *             if the logging window file not exist
	 */
	public String ExchangeCards(Card prm_card1, Card prm_card2, Card prm_card3) throws IOException {
		if (IsThreeSameCards(prm_card1, prm_card2, prm_card3)) {
			DeckCard(prm_card1);
			DeckCard(prm_card2);
			DeckCard(prm_card3);
			this.AddArmiesFromCards(5);
			cardExchange.UpdateHandCards(GetCardsByPlayerId(this.GetId()));
			LoggingWindow.Log("Three same cards were exchanged successfully and 5 armies added to the player's armies: "+this.GetName());
			return "Successfully three same cards exchanged!";
		} else if (IsThreeDifferentCards(prm_card1, prm_card2, prm_card3)) {
			DeckCard(prm_card1);
			DeckCard(prm_card2);
			DeckCard(prm_card3);
			this.AddArmiesFromCards(5);
			cardExchange.UpdateHandCards(GetCardsByPlayerId(this.GetId()));
			LoggingWindow
					.Log("Three different cards were exchanged successfully and 5 armies added to the player's armies: "+this.GetName());
			return "Successfully three different cards exchanged!";
		} else {

			LoggingWindow.Log("Three cards are not the same or different types so can not exchanged for player: "+this.GetName());
			return "Failed exchange!";
		}
	}

	/**
	 * this method checks if the three cards are the same type
	 * 
	 * @param prm_card1Id
	 *            the first card to be exchange
	 * @param prm_card2Id
	 *            the second card to be exchange
	 * @param prm_card3Id
	 *            the third card to be exchange
	 * @return true if the three cards are the same type otherwise false
	 */
	public boolean IsThreeSameCards(Card prm_card1Id, Card prm_card2Id, Card prm_card3Id) {
		if (prm_card1Id.GetType().equals(prm_card2Id) && prm_card1Id.GetType().equals(prm_card3Id))
			return true;
		return false;
	}

	/**
	 * this method checks if the three cards are different type
	 * 
	 * @param prm_card1Id
	 *            the first card to be exchange
	 * @param prm_card2Id
	 *            the second card to be exchange
	 * @param prm_card3Id
	 *            the third card to be exchange
	 * @return true if the three cards are different type otherwise false
	 */
	public boolean IsThreeDifferentCards(Card prm_card1Id, Card prm_card2Id, Card prm_card3Id) {
		if (!prm_card1Id.GetType().equals(prm_card2Id) && !prm_card1Id.GetType().equals(prm_card3Id)
				&& !prm_card2Id.GetType().equals(prm_card3Id))
			return true;
		return false;
	}

	/**
	 * this method performs decking of the card that is to be exchanged
	 * 
	 * @param prm_card
	 *            the card to be decked
	 * @return successful message
	 */
	public String DeckCard(Card prm_card) {
		for (Card c : this.deck) {
			if (c.GetId() == prm_card.GetId()) {
				c.SetPlayerId(-1);
				return "The card" + prm_card.GetId() + "Successfully decked!";
			}
		}
		return "Failed decking the card" + prm_card.GetId();
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
		LoggingWindow.Log("Reinforcement country: " + prm_armies+" armies added to countryId: "+prm_countryId);
		if (this.GetArmies() >= prm_armies) {
			map.AddArmiesToCountry(prm_countryId, prm_armies);
			this.AddArmies(-1 * prm_armies);
			return "SuccessFullyArmiesAdded";
		} else
			return "FailedNotEnoughArmies";
	}

	/**
	 * this method calculate the number of armies that the player can have
	 * 
	 * which its type is playerId, is the id of the player
	 * 
	 */
	public void CalculateReinforcementArmies() {
		int armiesFromCountries = map.GetCountriesByPlayerId(this.id).size();
		armiesFromCountries /= 3;
		int armiesFromContinents = Calculate_ArmiesFromContinentControl(this.id);
		int totArmies = armiesFromCountries + armiesFromContinents + this.GetArmiesFromCards();
		this.SetArmiesToplayer(totArmies <= 3 ? 3 : totArmies);

	}

	/**
	 * this method calculates the number of armies from the continent control if
	 * the player has the whol continent
	 * 
	 * @param prm_playerId
	 *            is the id of the player under questio
	 * @return is the number of calulated srmies from continent controls
	 */
	public int Calculate_ArmiesFromContinentControl(int prm_playerId) {
		boolean doesBelong = true;
		;
		int totArmies = 0;
		for (Continent c : this.map.GetContinents()) {
			doesBelong = true;
			for (Country cy : this.map.GetCountriesByContinentId(c.GetContinentId())) {
				if (cy.GetPlayerId() != prm_playerId)
					doesBelong = false;
			}
			if (doesBelong)
				totArmies += c.GetControl();
		}
		return totArmies;
	}

	public String AddArmies(int prm_armies) {
		this.armies += prm_armies;
		return "ArmiesSuccessfullyAdded";
	}

	/**
	 * this method ends the reinforcement phase this set the turn-organizer to
	 * reinforcement phase also it causes the turn to change also it recalculate
	 * the armies for the current player
	 * 
	 * @return a successful message
	 * @throws Exception
	 *             if there is now card left
	 */
	public String EndFortificationPhase() throws Exception {
		String result = "NotSuccessfulAttack";
		if (turnOrganizer.IsAttackSuccessful()) {
			DrawACard(turnOrganizer.GetCurrentPlayerId());
			result = "SuccessfulAttack";
		}
		turnOrganizer.GetNextPlayerId();
		return result;
	}

	/**
	 * this method draws a card from deck to the player with specific id
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return 1 if the draw is successful otherwise 0
	 * @throws Exception
	 *             if there is no card to draw
	 */
	public int DrawACard(int prm_playerId) throws Exception {
		int result = 0;
		if (GetUnassignedCards().get(0) != null) {
			GetUnassignedCards().get(0).playerId = prm_playerId;
			if(this.cardExchange!=null){
				this.cardExchange.UpdateHandCards(GetCardsByPlayerId(this.id));
			}
			return result = 1;
		} else
			throw new Exception("DeckHasNoCard");
	}

	/**
	 * this method returns unassigned cards
	 * 
	 * @return a list of available cards
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
	 * @throws IOException
	 *             if the logging window file does not exist
	 * @throws IOException
	 *             if the current phase not Reinforcement
	 */
	public String PlaceReinforcedArmiesOnCountry(int prm_countryId, int prm_armies) throws IOException {
		String result = "";
		// tbd
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Reinforcement)
			return "PhaseNotValid";
		int countryArmies = map.GetCountryById(prm_countryId).GetArmies();
		if (this.armies >= prm_armies) {
			map.GetCountryById(prm_countryId).AddArmies(prm_armies);
			this.AddArmies(-1 * prm_armies);
			LoggingWindow.Log("The reinforcement armies: " + prm_armies + " were placed on country "
					+ map.GetCountryById(prm_countryId).GetName());
			result = "SuccessfullReinforcement";
		} else {
			LoggingWindow.Log("Reinforcement armies: " + prm_armies
					+ " is more than the armies the player has which is: " + this.armies);
			return "FailedNotEnoughArmies";
		}
		return result;
	}

	/**
	 * this method places armies on a country
	 * 
	 * @param prm_countryIdS
	 *            is the id of the country on which armies are moved
	 * @param prm_countryIdD
	 *            is the id of the country on which armies are placed
	 * @param prm_armies
	 *            the number of armies to be placed
	 * @return 1 if it is succesful otherwise 0
	 * @throws Exception
	 *             if the current phase not Fortification
	 */
	public String MoveArmiesToCountryFromCountry(int prm_countryIdS, int prm_countryIdD, int prm_armies)
			throws Exception {
		// tbd
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Fortification)
			return "PhaseNotValid";
		int countrySArmies = map.GetCountryById(prm_countryIdS).GetArmies();
		int countryDArmies = map.GetCountryById(prm_countryIdD).GetArmies();
		if (!this.map.IsNeighborByCountryId(prm_countryIdS, prm_countryIdD)) {
			LoggingWindow.Log("The two countries are not connected to move armies between them");
			return "Countries are not connected";
		}
		LoggingWindow.Log("The two countries are connected to move armies between them");
		if (countrySArmies < prm_armies) {
			LoggingWindow.Log("The source country does not have at least one country after fortification");
			return "The source country should keep one country after fortification";
		}
		LoggingWindow.Log("The source country has at least one country after fortification");
		if (map.GetCountryById(prm_countryIdS).playerId != map.GetCountryById(prm_countryIdD).playerId) {
			LoggingWindow.Log("The two country for fortification do not belong the same player");
			return "The two country for fortification do not belong the same player";
		}
		map.GetCountryById(prm_countryIdS).AddArmies(-1 * prm_armies);
		map.GetCountryById(prm_countryIdD).AddArmies(prm_armies);
		String attackSuccessful = EndFortificationPhase();
		if (attackSuccessful.contains("SuccessfulAttack")) {
			LoggingWindow.Log(
					"The fortification is done, one card was drawn because a country was captured from attack and turn is ended");
		}
		LoggingWindow
				.Log("The fortification is done, no card was drawn because no country captured and turn is ended ");
		return "Fortification is done successfully";
	}

	// tbd
	/**
	 * this method ends the reinforcement phase this set the turn organizer to
	 * fortification phase
	 */
	public void EndReinforcementPhase() {
		turnOrganizer.SetCurrentPhase(TurnPhases.Attack);
	}
	/**this method checks if there is three same card or three different card and returns them
	 * 
	 * @return three cards to exchange
	 */
	public List<Card> GetThreeCardsToExchange(){
		List<Card> cards = new ArrayList<Card>();
		List<Card> artilleries = new ArrayList<Card>();
		List<Card> cavalries = new ArrayList<Card>();
		List<Card> infantries = new ArrayList<Card>();
		for(Card c : this.GetCardsByPlayerId(this.GetId())){
			switch(c.GetType()){
			case Artillery:
				artilleries.add(c);
				break;
			case Cavalry:
				cavalries.add(c);
				break;
			case Infantry:
				infantries.add(c);
				break;
			}
		}
		if(artilleries.size()>=3){
			for(int i=0;i<3;i++){
				cards.add(artilleries.get(i));
			}
		} 
		else if(cavalries.size()>=3){
			for(int i=0;i<3;i++){
				cards.add(cavalries.get(i));
			}
		} 
		else if(infantries.size()>=3){
			for(int i=0;i<3;i++){
				cards.add(infantries.get(i));
			}
		} 
		if(cards.size()<3){
			if(artilleries.size()>1 && cavalries.size()>1 && infantries.size()>1){
				cards = new ArrayList<Card>();
				cards.add(artilleries.get(0));
				cards.add(cavalries.get(0));
				cards.add(infantries.get(0));
			}
		}
		return cards;
	}
	/**this method performs automatic card exchange
	 * 
	 * @return successful message
	 * @throws IOException if the window logging not available
	 */
	public String DoCardExchange() throws IOException{
		List<Card> cardToDo = this.GetThreeCardsToExchange();
		if(cardToDo.size()==3){
			CardExchangeObserver cardExchangeObserver = new CardExchangeObserver();
			this.PrepareExchangeCards(cardExchangeObserver);
			this.cardExchange.UpdateHandCards(this.GetCardsByPlayerId(this.GetId()));
			this.ExchangeCards(cardToDo.get(0),
					cardToDo.get(1),
					cardToDo.get(2));
			this.EndExchangeCards();
			return "CardExchangeDone";
		}
		return null;
	}
	/**this method deligates the reinforcement to the strategy
	 * 
	 * @return result message
	 * @throws IOException if the looging window does not exist
	 * @throws Exception general exception
	 */
	public String Reinforcement() throws IOException, Exception{
		if(this.turnOrganizer.turn<this.turnOrganizer.turnMax){
		strategy.Reinforcement(this);
		}
		else{
			this.turnOrganizer.winner = "Draw";
		}
		return "";
	}
	/**this method deligates attack to strategy
	 * 
	 * @return successful message
	 * @throws IOException if logging window does not exist
	 * @throws Exception if strategy not plugged in
	 */
	public String Attack() throws IOException, Exception{
		strategy.Attack(this);
		return "";
	}
	/**this method performs the armies deduction
	 * 
	 * @param prm_currentPlayer the current player
	 * @param attackerC attacker country
	 * @param attackerDice attacker dice
	 * @param defenderC defender country
	 * @param defenderDice defender dice
	 * @return successful message
	 * @throws Exception if the logging window does not exist
	 */
	public String PerformDeduction(Player prm_currentPlayer,Country attackerC, int attackerDice, Country defenderC, int defenderDice) throws Exception{
		List<Dice> attackerDices = new ArrayList<Dice>(attackerDice);
		for (int i = 1; i <= attackerDice; i++) {
			attackerDices.add(new Dice());
		}
		List<Dice> defenderDices = new ArrayList<Dice>(defenderDice);
		for (int i = 1; i <= defenderDice; i++) {
			defenderDices.add(new Dice());
		}
		// roll dices
		for (Dice d : attackerDices)
			d.RollDice();
		for (Dice d : defenderDices)
			d.RollDice();
		// sort dices for each of attacker and defender country
		Collections.sort(attackerDices);
		Collections.reverse(attackerDices);
		Collections.sort(defenderDices);
		Collections.reverse(defenderDices);
		LoggingWindow.Log("Attack: attacker dices are: "+attackerDice+"defender dices are "+defenderDice);
		if (attackerDices.get(0).GetDice() > defenderDices.get(0).GetDice()) {
			prm_currentPlayer.map.AddArmiesToCountry(attackerC.GetId(), 1);
			prm_currentPlayer.map.AddArmiesToCountry(defenderC.GetId(), -1);
			String deductionResult = "AttackWon";
			LoggingWindow.Log("Propper number of 1 armies deducted from defender: "+defenderC.GetName()+" and that added to the attacker : "+attackerC.GetName()+"country");
		} else {
			prm_currentPlayer.map.AddArmiesToCountry(attackerC.GetId(), -1);
			prm_currentPlayer.map.AddArmiesToCountry(defenderC.GetId(), 1);
			String deductionResult = "AttackLost";
			LoggingWindow.Log("Propper number of 1 armies deducted from attacker: "+attackerC.GetName()+" and that added to the defender: "+defenderC.GetName()+" country");
		}
		// the second decision if each has two dices
		if (attackerDices.size() >= 2 && defenderDices.size() >= 2
				&& defenderC.GetArmies() > 0) {
			if (attackerDices.get(1).GetDice() > defenderDices.get(1).GetDice()) {
				prm_currentPlayer.map.AddArmiesToCountry(attackerC.GetId(), 1);
				prm_currentPlayer.map.AddArmiesToCountry(defenderC.GetId(), -1);
				String deductionResult = "AttackWon";
				LoggingWindow.Log("Propper number of 1 armies deducted from defender: "+defenderC.GetName()+" and that added to the attacker: "+attackerC.GetName()+" country");
			} 
			else if (defenderC.GetArmies() > 0) {
				prm_currentPlayer.map.AddArmiesToCountry(attackerC.GetId(), -1);
				prm_currentPlayer.map.AddArmiesToCountry(defenderC.GetId(), 1);
				String deductionResult = "AttackLost";
				LoggingWindow.Log("Propper number of 1 armies deducted from attacker: "+attackerC.GetName()+" and that added to the defender: "+defenderC.GetName()+" country");
			}
		}
		if (defenderC.GetArmies() == 0) {
			prm_currentPlayer.turnOrganizer.isAttackSuccessfull=true;
			prm_currentPlayer.map.ConquerCountry(defenderC.GetId(), prm_currentPlayer.GetId());
			prm_currentPlayer.turnOrganizer.SetAttackSuccessful(true);
			LoggingWindow.Log("The defender: "+defenderC.GetName()+" country captured");
			//occupy the defender
			int occypyArmies = attackerC.GetArmies()>=2?attackerC.GetArmies()-2:0;
			this.map.GetCountryByCountryId(attackerC.GetId()).AddArmies(-1 * occypyArmies);
			this.map.GetCountryByCountryId(defenderC.GetId()).AddArmies(occypyArmies);
			LoggingWindow.Log("The defender: "+defenderC.GetName()+" country occupied by player"+prm_currentPlayer.GetName());
			if (prm_currentPlayer.map.IsContinentCaptured(prm_currentPlayer.GetId(), defenderC.GetContinentId())) {
				LoggingWindow.Log("The continent " + defenderC.GetContinentId() + " is captured");
			}
			if (prm_currentPlayer.map.IsWorldCaptured(prm_currentPlayer.GetId())) {
				// end of the game
				prm_currentPlayer.EndGame();
				LoggingWindow.Log("The game is over and the player: " + prm_currentPlayer.GetId() + " is winner");
				return "the game is over you won";
			} else {
				return "you captured the defender country now you should occupy it";
			}
		}
		if (!prm_currentPlayer.map.IsAttackPossibleByPlayerId(prm_currentPlayer.GetId())) {
			prm_currentPlayer.EndAttackPhase();
			LoggingWindow.Log("More attack is not possible and attack phase is ended");
			return "More ttack is not possible";
		}
		return "";
	}
	/**this method sets the strategy for the player
	 * 
	 * @param strategy name of the strategy
	 * @return the strategy object
	 */
	public StrategyPlayer setStrategy(String strategy){
		switch(strategy){
		case "Human":
			return this.strategy=new HumanPlayer();
		case "Aggressive":
			return this.strategy=new AggressivePlayer();
		case "Benevolent":
			return this.strategy=new BenevolentPlayer();
		case "Random":
			return this.strategy=new RandomPlayer();
		case "Cheater":
			return this.strategy=new CheaterPlayer();
		default:
			return null;
		}
	}
	public String Fortification() throws IOException, Exception{
		this.strategy.Fortification(this);
		return "";
	}

}
