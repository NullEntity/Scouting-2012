
/**
 * Creates a hoop object to store hits and misses.
 * 
 * @author Bryce Walther
 * @version 2/13/12
 * 
 */

package com.nacteam93.scouting.match;

public class Hoop {
	// Instantiate object variables
	private int hits = 0, misses = 0;
	
	public void addHit() {
		hits++;
	}
	
	public void addMiss() {
		misses++;
	}
	
	public void subtractHit() {
		hits--;
	}
	
	public void subtractMiss() {
		misses--;
	}
	
	public int getHits() {
		return hits;
	}
	
	public int getMisses() {
		return misses;
	}
	
	public int getTotal() {
		return hits + misses;
	}
}