package com.example.edugateguru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class agenda extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    SimpleDateFormat dateFormatter;
    DatePickerDialog datePickerDialog;
    TextView timeF,timeL,txtDate;
    ImageButton date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        date = findViewById(R.id.date);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        txtDate= findViewById(R.id.txtDate);
        timeF = findViewById(R.id.time_first);
        timeL = findViewById(R.id.time_last);

        date.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showDataDialog();
           }
       });

        timeF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });

        timeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog2();
            }
        });

    }

    private void showTimeDialog() {
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int min = Calendar.getInstance().get(Calendar.MINUTE);

        boolean is24HoursFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String time = hour + ":" + min ;
                timeF.setText(time);
            }
        },hour,min,is24HoursFormat);
        timePickerDialog.show();
    }

    private void showTimeDialog2() {
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int min = Calendar.getInstance().get(Calendar.MINUTE);

        boolean is24HoursFormat = DateFormat.is24HourFormat(this);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String time = hour + ":" + min;
                timeL.setText(time);
            }
        }, hour, min, is24HoursFormat);
        timePickerDialog.show();
    }


    private void showDataDialog(){

        DatePickerDialog datePickerDialog =  new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month += 1;
        String date1 = year + "/" + month + "/" +day;
        txtDate.setText(date1);
    }
}
