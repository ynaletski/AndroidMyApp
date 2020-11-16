package com.example.mystart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent; // подключаем класс Intent
import android.view.View; // подключаем класс View для обработки нажатия кнопки
import android.widget.EditText; // подключаем класс EditText
import android.os.Bundle;
import android.widget.TextView;

public class AddEventActivity extends AppCompatActivity {

    private EditText etDateAndTime;  //из первой активити
    private EditText etNumb;       //в первую активити
    private EditText etDes;        //в первую активити
    private TextView errorNumb;    //проверка данных после нажатия кнопки Подтвердить
    private TextView errorDes;     //проверка данных после нажатия кнопки Подтвердить

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        //Привязываем объявленные объекты к созданным нами элементам в activity_display_too.xml
        etDateAndTime = findViewById(R.id.editTextTimeDate);
        //устанавливаем текст переданный из первой активити
        etDateAndTime.setText(getIntent().getStringExtra("EXTRA_TIME_DATE"));
    }

    // Метод обработки нажатия на кнопку Отменить
    public void goToMainActivityWithoutEvent(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_CANCELED, intent);
        finish();
        //startActivity(intent);
    }

    //Метод обработки нажатия на кнопку Подтвердить
    @SuppressLint("SetTextI18n")
    public void goToMainActivityWithEvent(View view) {
        //Привязываем объявленные объекты к созданным нами элементам в activity_display_too
        etNumb = findViewById(R.id.editTextNumb);
        etDes = findViewById(R.id.editTextDescriptor);
        etDateAndTime = findViewById(R.id.editTextTimeDate);
        //проверка ввода номера события и описания
        if (etNumb.getText().length() > 0) {
            if (Integer.parseInt(etNumb.getText().toString()) < 1 ||
                    Integer.parseInt(etNumb.getText().toString()) > 1000) {
                errorNumb = findViewById(R.id.textErrorNumb);
                errorNumb.setText("Введите: 1-100");
            } else {
                //проверка ввода описания
                if (etDes.getText().length() > 0) {
                    //отправка данных в первую активити
                    SendDataToDisOne();
                } else {
                    errorDes = findViewById(R.id.textErrorDesc);
                    errorDes.setText("Введите описание");
                }
            }
        } else {
            errorNumb = findViewById(R.id.textErrorNumb);
            errorNumb.setText("Введите значение");
        }
    }

    //функция для отправки данных на первый activity
    public void SendDataToDisOne() {
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(this, MainActivity.class);
        // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
        // второй параметр - значение этого объекта
        intent.putExtra("NUMB", etNumb.getText().toString());
        intent.putExtra("DESC", etDes.getText().toString());
        intent.putExtra("TAD", etDateAndTime.getText().toString());
        //отправка результата
        setResult(RESULT_OK, intent);
        finish();
    }
}