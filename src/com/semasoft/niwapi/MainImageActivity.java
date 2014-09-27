package com.semasoft.niwapi;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainImageActivity extends Activity {
	TextView mAdStatus;
	SharedPreferences MainPrefs;
	String uid;
	String TAG = "MAINIMAGEACTIVITY";

	AdView mAd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_image);

	}

	@Override
	protected void onStart() {
		super.onStart();
		MainPrefs = PreferenceManager
				.getDefaultSharedPreferences(MainImageActivity.this);
		uid = MainPrefs.getString("uid", null);
		Log.d(TAG, uid);
		// init Ui and things
		mAd = (AdView) findViewById(R.id.AdHome);
		AdRequest maAdRequest = new AdRequest.Builder().build();
		mAd.loadAd(maAdRequest);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_image, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_logout:
			performLogout();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void performLogout() {
		Editor MainEdit = MainPrefs.edit();
		MainEdit.remove("uid");
		MainEdit.commit();
		Log.d(TAG, "user Removed Redirecting to Login");
		startActivity(new Intent(MainImageActivity.this, LoginActivity.class));

	}

}
