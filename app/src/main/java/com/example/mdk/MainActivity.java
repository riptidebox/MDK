package com.example.mdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTimer;

    private int seconds = 0; //переменная которая показывает хранит ли кол-во прошедших секунд, вторая isRunning или нет

    private boolean isRunning = false;

 @Override
    protected void onCreate(Bundle savedInstanceState) //Сохранение данных Activity при повороте экрана
    {
        super.onCreate(savedInstanceState); //сохранение активности
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);
        if (savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }
        runTimer(); //должен взять кол-во секунд, прошедших сначала запуска таймера, получить из него кол-во часов, минут и секунд.
    }
 @Override
    protected void onSaveInstanceState(Bundle outState) //- сохраняем данные при повороте экрана


 {
        super.onSaveInstanceState(outState); //сохранение даных с последующим уничтожением activity
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
    }
    public void onClickResetTimer(View view) // при сбросе обнуляем
    {
        isRunning = false;
        seconds = 0;
    }

    public void onClickStopTimer(View view)
    {
        isRunning = false;
    }

    public void onClickStartTimer(View view)
    {
        isRunning = true;
    }

    private void runTimer()
    {
        final Handler handler = new Handler(); // для планирования выполнения кода каждую секунду

        handler.post(new Runnable() { //Handler необходимо упаковать весь код, который нужно запланировать в Runnable
 @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                //вычисление количества минут/секунд,

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs); //преобразование текста в формат текста со временем
                textViewTimer.setText(time);

                if (isRunning) {
                    seconds++; //секунда +1
                }
                handler.postDelayed(this, 1000); //планирует сам себя через 1000 мсек
            }
        });
    }
}