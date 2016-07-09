package greendao;




import java.util.List;

import common.ApplicationSmart;


import android.content.Context;

public class DBHelper {
	private static final String TAG = DBHelper.class.getSimpleName();
	private static DBHelper instance;
	private static Context appContext;
	private DaoSession mDaoSession;
    public final static int SCENE_TYPE_LIGHT=0;
    public final static int SCENE_TYPE_CURTAIN=1;
    public final static int SCENE_TYPE_AC=2;
    public final static int SCENE_TYPE_MUSIC=3;
    public final static int SCENE_TYPE_EXE_MODE=4;
    
	private CurtainEntityDao mCurtainEntityDao;
	private ACEntityDao mACEntityDao;
	private LightEntityDao mLightEntityDao;
	private SceneEntityDao mSceneEntityDao;
	
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
                .where(greendao.SceneEntityDao.Properties.StrName.eq(strText))
                .orderAsc(greendao.SceneEntityDao.Properties.Id).limit(5)
                .list();
    }
	

}
