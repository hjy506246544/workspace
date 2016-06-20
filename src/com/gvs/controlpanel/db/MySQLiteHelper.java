package com.gvs.controlpanel.db;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private final static String DB_NAME = "db_controlpanel";

	private final static int VERSION = 1;

	private SQLiteDatabase dbConn = null;

	public MySQLiteHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
		dbConn = this.getReadableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists db_controlpanel(_id integer primary key autoincrement,scenename,typename)");
		//db.execSQL("create table if not exists db_sxt(_id integer primary key sxtname,typename)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if (oldVersion < newVersion) {
			db.execSQL("drop table if not exists db_controlpanel");
			//db.execSQL("drop table if not exists db_type");
			onCreate(dbConn);
		}

	}

	/**
	 * 执行带占位符的SQL语句 返回cursor
	 *
	 * @param sql
	 * @param selectionArgs
	 * @return
	 */
	public Cursor selectCursor(String sql, String[] selectionArgs) {

		return dbConn.rawQuery(sql, selectionArgs);
	}

	/**
	 * 执行带占位符的SQL语句 返回查询数量count
	 *
	 * @param sql
	 * @param selectionArgs
	 * @return
	 */
	public int selectCount(String sql, String[] selectionArgs) {
		Cursor cursor = dbConn.rawQuery(sql, selectionArgs);
		if (cursor != null) {
			cursor.moveToFirst();
			int count = cursor.getInt(0);
			cursor.close();
			return count;
		} else {
			return 0;
		}

	}

	public List<Map<String, Object>> selectList(String sql,
			String[] selectionArgs) {
		Cursor cursor = dbConn.rawQuery(sql, selectionArgs);
		return cursorToList(cursor);

	}

	/**
	 * 将Cursor转为 List集合
	 *
	 * @param cursor
	 * @return
	 */
	public List<Map<String, Object>> cursorToList(Cursor cursor) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				map.put(cursor.getColumnName(i), cursor.getString(i));
			}
			list.add(map);
		}

		return list;
	}

	/**
	 * 执行增删改方法
	 * @param sql
	 * @param bindArgs
	 * @return
	 */

	public boolean execData(String sql, Object[] bindArgs) {
		try {

			dbConn.execSQL(sql, bindArgs);

			return true;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;

	}

	public void destroy() {
		if (dbConn != null) {

			dbConn.close();
		}

	}

}
