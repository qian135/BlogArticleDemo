package com.whereru.testcode2;

/**
 * 类型二
 */

import android.graphics.Bitmap;

public class ListItemTypeTwo extends ListItemBaseType {

    private Bitmap mBitmap;

    public ListItemTypeTwo(int itemType, Bitmap bitmap) {
        super(itemType);
        mBitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

}
