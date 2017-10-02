package com.risk.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int id;
	private String name;
	private List<Land> lands;

	public Player(int new_id, String new_name) {
		this.id = new_id;
		this.name = new_name;
		lands = new ArrayList<Land>();
	}

	public int GetId() {
		return this.id;
	}

	public String GetName() {
		return this.name;
	}

	public int AddLand(Land new_land) {
		int result = 0;
		if (!DoesOwnLand(new_land)) {
			this.lands.add(new_land);
			result = 1;
		}
		return result;
	}

	public int RemoveLand(Land new_land) {
		int result = 0;
		if (!DoesOwnLand(new_land)) {
			this.lands.remove(new_land);
			result = 1;
		}
		return result;
	}

	public boolean DoesOwnLand(Land new_land) {
		boolean result = false;
		for (Land c : this.lands) {
			if (new_land.GetId() == c.GetId()) {
				result = true;
				return result;
			}
		}
		return result;
	}

}
