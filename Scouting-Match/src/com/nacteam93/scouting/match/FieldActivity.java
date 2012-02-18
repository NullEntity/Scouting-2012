package com.nacteam93.scouting.match;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class FieldActivity extends Activity{
	
	DbAdapter m_DbA;
	int target;
	final CharSequence[] items = {"Top", "Middle", "Bottom", "Miss"};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.field); // This section makes the field view start first instead of the login screen (for debugging purposes).
        LinearLayout root = (LinearLayout)findViewById(R.id.field_root); // Finds the layout for the field view.
        
        Log.d("SM", "Field Activity started.");
        
        target = -1;
        
        m_DbA = new DbAdapter(this);
        try
        {
        	m_DbA.open();
        	Log.d("SM", "Database opened (onCreate) from Field Activity.");
        }
        catch(android.database.SQLException e)
        {
        	Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG);
        	Log.d("SM-ERROR", e.getMessage());
        }
        
        root.setOnTouchListener(  // Creates a listener for screen-touches.
        		new View.OnTouchListener() {
					public boolean onTouch(View v, MotionEvent event) {
						int section = getSection(event.getX(), event.getY());
						final int[] shotCount = new int[22];
						Log.d("SM", "Attempting to add score...");
				
						showDialog(0);
						//boolean score = addScore(event.getX(), event.getY());
						Log.d("SM", "onTouchListener initialized.");
						Log.d("SM-Pos", String.format("X: %f\t \r\nY: %f", event.getX(), event.getY())); // Logs debug message when screen is touched. 
						  // Also shows position (x, y) of where the screen was touched.
						//showDialog(0); // Pop-up dialog showing Top, Middle, Bottom, and Miss buttons.

						CharSequence text;

						if (section > -1) {
							m_DbA.createScore(section, 32);
							Log.d("DB-SM", "initialValues inserted into Database.");
							shotCount[section]++;
							text = "Section: " + section + "\r\nCount: " + shotCount[section] + "\r\nTarget: " + target;
						}
						else {
							text = "Error: Invalid Section " + section;
						}

						Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
						Log.d("SM", "Score added.");
						//target = -2;
					return false;
					}
				});
	}
	
	private boolean addScore(float x, float y){
		int section = getSection(x, y);
		final int[] shotCount = new int[22];
		Log.d("SM", "Attempting to add score...");
		/*
		for (int i = 0; i <= 4200000; i ++){
			if (target != -1){
				Log.d("SM-Pos", String.format("X: %f\t \r\nY: %f", x, y)); // Logs debug message when screen is touched. 
				  // Also shows position (x, y) of where the screen was touched.
				//showDialog(0); // Pop-up dialog showing Top, Middle, Bottom, and Miss buttons.

				CharSequence text;

				if (section > -1) {
					m_DbA.createScore(section, 32);
					Log.d("DB-SM", "initialValues inserted into Database.");
					shotCount[section]++;
					text = String.format("Section: %d%nCount: %d", section, shotCount[section]);
				}
				else {
					text = String.format("Error: Invalid Section %d", section);
				}

				Toast.makeText(getApplicationContext(), text + "\r\nTarget: " + target, Toast.LENGTH_SHORT).show();
				Log.d("SM", String.format("Score added: %d, %d, %d.", section, shotCount[section], target));
				target = -2;
				return false;
			}
			else if (target == -2){
				target = -1;
				return false;
			}
		}*/
		return false;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (m_DbA != null){
			m_DbA.close();
	    }
		Log.d("SM", "Database closed (onDestroy) from Field Activity.");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (m_DbA == null){
			m_DbA.close();
			Log.d("SM", "Database closed (onPause) from Field Activity.");
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (m_DbA == null){
			m_DbA.open();
			Log.d("SM", "Database opened (onResume) from Field Activity.");
		}
	}
	
	protected Dialog onCreateDialog(int id) { // This function is for pop-up dialogs.
    	Dialog dialog;
    	switch(id){ // Used to display different dialogs.
    	case 0:	// Field shot pop-up.
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("Select Target");
            dialog.setCancelable(true);				// Allow the pop-up to be cancelled by clicking outside of it.
            dialog.setCanceledOnTouchOutside(true);
    		break;
		default:
			dialog = null;
    	}
    	Log.d("SM", "Dialog created from Field Activity.");
    	return dialog;
    }
    
    public int getSection(float x, float y) {
    	//Don't change any of these; doing so will mess things up.
    	float[] x1;
    	float[] x2;
    	float[] y1;
    	float[] y2;
    	x1 = new float[21];
    	y1 = new float[21];
    	x2 = new float[21];
    	y2 = new float[21];
    	
    	// 1)
    	x1[0] = 199.0f;
    	y1[0] = 128.0f;
    	x2[0] = 586.0f;
    	y2[0] = 278.0f;

    	// 2)
    	x1[1] = 586;
    	y1[1] = 133;
    	x2[1] = 705;
    	y2[1] = 200;

    	// 3)
    	x1[2] = 705;
    	y1[2] = 130;
    	x2[2] = 794;
    	y2[2] = 202;

    	// 4)
    	x1[3] = 796;
    	y1[3] = 131;
    	x2[3] = 915;
    	y2[3] = 200;

    	// 5)
    	x1[4] = 918;
    	y1[4] = 128;
    	x2[4] = 1092;
    	y2[4] = 202;

    	// 6)
    	x1[5] = 586;
    	y1[5] = 196;
    	x2[5] = 705;
    	y2[5] = 319;

    	// 7)
    	x1[6] = 586;
    	y1[6] = 384;
    	x2[6] = 705;
    	y2[6] = 507;

    	// 8)
    	x1[7] = 586;
    	y1[7] = 320;
    	x2[7] = 705;
    	y2[7] = 382;

    	// 9)
    	x1[8] = 586;
    	y1[8] = 503;
    	x2[8] = 705;
    	y2[8] = 578;

    	// 10)
    	x1[9] = 202;
    	y1[9] = 504;
    	x2[9] = 377;
    	y2[9] = 572;

    	// 11)
    	x1[10] = 380;
    	y1[10] = 503;
    	x2[10] = 497;
    	y2[10] = 588;

    	// 12)
    	x1[11] = 505;
    	y1[11] = 503;
    	x2[11] = 583;
    	y2[11] = 585;

    	// 13)
    	x1[12] = 199;
    	y1[12] = 283;
    	x2[12] = 398;
    	y2[12] = 420;

    	// 14)
    	x1[13] = 399;
    	y1[13] = 283;
    	x2[13] = 468;
    	y2[13] = 418;

    	// 15)
    	x1[14] = 465;
    	y1[14] = 280;
    	x2[14] = 586;
    	y2[14] = 418;
    	
    	// 16)
    	x1[15] = 200;
    	y1[15] = 422;
    	x2[15] = 586;
    	y2[15] = 504;

    	// 17)
    	x1[16] = 707;
    	y1[16] = 202;
    	x2[16] = 1094;
    	y2[16] = 283;

    	// 18)
    	x1[17] = 707;
    	y1[17] = 284;
    	x2[17] = 831;
    	y2[17] = 420;

    	// 19)
    	x1[18] = 830;
    	y1[18] = 284;
    	x2[18] = 893;
    	y2[18] = 422;

    	// 20)
    	x1[19] = 892;
    	y1[19] = 284;
    	x2[19] = 1089;
    	y2[19] = 419;

    	// 21)
    	x1[20] = 702;
    	y1[20] = 420;
    	x2[20] = 1092;
    	y2[20] = 570;
    	
		for (int i = 0; i <= 20; i++) {
			if (x >= x1[i] && x <= x2[i] && y >= y1[i] && y <= y2[i]) {
				Log.d("SM", "Field Section (" + Integer.toString(i+1) +") found from Field Activity.");
				return i+1;
			}
		}
		Log.d("SM", "Field Section was not found from Field Activity.");
		return -1;
    }
}