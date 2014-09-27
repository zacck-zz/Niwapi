package com.semasoft.niwapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.internal.ci;
import com.google.android.gms.internal.li;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainImageActivity extends Activity {
	TextView mAdStatus;
	SharedPreferences MainPrefs;
	String uid;
	String TAG = "MAINIMAGEACTIVITY";
	AdView mAd;
	ContestAdapter mAdapter;
	String Imp;
	ListView lx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_image);

	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Resources mResources = getResources();
		Imp = mResources.getString(R.string.image_pre);
		lx = (ListView)findViewById(R.id.lstContest);
		MainPrefs = PreferenceManager
				.getDefaultSharedPreferences(MainImageActivity.this);
		uid = MainPrefs.getString("uid", null);
		Log.d(TAG, uid);
		pickContests pc = new pickContests();
		pc.execute();
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
	
	class pickContests extends AsyncTask<Void, Void, Void> {
		
		String ServerResp;
		String[] ctitle, cimage, cid,cvotes;
		List<Contest> constestList = new ArrayList<Contest>();

		@Override
		protected Void doInBackground(Void... params) {
			Log.d(TAG, "Async initiated");
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://appbase.co.ke/niwapi_rest/collect_contests.php");

				HttpResponse response = httpclient.execute(httppost);
				ServerResp = EntityUtils.toString(response.getEntity());
				Log.d(TAG, response.getStatusLine().toString());
				// lets parse the json
				JSONObject parentObject = new JSONObject(ServerResp);
				JSONArray parentArray = parentObject.getJSONArray("contests");
				ctitle = new String[parentArray.length()];
				cimage = new String[parentArray.length()];
				cid = new String[parentArray.length()];
				cvotes = new String[parentArray.length()];

				for (int now = 0; now < parentArray.length(); now++) {
					JSONObject childObject = parentArray.getJSONObject(now)
							.getJSONObject("contest");
					ctitle[now] = childObject.getString("niwapi_contest_title");
					cimage[now] = childObject.getString("niwapi_contest_image");
					cid[now] = childObject.getString("niwapi_contest_id");
					cvotes[now] = parentArray.getJSONObject(0).getString("votes");
					
				}
				
			} catch (Exception e) {
				Log.d(TAG, e.toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			//lets draw on the list
			for(int lilfuture = 0; lilfuture <cimage.length; lilfuture++)
			{
					Contest Cts = new Contest();
					Cts.setID(cid[lilfuture]);
					Cts.setTitle(ctitle[lilfuture]);
					Cts.setImageLink(Imp+cimage[lilfuture]);
					Cts.setVotes(Integer.valueOf(cvotes[lilfuture]));
					constestList.add(Cts);
			}
			mAdapter = new ContestAdapter(MainImageActivity.this, constestList);
			lx.setAdapter(mAdapter);
			
		}

	}

}
