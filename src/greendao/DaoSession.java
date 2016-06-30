package greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig aCEntityDaoConfig;
    private final DaoConfig curtainEntityDaoConfig;
    private final DaoConfig lightEntityDaoConfig;
    private final DaoConfig bgMusicSelectedListEntityDaoConfig;
    private final DaoConfig sceneEntityDaoConfig;
    private final DaoConfig sceneItemLightEntityDaoConfig;
    private final DaoConfig sceneItemCurtainEntityDaoConfig;
    private final DaoConfig sceneItemACEntityDaoConfig;

    private final ACEntityDao aCEntityDao;
    private final CurtainEntityDao curtainEntityDao;
    private final LightEntityDao lightEntityDao;
    private final BgMusicSelectedListEntityDao bgMusicSelectedListEntityDao;
    private final SceneEntityDao sceneEntityDao;
    private final SceneItemLightEntityDao sceneItemLightEntityDao;
    private final SceneItemCurtainEntityDao sceneItemCurtainEntityDao;
    private final SceneItemACEntityDao sceneItemACEntityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        aCEntityDaoConfig = daoConfigMap.get(ACEntityDao.class).clone();
        aCEntityDaoConfig.initIdentityScope(type);

        curtainEntityDaoConfig = daoConfigMap.get(CurtainEntityDao.class).clone();
        curtainEntityDaoConfig.initIdentityScope(type);

        lightEntityDaoConfig = daoConfigMap.get(LightEntityDao.class).clone();
        lightEntityDaoConfig.initIdentityScope(type);

        bgMusicSelectedListEntityDaoConfig = daoConfigMap.get(BgMusicSelectedListEntityDao.class).clone();
        bgMusicSelectedListEntityDaoConfig.initIdentityScope(type);

        sceneEntityDaoConfig = daoConfigMap.get(SceneEntityDao.class).clone();
        sceneEntityDaoConfig.initIdentityScope(type);

        sceneItemLightEntityDaoConfig = daoConfigMap.get(SceneItemLightEntityDao.class).clone();
        sceneItemLightEntityDaoConfig.initIdentityScope(type);

        sceneItemCurtainEntityDaoConfig = daoConfigMap.get(SceneItemCurtainEntityDao.class).clone();
        sceneItemCurtainEntityDaoConfig.initIdentityScope(type);

        sceneItemACEntityDaoConfig = daoConfigMap.get(SceneItemACEntityDao.class).clone();
        sceneItemACEntityDaoConfig.initIdentityScope(type);

        aCEntityDao = new ACEntityDao(aCEntityDaoConfig, this);
        curtainEntityDao = new CurtainEntityDao(curtainEntityDaoConfig, this);
        lightEntityDao = new LightEntityDao(lightEntityDaoConfig, this);
        bgMusicSelectedListEntityDao = new BgMusicSelectedListEntityDao(bgMusicSelectedListEntityDaoConfig, this);
        sceneEntityDao = new SceneEntityDao(sceneEntityDaoConfig, this);
        sceneItemLightEntityDao = new SceneItemLightEntityDao(sceneItemLightEntityDaoConfig, this);
        sceneItemCurtainEntityDao = new SceneItemCurtainEntityDao(sceneItemCurtainEntityDaoConfig, this);
        sceneItemACEntityDao = new SceneItemACEntityDao(sceneItemACEntityDaoConfig, this);

        registerDao(ACEntity.class, aCEntityDao);
        registerDao(CurtainEntity.class, curtainEntityDao);
        registerDao(LightEntity.class, lightEntityDao);
        registerDao(BgMusicSelectedListEntity.class, bgMusicSelectedListEntityDao);
        registerDao(SceneEntity.class, sceneEntityDao);
        registerDao(SceneItemLightEntity.class, sceneItemLightEntityDao);
        registerDao(SceneItemCurtainEntity.class, sceneItemCurtainEntityDao);
        registerDao(SceneItemACEntity.class, sceneItemACEntityDao);
    }
    
    public void clear() {
        aCEntityDaoConfig.getIdentityScope().clear();
        curtainEntityDaoConfig.getIdentityScope().clear();
        lightEntityDaoConfig.getIdentityScope().clear();
        bgMusicSelectedListEntityDaoConfig.getIdentityScope().clear();
        sceneEntityDaoConfig.getIdentityScope().clear();
        sceneItemLightEntityDaoConfig.getIdentityScope().clear();
        sceneItemCurtainEntityDaoConfig.getIdentityScope().clear();
        sceneItemACEntityDaoConfig.getIdentityScope().clear();
    }

    public ACEntityDao getACEntityDao() {
        return aCEntityDao;
    }

    public CurtainEntityDao getCurtainEntityDao() {
        return curtainEntityDao;
    }

    public LightEntityDao getLightEntityDao() {
        return lightEntityDao;
    }

    public BgMusicSelectedListEntityDao getBgMusicSelectedListEntityDao() {
        return bgMusicSelectedListEntityDao;
    }

    public SceneEntityDao getSceneEntityDao() {
        return sceneEntityDao;
    }

    public SceneItemLightEntityDao getSceneItemLightEntityDao() {
        return sceneItemLightEntityDao;
    }

    public SceneItemCurtainEntityDao getSceneItemCurtainEntityDao() {
        return sceneItemCurtainEntityDao;
    }

    public SceneItemACEntityDao getSceneItemACEntityDao() {
        return sceneItemACEntityDao;
    }

}