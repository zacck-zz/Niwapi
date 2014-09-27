package com.semasoft.niwapi;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TryActivity extends ListActivity implements OnClickListener {
	String TAG = "TRYACTIVITY";
	String _id;
	String[] tt, tc, tu;
	SharedPreferences TrialPrefs;
	String uid;
	EditText etTrial;
	ImageButton btPost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contest_trials);
	}

	@Override
	protected void onStart() {
		super.onStart();
		TrialPrefs = PreferenceManager
				.getDefaultSharedPreferences(TryActivity.this);
		uid = TrialPrefs.getString("uid", null);
		Log.d(TAG, "uid is" + uid);
		_id = getIntent().getStringExtra("contest_id");
		Log.d(TAG, _id);

		btPost = (ImageButton) findViewById(R.id.btTrialsTry);
		btPost.setOnClickListener(this);
		
		etTrial = (EditText)findViewById(R.id.etTryContent);

		getContestTrials gct = new getContestTrials();
		gct.execute();
	}

	class PostTrial extends AsyncTask<String[], Void, Void> {
		String ServerResp;

		@Override
		protected Void doInBackground(String[]... params) {
			try {
				String [] meh = params[0];
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://appbase.co.ke/niwapi_rest/contest_trial.php");

				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("uid", meh[2]));
				nameValuePairs.add(new BasicNameValuePair("trial", meh[0]));
				nameValuePairs.add(new BasicNameValuePair("cid",meh[1]));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				ServerResp = EntityUtils.toString(response.getEntity());
			} catch (Exception e) {
				Log.d(TAG, e.toString());
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Toast.makeText(TryActivity.this, "Congratulations " + ServerResp,
					Toast.LENGTH_LONG).show();
			etTrial.setText("");
			new getContestTrials().execute();
			

		}

	}

	class getContestTrials extends AsyncTask<Void, Void, Void> {
		String ServerResp = "";

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// Create a new HttpClient and Post Header
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://appbase.co.ke/niwapi_rest/contest_trial.php");

				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("cont_id", _id));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				ServerResp = EntityUtils.toString(response.getEntity());

			} catch (Exception e) {
				Log.d(TAG, e.toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (ServerResp.isEmpty()) {
				Toast.makeText(TryActivity.this, "There are no Trials Yet",
						Toast.LENGTH_LONG).show();

			} else {
				try {
					JSONObject parentTrialsObject = new JSONObject(ServerResp);
					JSONArray parentTrialArrays = parentTrialsObject
							.getJSONArray("trials");
					tt = new String[parentTrialArrays.length()];
					tu = new String[parentTrialArrays.length()];
					tc = new String[parentTrialArrays.length()];
					for (int inc = 0; inc < parentTrialArrays.length(); inc++) {
						JSONObject childTrialObject = parentTrialArrays
								.getJSONObject(inc).getJSONObject("trial");
						tt[inc] = childTrialObject.getString("time_posted");
						tu[inc] = childTrialObject
								.getString("niwapi_user_name");
						tc[inc] = childTrialObject
								.getString("niwapi_trial_content");
					}

					ArrayList<HashMap<String, String>> trials = new ArrayList<HashMap<String, String>>();

					for (int maket = 0; maket < parentTrialArrays.length(); maket++) {
						HashMap<String, String> t = new HashMap<String, String>();
						t.put("time", tt[maket]);
						t.put("user", tu[maket]);
						t.put("content", tc[maket]);
						trials.add(t);
					}

					SimpleAdapter adp = new SimpleAdapter(TryActivity.this,
							trials, R.layout.try_list_item, new String[] {
									"time", "user", "content" }, new int[] {
									R.id.tvTime, R.id.tvUName, R.id.tvTTrial });
					setListAdapter(adp);

				} catch (Exception e) {
					Log.d(TAG, e.toString());
				}
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btTrialsTry:
			String content = etTrial.getText().toString();
			if (content.isEmpty()) {
				Toast.makeText(TryActivity.this, "Please Fill in Your Trial",
						Toast.LENGTH_LONG).show();
			} else {
				String[] trialParams = new String[] { content, _id, uid };
				PostTrial pt = new PostTrial();
				pt.execute(trialParams);

			}
			break;
		}

	}

}
