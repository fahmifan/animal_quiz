package com.example.mortezasaadat.animalquiz;

public class Aksara {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String mAksaraWord;
    private int mImageResourceId = NO_IMAGE_PROVIDED ;

    public Aksara(String aksaraWord) {
        mAksaraWord = aksaraWord;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public Aksara(String aksaraWord, int imageID) {
        mAksaraWord = aksaraWord;
        mImageResourceId = imageID;
    }

    public Boolean isHasImage() {
        return this.mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public String getmAksaraWord() {
        return mAksaraWord;
    }
}
