package com.nacteam93.scouting.match;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Field {
	private Map<String, Zone> Zones = new HashMap<String, Zone>();
	Field() {
		Zones.put("BlueBridge", new Zone());
		Zones.put("NextToBlueBridge", new Zone());
		Zones.put("BlueLeftOfKey", new Zone());
		Zones.put("BlueBehindKey", new Zone());
		Zones.put("BlueKey", new Zone());
		Zones.put("BlueFender", new Zone());
		Zones.put("BlueRightOfKey", new Zone());
		Zones.put("BlueAlleyNear", new Zone());
		Zones.put("BlueAlleyMiddle", new Zone());
		Zones.put("BlueAlleyFar", new Zone());
		Zones.put("RedBridge", new Zone());
		Zones.put("NextToRedBridge", new Zone());
		Zones.put("RedLeftOfKey", new Zone());
		Zones.put("RedBehindKey", new Zone());
		Zones.put("RedKey", new Zone());
		Zones.put("RedFender", new Zone());
		Zones.put("RedRightOfKey", new Zone());
		Zones.put("RedAlleyNear", new Zone());
		Zones.put("RedAlleyMiddle", new Zone());
		Zones.put("RedAlleyFar", new Zone());
		Zones.put("CoopBridge", new Zone());
	}
}
// #SWaG