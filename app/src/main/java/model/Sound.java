package model;

import android.content.Context;
import android.media.MediaPlayer;

import com.appsmontreal.menu2.R;

public class Sound {
    private Context context;
    MediaPlayer mediaPlayer;

    public Sound(Context context) {
        this.context = context;
    }

    public void playSound(MediaPlayer play){
        play.start();
        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaP) {
                mediaP.release();
            };
        });

    }

    public void soundGoForward(){
        mediaPlayer = MediaPlayer.create(context,R.raw.ticka);
        playSound(mediaPlayer);
    }

    public void soundGoBack(){
        mediaPlayer = MediaPlayer.create(context,R.raw.woosha);
        playSound(mediaPlayer);
    }

    public void soundGoExit(){
        mediaPlayer = MediaPlayer.create(context,R.raw.goodbye);
        playSound(mediaPlayer);
    }
}
