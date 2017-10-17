package com.risk.model;
import com.risk.utility.*;
/**this class represent a card
 * it hase properties such as id, name, playerId
 * @author Kourosh
 * @version 1.0.0.0
 */
public class Card {
	private static int counter=0;
    protected int id;
	protected ECards type;
	protected int playerId;
	/**this is the constructor
	 * it takes the name and creates the card object
	 * @param prm_name
	 */
	public  Card(ECards prm_name)
	{
		counter++;
		this.id=this.counter;
		this.type=prm_name;
		this.playerId=-1;
		
	}
	/**this method returns the name of the card
	 * @return the type of the object
	 */
	public ECards GetType()
	{
		return this.type;
	}
    /**this method sets playerId
     * 
     */
	public void SetPlayerId(int prm_playerId)
	{
		this.playerId=prm_playerId;
	}
}
