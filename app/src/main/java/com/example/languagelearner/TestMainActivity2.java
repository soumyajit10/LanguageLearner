package com.example.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestMainActivity2 extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener FocusClickListener= new AudioManager.OnAudioFocusChangeListener() {
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
    private  MediaPlayer.OnCompletionListener CompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
      Toast.makeText(TestMainActivity2.this,"stop",Toast.LENGTH_SHORT).show();
      releaseMediaPlayer();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);
        ArrayList<family> CityName = new ArrayList<family>();
       CityName.add(new family("paris","France",R.drawable.paris,R.raw.tamil));
        CityName.add(new family("New york city","USA",R.drawable.nyc,R.raw.nyc));
                CityName.add(new family("London","England",R.drawable.london,R.raw.london));
        CityName.add(new family("Bangkock","thailand",R.drawable.bankok,R.raw.bangkok));
        CityName.add(new family("Hong kong","China",R.drawable.hongkong,R.raw.hongkong));
        CityName.add(new family("dubai","UAE",R.drawable.dubai,R.raw.dubai));
        CityName.add(new family("Singapore","malaysia",R.drawable.singapore,R.raw.singapore));
        CityName.add(new family("Rome","italy",R.drawable.rome,R.raw.rome));
        CityName.add(new family("macau","China",R.drawable.macau,R.raw.macau));
        CityName.add(new family("istanbul","turkey",R.drawable.istanbul,R.raw.istanbul));
        CityName.add(new family("kuala lampur","Malaysia",R.drawable.kl,R.raw.kua));
        CityName.add(new family("delhi","India",R.drawable.delhi,R.raw.delhi));
        CityName.add(new family("tokyo","japan",R.drawable.japan,R.raw.tokyo));
        CityName.add(new family("rio de jenario","brazil",R.drawable.antalya,R.raw.riode));


        familysAdapter ShowCity = new familysAdapter(this,CityName,R.color.purple_500);
        ListView CityListView =(ListView) findViewById(R.id.Family);
        CityListView.setAdapter(ShowCity);

        CityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                family citiesName = CityName.get(position);
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(FocusClickListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mediaPlayer = MediaPlayer.create(TestMainActivity2.this,citiesName.getAudioResourceId());
                Toast.makeText(TestMainActivity2.this,"play",Toast.LENGTH_SHORT).show();
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
        if (mediaPlayer!=null){
            mediaPlayer.release();
//            mediaPlayer = null;o0i9u8
        }
    }

}