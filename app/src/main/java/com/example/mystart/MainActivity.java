package com.example.mystart;

import android.annotation.SuppressLint;
import android.content.Intent; // подключаем класс Intent
import android.os.Bundle;
import android.view.View; // подключаем класс View для обработки нажатия кнопки
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_TIME_DATE = "EXTRA_TIME_DATE";
    final int REQUEST_TWO_ACT = 1;

    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Cash.getInstance().getArray().size() == 0) {
            Cash.getInstance().addEventDefault();
        }

        ListView eventList = findViewById(R.id.eventList);
        adapter = new EventAdapter(this, R.layout.event_item, Cash.getInstance().getArray());
        eventList.setAdapter(adapter);
    }

    // Метод обработки нажатия на кнопку
    public void goToAddEventActivity(View view) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(this, AddEventActivity.class);

        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatForDate = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        String mess = formatForDate.format(date);

        // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
        // второй параметр - значение этого объекта в нашем случае это системное время и дата
        intent.putExtra(EXTRA_TIME_DATE, mess);

        // запуск activity
        startActivityForResult(intent, REQUEST_TWO_ACT);
    }

    //метод принимающий результат со второй активити()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Cash.getInstance().addEvent(data.getStringExtra("NUMB"), data.getStringExtra("DESC"),
                    data.getStringExtra("TAD"));
            adapter.notifyDataSetChanged();
        }
    }

}