package com.nacteam93.scouting.inputtest;

import java.io.StringWriter;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;
import android.util.Xml;

public class Match {
	int myMatchNumber;
	int myRedScore;
	int myBlueScore;
	
	public final String MATCHTEXT = "Match";
	
	public Match(int MatchNumber, int RedScore, int BlueScore){
		myMatchNumber = MatchNumber;
		myRedScore = RedScore;
		myBlueScore = BlueScore;
	}
	
	String toXML() {
		
		String xml = ""; 
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try{
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.text("\n");
			serializer.startTag("", MATCHTEXT);
			serializer.text("\n\t");
			serializer.startTag("", "MatchNumber");
			serializer.text(Integer.toString(myMatchNumber));
			serializer.endTag("", "MatchNumber");
			serializer.text("\n\t");
			serializer.startTag("", "Redscore");
			serializer.text(Integer.toString(myRedScore));
			serializer.endTag("", "Redscore");
			serializer.text("\n\t");
			serializer.startTag("", "Bluescore");
			serializer.text(Integer.toString(myBlueScore));
			serializer.endTag("", "Bluescore");
			serializer.text("\n");
			serializer.endTag("", "Match");
			serializer.endDocument();
			xml = writer.toString();
					
		
		}
		catch (Exception e)
		{
			Log.d("XML",e.getMessage());
		}
		return xml;
	}
	
}
	

