package com.example.bbtt.hf7_workout;

//Создание споскового фрагмента
import android.os.Bundle;
import android.app.ListFragment;  //По умолчанию было android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class WorkoutListFragment extends ListFragment {


    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] names = new String[Workout.workouts.length];
        for (int i=0; i<names.length; i++) {
            names[i] = Workout.workouts[i].getName(); //Создать массив строк с названиями комплексов упражнений.
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(  //Создать адаптер массива
                inflater.getContext(), android.R.layout.simple_list_item_1, names); //Получить контекст от LayoutInflater
        setListAdapter(adapter); //Связать адаптер массива со списковым представлением
        return super.onCreateView(inflater, container, savedInstanceState) ;  //Вызов метода onCreateView() суперкласса предоставляет макет по умолчанию для ListFragment
    }

}
