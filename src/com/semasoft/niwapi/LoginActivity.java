package com.semasoft.niwapi;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {

	NiwapiController nc;

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
		//check if our user is in already 
		SharedPreferences  loginPrefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
		String userloginid = loginPrefs.getString("uid", null);
		if(userloginid != null )
		{
			startActivity(new Intent(LoginActivity.this, MainImageActivity.class));
		}
		else
		{
			//init UI and stuff 
			EditText ln, lp;
			Button btlog;
			
			ln = (EditText)findViewById(R.id.etuname);
			lp = (EditText) findViewById(R.id.ettuno);
			
			btlog = (Button)findViewById(R.id.btnLogin);
			btlog.setOnClickListener(this);
		}
		
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.btnLogin:
			break;
		}
	}

	
}