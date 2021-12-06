package com.example.languagelearner;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.languagelearner.EventListener;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener FocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
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
        public void onCompletion(MediaPlayer mediaPlayer) {

            Toast.makeText(ColorsActivity.this,"now playing",Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        ArrayList<family> wordsName= new ArrayList<family>();
        wordsName.add(new family("Red","Rojo",R.drawable.red,R.raw.rojo));
        wordsName.add(new family("Green","Verde",R.drawable.green,R.raw.verde));
        wordsName.add(new family("Blue","Azul",R.drawable.blue,R.raw.azuk));
        wordsName.add(new family("Orange","Anaranjado",R.drawable.orange,R.raw.ana));
        wordsName.add(new family("Yellow","Amarillo",R.drawable.yellow,R.raw.amarillo));
        wordsName.add(new family("Purple","Morad0",R.drawable.purple,R.raw.primo));
        wordsName.add(new family("Pink","Rosado",R.drawable.pink,R.raw.rosado));
        wordsName.add(new family("Black","Negro",R.drawable.black,R.raw.negro));
        wordsName.add(new family("Brown","Marron",R.drawable.green,R.raw.marron));
        wordsName.add(new family("White","Blanco",R.drawable.white,R.raw.blanco));
        wordsName.add(new family("Dark","Oscuro",R.drawable.black,R.raw.oscuro));
        wordsName.add(new family("Light","Claro",R.drawable.white,R.raw.claro));


        familysAdapter colorAdapter= new familysAdapter(this,wordsName,R.color.teal_200);
        ListView ColorlistView = (ListView)findViewById(R.id.Family);
        ColorlistView.setAdapter(colorAdapter);

        ColorlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                family wordsNames = wordsName.get(position) ;
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(FocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
             mediaPlayer = MediaPlayer.create(ColorsActivity.this,wordsNames.getAudioResourceId());
                Toast.makeText(ColorsActivity.this,"play",Toast.LENGTH_SHORT).show();
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
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer= null;
            audioManager.abandonAudioFocus(FocusChangeListener);
        }
    }

}