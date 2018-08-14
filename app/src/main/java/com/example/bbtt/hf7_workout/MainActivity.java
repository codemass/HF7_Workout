package com.example.bbtt.hf7_workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {

        View fragmentContainer = findViewById(R.id.fragment_container); //Получить ссылку на фрейм, содержащий WorkoutDetailFragment. Он будет существовать, если приложение выполняется на устройстве с большим экраном.
        if (fragmentContainer !=null) { //Если fragmentContainer существует, значит приложение использует макет activity_main.xml из папки layout-large. Значит приложение запустили на планшете.
            //Здесь размещается код отображения подробной информации. Этот метод определяется в слушателе.
            //Замена фрагмента во время выполнения происходит в виде транзакции фрагмента - набора изменений относящихся к фрагменту.
            //Этот код должен выполняться только в том случае, если фрейм присутсвует в макете.
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction(); //Начало транзакции фрагмента.
            details.setWorkout(id);
            ft.replace(R.id.fragment_container,details); //Заменить фрагмент и добавить его в стек возврата.
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //Включить анимацию растворения и проявления фрагментов.
            ft.commit(); //Закрепить изменения в транзакции.
            //Теперь при выборе упражнения, справа во фрагменте с подробностями отображаются сведения о выбранных упражнениях. Кнопка назад возвражяет к предыдущему выбранному упражнению. И так до первого.

        } else {
            //Если фрейм отсутствует, значит прилжение выполняется на устройстве с малым экраном. В этом случае следует запустить активность DetailActivity и передать ей id комплекса.
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }

    }
}
