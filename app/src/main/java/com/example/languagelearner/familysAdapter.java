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

public class familysAdapter extends ArrayAdapter<family> {

    private int mPaintResourceId;

    public familysAdapter (Activity context, ArrayList<family> MemberInfo,int paintResourceId){
        super(context,0,MemberInfo);
        mPaintResourceId = paintResourceId;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        family currentPiece= getItem(position);


        TextView englishTextView =(TextView)listItemView.findViewById(R.id.english);
        englishTextView.setText(currentPiece.getDefaultTranslation());

        TextView spanishTextVView= (TextView)listItemView.findViewById(R.id.spanish);
        spanishTextVView.setText(currentPiece.getSpanishTranslation());

        ImageView imageView = (ImageView)listItemView.findViewById(R.id.picture);
        imageView.setImageResource(currentPiece.getmImageResourceId());

        View layOut2 = listItemView.findViewById(R.id.layout2);
        int paint = ContextCompat.getColor(getContext(),mPaintResourceId);
      layOut2.setBackgroundColor(paint);








        return listItemView;
    }
}
