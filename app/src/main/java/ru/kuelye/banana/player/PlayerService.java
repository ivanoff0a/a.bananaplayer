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

    /**
     * Действие: песня, играй!
     */
    public static String ACTION_PLAY = "ru.kuelye.banana.player.PLAY";
    /**
     * Действие: песня, не играй!
     */
    public static String ACTION_PAUSE = "ru.kuelye.banana.player.PAUSE";

    /**
     * Проигрыватель. Виниловый.
     */
    MediaPlayer player;

    /*
     * Этот метод нам пока не нужен.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Метод выполняется каждый раз, когда сервису приходит конверт (Intent).
     */
    public int onStartCommand(Intent intent, int flags, int startId) {
        // создаём проигрыватель
        createPlayer();

        // в зависимости от сообщения либо запускаем, либо останавливаем воспроизведение песенки
        if (intent.getAction().equals(ACTION_PLAY)) {
            play();
        } else if (intent.getAction().equals(ACTION_PAUSE)) {
            pause();
        }

        return START_NOT_STICKY;
    }

    /**
     * Метод создаёт проигрыватель.
     * Если он, конечно, уже не создан.
     */
    private void createPlayer() {
        if (player == null) { // если проигрыватель ещё не создан
            // создаём его
            player = MediaPlayer.create(PlayerService.this, R.raw.song); // первый параметр - Context, второй - песенка
        }
    }

    /**
     * Метод запускает проигрыватель.
     * Если, конечно, он уже не запущен.
     */
    private void play() {
        if (!player.isPlaying()) { // если проигрыватель не запущен
            // запускаем его
            player.start();
            player.setVolume(0.7f, 0.7f);
            player.setAuxEffectSendLevel(5f);
        }
    }

    /**
     * Метод ставит проигрыватель на паузу.
     * Если, конечно, он запущён.
     * Если уже стоит на паузе - зачем его ставить на паузу второй раз?
     */
    private void pause() {
        if (player.isPlaying()) { // если проигрыватель запущен
            // останавливаем его
            player.pause();
        }
    }

}
