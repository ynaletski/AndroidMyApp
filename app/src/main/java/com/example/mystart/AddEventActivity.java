package com.example.mystart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent; // подключаем класс Intent
import android.view.View; // подключаем класс View для обработки нажатия кнопки
import android.widget.EditText; // подключаем класс EditText
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventActivity extends AppCompatActivity {

    private EditText dateAndTime;  //из первой активити
    private EditText noteNumber;       //в первую активити
    private EditText noteDescription;        //в первую активити
    private TextView errorNumber;    //проверка данных после нажатия кнопки Подтвердить
    private TextView errorDescription;     //проверка данных после нажатия кнопки Подтвердить

    private enum Error {
        NUMB_SCALE,
        NUMB_NULL,
        DESCRIPTION_NULL,
        NO_ERROR
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
    }

    // Метод обработки нажатия на кнопку Отменить
    public void goToMainActivityWithoutEvent(View view) {
        /*Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_CANCELED, intent);*/
        finish();
    }

    //Метод обработки нажатия на кнопку Подтвердить
    @SuppressLint("SetTextI18n")
    public void goToMainActivityWithEvent(View view) {

        initializeView();

        Error error = validationOfData();

        switch (error) {
            case NO_ERROR:
                sendDataToMainActivity();
                break;
            case NUMB_NULL:
                errorNumber.setText(getResources().getString(R.string.errorNumbNull));
                break;
            case NUMB_SCALE:
                errorNumber.setText(getResources().getString(R.string.errorNumbScale));
                break;
            case DESCRIPTION_NULL:
                errorDescription.setText(getResources().getString(R.string.errorDescriptionNull));
                break;
        }
    }

    //метод для инициализации вьюшек
    public void initializeView() {

        noteNumber = findViewById(R.id.editTextNumb);
        noteDescription = findViewById(R.id.editTextDescriptor);
        dateAndTime = findViewById(R.id.editTextTimeDate);
        errorNumber = findViewById(R.id.errorNumb);
        errorDescription = findViewById(R.id.errorDesc);
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatForDate = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        dateAndTime.setText(formatForDate.format(date));

    }

    //функция для отправки данных на первый activity
    public void sendDataToMainActivity() {
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(this, MainActivity.class);
        // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
        // второй параметр - значение этого объекта
        //intent.putExtra("NUMB", noteNumber.getText().toString());
        //intent.putExtra("DESC", noteDescription.getText().toString());
        //intent.putExtra("TAD", dateAndTime.getText().toString());
        //отправка результата
        Cash.getInstance().addEvent(noteNumber.getText().toString(), noteDescription.getText().toString(), dateAndTime.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public Error validationOfData() {
        if (noteNumber.getText().length() > 0) {
            if (Integer.parseInt(noteNumber.getText().toString()) < 1 ||
                    Integer.parseInt(noteNumber.getText().toString()) > 1000) {
                return Error.NUMB_SCALE;
            } else {
                if (noteDescription.getText().length() > 0) {
                    return Error.NO_ERROR;
                } else {
                    return Error.DESCRIPTION_NULL;
                }
            }
        } else {
            return Error.NUMB_NULL;
        }
    }

}