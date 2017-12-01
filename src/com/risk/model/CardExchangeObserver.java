package com.risk.model;

import java.util.Observable;
import java.util.Observer;

/**this class represents observer for card exchange view
 * it looks at CardExchange observable to show the current cards of player in hand
 * @author Kourosh
 *@version 1.0.0.0
 */
public class CardExchangeObserver implements Observer{

	private String hand="";
	/**this method updates the state once is notified
	 * 
	 */
	/**standard constructor for json use
	 * 
	 */
	public CardExchangeObserver(){
		
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		hand = "";
		CardExchange cardExchange = (CardExchange)o;
		for(Card c : cardExchange.hand){
			hand+=c.GetType().toString();
		}
		if(hand!=""){
			System.out.println("_____Card Exchange View_____");
			System.out.println(hand);
			System.out.println("_____End of Card Exchange View_____");
		}
		
	}

}
