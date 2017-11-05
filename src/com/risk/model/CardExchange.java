package com.risk.model;

import java.util.List;
import java.util.Observable;
/**this class provides an observable object for exchange view
 * it shows the vards of hand and its change
 * @author Kourosh
 * @version 1.0.0.0
 */
public class CardExchange extends Observable{
	private List<Card> hand;
	/**this cunstruct builds the object and sets the hand cards
	 * 
	 * @param prm_hand cards in hand
	 */
	public CardExchange(List<Card> prm_hand){
		this.hand=prm_hand;
	}
	/**this method updates the hand after successful exchange
	 * 
	 * @param prm_hand updated cards in hand
	 */
	public void UpdateHandCards(List<Card> prm_hand){
		this.hand=prm_hand;
		setChanged();
		notifyObservers(this);
	}
	/**this method returns the hand
	 * 
	 * @return hand cards
	 */
	public List<Card> GetCards(){
		return this.hand;
	}

}
