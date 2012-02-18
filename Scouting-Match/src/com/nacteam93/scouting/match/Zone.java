package com.nacteam93.scouting.match;

public class Zone {
	static protected Hoop Top, Middle, Bottom;
	private int Airballs = 0;
	
	public void addAirball() {
		Airballs++;
	}
	
	public void subtractAirball() {
		Airballs--;
	}
	
	public int getAirballs() {
		return Airballs;
	}
}