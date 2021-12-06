package com.example.languagelearner;

public class Word {
    private String mEnglishTranslation;
    private String mSpanishTranslation;
    private int mAudioResourceId;
    private int mImageResourceId;
    //private int mImageId = NO_IMAGE_PROVIDED;
    //private static final int NO_IMAGE_PROVIDED =-1;


    public Word (String englishTranslation,String spanishTranslation,int audioResourceId,int imageResourceId){
        mEnglishTranslation= englishTranslation;
        mSpanishTranslation = spanishTranslation;
        mAudioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;
       // mImageId = imageId;

    }
    public String getEnglishTranslation(){ return mEnglishTranslation;}



    public String getSpanishTranslation(){
        return mSpanishTranslation;
    }
    public int getAudioResourceId(){return mAudioResourceId;}

    public int getImageResourceId() {
        return mImageResourceId;
    }
    //public int getmImageId(){
      //  return mImageId;

    @Override
    public String toString() {
        return "Word{" +
                "mEnglishTranslation='" + mEnglishTranslation + '\'' +
                ", mSpanishTranslation='" + mSpanishTranslation + '\'' +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
   // public boolean hasImage(){
     //   return mImageId != NO_IMAGE_PROVIDED;




