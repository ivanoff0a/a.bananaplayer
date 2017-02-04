package ru.kuelye.banana.player;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.data;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ищем кнопочки на макете
        Button playButton = (Button) findViewById(R.id.play_button);
        Button pauseButton = (Button) findViewById(R.id.pause_button);
        // назначаем действие для кнопки Play
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // создаём конверт
                Intent intent = new Intent(MainActivity.this, PlayerService.class);
                // указываем, что хотим запустить проигрывание песни
                intent.setAction(PlayerService.ACTION_PLAY);
                // посылаем конверт сервису
                startService(intent);
            }
        });
        // назначаем действие для кнопки Pause
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // создаём конверт
                Intent intent = new Intent(MainActivity.this, PlayerService.class);
                // указываем, что хотим приостановить проигрывание песни
                intent.setAction(PlayerService.ACTION_PAUSE);
                // посылаем конверт сервису
                startService(intent);
            }
        });
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/mpeg");
        startActivityForResult(Intent.createChooser(intent, "Choose the song"), 666);
    }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 666 && resultCode == Activity.RESULT_OK){
                if (data != null && data.getData() != null) {
                    Uri songUri = data.getData();

                    Intent intent = new Intent(this, PlayerService.class);
                    intent.setAction(PlayerService.ACTION_PLAY);
                    intent.setData(songUri);
                    startService(intent);
                }
            }
        }
    }

















}

