package ru.kuelye.banana.player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }

}
