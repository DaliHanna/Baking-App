package com.example.android.bakingapp.Model;

import android.graphics.Bitmap;

public class Recipe {
    private String mId;
    private String mRecipeName;
    private String mServings;
    private Bitmap mImageBitmap;
    private String imageUrl;

    public Recipe(String mId, String mRecipeName, String mServings, Bitmap mImageBitmap, String imageUrl) {
        this.mId = mId;
        this.mRecipeName = mRecipeName;
        this.mServings = mServings;
        this.mImageBitmap = mImageBitmap;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return mId;
    }

    public String getRecipeName() {
        return mRecipeName;
    }

    public String getServings() {
        return mServings;
    }

    public Bitmap getImageBitmap() {
        return mImageBitmap;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
