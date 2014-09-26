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

import com.google.android.gms.internal.lu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	NiwapiController nc;
	String TAG = "LOGINACTIVITY";
	EditText ln, lp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

		// Listening to register new account link
		registerScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(),
						RegisterActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		// check if our user is in already
		SharedPreferences loginPrefs = PreferenceManager
				.getDefaultSharedPreferences(LoginActivity.this);
		String userloginid = loginPrefs.getString("uid", null);
		if (userloginid != null) {
			startActivity(new Intent(LoginActivity.this,
					MainImageActivity.class));
		} else {
			// init UI and stuff

			Button btlog;

			ln = (EditText) findViewById(R.id.etuname);
			lp = (EditText) findViewById(R.id.ettuno);

			btlog = (Button) findViewById(R.id.btnLogin);
			btlog.setOnClickListener(this);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			String logpho,
			logu;
			logpho = lp.getText().toString();
			logu = ln.getText().toString();
			if (logpho.isEmpty() || logu.isEmpty()) {
				Toast.makeText(LoginActivity.this,
						"Please Fill in Required Fields Then Try again",
						Toast.LENGTH_LONG).show();

			}
			else
			{
				String[] pars = new String[]{logu, logpho};
				LogUserIn lui = new LogUserIn();
				lui.execute(pars);
				
			}
			break;
		}
	}

	class LogUserIn extends AsyncTask<String[], Void, Void> {
		String ServerResp = "";

		@Override
		protected Void doInBackground(String[]... params) {
			Log.d(TAG, "Async initiated");
			try {
				String[] data = params[0];
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://appbase.co.ke/niwapi_rest/login_user.php");

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs
						.add(new BasicNameValuePair("login_name", data[0]));
				nameValuePairs
						.add(new BasicNameValuePair("login_no", data[1]));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				HttpResponse response = httpclient.execute(httppost);
				ServerResp = EntityUtils.toString(response.getEntity());
				Log.d(TAG, response.getStatusLine().toString());
				Log.d(TAG, ServerResp);

			} catch (Exception e) {
				Log.d(TAG, e.toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Log.d(TAG, ServerResp);
		}

	}

}