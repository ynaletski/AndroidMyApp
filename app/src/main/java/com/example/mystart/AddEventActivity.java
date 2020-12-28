package com.example.mystart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventActivity extends AppCompatActivity {

    private EditText dateAndTime;  //из первой активити
    private EditText noteNumber;       //в первую активити
    private EditText noteDescription;        //в первую активити
    private TextView errorNumber;    //проверка данных после нажатия кнопки Подтвердить
    private TextView errorDescription;     //проверка данных после нажатия кнопки Подтвердить

    private Repository repository;

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

        initializeView();
    }

    // Метод обработки нажатия на кнопку Отменить
    public void goToMainActivityWithoutEvent(View view) {
        finish();
    }

    //Метод обработки нажатия на кнопку Подтвердить
    @SuppressLint("SetTextI18n")
    public void goToMainActivityWithEvent(View view) {

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
        repository = new Repository(this);

    }

    //функция для отправки данных на первый activity
    public void sendDataToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("NUMB", noteNumber.getText().toString());
        //intent.putExtra("DESC", noteDescription.getText().toString());
        //intent.putExtra("TAD", dateAndTime.getText().toString());
        //отправка результата

        /*WITHOUT CASH
        Cash.getInstance().addEvent(noteNumber.getText().toString(), noteDescription.getText().toString(), dateAndTime.getText().toString());
        */
        repository.open();
        repository.insert(new Event(0,
                                        noteNumber.getText().toString(),
                                        noteDescription.getText().toString(),
                                        dateAndTime.getText().toString()));
        repository.close();

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