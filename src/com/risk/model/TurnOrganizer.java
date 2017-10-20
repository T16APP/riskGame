package com.risk.model;

import com.risk.utility.TurnPhases;

/**
 * This class maintains the turn and phase status  
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */
public class TurnOrganizer {
	private boolean isMapLoaded;
	private boolean isGameStarted;
	private int currentPlayerId;
	private TurnPhases currentPhase;
	private boolean isAttackSuccessfull;
	
/**
 * This the constructor of the class
 * it initialize properties of the object
 */
	public TurnOrganizer()
	{
		isMapLoaded = false;
		isGameStarted=false;
		currentPlayerId = -1;
		currentPhase= TurnPhases.PreGame;
		/**missing this should change once attack feature added 
		 * 
		 */
		isAttackSuccessfull=true;
	}
	/**
	 * This method set map-loaded flag
	 * 
	 */	
	public void MapLoaded()
	{
		this.isMapLoaded=true;
	}
	/**
	 * This method set game-started flag
	 * 
	 */	
	public void GameStarted()
	{
		this.isGameStarted=true;
	}
	/**
	 * This method set the current playerId
	 * @param prm_playerId, which its type is integer, 
	 * will replace the current player ID
	 */
	public void SetCurrentPlayerId(int prm_playerId)
	{
		this.currentPlayerId=prm_playerId;
	}
	/**
	 * This method set the current phase
	 * @param prm_currentPhase, which its type is enum,  
	 * will replace the current phase
	 */
	public void SetCurrentPhase(TurnPhases prm_currentPhase)
	{
		this.currentPhase=prm_currentPhase;
	}
	/**
	 * This method returns the current phase
	 * @return , which its type is enum, is the current phase of the game  
	 * 
	 */
	public TurnPhases GetCurrentPhase()
	{
		return this.currentPhase;
	}
	//tbd
	//in the build1 we didn't have phase attack, however in the future it should be implementd
	/**
	 * This method compute the next phase
	 * and return it
	 * @return , which its type is enum,  the current phase of the game  
	 * 
	 */
	public TurnPhases GetNextPhase()
	{
		switch(this.currentPhase)
		{
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
	/**this method returns the current playerId
	 * @return the id of the current player
	 */
	public int GetCurrentPlayerId()
	{
		return currentPlayerId;
	}
	/**this method verifies if there was a successful attack attack 
	 * 
	 */
	public boolean IsAttackSuccessful()
	{
		return this.isAttackSuccessfull;
	}
	//tbd
	/**this method verifies if map is loaded
	 * 
	 */
	public boolean IsMapLoaded()
	{
		return this.isMapLoaded;
	}
	/**this method verifies if game stated
	 * 
	 */
	public boolean IsGameStarted()
	{
		return this.isGameStarted;
	}
	
	

}
