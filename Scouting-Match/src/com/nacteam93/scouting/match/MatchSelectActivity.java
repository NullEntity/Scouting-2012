package com.nacteam93.scouting.match;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MatchSelectActivity extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SM", "MatchSelect Activity started.");
        
        //String[] matches = getResources().getStringArray(R.array.matches_array);
        
        String[] matches = new String[20];
		for (int m = 0; m < matches.length; m++){
        	matches[m] = "Match " + Integer.toString(m + 1);
        	
        }
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, matches));
        Log.d("SM", "Matches loaded from MatchSelect Activity.");
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	nextPage();
            }
        });
	}
	
	private void nextPage(){
		Intent in = new Intent(this, FieldActivity.class);
		//in.putExtra("name", name); // Transfers the name variable to Field.class.
		Log.d("SM", "Match selected - moving to Field Activity from MatchSelect Activity.");
		startActivity(in); // Changes the activity to Field.class.
	}
}