package greendao;




import java.util.List;

import common.ApplicationSmart;


import android.content.Context;

public class DBHelper {
	private static final String TAG = DBHelper.class.getSimpleName();
	private static DBHelper instance;
	private static Context appContext;
	private DaoSession mDaoSession;

	private CurtainEntityDao mCurtainEntityDao;
	private ACEntityDao mACEntityDao;
	private LightEntityDao mLightEntityDao;
	private SceneEntityDao mSceneEntityDao;
	private SceneItemLightEntityDao mSceneItemLightEntityDao;
	private SceneItemACEntityDao mSceneItemACEntityDao;
	private SceneItemCurtainEntityDao mSceneItemCurtainEntityDao;
	private BgMusicSelectedListEntityDao mBgMusicSelectedListEntityDao;
	
	private DBHelper() {
	}

	public static DBHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DBHelper();
			if (appContext == null) {
				appContext = context.getApplicationContext();
			}
			instance.mDaoSession = ApplicationSmart.getDaoSession(context);
			instance.mCurtainEntityDao = instance.mDaoSession
					.getCurtainEntityDao();
			instance.mACEntityDao = instance.mDaoSession
					.getACEntityDao();
			instance.mLightEntityDao = instance.mDaoSession
					.getLightEntityDao();
			
			instance.mSceneEntityDao = instance.mDaoSession
					.getSceneEntityDao();
			instance.mSceneItemLightEntityDao = instance.mDaoSession
					.getSceneItemLightEntityDao();
			instance.mSceneItemACEntityDao = instance.mDaoSession
					.getSceneItemACEntityDao();
			instance.mSceneItemCurtainEntityDao = instance.mDaoSession
					.getSceneItemCurtainEntityDao();
			instance.mBgMusicSelectedListEntityDao = instance.mDaoSession
					.getBgMusicSelectedListEntityDao();
			
		}
		return instance;
	}

	public long saveCurtainEntity(CurtainEntity session) {
		return mCurtainEntityDao.insertOrReplace(session);
	}

	public List<CurtainEntity> loadAllCurtainEntity() {

		return mCurtainEntityDao.loadAll();
	}

	public CurtainEntity loadCurtainEntityById(long id) {
		return mCurtainEntityDao.load(id);
	}

	public void DeleteCurtainEntityById(long id) {
		mCurtainEntityDao.deleteByKey(id);
	}

	public void DeleteCurtainEntity(CurtainEntity entity) {
		mCurtainEntityDao.delete(entity);
	}
	
	public  List<CurtainEntity> select(String strText)
    {
        return  mCurtainEntityDao.queryBuilder()
                .where(greendao.CurtainEntityDao.Properties.StrText.eq(strText))
                .orderAsc(greendao.CurtainEntityDao.Properties.Id).limit(5)
                .list();
    }
	
	
	
	
	/*************************AC*******************************/
	public long saveACEntity(ACEntity session) {
		return mACEntityDao.insertOrReplace(session);
	}

	public List<ACEntity> loadAllACEntity() {

		return mACEntityDao.loadAll();
	}

	public ACEntity loadACEntityById(long id) {
		return mACEntityDao.load(id);
	}

	public void DeleteACEntityById(long id) {
		mACEntityDao.deleteByKey(id);
	}

	public void DeleteACEntity(ACEntity entity) {
		mACEntityDao.delete(entity);
	}
	
	public  List<ACEntity> select_AC(String strText)
    {
        return  mACEntityDao.queryBuilder()
                .where(greendao.ACEntityDao.Properties.StrText.eq(strText))
                .orderAsc(greendao.ACEntityDao.Properties.Id).limit(5)
                .list();
    }
	/*******************************light***********************/
	public long saveLightEntity(LightEntity session) {
		return mLightEntityDao.insertOrReplace(session);
	}

	public List<LightEntity> loadAllLightEntity() {

		return mLightEntityDao.loadAll();
	}

	public LightEntity loadLightEntityById(long id) {
		return mLightEntityDao.load(id);
	}

	public void DeleteLightEntityById(long id) {
		mLightEntityDao.deleteByKey(id);
	}

	public void DeleteLightEntity(LightEntity entity) {
		mLightEntityDao.delete(entity);
	}
	
	public  List<LightEntity> select_Light(String strText)
    {
        return  mLightEntityDao.queryBuilder()
                .where(greendao.LightEntityDao.Properties.StrText.eq(strText))
                .orderAsc(greendao.LightEntityDao.Properties.Id).limit(5)
                .list();
    }
	/*******************************scene***********************/
	public long saveSceneEntity(SceneEntity session) {
		return mSceneEntityDao.insertOrReplace(session);
	}

	public List<SceneEntity> loadAllSceneEntity() {

		return mSceneEntityDao.loadAll();
	}

	public SceneEntity loadSceneEntityById(long id) {
		return mSceneEntityDao.load(id);
	}

	public void DeleteSceneEntityById(long id) {
		mSceneEntityDao.deleteByKey(id);
	}

	public void DeleteSceneEntity(SceneEntity entity) {
		mSceneEntityDao.delete(entity);
	}
	
	public  List<SceneEntity> select_Scene(String strText)
    {
        return  mSceneEntityDao.queryBuilder()
                .where(greendao.SceneEntityDao.Properties.StrText.eq(strText))
                .orderAsc(greendao.SceneEntityDao.Properties.Id).limit(5)
                .list();
    }
	/*******************************scene light***********************/
	public long saveSceneItemLightEntity(SceneItemLightEntity session) {
		return mSceneItemLightEntityDao.insertOrReplace(session);
	}

	public List<SceneItemLightEntity> loadAllSceneItemLightEntity() {

		return mSceneItemLightEntityDao.loadAll();
	}

	public SceneItemLightEntity loadSceneItemLightEntityById(long id) {
		return mSceneItemLightEntityDao.load(id);
	}

	public void DeleteSceneItemLightEntityById(long id) {
		mSceneItemLightEntityDao.deleteByKey(id);
	}

	public void DeleteSceneItemLightEntity(SceneItemLightEntity entity) {
		mSceneItemLightEntityDao.delete(entity);
	}
	
	public  List<SceneItemLightEntity> select_SceneItemLight(String strText)
    {
        return  mSceneItemLightEntityDao.queryBuilder()
                .where(greendao.SceneItemLightEntityDao.Properties.StrText.eq(strText))
                .orderAsc(greendao.SceneItemLightEntityDao.Properties.Id).limit(5)
                .list();
    }
	/*******************************scene AC***********************/
	public long saveSceneItemACEntity(SceneItemACEntity session) {
		return mSceneItemACEntityDao.insertOrReplace(session);
	}

	public List<SceneItemACEntity> loadAllSceneItemACEntity() {

		return mSceneItemACEntityDao.loadAll();
	}

	public SceneItemACEntity loadSceneItemACEntityById(long id) {
		return mSceneItemACEntityDao.load(id);
	}

	public void DeleteSceneItemACEntityById(long id) {
		mSceneItemACEntityDao.deleteByKey(id);
	}

	public void DeleteSceneItemACEntity(SceneItemACEntity entity) {
		mSceneItemACEntityDao.delete(entity);
	}
	
	public  List<SceneItemACEntity> select_SceneItemAC(String strText)
    {
        return  mSceneItemACEntityDao.queryBuilder()
                .where(greendao.SceneItemACEntityDao.Properties.StrText.eq(strText))
                .orderAsc(greendao.SceneItemACEntityDao.Properties.Id).limit(5)
                .list();
    }
	/******************************* BgMusicSelectedListEntityDao***********************/
	public long saveBgMusicSelectedListEntity(BgMusicSelectedListEntity session) {
		return mBgMusicSelectedListEntityDao.insertOrReplace(session);
	}

	public List<BgMusicSelectedListEntity> loadAllBgMusicSelectedListEntity() {

		return mBgMusicSelectedListEntityDao.loadAll();
	}

	public BgMusicSelectedListEntity loadBgMusicSelectedListEntityById(long id) {
		return mBgMusicSelectedListEntityDao.load(id);
	}

	public void DeleteBgMusicSelectedListEntityById(long id) {
		mBgMusicSelectedListEntityDao.deleteByKey(id);
	}

	public void DeleteBgMusicSelectedListEntity(BgMusicSelectedListEntity entity) {
		mBgMusicSelectedListEntityDao.delete(entity);
	}
	
	public  List<BgMusicSelectedListEntity> select_BgMusicSelectedList(String strText)
    {
        return  mBgMusicSelectedListEntityDao.queryBuilder()
                .where(greendao.BgMusicSelectedListEntityDao.Properties.Path.eq(strText))
                .orderAsc(greendao.BgMusicSelectedListEntityDao.Properties.Id).limit(5)
                .list();
    }
	/*******************************scene Curtain***********************/
	public long saveSceneItemCurtainEntity(SceneItemCurtainEntity session) {
		return mSceneItemCurtainEntityDao.insertOrReplace(session);
	}

	public List<SceneItemCurtainEntity> loadAllSceneItemCurtainEntity() {

		return mSceneItemCurtainEntityDao.loadAll();
	}

	public SceneItemCurtainEntity loadSceneItemCurtainEntityById(long id) {
		return mSceneItemCurtainEntityDao.load(id);
	}

	public void DeleteSceneItemCurtainEntityById(long id) {
		mSceneItemCurtainEntityDao.deleteByKey(id);
	}

	public void DeleteSceneItemCurtainEntity(SceneItemCurtainEntity entity) {
		mSceneItemCurtainEntityDao.delete(entity);
	}
	
	public  List<SceneItemCurtainEntity> select_SceneItemCurtain(String strText)
    {
        return  mSceneItemCurtainEntityDao.queryBuilder()
                .where(greendao.SceneItemCurtainEntityDao.Properties.StrText.eq(strText))
                .orderAsc(greendao.SceneItemCurtainEntityDao.Properties.Id).limit(5)
                .list();
    }

}
