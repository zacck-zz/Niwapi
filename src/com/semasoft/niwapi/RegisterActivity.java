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
import android.widget.Toast;

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
		setContentView(R.layout.register);
		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

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

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnRegister:
			Log.d(TAG, "reg button pressed");
			String un,
			up,
			ucp;
			un = ETName.getText().toString();
			up = ETPhone.getText().toString();
			ucp = ETConfP.getText().toString();

			if (un.isEmpty() || up.isEmpty() || ucp.isEmpty()) {
				Toast.makeText(RegisterActivity.this,
						"Please fill in all the fields to proceed",
						Toast.LENGTH_LONG).show();

			} else {
				if (up.equalsIgnoreCase(ucp)) {
					String[] ps = new String[] { up, un };
					Log.d(TAG, up + un);
					registerUser ru = new registerUser();
					ru.execute(ps);

				} else {
					Toast.makeText(
							RegisterActivity.this,
							"Please confirm that the phone number is same as the confirmation",
							Toast.LENGTH_LONG).show();
				}
			}

			break;

		}

	}

	class registerUser extends AsyncTask<String[], Void, Void> {

		String ServerResp = "";

		@Override
		protected Void doInBackground(String[]... params) {
			Log.d(TAG, "Async initiated");
			try {
				String[] data = params[0];
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://www.appbase.co.ke/niwapi_rest/register_user.php");

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs
						.add(new BasicNameValuePair("user_name", data[1]));
				nameValuePairs
						.add(new BasicNameValuePair("user_phone", data[0]));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

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
			Log.d(TAG, ServerResp);
		}

	}

}
