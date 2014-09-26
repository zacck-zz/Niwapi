package com.semasoft.niwapi;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class MainImageActivity extends Activity {
	TextView mAdStatus;
	
	AdView mAd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_image);

	}
	
	@Override
	protected void onStart() {
		super.onStart();
		//init Ui and things
		mAd = (AdView)findViewById(R.id.AdHome);
		AdRequest maAdRequest = new AdRequest.Builder().build();
		mAd.loadAd(maAdRequest);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_image, menu);
		return super.onCreateOptionsMenu(menu);
	}


}
