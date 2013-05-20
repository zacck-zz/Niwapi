package com.semasoft.niwapi;



import com.semasoft.niwapi.database.DaoMaster;
import com.semasoft.niwapi.database.DaoMaster.DevOpenHelper;
import com.semasoft.niwapi.database.DaoSession;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class NiwapiController extends Application {
	
	DevOpenHelper NiwapiOpenHelper;
	SQLiteDatabase db;
	DaoMaster dMaster;
	DaoSession dSession;
	
	@Override
	public void onCreate() {
		super.onCreate();
		NiwapiOpenHelper = new DevOpenHelper(this, "niwapi_db", null);
		db = NiwapiOpenHelper.getWritableDatabase();
		dMaster = new DaoMaster(db);
		dSession = dMaster.newSession();
		
	}

	

}
