package com.semasoft.niwapi;

import com.actionbarsherlock.app.SherlockActivity;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.os.Bundle;

import android.widget.TextView;

public class MainImageActivity extends SherlockActivity {
	TextView mAdStatus;
	AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_image);
		
		//setup the ad 
		//ads:testDevices="9912BCBF8DF01A6CA86CAE5842F4F3D6" 
		AdView mAd = (AdView)findViewById(R.id.adView);
		mAd.loadAd(new AdRequest());

	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {

		return super.onCreateOptionsMenu(menu);
	}

}
