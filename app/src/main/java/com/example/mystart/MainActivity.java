package com.example.mystart;

import android.content.Intent; // подключаем класс Intent
import android.os.Bundle;
import android.view.View; // подключаем класс View для обработки нажатия кнопки
import android.widget.EditText; // подключаем класс EditText
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_TIME_DATE = "EXTRA_TIME_DATE";

    ArrayList<Event> events = new ArrayList();
    ListView eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получаем текстовые данные из второго Activity:
        String NMB;
        String DSC;
        String TD;
        try {
            NMB = getIntent().getStringExtra("NUMB");
            DSC = getIntent().getStringExtra("DESC");
            TD = getIntent().getStringExtra("TAD");
            //events.add(new Event("522", "Lower", "15.15.15 11:45"));
            if(NMB.length() != 0){
                events.add(new Event( NMB, DSC, TD ));
            }
        }
        catch (Exception ex){
            //events.add(new Event("12", "Hello", "10.10.10 10:10"));
        }

        //if(events.size()==0){
            //events.add(new Event("12", "Hello", "10.10.10 10:10"));
            //event.add(new Event("23", "Meet", "20.02.20 20:20"));
            //events.add(new Event("3", "Timer", "11.11.11 11:11"));
            //events.add(new Event("46", "Year", "15.15.15 15:15"));
            //events.add(new Event("522", "Lower", "15.15.15 11:45"));
        //}

        eventList = (ListView) findViewById(R.id.eventList);
        EventAdapter adapter = new EventAdapter(this, R.layout.event_item, events);
        eventList.setAdapter(adapter);

    }
    // Метод обработки нажатия на кнопку
    public void GoToDisToo(View view) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(this, DisplayTooActivity.class);

        Date date = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        String mess = formatForDate.format(date);

        // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
        // второй параметр - значение этого объекта
        intent.putExtra(EXTRA_TIME_DATE, mess);

        // запуск activity
        startActivity(intent);
        }

}