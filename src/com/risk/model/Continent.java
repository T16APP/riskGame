package com.risk.model;
/**
 * This is a class that belongs to map and contains countries
 * @author Kourosh Aziz-Nejad
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Continent  extends Land {
double control;
int db1;
int playerId;

public Continent(String new_name, int new_db1)
{
	super(new_name);
	this.db1=new_db1;
	}
public int GetContinentId()
{
	return this.id;
	}
public int GetDb1()
{
	return db1;
	}

}