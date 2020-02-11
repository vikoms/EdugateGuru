package com.example.edugateguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.edugateguru.Models.Agenda;
import com.example.edugateguru.Models.Tugas;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AgendaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    SimpleDateFormat dateFormatter;
    DatePickerDialog datePickerDialog;
    TextView timeF,timeL,txtDate;
    ImageButton jamMulai,jamSelesai,btnDate;
    EditText mapel,materi,kelas,siswaTidakMasuk;
    Button btnButton;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ProgressBar pgAgenda;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        jamMulai = findViewById(R.id.btn_jam_mulai);
        jamSelesai = findViewById(R.id.btn_jam_selesai);
        btnDate = findViewById(R.id.date);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        txtDate= findViewById(R.id.txtDate);
        timeF = findViewById(R.id.time_first);
        timeL = findViewById(R.id.time_last);
        btnButton = findViewById(R.id.btnSubmitAgenda);
        mapel = findViewById(R.id.pelajaran);
        materi= findViewById(R.id.materi_belajar);
        kelas = findViewById(R.id.kelas_belajar);
        siswaTidakMasuk = findViewById(R.id.siswa_tidak_masuk);
        pgAgenda = findViewById(R.id.progressBar_agenda);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("agenda");


        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnButton.setVisibility(View.INVISIBLE);
                pgAgenda.setVisibility(View.VISIBLE);
                addAgenda();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showDataDialog();
           }
       });

        jamMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });

        jamSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog2();
            }
        });

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Agenda Kelas");
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private void addAgenda() {
        String tanggal = txtDate.getText().toString().trim();
        String jamMulai= timeF.getText().toString().trim();
        String jamSelesai = timeL.getText().toString().trim();
        String pelajaranVal = mapel.getText().toString().trim();
        String materiVal = materi.getText().toString().trim();
        String kelasVal = kelas.getText().toString().trim();
        String siswaTidakMasukVal = siswaTidakMasuk.getText().toString().trim();

        String id = ref.push().getKey();
        Agenda agenda = new Agenda(tanggal,jamMulai,jamSelesai,pelajaranVal,materiVal,kelasVal,siswaTidakMasukVal);

        ref.child(id).setValue(agenda).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AgendaActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                finish();
                btnButton.setVisibility(View.VISIBLE);
                pgAgenda.setVisibility(View.INVISIBLE);
            }
        });

        startActivity(new Intent(AgendaActivity.this,HomeActivity.class));


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
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month += 1;
        String date1 = year + "/" + month + "/" +day;
        txtDate.setText(date1);
    }
}
