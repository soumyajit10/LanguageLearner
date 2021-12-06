package com.example.languagelearner;

import androidx.appcompat.app.AppCompatActivity;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;



public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener FocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_LOSS)
                mediaPlayer.release();
        }
    };







    private MediaPlayer.OnCompletionListener CompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(NumbersActivity.this,"ruk jao sabar kaaro",Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

      ArrayList <family> NumbersToTry =  new ArrayList <family>();
        NumbersToTry.add(new family("1","uno",R.drawable.one,R.raw.uno));
      NumbersToTry.add(new family("2","des",R.drawable.two,R.raw.des));
        NumbersToTry.add(new family("3","Tres",R.drawable.three,R.raw.tress));
        NumbersToTry.add(new family("4","cuatro",R.drawable.four,R.raw.cuatro));
        NumbersToTry.add(new family("5","cinco",R.drawable.five,R.raw.cinco));
        NumbersToTry.add(new family("6","seis",R.drawable.six,R.raw.seis));
        NumbersToTry.add(new family("7","siete",R.drawable.seven,R.raw.seite));
        NumbersToTry.add(new family("8","ocho",R.drawable.eight,R.raw.ocho));
        NumbersToTry.add(new family("9","nueve",R.drawable.nine,R.raw.nueve));
     NumbersToTry.add(new family("10","diez",R.drawable.ten,R.raw.diez));
        NumbersToTry.add(new family("20","veinte",R.drawable.boy,R.raw.veinte));


        familysAdapter ShowNumber = new familysAdapter(this,NumbersToTry,R.color.sea_green);
       ListView NumbersListView =(ListView) findViewById(R.id.Family);
        NumbersListView.setAdapter(ShowNumber);

        NumbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                family NumbersToTryS = NumbersToTry.get(position);
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(FocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, NumbersToTryS.getAudioResourceId());
                Toast.makeText(NumbersActivity.this,"play",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(CompleteListener);
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
        if (mediaPlayer!= null){
            mediaPlayer.release();
            mediaPlayer= null;
            audioManager.abandonAudioFocus(FocusChangeListener);
        }
    }
    }


