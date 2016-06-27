package common;

import greendao.DaoMaster;
import greendao.DaoMaster.OpenHelper;
import greendao.DaoSession;

import java.util.ArrayList;



import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ApplicationSmart extends Application {
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	public static SQLiteDatabase db;
	public static final String DB_NAME = "com.gvs.controlcenter.db";



	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		super.onCreate();

	}

	/**
	 * create DaoMaster
	 * 
	 * @param context
	 * @return
	 */
	public static DaoMaster getDaoMaster(Context context) {
		if (daoMaster == null) {
			OpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME,
					null);
			daoMaster = new DaoMaster(helper.getWritableDatabase());
		}
		return daoMaster;
	}

	/**
	 * get DaoSession
	 * 
	 * @param context
	 * @return
	 */
	public static DaoSession getDaoSession(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

	/**
	 * create Datebase
	 * 
	 * @param context
	 * @return
	 */
	public static SQLiteDatabase getSQLDatebase(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			db = daoMaster.getDatabase();
		}
		return db;
	}

}
