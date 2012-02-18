package com.nacteam93.scouting.inputtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InputTestActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button output = (Button)findViewById(R.id.output);
        output.setOnClickListener(this);
        Button input = (Button)findViewById(R.id.input);
        input.setOnClickListener(this);
        Button parse = (Button)findViewById(R.id.fromxml);
        parse.setOnClickListener(this);
    }

	String res = null;
	String line = "";
	Match match = new Match(1,3,5);
    public void onClick(View view) {
    	String debugtag = new String("swag");
    	String filename = new String("/mnt/sdcard2/XMLFTW.txt");
		switch(view.getId()){
			case R.id.output:
				try{
					File testxml = new File(filename) ;
		    		Toast.makeText(getApplicationContext(), "File Created: " + testxml.getAbsolutePath(), Toast.LENGTH_LONG).show();
		    		testxml.createNewFile();
		    		FileOutputStream fOut = new FileOutputStream(testxml);
		    		OutputStreamWriter osw = new OutputStreamWriter(fOut);
		    		String fileContent = match.toXML();
		    		//String fileContent = "Total shots: 12\r\nTotal missed: 4\r\nShooting Percentage: 66%";
		    		osw.write(fileContent);
		    		osw.flush();
		    		osw.close();
		    		
		    	} 
		    	catch (FileNotFoundException e)
		    	{
		    		Toast.makeText(getApplicationContext(), "Error: " + e.getMessage() + " that.", Toast.LENGTH_SHORT).show();
		    		Log.e("[SWAG]", "Error: " + e.getMessage());
		    	} 
		    	catch (IOException e)
		    	{
		    		Toast.makeText(getApplicationContext(), "Error: " + e.getMessage() + " this.", Toast.LENGTH_SHORT).show();
		    		Log.e("[SWAG]", "Error: " + e.getMessage());
		    	}
		    	catch (Exception e)
		    	{
		    		
		    	}
			
			break;
			case R.id.input:
		    	try{
					File test = new File(filename);
					FileInputStream in =  new FileInputStream(test);
					
					if(in != null){
						InputStreamReader input = new InputStreamReader(in);
						BufferedReader buff = new BufferedReader(input);
						while(( line = buff.readLine()) != null) {
							Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();
							}
						in.close();
						}
						
				
				} catch(Exception e){
					Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
				}
	    	break;
	
			case R.id.fromxml:
				try{
					int textintvariable = 0;
					XmlPullParserFactory fact = XmlPullParserFactory.newInstance();
					fact.setNamespaceAware(true);
					XmlPullParser xpp = fact.newPullParser();
					
					xpp.setInput( new FileReader (filename) );
					int eventType = xpp.getEventType();
					String tagname = new String("");
					while (eventType != XmlPullParser.END_DOCUMENT){
						if(eventType == XmlPullParser.START_DOCUMENT){
							Log.d(debugtag,"StartDocument");
						} 
						else if(eventType == XmlPullParser.START_TAG) {
							Log.d(debugtag,"Event=StartTag");
							tagname = xpp.getName();
							Log.d(debugtag,"Start "+tagname);
						} 
						else if (eventType == XmlPullParser.END_TAG){
							Log.d(debugtag,"Event=EndTag");
							tagname = xpp.getName();
							Log.d(debugtag,"End "+tagname);
						} 
						else if (eventType == XmlPullParser.TEXT) {
							Log.d(debugtag,"Event=TEXT");
							String text = xpp.getText();
							Log.d(debugtag,"Text = "+text);
							if(text.equals("\t")){
								Log.d(debugtag,"Ignore Tab");
							}
							else if(text.equals("\n")){
								Log.d(debugtag,"Ignore Newline");
							}
							else if(text.equals("\r")){
								Log.d(debugtag,"Ignore return");
							}
							else if(text.equals("\r\n")){
								Log.d(debugtag,"Ignore return and newline");
							}
							else if(text.equals("\n\t")){
								Log.d(debugtag,"Ignore Newline and Tab");
							}
							else if(text.equals("")){
								Log.d(debugtag,"Ignore Null Empty String");
							}
							else if(tagname.equalsIgnoreCase("MatchNumber")) {
								Log.d(debugtag,"MatchNumber = "+text+".");
								textintvariable = Integer.parseInt(text);
								match.myMatchNumber = textintvariable;
								Toast.makeText(getApplicationContext(), "Match Number is " + new Integer(textintvariable).toString(), Toast.LENGTH_SHORT).show();
							}
							else if(tagname.equalsIgnoreCase("RedScore")){
								Log.d(debugtag,"RedScore = "+text);
								textintvariable = Integer.parseInt(text);
								match.myMatchNumber = textintvariable;
								Toast.makeText(getApplicationContext(), "Red Score is " + new Integer(textintvariable).toString(), Toast.LENGTH_SHORT).show();
							}
							else if(tagname.equalsIgnoreCase("BlueScore")){
								Log.d(debugtag,"BlueScore = "+text);
								textintvariable = Integer.parseInt(text);
								match.myMatchNumber = textintvariable;
								Toast.makeText(getApplicationContext(), "Blue Score is " + new Integer(textintvariable).toString(), Toast.LENGTH_SHORT).show();
							}
						}
						eventType = xpp.next();		
					}//end while
					Log.d(debugtag,"XML Parsing Complete");
					Toast.makeText(getApplicationContext(), "XML Parse is Complete", Toast.LENGTH_SHORT).show();
				}
				catch(XmlPullParserException e){
					Log.d(debugtag,"XmlPullParserException happened");					
				}
				catch(Exception e){
					Log.d(debugtag,"Generic Exception happened: "+ e.toString());
				}
			break;
    	}//end switch
	}//onClick

}//end activity