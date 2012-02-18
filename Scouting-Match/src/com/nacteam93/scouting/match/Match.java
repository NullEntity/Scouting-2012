package com.nacteam93.scouting.match;

public class Match {
	enum Color { red, blue };
	
	Color Alliance;
	Field Hybrid;
	Field Teleop;
	
	int DefenseRating, SpeedRating, HumanPlayerThrowingAbility, TotalScore, TotalOpposingAllianceScore, TotalPenaltyPoints, Fouls, TechnicalFouls;
	int MatchNumber, TeamNumber;
	int BallsCarry /*0-3*/, BalanceWithOthers/*0-2*/;
	
	boolean UsedKinect, NoShow, BrokeDown, UsedCameraTargeting, CrossedBarrier, PickedUpFromFloor,
			GatheredBallsDuringHybrid, UsedCoopertitionBridge, YellowCarded, RedCarded;
	
	public void addShot(Field f, Zone z/*, HoopType h, ShotType s*/){
		
	}
	
	public void addAirball(Field f, Zone z){
		
	}
}