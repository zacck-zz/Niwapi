package com.semasoft.niwapi;

import java.util.List;

import com.semasoft.niwapi.database.DaoSession;
import com.semasoft.niwapi.database.UserDao;

import de.greenrobot.dao.QueryBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends Activity {

	DaoSession mSession;
	NiwapiController nc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		init();

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

	private void init() {
		nc = (NiwapiController) getApplication();
		mSession = nc.dSession;
		UserDao ud = mSession.getUserDao();

		QueryBuilder qb = ud.queryBuilder();

		List users = qb.list();
		if (users.isEmpty()) {

		} else {
			startActivity(new Intent(LoginActivity.this,
					MainImageActivity.class));
		}

	}
}