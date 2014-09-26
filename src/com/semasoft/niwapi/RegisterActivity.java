package com.semasoft.niwapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity implements OnClickListener {

	EditText ETName, ETPhone, ETConfP;
	Button BtReg;
	String name, mail, password;
	NiwapiController nc;
	String TAG = "REGISTERACTIVITY";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		// Set View to register.xml
		setContentView(R.layout.register);

		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

		// Listening to Login Screen link
		loginScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Switching to Login Screen/closing register screen
				finish();
			}
		});
		// initialize the page elements
		init();
	}

	private void init() {

		ETPhone = (EditText) findViewById(R.id.reg_phone_no);
		ETName = (EditText) findViewById(R.id.reg_uname);
		ETConfP = (EditText) findViewById(R.id.reg_conf_phone);
		nc = (NiwapiController) getApplication();
		BtReg = (Button) findViewById(R.id.btnRegister);
		BtReg.setOnClickListener(this);

	}

	public Date convertStringToDate(String date) {
		Date giveBack = null;
		DateFormat df = new SimpleDateFormat("yyyy,MM,dd");
		try {
			giveBack = df.parse(date);
			String newDateString = df.format(giveBack);
			Log.v("Logging", newDateString);

		} catch (Exception e) {
			Log.v("ERROR", e.toString());
		}

		return giveBack;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnRegister:

			break;

		}

	}

	class registerUser extends AsyncTask<String[], Void, Void> {

		@Override
		protected Void doInBackground(String[]... params) {
			try {
				// call http methods
				// Create a new HttpClient and Post Header
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://www.yoursite.com/script.php");

				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("id", "12345"));
				nameValuePairs.add(new BasicNameValuePair("stringdata",
						"AndDev is Cool!"));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);

			} catch (Exception e) {
				Log.d(TAG, e.toString());
			}

			return null;
		}

	}

}
