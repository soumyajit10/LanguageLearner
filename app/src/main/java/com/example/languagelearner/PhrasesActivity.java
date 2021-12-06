package com.example.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener FocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){
                mediaPlayer.release();
            }
        }
    };
    private MediaPlayer.OnCompletionListener CompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(PhrasesActivity.this,"now",Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        ArrayList<Word> MyPhrases =  new ArrayList <Word>();
        MyPhrases.add(new Word("hello", "hola",R.raw.hola,R.drawable.playicon2));
        MyPhrases.add(new Word("goodbye","adios",R.raw.adios,R.drawable.playicon2));
       MyPhrases.add(new Word("Good morning","Buenos dias",R.raw.buenos,R.drawable.playicon2));
    MyPhrases.add(new Word("Good afternoon","Buenas tardes",R.raw.buenos,R.drawable.playicon2));
       MyPhrases.add(new Word("Good evening","Buenas noches",R.raw.buenos,R.drawable.playicon2));
       MyPhrases.add(new Word("My name is john","hola,me llamao Juan",R.raw.holame,R.drawable.playicon2));
    MyPhrases.add(new Word("what is your name","como te llamas",R.raw.como,R.drawable.playicon2));
        MyPhrases.add(new Word("my name is","me llamo",R.raw.como,R.drawable.playicon2));
        MyPhrases.add(new Word("nice to meet you","mucho gusto",R.raw.mucho,R.drawable.playicon2));
      MyPhrases.add(new Word("thank you","gracias",R.raw.gracias,R.drawable.playicon2));
      MyPhrases.add(new Word("sorry","Lo siento",R.raw.lo,R.drawable.playicon2));
      MyPhrases.add(new Word("Bless you","Salud",R.raw.salud,R.drawable.playicon2));
      MyPhrases.add(new Word("yes","si",R.raw.si,R.drawable.playicon2));
      MyPhrases.add(new Word("who","Quien",R.raw.si,R.drawable.playicon2));
        MyPhrases.add(new Word("What","Que",R.raw.si,R.drawable.playicon2));
      MyPhrases.add(new Word("Where","Donde",R.raw.si,R.drawable.playicon2));
       MyPhrases.add(new Word("Excuse me","Disculpa",R.raw.disculpa,R.drawable.playicon2));
       MyPhrases.add(new Word("what is the time is","que hora es",R.raw.que,R.drawable.playicon2));
       MyPhrases.add(new Word("i am lost ","estoy perdido",R.raw.estoy,R.drawable.playicon2));
       MyPhrases.add(new Word("i don't understand","yo no comprendo",R.raw.yo,R.drawable.playicon2));
        MyPhrases.add(new Word(" I miss you","Te extrano",R.raw.teex,R.drawable.playicon2));
        MyPhrases.add(new Word("I love you","te quiero",R.raw.tequi,R.drawable.playicon2));



        WordAdapter ShowText = new WordAdapter (this,MyPhrases,R.color.very_light_purple);
        ListView PhrasesListView =(ListView) findViewById(R.id.Family);
        PhrasesListView.setAdapter(ShowText);

        PhrasesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word myPhrases = MyPhrases.get(position);
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(FocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, myPhrases.getAudioResourceId());
                    Toast.makeText(PhrasesActivity.this, "play", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(CompleteListener);
                }

            }
        });




    }

    @Override
    protected void onPause(){
        super.onPause();
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
