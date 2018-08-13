package com.example.bbtt.hf7_workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        //Здесь размещается код отображения подробной информации. Этот метод определяется в слушателе.
        //Замена фрагмента во время выполнения происходит в виде транзакции фрагмента - набора изменений относящихся к фрагменту.
        WorkoutDetailFragment details = new WorkoutDetailFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction(); //Начало транзакции фрагмента.
        details.setWorkout(id);
        ft.replace(R.id.fragment_container,details); //Заменить фрагмент и добавить его в стек возврата.
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //Включить анимацию растворения и проявления фрагментов.
        ft.commit(); //Закрепить изменения в транзакции.
        //Теперь при выборе упражнения, справа во фрагменте с подробностями отображаются сведения о выбранных упражнениях. Кнопка назад возвражяет к предыдущему выбранному упражнению. И так до первого.
    }
}
