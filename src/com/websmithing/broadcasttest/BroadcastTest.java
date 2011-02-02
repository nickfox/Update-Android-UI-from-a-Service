package com.websmithing.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BroadcastTest extends Activity {
	private static final String TAG = "BroadcastTest";
	private Intent intent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        intent = new Intent(this, BroadcastService.class);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	updateUI(intent);       
        }
    };    
    
	@Override
	public void onResume() {
		super.onResume();		
		startService(intent);
		registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
	}
	
	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(broadcastReceiver);
		stopService(intent); 		
	}	
	    
    private void updateUI(Intent intent) {
    	String counter = intent.getStringExtra("counter"); 
    	String time = intent.getStringExtra("time");
    	Log.d(TAG, counter);
    	Log.d(TAG, time);
    	
    	TextView txtDateTime = (TextView) findViewById(R.id.txtDateTime);  	
    	TextView txtCounter = (TextView) findViewById(R.id.txtCounter);
    	txtDateTime.setText(time);
    	txtCounter.setText(counter);
    }
}