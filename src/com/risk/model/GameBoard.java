package com.risk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
	public List<Player> players;
	public Map map;
	public static GameBoard instance;
	public List<Integer> roundRobin;

	public GameBoard() {
		if (this.instance == null) {
			players = new ArrayList<Player>();
			map = new Map("map");
		}

	}

	public static GameBoard GetGameBoard() {
		if (instance == null) {
			return new GameBoard();
		} else
			return new GameBoard();
	}

	public void SetupPlayers() {
		for (int i = 1; i < 7; i++) {
			players.add(new Player(i, "player" + i));
		}
		roundRobin = new ArrayList<Integer>(players.size());
		InitRoundRobin();
	}

	public void AssignCountriesRandom() {
		Country countryRandom;
		while (map.GetCountriesNotAssigned().size() > 0) {
			for (Player p : players) {
				if (map.GetCountriesNotAssigned().size() > 0) {
					map.GetNotAssignedCountryRandom().SetPlayerId(p.GetId());
				} else
					break;
			}
		}
	}

	public void InitRoundRobin() {
		for (Player p : this.players) {
			roundRobin.add(p.GetId());
		}
	}

	public int GetNextPlayerId() {
		if (roundRobin.size() < 1) {
			InitRoundRobin();
		}
		int indexNextPlayerId = new Random().nextInt(roundRobin.size());
		int nextPlayerId = roundRobin.get(indexNextPlayerId);
		roundRobin.remove(indexNextPlayerId);
		return nextPlayerId;
	}

}
