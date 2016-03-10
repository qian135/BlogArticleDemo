package com.whereru.testcode2;

/**
 * 类型一
 */

public class ListItemTypeOne extends ListItemBaseType {

    private String mString;

    public ListItemTypeOne(int itemType, String string) {
        super(itemType);
        mString = string;
    }

    public String getString() {
        return mString;
    }

}
