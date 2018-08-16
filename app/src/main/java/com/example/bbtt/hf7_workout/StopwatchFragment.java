package com.example.bbtt.hf7_workout;


import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;


public class StopwatchFragment extends Fragment implements View.OnClickListener { //Фрагмент реализует интерфейс OnClickListener

    private int seconds=0; //Количество секунд на секундомере
    private boolean running; //Секундомер работает?
    private boolean wasRunning; //Хранит состояние работал ли таймер перед вызовом onStop()


    public StopwatchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");//Восстановить состояние переменной wasRunning, если активность создается заново
            if (wasRunning) {
                running = true;
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);

        Button startButton = (Button) layout.findViewById(R.id.start_button); //Получить ссылку на кнопку.
        startButton.setOnClickListener(this); //Назначить слушателя для кнопки.

        Button stopButton = (Button) layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);

        Button resetButton = (Button) layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        return layout;
    }

    //Реализация интерфеса OnClickListener требует переопределения метода onClick().
    @Override
    public void onClick (View v) {  //Представление на котором сделан клик.
        switch (v.getId()) {        //Проверить на каком представлении кликнул пользователь.
            case R.id.start_button: //Если клик был на кнопке Start, вызвать метод onClickStart
                onClickStart(v);
                break;
            case R.id.stop_button: //Если клик был на кнопке Stop, вызвать метод onClickStop
                onClickStop(v);
                break;
            case R.id.reset_button: //Если клик был на кнопке Reset, вызвать метод onClickReset
                onClickReset(v);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState); //После того, как добавил тут super, все заработало.
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    //Запустить секундомер при щелчке на кнопке Start
    public void onClickStart(View view) {
        running = true; //Запустить секундомер
    }

    //Запустить секундомер при щелчке на кнопке Stop
    public void onClickStop(View view) {
        running = false; //Остановить секундомер
    }

    //Обнулить секундомер при щелчке на кнопке Reset
    public void onClickReset(View view) {
        running = false; //Остановить секундомер
        seconds = 0; //И присвоить seconds значение 0
    }

    private void runTimer (View view) {
        final TextView timeViev = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable () { //Использовать Handler для передачи кода на выполнение
            @Override
            public void run () {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs); //Отформатировать seconds в часы, минуты и секунды.
                timeViev.setText(time); //Задать текст надписи
                if (running) {
                    seconds++;  //Если значение running инстинно, то увеличить переменную seconds.
                }
                handler.postDelayed(this, 1000); //Запланировать повторное выполнение кода с задержкой в 1 секунду.
            }
        });
    }

}
