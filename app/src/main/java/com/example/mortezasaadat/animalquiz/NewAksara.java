package com.example.mortezasaadat.animalquiz;

import android.graphics.Bitmap;

public class NewAksara {
    private static final Bitmap NO_IMAGE_PROVIDED = null;
    private String mAksaraWord;
    private Bitmap mImageBitmap = NO_IMAGE_PROVIDED ;

    public NewAksara(String aksaraWord) {
        mAksaraWord = aksaraWord;
    }

    public Bitmap getmImageBitmap() {
        return mImageBitmap;
    }

    public NewAksara(String aksaraWord, Bitmap imageID) {
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
