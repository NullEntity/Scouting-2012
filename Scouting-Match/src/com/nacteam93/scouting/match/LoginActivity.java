package com.nacteam93.scouting.match;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class LoginActivity extends Activity implements OnClickListener, OnEditorActionListener {
	DbAdapter m_DbA;
	private int ADD_NEW_TAB = Menu.FIRST;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); // Sets the view to main.xml (login screen).
        Log.d("SM", "Login Activity started.");
        
        m_DbA = new DbAdapter(this);
        try
        {
        	m_DbA.open();
        	Log.d("SM", "Database opened (onCreate) from Login Activity.");
        }
        catch(android.database.SQLException e)
        {
        	Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG);
        	Log.d("SM-ERROR", "Error: " + e.getMessage());
        }
        
        Button enter = (Button)findViewById(R.id.button1); //Finds the button 'button1' to be able to test if it's clicked.
        enter.setOnClickListener(this); // Creates a click listener for the button 'enter' on the view.
        
        Button outputxml = (Button)findViewById(R.id.button2); //Finds the button 'button2' to be able to test if it's clicked.
        outputxml.setOnClickListener(this); // Creates a click listener for the button 'outputxml' on the view.
        
        EditText name = (EditText)findViewById(R.id.etName); //Finds text box 'editText1' to display the name in the match select screen.
        name.setOnEditorActionListener(this); // Detects if you press the enter key after inputting a name.
        
        EditText teamN = (EditText)findViewById(R.id.etTeamNum);
        teamN.setOnClickListener(this);
        
        RadioGroup rGroup3 = (RadioGroup) findViewById(R.id.rgRobotC);
        
        TabHost tabs = (TabHost)findViewById(R.id.tabhost);
        tabs.setup();
        TabSpec tspec1 = tabs.newTabSpec("First Tab");
        tspec1.setIndicator("One");
        tspec1.setContent(R.id.imageView1);
        tabs.addTab(tspec1);
        TabSpec tspec2 = tabs.newTabSpec("Second Tab");
        tspec2.setIndicator("Two");
        tspec2.setContent(R.id.imageView1);
        tabs.addTab(tspec2);
        TabSpec tspec3 = tabs.newTabSpec("Third Tab");
        tspec3.setIndicator("Three");
        tspec3.setContent(R.id.imageView1);
        tabs.addTab(tspec3);
        
        OnClickListener radio_listener = new OnClickListener() {
            public void onClick(View v) {
                onRadioButtonClick(v);
            }
        };
         
        RadioButton rbRed = (RadioButton)findViewById(R.id.rbRed);
        RadioButton rbBlue = (RadioButton)findViewById(R.id.rbBlue);
        
        EditText matchN = (EditText)findViewById(R.id.etMatchNum);
        matchN.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	switch (v.getId()){
    		case R.id.button1:
    			nextPage();
    			break;
    		case R.id.button2:
    			outputXML();
    			break;
    	}
	}
    
    public void onRadioButtonClick(View v) {
        RadioButton button = (RadioButton) v;
        Toast.makeText(getApplicationContext(), button.getText() + " was chosen. Yay!", Toast.LENGTH_SHORT).show();
    }
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
		if (m_DbA != null){
			m_DbA.close();
	    }
		Log.d("SM", "Database closed (onDestroy) from Login Activity.");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (m_DbA == null){
			m_DbA.close();
			Log.d("SM", "Database closed (onPause) from Login Activity.");
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (m_DbA == null){
			m_DbA.open();
			Log.d("SM", "Database opened (onResume) from Login Activity.");
		}
	}
	
	public void outputXML(){
		Cursor cursor = m_DbA.fetchAllScores();
		Log.d("OutputXML","Data fetched.");
		
		int[] data = new int[cursor.getCount()];
		int index = 0;
		int i = 0;
		String strData = "";
		
		if (cursor.moveToFirst()){
			index = cursor.getColumnIndex(m_DbA.KEY_ZONE);
			i = 0;
			
			do{
				data[i] = cursor.getInt(index);
				Log.d("OutputXML", "Cursor moved to " + Integer.toString(i) + ".");
				i++;
			}
			while (cursor.moveToNext());
		}
		for (int j = 0; j < cursor.getCount(); j++){
			strData += data[j] + "\r\n";
		}
		cursor.close();
		Log.d("SM", "Data read from database.");
		
		try{
			File testxml = new File("/mnt/usb0/part0/test.txt");
    		Toast.makeText(getApplicationContext(), testxml.getAbsolutePath(), Toast.LENGTH_LONG).show();
    		testxml.createNewFile();
    		Toast.makeText(getApplicationContext(), "File Created", Toast.LENGTH_LONG).show();
    		
    		FileOutputStream fOut = new FileOutputStream(testxml);
    		OutputStreamWriter osw = new OutputStreamWriter(fOut);
    		
    		String fileContent = strData;
    		osw.write(fileContent);
    		osw.flush();
    		osw.close();
    		
    		Log.d("SM", "XML File written.");
    	} 
    	catch (FileNotFoundException e)
    	{
    		Toast.makeText(getApplicationContext(), "Error: " + e.getMessage() + " that.", Toast.LENGTH_SHORT).show();
    		Log.e("SM-ERROR", "Error: " + e.getMessage());
    	} 
    	catch (IOException e)
    	{
    		Toast.makeText(getApplicationContext(), "Error: " + e.getMessage() + " this.", Toast.LENGTH_SHORT).show();
    		Log.e("SM-ERROR", "Error: " + e.getMessage());
    	}
    	catch (Exception e)
    	{
    		Toast.makeText(getApplicationContext(), "Error: " + e.getMessage() + " this.", Toast.LENGTH_SHORT).show();
    		Log.e("SM-ERROR", "Error: " + e.getMessage());
    	}
	}

	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		
		if(actionId == EditorInfo.IME_ACTION_DONE){
			nextPage(); // Goes to the nextPage function which changes the activity to the match selection screen.
			return true;
		}
		
		return false;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		//outState.putLong(m_DbA.KEY_USER_ROWID, usersRowId); Y U NO WERK??!?
	}
	
	private void nextPage() {
		EditText etName = (EditText)findViewById(R.id.etName); // Finds the text box.
		EditText etTeamN = (EditText)findViewById(R.id.etTeamNum);
		RadioButton rbRed = (RadioButton)findViewById(R.id.rbRed);
        RadioButton rbBlue = (RadioButton)findViewById(R.id.rbBlue);
		EditText etMatchN = (EditText)findViewById(R.id.etMatchNum);
		
		String name = etName.getText().toString(); // Gets the string in the text-box so the next screen can display it.
		String teamN = etTeamN.getText().toString(); 
		//String robotC;
		String matchN = etMatchN.getText().toString(); 
		
		String robotC = "";
        Boolean foundUser = false;
		
		//m_DbA.open();
		String[] users = {"Chris", "Daniel", "Bryce", "Charais", "Matt"};
		//m_DbA.close();
		if (rbRed.isChecked()){
			robotC = "Red";
		}
		else{
			robotC = "Blue";
		}
		
		for (int u = 0; u < users.length; u++)
		{
			if (name.toLowerCase().equals(users[u].toLowerCase()))
			{
				foundUser = true;
				Intent in = new Intent(this, FieldActivity.class); // Creates an intent 'in', which is the MatchSelect activity.
				in.putExtra("name", name); // Transfers the name variable to MatchSelect.class.
				Log.d("SM", "Name accepted - moving to MatchSelect Activity from Login Activity.");
				Log.d("SM", "Scouter Info:\r\nName: " + name + "\r\nTeam Number: " + teamN + "\r\nRobot Color: " + robotC + "\r\nMatch Number: " + matchN);
				startActivity(in); // Changes the activity to MatchSelect.class.
			}
		}
		if (!foundUser){
			Toast.makeText(getApplicationContext(), "Error: Invalid name '" + name + "'.", Toast.LENGTH_SHORT).show();
		}
	}
}