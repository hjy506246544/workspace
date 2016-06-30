package greendao;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SCENE_ITEM_CURTAIN_ENTITY".
*/
public class SceneItemCurtainEntityDao extends AbstractDao<SceneItemCurtainEntity, Long> {

    public static final String TABLENAME = "SCENE_ITEM_CURTAIN_ENTITY";

    /**
     * Properties of entity SceneItemCurtainEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StrText = new Property(1, String.class, "strText", false, "STR_TEXT");
        public final static Property IconId = new Property(2, int.class, "iconId", false, "ICON_ID");
        public final static Property IsOn = new Property(3, boolean.class, "isOn", false, "IS_ON");
    };


    public SceneItemCurtainEntityDao(DaoConfig config) {
        super(config);
    }
    
    public SceneItemCurtainEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCENE_ITEM_CURTAIN_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"STR_TEXT\" TEXT NOT NULL ," + // 1: strText
                "\"ICON_ID\" INTEGER NOT NULL ," + // 2: iconId
                "\"IS_ON\" INTEGER NOT NULL );"); // 3: isOn
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCENE_ITEM_CURTAIN_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SceneItemCurtainEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getStrText());
        stmt.bindLong(3, entity.getIconId());
        stmt.bindLong(4, entity.getIsOn() ? 1L: 0L);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SceneItemCurtainEntity readEntity(Cursor cursor, int offset) {
        SceneItemCurtainEntity entity = new SceneItemCurtainEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // strText
            cursor.getInt(offset + 2), // iconId
            cursor.getShort(offset + 3) != 0 // isOn
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SceneItemCurtainEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStrText(cursor.getString(offset + 1));
        entity.setIconId(cursor.getInt(offset + 2));
        entity.setIsOn(cursor.getShort(offset + 3) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SceneItemCurtainEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(SceneItemCurtainEntity entity) {
        if(entity != null) {
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
