package com.example.mystart;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; // подключаем класс Intent
import android.view.View; // подключаем класс View для обработки нажатия кнопки
import android.widget.EditText; // подключаем класс EditText
import android.os.Bundle;
import android.widget.TextView;

public class DisplayTooActivity extends AppCompatActivity
{

    private EditText Date_Time;     //из первой активити

    private EditText str_numb;      //в первую активити
    private EditText str_des;       //в первую активити
    private EditText str_time_date; //в первую активити

    private TextView Error_numb;    //проверка данных после нажатия кнопки Подтвердить
    private TextView Error_des;     //проверка данных после нажатия кнопки Подтвердить


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_too);

        //Привязываем объявленные объекты к созданным нами элементам в activity_display_too
        Date_Time = (EditText)findViewById(R.id.editTextTimeDate);

        //Получаем текстовые данные с первого Activity:
        String DT = getIntent().getStringExtra("EXTRA_TIME_DATE");

        //Присваиваем созданным элементам TextView значение полученных текстовых данных:
        Date_Time.setText(DT);
    }


    // Метод обработки нажатия на кнопку Отменить
    public void GoToDisOneEmpty(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //Метод обработки нажатия на кнопку Подтвердить
    public void GoToDisOne(View view)
    {

        //Привязываем объявленные объекты к созданным нами элементам в activity_display_too
        str_numb = (EditText)findViewById(R.id.editText_numb);
        str_des = (EditText)findViewById(R.id.editTextDescriptor);
        str_time_date = (EditText)findViewById(R.id.editTextTimeDate);

        //проверка ввода номера события и описания
        if(str_numb.getText().length() > 0)
        {
            if(Integer.parseInt(str_numb.getText().toString()) < 1 ||
                    Integer.parseInt(str_numb.getText().toString()) > 100)
            {
                Error_numb = (TextView)findViewById(R.id.text_error_numb);
                Error_numb.setText("Введите: 1-100");
            }
            else{

                //проверка ввода описания
                if(str_des.getText().length() > 0)
                {
                    SendDataToDisOne();
                }
                else {
                    Error_des = (TextView)findViewById(R.id.text_error_desc);
                    Error_des.setText("Введите описание");
                }

             }
        }
        else {
            Error_numb = (TextView)findViewById(R.id.text_error_numb);
            Error_numb.setText("Введите значение");
        }


    }

    //функция для отправка данных на первый activity и запуска её
    public void SendDataToDisOne()
    {
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(this, MainActivity.class);

        // Получаем текстовые поля в текущей Activity
        EditText editText_1 = (EditText) findViewById(R.id.editText_numb);
        EditText editText_2 = (EditText) findViewById(R.id.editTextDescriptor);
        EditText editText_3 = (EditText) findViewById(R.id.editTextTimeDate);

        // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
        // второй параметр - значение этого объекта
        intent.putExtra("NUMB", editText_1.getText().toString());
        intent.putExtra("DESC", editText_2.getText().toString());
        intent.putExtra("TAD", editText_3.getText().toString());

        // запуск activity
        startActivity(intent);
    }


}