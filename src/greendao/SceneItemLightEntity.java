package greendao;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "SCENE_ITEM_LIGHT_ENTITY".
 */
public class SceneItemLightEntity {

    private Long id;

    private String strText;
    private int iconId;
    private boolean isOn;

    public SceneItemLightEntity() {
    }

    public SceneItemLightEntity(Long id) {
        this.id = id;
    }

    public SceneItemLightEntity(Long id, String strText, int iconId, boolean isOn) {
        this.id = id;
        this.strText = strText;
        this.iconId = iconId;
        this.isOn = isOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrText() {
        return strText;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setStrText( String strText) {
        this.strText = strText;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

}
