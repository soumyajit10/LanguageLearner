package com.example.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Family_membersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener FocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if ( focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange ==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){
                mediaPlayer.release();
            }
        }
    };


    private MediaPlayer.OnCompletionListener mCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(Family_membersActivity.this,"done",Toast.LENGTH_SHORT).show();

            releaseMediaPlayer();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList <family> MemberInfo = new ArrayList<family>();
        MemberInfo.add(new family("Me","i",R.drawable.uncle,R.raw.i));
        MemberInfo.add(new family("husband","esposo",R.drawable.boy,R.raw.esposo));
       MemberInfo.add(new family("wife","esposa",R.drawable.girl,R.raw.esposa));
      MemberInfo.add(new family("son","hijo",R.drawable.boy,R.raw.hijo));
       MemberInfo.add(new family("daughter","hija",R.drawable.daughter,R.raw.hijo));
      MemberInfo.add(new family("Father","padre",R.drawable.father,R.raw.padre));
       MemberInfo.add(new family("Mother","madre",R.drawable.mother,R.raw.padre));
       MemberInfo.add(new family("brother","hermano",R.drawable.cousin,R.raw.hermano));
     MemberInfo.add(new family("sister","hermana",R.drawable.boy,R.raw.hermano));
       MemberInfo.add(new family("cousins","primo / prima",R.drawable.cousin,R.raw.primo));
       MemberInfo.add(new family("grand father","abuelo",R.drawable.grandpa,R.raw.abuelo));
     MemberInfo.add(new family("grand mother","abuela",R.drawable.grandma,R.raw.abuelo));
     MemberInfo.add(new family("uncle","tio",R.drawable.father,R.raw.tio));
     MemberInfo.add(new family("Aunt","tia",R.drawable.mother,R.raw.tio));
      MemberInfo.add(new family("mom","mama",R.drawable.mother,R.raw.mama));
      MemberInfo.add(new family("dad","papa",R.drawable.father,R.raw.mama));
        MemberInfo.add(new family("the family","la familia",R.drawable.all,R.raw.familia));
                MemberInfo.add(new family("parents","mis padres",R.drawable.family,R.raw.familia));

        familysAdapter MemberAdapter = new familysAdapter(this,MemberInfo,R.color.purple_500 );
        ListView listView = (ListView)findViewById(R.id.Family);
        listView.setAdapter(MemberAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(FocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result ==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    family MemberInfos = MemberInfo.get(position);

                    mediaPlayer = MediaPlayer.create(Family_membersActivity.this, MemberInfos.getAudioResourceId());
                    Toast.makeText(Family_membersActivity.this, "play", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompleteListener);


                }

            }
        });


    }
    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer= null;
            audioManager.abandonAudioFocus(FocusChangeListener);
        }
    }

}
