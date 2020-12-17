package com.example.mystart;

import android.content.Intent; // подключаем класс Intent
import android.os.Bundle;
import android.view.View; // подключаем класс View для обработки нажатия кнопки

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    //public final static String EXTRA_TIME_DATE = "EXTRA_TIME_DATE";
    final int REQUEST_TWO_ACT = 1;

    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Cash.getInstance().getEvents().size() == 0) {
            Cash.getInstance().addEvent
                    (getResources().getString(R.string.defaultNumber),
                            getResources().getString(R.string.defaultTextDescription),
                            getResources().getString(R.string.defaultDateTime));
        }

        //стандартный адаптер
        /*private EventAdapter adapter;*/
        RecyclerView recyclerView = findViewById(R.id.eventList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //если знаем за ранее размер списка то true
        recyclerView.setHasFixedSize(false);
        eventAdapter = new EventAdapter(this, /*Cash.getInstance().getEvents(),*/ new removeClickListener() {

            @Override
            public void removeEvent(int positionEvent) {
                Cash.getInstance().getEvents().remove(positionEvent);
                if (Cash.getInstance().getEvents().size() == 0) {
                    Cash.getInstance().addEvent
                            (getResources().getString(R.string.defaultNumber),
                                    getResources().getString(R.string.defaultTextDescription),
                                    getResources().getString(R.string.defaultDateTime));
                }
                eventAdapter.insertData(Cash.getInstance().getEvents());
            }
        });
        recyclerView.setAdapter(eventAdapter);
        eventAdapter.insertData(Cash.getInstance().getEvents());

    }

    // Метод обработки нажатия на кнопку
    public void goToAddEventActivity(View view) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(this, AddEventActivity.class);
        // запуск activity
        startActivityForResult(intent, REQUEST_TWO_ACT);
    }

    //метод принимающий результат со второй активити()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            //ArrayList<Event> eventList = new ArrayList<>();
            //eventList.add(new Event(data.getStringExtra("NUMB"), data.getStringExtra("DESC"), data.getStringExtra("TAD")));

            //перезапись
            //Cash.getInstance().addEvent(data.getStringExtra("NUMB"), data.getStringExtra("DESC"), data.getStringExtra("TAD"));
            //eventAdapter.notifyDataSetChanged();

            //eventAdapter.insertData(eventList);
            eventAdapter.insertData(Cash.getInstance().getEvents());
        }
    }
}