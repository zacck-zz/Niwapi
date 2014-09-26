package com.semasoft.niwapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



import android.app.Activity;
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

	/*
	 * private class initializeUser extends AsyncTask<Void, Void, Void> {
	 * 
	 * UserDao ud = mSession.getUserDao(); ContestDao cd =
	 * mSession.getContestDao(); User me; Contest c; HttpResponse response;
	 * String resp = null, resp2;
	 * 
	 * @Override protected Void doInBackground(Void... arg0) { // Create a new
	 * HttpClient and Post Header HttpClient httpclient = new
	 * DefaultHttpClient(); HttpPost httppost = new HttpPost(
	 * "http://momoviez.co.ke/niwapi_remote_service/webservice.php");
	 * 
	 * try { // Add your data List<NameValuePair> nameValuePairs = new
	 * ArrayList<NameValuePair>( 4); nameValuePairs.add(new
	 * BasicNameValuePair("type", "1")); nameValuePairs.add(new
	 * BasicNameValuePair("user_name", name)); nameValuePairs .add(new
	 * BasicNameValuePair("user_pass", password)); nameValuePairs.add(new
	 * BasicNameValuePair("user_mail", mail)); httppost.setEntity(new
	 * UrlEncodedFormEntity(nameValuePairs));
	 * 
	 * // Execute HTTP Post Request response = httpclient.execute(httppost); try
	 * { resp = EntityUtils.toString(response.getEntity()); } catch
	 * (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } } catch (Exception e) {
	 * 
	 * } return null; }
	 * 
	 * @Override protected void onProgressUpdate(Void... values) {
	 * 
	 * super.onProgressUpdate(values); }
	 * 
	 * @Override protected void onPostExecute(Void result) {
	 * 
	 * resp2 = resp;// ;EntityUtils.toString(response.getEntity());
	 * Log.d("LOGGING", resp.toString()); JSONObject jsonResp = null; JSONObject
	 * contestobj = null; try { jsonResp = new JSONObject(resp2);
	 * 
	 * JSONObject userobj = null;
	 * 
	 * userobj = jsonResp.getJSONArray("users").getJSONObject(0)
	 * .getJSONObject("user");
	 * 
	 * // gotten the user objects JSONObject respy = null;
	 * 
	 * respy = new JSONObject(resp2);
	 * 
	 * contestobj = respy.getJSONArray("contests").getJSONObject(0)
	 * .getJSONObject("contest");
	 * 
	 * Log.d("REGACT", userobj.toString() + "   this is the other one "// + //
	 * contestobj.toString() );
	 * 
	 * me = new User(userobj.getInt("user_id"), userobj.getString("fb_token"),
	 * userobj.getString("user_name"), userobj.getString("user_pass"),
	 * userobj.getString("user_mail")); c = new
	 * Contest(contestobj.getInt("MAX(contest_id)"),
	 * contestobj.getString("contest_media"), convertStringToDate(contestobj
	 * .getString("contest_date_loaded")), convertStringToDate(contestobj
	 * .getString("contest_date_solved"))); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * cd.insert(c); ud.insert(me); startActivity(new
	 * Intent(RegisterActivity.this, MainImageActivity.class));
	 * 
	 * super.onPostExecute(result); } }
	 */

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
			// if (ETName.getText().toString().isEmpty()
			// || ETPassword.getText().toString().isEmpty()
			// || ETEmail.getText().toString().isEmpty()) {
			// Toast.makeText(RegisterActivity.this,
			// "Please Fill in All details correctly then try Agaim",
			// Toast.LENGTH_LONG).show();
			//
			// } else {
			// name = ETName.getText().toString();
			// password = ETPassword.getText().toString();
			// mail = ETEmail.getText().toString();
			// initializeUser iu = new initializeUser();
			// iu.execute();
			break;

		}

	}

}
