package com.example.bbtt.hf7_workout;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment; //По умолчанию студия импортирует android.support.v4.app.Fragment. Но надо поменять на app.Fragment, иначе в классе MainActivity будет ошибка с приведеним типов.
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {
    private long workoutId; //Идентификатор комплекса упражнений, выбранного пользователем. Позднее будет использован для заполнения данных в фрагменте.

    //Данный метод вызывается тогда, когда Android потребуется макет фрагмента
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //Данный метод вызывается тогда, когда Android потребуется макет фрагмента
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong("workoutId"); //Задать значение workoutId
        } else { //Нужен для того, что бы при повороте экрана, не создавался новый фрагмент и тем самым не сбразывались данные о количестве секунд секундомера.
            FragmentTransaction ft = getChildFragmentManager().beginTransaction(); //Открыть транзакцию. Обратить внимание: тут вместо getFragmentManager используется getChildFragmentManager. Он создает транцанцию вложенную в другую транзакцию. Используется когда фрагмент вложен в фрагмент.
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            ft.replace(R.id.stopwatch_container, stopwatchFragment); //Заменить фрагмент во фрейме
            ft.addToBackStack(null); //Добавить транзакцию в стек возврата.
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //Выбрать стиль анимации перехода.
            ft.commit(); //Закрепить транзакцию.
        }
        //Сообщает Android, какой макет используется фрагментом
        return inflater.inflate(R.layout.fragment_workout_detail, container, false); //Сообщает Android какой макет используется фрагментом (в данном случае fragment_workout_detail)
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView(); //Метод getView() получает корневой объект View фрагмента. Далее полученный объект используется для получения ссылок на надписи, предназначенные для названия и описания комплекса упражнений.
        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { //Сохранить значение workoutId в объекте savedInstanceState типа Bundle перед уничтожением фрагмента. Позднее сохраненное значение читается в методе onCreateView().
        savedInstanceState.putLong("workoutId", workoutId);
    }

    public void setWorkout(long id) { //Метод для присваивания идентификатора. Метод используется активностью для передачи идентификатора фрагменту.
        this.workoutId = id;
    }

}
