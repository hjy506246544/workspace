package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * DAO for table "LIGHT_ENTITY".
 */
public class LightEntityDao extends AbstractDao<LightEntity, Long> {

	public static final String TABLENAME = "LIGHT_ENTITY";

	/**
	 * Properties of entity LightEntity.<br/>
	 * Can be used for QueryBuilder and for referencing column names.
	 */
	public static class Properties {
		public final static Property Id = new Property(0, Long.class, "id",
				true, "_id");
		public final static Property IndexId = new Property(1, int.class,
				"indexId", false, "INDEX_ID");
		public final static Property IconId = new Property(2, int.class,
				"iconId", false, "ICON_ID");
		public final static Property StrText = new Property(3, String.class,
				"strText", false, "STR_TEXT");
		public final static Property Address = new Property(4, int.class,
				"address", false, "ADDRESS");
	};

	public LightEntityDao(DaoConfig config) {
		super(config);
	}

	public LightEntityDao(DaoConfig config, DaoSession daoSession) {
		super(config, daoSession);
	}

	/** Creates the underlying database table. */
	public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
		String constraint = ifNotExists ? "IF NOT EXISTS " : "";
		db.execSQL("CREATE TABLE " + constraint + "\"LIGHT_ENTITY\" (" + //
				"\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
				"\"INDEX_ID\" INTEGER NOT NULL ," + // 1: indexId
				"\"ICON_ID\" INTEGER NOT NULL ," + // 2: iconId
				"\"STR_TEXT\" TEXT NOT NULL ," + // 3: strText
				"\"ADDRESS\" INTEGER NOT NULL );"); // 4: address
	}

	/** Drops the underlying database table. */
	public static void dropTable(SQLiteDatabase db, boolean ifExists) {
		String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "")
				+ "\"LIGHT_ENTITY\"";
		db.execSQL(sql);
	}

	/** @inheritdoc */
	@Override
	protected void bindValues(SQLiteStatement stmt, LightEntity entity) {
		stmt.clearBindings();

		Long id = entity.getId();
		if (id != null) {
			stmt.bindLong(1, id);
		}
		stmt.bindLong(2, entity.getIndexId());
		stmt.bindLong(3, entity.getIconId());
		stmt.bindString(4, entity.getStrText());
		stmt.bindLong(5, entity.getAddress());
	}

	/** @inheritdoc */
	@Override
	public Long readKey(Cursor cursor, int offset) {
		return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
	}

	/** @inheritdoc */
	@Override
	public LightEntity readEntity(Cursor cursor, int offset) {
		LightEntity entity = new LightEntity( //
				cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
				cursor.getInt(offset + 1), // indexId
				cursor.getInt(offset + 2), // iconId
				cursor.getString(offset + 3), // strText
				cursor.getInt(offset + 4) // address
		);
		return entity;
	}

	/** @inheritdoc */
	@Override
	public void readEntity(Cursor cursor, LightEntity entity, int offset) {
		entity.setId(cursor.isNull(offset + 0) ? null : cursor
				.getLong(offset + 0));
		entity.setIndexId(cursor.getInt(offset + 1));
		entity.setIconId(cursor.getInt(offset + 2));
		entity.setStrText(cursor.getString(offset + 3));
		entity.setAddress(cursor.getInt(offset + 4));
	}

	/** @inheritdoc */
	@Override
	protected Long updateKeyAfterInsert(LightEntity entity, long rowId) {
		entity.setId(rowId);
		return rowId;
	}

	/** @inheritdoc */
	@Override
	public Long getKey(LightEntity entity) {
		if (entity != null) {
			return entity.getId();
		} else {
			return null;
		}
	}

	/** @inheritdoc */
	@Override
	protected boolean isEntityUpdateable() {
		return true;
	}

}
