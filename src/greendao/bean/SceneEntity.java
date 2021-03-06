package greendao.bean;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "SCENE_ENTITY".
 */
public class SceneEntity {

    private Long id;

    private String strName;
    private int iconId;

    private String strType;

    private String strSubitemName;
    private int action;
    private int exeTimeSegment;

    public SceneEntity() {
    }

    public SceneEntity(Long id) {
        this.id = id;
    }

    public SceneEntity(Long id, String strName, int iconId, String strType, String strSubitemName, int action, int exeTimeSegment) {
        this.id = id;
        this.strName = strName;
        this.iconId = iconId;
        this.strType = strType;
        this.strSubitemName = strSubitemName;
        this.action = action;
        this.exeTimeSegment = exeTimeSegment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrName() {
        return strName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setStrName( String strName) {
        this.strName = strName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getStrType() {
        return strType;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setStrType( String strType) {
        this.strType = strType;
    }

    public String getStrSubitemName() {
        return strSubitemName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setStrSubitemName( String strSubitemName) {
        this.strSubitemName = strSubitemName;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getExeTimeSegment() {
        return exeTimeSegment;
    }

    public void setExeTimeSegment(int exeTimeSegment) {
        this.exeTimeSegment = exeTimeSegment;
    }

}
