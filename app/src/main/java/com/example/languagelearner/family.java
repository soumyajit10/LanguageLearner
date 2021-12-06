
package com.example.languagelearner;

public class family {

    private String mDefaultTranslation;
    private String mSpanishTranslation;
    private int mImageResourceId;
    private int mAudioResourceId;

    public family(String defaultTranslation,String spanishTranslation,int imageResourceId,int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mSpanishTranslation = spanishTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;

    }
    public String getDefaultTranslation(){
        return mDefaultTranslation;

    }

    public String getSpanishTranslation(){
        return mSpanishTranslation;
    }

    public int getmImageResourceId(){
        return mImageResourceId;
    }
    public int getAudioResourceId(){return  mAudioResourceId;}



}
