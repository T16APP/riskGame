package com.risk.model;
/**this class simulate the dice
 * it has method to roll dice
 * 
 * @author Kourosh
 * @version 1.0.0.0
 */
public class Dice implements Comparable{
private int dice;

/**this is the default constructor of the class
 * 
 */
public Dice(){}
/**this method simulate the rolling the dice 
 * and set the dice property of the object
 */
public int RollDice(){
	return dice=(int)(Math.random()*6+1);
}
/**this method returns the result of the rolling the dice
 * 
 * @return is the result of the dice
 */
public int GetDice(){
	return this.dice;
}
/**this method makes the dice objects comparable
 * it is used in sorting dices
 */
@Override
public int compareTo(Object o) {
	// TODO Auto-generated method stub
	if(this.dice==((Dice)o).GetDice()) return 0;
	else if(this.dice>((Dice)o).GetDice()) return 1;
	else if(this.dice<((Dice)o).GetDice()) return -1;
	return 0;
}
}

