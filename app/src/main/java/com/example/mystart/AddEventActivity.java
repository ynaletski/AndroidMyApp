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
    private EditText numb;       //в первую активити
    private EditText des;        //в первую активити
    private TextView errorNumb;    //проверка данных после нажатия кнопки Подтвердить
    private TextView errorDes;     //проверка данных после нажатия кнопки Подтвердить

    private enum Error{
        numbScale,
        numbNull,
        descriptionNull,
        without
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
    }

    // Метод обработки нажатия на кнопку Отменить
    public void goToMainActivityWithoutEvent(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    //Метод обработки нажатия на кнопку Подтвердить
    @SuppressLint("SetTextI18n")
    public void goToMainActivityWithEvent(View view) {

        initializeView();

        Error error = validationOfData();

        switch (error){
            case without:
                sendDataToMainActivity();
                break;
            case numbNull:
                errorNumb.setText(getResources().getString(R.string.errorNumbNull));
                break;
            case numbScale:
                errorNumb.setText(getResources().getString(R.string.errorNumbScale));
                break;
            case descriptionNull:
                errorDes.setText(getResources().getString(R.string.errorDescriptionNull));
                break;
        }
    }

    //метод для инициализации вьюшек
    public void initializeView(){

        numb = findViewById(R.id.editTextNumb);
        des = findViewById(R.id.editTextDescriptor);
        dateAndTime = findViewById(R.id.editTextTimeDate);
        errorNumb = findViewById(R.id.errorNumb);
        errorDes = findViewById(R.id.errorDesc);
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
        intent.putExtra("NUMB", numb.getText().toString());
        intent.putExtra("DESC", des.getText().toString());
        intent.putExtra("TAD", dateAndTime.getText().toString());
        //отправка результата
        setResult(RESULT_OK, intent);
        finish();
    }

    public Error validationOfData(){
        if (numb.getText().length() > 0) {
            if (Integer.parseInt(numb.getText().toString()) < 1 ||
                    Integer.parseInt(numb.getText().toString()) > 1000) {
                return Error.numbScale;
            } else {
                if (des.getText().length() > 0) {
                    return Error.without;
                } else {
                    return Error.descriptionNull;
                }
            }
        } else {
            return Error.numbNull;
        }
    }

}