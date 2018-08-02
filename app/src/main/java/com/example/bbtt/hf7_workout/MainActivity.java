package com.example.bbtt.hf7_workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorkoutDetailFragment frag = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag); //Возвращает ссылку на фрагмент WorkoutDetailFragment. В макете активности этому фрагменту присвоен идентификатор detail_frag.
        frag.setWorkout(1); //Приказываем WorkoutDetailFragment вывести подробную информацию о произвольно выбранном комплексе, что бы убедиться, что все работает.
    }
}
