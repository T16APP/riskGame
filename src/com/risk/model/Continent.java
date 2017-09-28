package com.risk.model;
/**
 * This is a class that belongs to map and contains countries
 * @author Kourosh Aziz-Nejad
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Continent  extends Land {
public List<Country> countries;
double control;
int playerId;

public Continent(int new_id,String new_name)
{
	super(new_id,new_name);
	countries = new ArrayList<Country>();
	playerId=-1;
	}


}
