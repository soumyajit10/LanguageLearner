package com.example.languagelearner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;


    public WordAdapter (Activity context, ArrayList<Word> MyPhrases,int ColorResourceId){

        super(context,0,MyPhrases);
       mColorResourceId = ColorResourceId;

}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemsView = convertView;
        if(listItemsView==null){
            listItemsView= LayoutInflater.from(getContext()).inflate(R.layout.word_item,parent,false);

        }
        Word currentItem= getItem(position);


        TextView englishTextView =(TextView)listItemsView.findViewById(R.id.english);
        englishTextView.setText(currentItem.getEnglishTranslation());

        TextView spanishTextVView= (TextView)listItemsView.findViewById(R.id.spanish);
        spanishTextVView.setText(currentItem.getSpanishTranslation());

        ImageView imageView= (ImageView)listItemsView.findViewById(R.id.play2);
        imageView.setImageResource(currentItem.getImageResourceId());

       // ImageView imageViews = (ImageView)listItemsView.findViewById(R.id.view);
        //int k= currentItem.getmImageId();
      //  boolean b = (k!=0);
       // if(b==true){
       // if(currentItem.hasImage()) {
          //  imageViews.setImageResource(currentItem.getmImageId());
          //  imageViews.setVisibility(View.VISIBLE);

       // }else {
          //  imageViews.setVisibility(View.GONE);
       // }
        View textContainer = listItemsView.findViewById(R.id.textContainer);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemsView;
    }














}
