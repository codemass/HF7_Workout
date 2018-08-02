package com.example.bbtt.hf7_workout;


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

    public void setWorkout(long id) { //Метод для присваивания идентификатора. Метод используется активностью для передачи идентификатора фрагменту.
        this.workoutId = id;
    }

}
