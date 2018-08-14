package com.example.bbtt.hf7_workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag); //Получить ссылку на фрагмент
        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID); //Получить идентификатор комплекса, выбранного пользователем, из интента.
        workoutDetailFragment.setWorkout(workoutId); //Передать идентификатор комплекса фрагменту.
    }
}
