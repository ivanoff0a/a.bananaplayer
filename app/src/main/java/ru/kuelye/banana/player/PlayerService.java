package ru.kuelye.banana.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Alexey Leshchuk (ale@) on 2/1/2017.
 */

public class PlayerService extends Service {

    public static String ACTION_PLAY = "ru.kuelye.banana.player.PLAY";
    public static String ACTION_PAUSE = "ru.kuelye.banana.player.PAUSE";

    MediaPlayer player;
    boolean playing = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(ACTION_PLAY)) {
            if (player == null) {
                player = MediaPlayer.create(PlayerService.this, R.raw.song);
            }
            player.start();
            playing = true;
        } else if (intent.getAction().equals(ACTION_PAUSE)) {
            if (playing) {
                player.pause();
            }
        }

        return START_NOT_STICKY;
    }

}
