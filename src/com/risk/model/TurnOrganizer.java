package com.risk.model;

import java.util.List;
import java.util.Observable;

import com.risk.utility.TurnPhases;

/**
 * This class maintains the turn and phase status
 * 
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */
public class TurnOrganizer extends Observable {
	private boolean isMapLoaded;
	private boolean isGameStarted;
	private int currentPlayerId;
	private TurnPhases currentPhase;
	public boolean isAttackSuccessfull;
	private String currentAction;
	public List<Player> players;
	public List<Integer> roundRobin;
	public int turn;
	public int turnMax;
	public String winner;

	/**
	 * This the constructor of the class it initialize properties of the object
	 */
	public TurnOrganizer() {
		isMapLoaded = false;
		isGameStarted = false;
		currentPlayerId = -1;
		currentPhase = TurnPhases.PreGame;
		/**
		 * missing this should change once attack feature added
		 * 
		 */
		isAttackSuccessfull = true;
		this.turnMax=100000000;
	}
	public TurnOrganizer(int prm_turnMax) {
		isMapLoaded = false;
		isGameStarted = false;
		currentPlayerId = -1;
		currentPhase = TurnPhases.PreGame;
		/**
		 * missing this should change once attack feature added
		 * 
		 */
		isAttackSuccessfull = true;
		this.turnMax=prm_turnMax;
		this.turn=1;
	}


	/**
	 * This method set map-loaded flag
	 * 
	 */
	public void MapLoaded() {
		this.isMapLoaded = true;
	}

	/**
	 * This method set game-started flag
	 * 
	 */
	public void GameStarted() {
		this.isGameStarted = true;
	}

	/**
	 * This method returns the current phase
	 * 
	 * @return , which its type is enum, is the current phase of the game
	 * 
	 */
	public TurnPhases GetCurrentPhase() {
		return this.currentPhase;
	}

	// tbd
	// in the build1 we didn't have phase attack, however in the future it
	// should be
	// implementd
	/**
	 * This method compute the next phase and return it
	 * 
	 * @return , which its type is enum, the current phase of the game
	 * 
	 */
	public TurnPhases GetNextPhase() {
		switch (this.currentPhase) {
		case Startup:
			return TurnPhases.Reinforcement;
		case Reinforcement:
			return TurnPhases.Fortification;
		case Fortification:
			return TurnPhases.Reinforcement;
		default:
			return TurnPhases.PreGame;
		}
	}

	/**
	 * this method returns the current playerId
	 * 
	 * @return the id of the current player
	 */
	public int GetCurrentPlayerId() {
		return currentPlayerId;
	}

	/**
	 * this method verifies if there was a successful attack attack
	 * 
	 * @return true if a country is captured
	 */
	public boolean IsAttackSuccessful() {
		return this.isAttackSuccessfull;
	}

	public void SetAttackSuccessful(boolean prm_result) {
		this.isAttackSuccessfull = prm_result;
	}

	// tbd
	/**
	 * this method verifies if map is loaded
	 * 
	 * @return is true if the map is loaded
	 */
	public boolean IsMapLoaded() {
		return this.isMapLoaded;
	}

	/**
	 * this method verifies if game stated
	 * 
	 * @return is true if the game is started
	 */
	public boolean IsGameStarted() {
		return this.isGameStarted;
	}

	/**
	 * this method sets the current action which is internal steps of phases
	 * 
	 * @param prm_currentAction
	 *            is the current action
	 */
	public void SetCurrentAction(String prm_currentAction) {
		this.currentAction = prm_currentAction;
		UpdatePhase();
	}

	/**
	 * this method declares any change in the object as observable
	 * 
	 */
	public void UpdatePhase() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * this method returns the current action which is internal steps of phases
	 * 
	 * @return the current action
	 */
	public String GetCurrentAction() {
		return this.currentAction;
	}

	/**
	 * this method returns the next player to play
	 * 
	 * @return the id of the next player who should play
	 */
	public int GetNextPlayerId() {
		int nextPlayerId = -1;
		if (roundRobin.size() < 1) {
			InitRoundRobin();
			turn+=1;
		}
		// tbd
		nextPlayerId = roundRobin.get(0);
		roundRobin.remove(0);
		// tbd
		this.currentPhase=TurnPhases.Reinforcement;
		this.SetCurrentPlayerId(nextPlayerId);
		this.SetAttackSuccessful(false);
		// tbd
		GetCurrentPlayer().CalculateReinforcementArmies();
		return nextPlayerId;
	}

	/**
	 * this method returns the next player to play
	 * 
	 * @return the id of the next player who should play
	 */
	public int GetNextPlayerIdForStartUp() {
		int nextPlayerId = -1;
		if (roundRobin.size() < 1) {
			InitRoundRobin();
		}
		// tbd
		nextPlayerId = roundRobin.get(0);
		roundRobin.remove(0);
		// tbd
		this.SetCurrentPlayerId(nextPlayerId);
		this.SetAttackSuccessful(false);
		// tbd
		return nextPlayerId;
	}

	/**
	 * this method initialaizes the roundrobin objects it adds all players to
	 * the instance of roundrobin
	 */
	public void InitRoundRobin() {
		for (Player p : this.players) {
			roundRobin.add(p.GetId());
		}

	}

	/**
	 * this method returns the current player
	 * 
	 * @return current player
	 */
	public Player GetCurrentPlayer() {
		for (Player p : players) {
			if (p.GetId() == this.currentPlayerId)
				return p;
		}
		return null;
	}

	/**
	 * This method set the current playerId
	 * 
	 * @param prm_playerId,
	 *            which its type is integer, will replace the current player ID
	 */
	public void SetCurrentPlayerId(int prm_playerId) {
		this.currentPlayerId = prm_playerId;
		UpdatePhase();
	}

	/**
	 * This method set the current phase
	 * 
	 * @param prm_currentPhase,
	 *            which its type is enum, will replace the current phase
	 */
	public void SetCurrentPhase(TurnPhases prm_currentPhase) {
		this.currentPhase = prm_currentPhase;
		UpdatePhase();
	}

}
