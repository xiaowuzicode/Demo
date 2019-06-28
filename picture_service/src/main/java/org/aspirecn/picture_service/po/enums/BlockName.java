package org.aspirecn.picture_service.po.enums;

/**
 * @auther cg
 */
public enum BlockName {

    /*
     * 图1
     */
    ONE(1,"1.png"),
    /*
     * 图2
     */
    TWO(2,"2.png"),
    /*
     * 图3
     */
    THREE(3,"3.png"),

    /*
     * 图4
     */
    FOUR(4,"4.png");


    private int key;
    private String value;

    BlockName(int key, String value){
        this.key=key;
        this.value=value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BlockName{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
