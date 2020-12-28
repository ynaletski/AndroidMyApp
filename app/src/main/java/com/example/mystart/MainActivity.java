package com.example.mystart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_TWO_ACT = 1;

    private EventAdapter eventAdapter;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new Repository(this);

        repository.open();
        if(repository.getCount() == 0){
            repository.insert(new Event(0,
                    getResources().getString(R.string.defaultNumber),
                    getResources().getString(R.string.defaultTextDescription),
                    getResources().getString(R.string.defaultDateTime)));
        }
        repository.close();
        /*WITHOUT CASH
        if (Cash.getInstance().getEvents().size() == 0) {
            Cash.getInstance().addEvent
                    (getResources().getString(R.string.defaultNumber),
                            getResources().getString(R.string.defaultTextDescription),
                            getResources().getString(R.string.defaultDateTime));
        }*/

        final RecyclerView recyclerView = findViewById(R.id.eventList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //если знаем за ранее размер списка то true
        recyclerView.setHasFixedSize(false);
        eventAdapter = new EventAdapter(this, new removeClickListener() {

            @Override
            public void removeEvent(int positionEvent) {

                repository.open();
                repository.delete(repository.getEvents().get(positionEvent).getId());
                /*WITHOUT CASH
                Cash.getInstance().getEvents().remove(positionEvent);
                */

                if(repository.getCount() == 0){
                    repository.insert(new Event(0,
                            getResources().getString(R.string.defaultNumber),
                            getResources().getString(R.string.defaultTextDescription),
                            getResources().getString(R.string.defaultDateTime)));
                }
                /*WITHOUT CASH
                if (Cash.getInstance().getEvents().size() == 0) {
                    Cash.getInstance().addEvent
                            (getResources().getString(R.string.defaultNumber),
                                    getResources().getString(R.string.defaultTextDescription),
                                    getResources().getString(R.string.defaultDateTime));
                }
                */

                eventAdapter.insertData(repository.getEvents());
                repository.close();
                /*WITHOUT CASH
                eventAdapter.insertData(Cash.getInstance().getEvents());
                */
            }
        });
        recyclerView.setAdapter(eventAdapter);

        repository.open();
        eventAdapter.insertData(repository.getEvents());
        repository.close();
        /*WITHOUT CASH
        eventAdapter.insertData(Cash.getInstance().getEvents());
        */

    }

    // Метод обработки нажатия на кнопку
    public void goToAddEventActivity(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivityForResult(intent, REQUEST_TWO_ACT);
    }

    //метод принимающий результат со второй активити()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            repository.open();
            eventAdapter.insertData(repository.getEvents());
            repository.close();
            /*WITHOUT CASH
            eventAdapter.insertData(Cash.getInstance().getEvents());
            */
        }
    }
}