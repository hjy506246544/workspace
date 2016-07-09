package greendao;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ACENTITY".
 */
public class ACEntity {

    private Long id;
    private int type;
    private int iconId;

    private String strText;
    private int address;
    private boolean isOpen;

    public ACEntity() {
    }

    public ACEntity(Long id) {
        this.id = id;
    }

    public ACEntity(Long id, int indexId, int iconId, String strText, int address, boolean isOpen) {
        this.id = id;
        this.type = indexId;
        this.iconId = iconId;
        this.strText = strText;
        this.address = address;
        this.isOpen = isOpen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIndexId() {
        return type;
    }

    public void setIndexId(int indexId) {
        this.type = indexId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getStrText() {
        return strText;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setStrText( String strText) {
        this.strText = strText;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

}
