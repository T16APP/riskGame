
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

public class TestMapParser {

	@Test
	public void TestLandParseContinents() throws IOException {

		GameBoard gameboard = GameBoard.GetGameBoard();
		MapParser mprsr = new MapParser();
		gameboard.map = mprsr.MapParser("Earth.map");
		FactoryLand fl = new FactoryLand();
		assertNotNull(mprsr.ParseCountries("C1,390,325,CENTRO,C2,C3,C4,C5,C6,C7", 390).toString(),
				mprsr.ParseCountries("C1,390,325,CENTRO,C2,C3,C4,C5,C6,C7", 390).toString());
	}

}
