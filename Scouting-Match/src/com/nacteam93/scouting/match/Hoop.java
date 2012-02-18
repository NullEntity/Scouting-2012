
/**
 * Creates a hoop object to store hits and misses.
 * 
 * @author Bryce Walther
 * @version 2/13/12
 * 
 */

package com.nacteam93.scouting.match;

import android.util.Log;

public class Hoop {
	// Instantiate object variables
	private int hits = 0, misses = 0;
	
	public void addHit() {
		Log.d("Hoop","Add a made hoop");
		hits++;
	}
	
	public void addMiss() {
		Log.d("Hoop","Add a missed hoop");
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