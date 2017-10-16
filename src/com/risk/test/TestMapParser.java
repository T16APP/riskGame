package com.risk.test;

import static org.junit.Assert.*;


import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.FactoryLand;
import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Map;
import com.risk.utility.MapParser;

import org.junit.Test;

public class TestMapParser 
{

	@Test
	public void TestLandParseContinents() throws IOException 
	{

		GameBoard gameboard = GameBoard.GetGameBoard();
		System.out.println(gameboard);
		MapParser mprsr = new MapParser();
		System.out.println(mprsr);
		gameboard.map = mprsr.MapParser("Earth.map");
		System.out.println(gameboard.map);
		FactoryLand fl = new FactoryLand();
		System.out.println(fl);
		assertNotNull(mprsr.ParseCountries("C1,390,325,CENTRO,C2,C3,C4,C5,C6,C7", 390).toString(),
				//System.out.println("assertNotNull")
				mprsr.ParseCountries("C1,390,325,CENTRO,C2,C3,C4,C5,C6,C7", 390).toString());
		System.out.println("assertNotNull");
		//fail("Not yet implemented");
}
}


