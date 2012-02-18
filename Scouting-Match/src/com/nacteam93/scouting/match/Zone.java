package com.nacteam93.scouting.match;

import android.util.Log;

public class Zone {
	static protected Hoop Top, Middle, Bottom;
	private int Airballs = 0;
	
	public void addShot(Hoop h, Shot s){
		Log.d("Zone", "Shot added");
		switch (s) {
		case HIT:
			h.addHit();
			break;
		case MISS:
			h.addMiss();
			break;
		}
	}
	
	public void addAirball() {
		Log.d("Zone", "Airball added");
		Airballs++;
	}
	
	public void subtractAirball() {
		Airballs--;
	}
	
	public int getAirballs() {
		return Airballs;
	}
}