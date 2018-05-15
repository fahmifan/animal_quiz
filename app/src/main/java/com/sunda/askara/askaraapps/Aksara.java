package com.sunda.askara.askaraapps;

import android.graphics.Bitmap;

/*
* Aksara class will hold the image of aksara and the text corresponding to it
* */
public class Aksara {
    private static final Bitmap NO_IMAGE_PROVIDED = null;
    private String mAksaraWord;
    private Bitmap mImageBitmap = NO_IMAGE_PROVIDED ;

    public Aksara(String aksaraWord) {
        mAksaraWord = aksaraWord;
    }

    public Bitmap getmImageBitmap() {
        return mImageBitmap;
    }

    public Aksara(String aksaraWord, Bitmap imageID) {
        mAksaraWord = aksaraWord;
        mImageBitmap = imageID;
    }

    public Boolean isHasImage() {
        return this.mImageBitmap != NO_IMAGE_PROVIDED;
    }

    public String getmAksaraWord() {
        return mAksaraWord;
    }
}
