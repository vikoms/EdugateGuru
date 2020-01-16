package com.example.edugateguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.edugateguru.Models.Kelas;
import com.example.edugateguru.Models.Tugas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class TugasActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button btn_tugas;
    DatabaseReference ref;
    String namaTugas, descTugas, dateTugas, timeTugas, kelasTugas;
    EditText editNamaTugas, editDescTugas, editDateTugas, editTimeTugas, editKelasTugas;
    TextView tvDateTugas,tvTimeTugas;
    ImageView dateChooser;
    ArrayAdapter<Kelas> adapter;
    ArrayList<Kelas> listKelas;
    DatabaseReference refKelas;
    Spinner spinnerKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);


        editNamaTugas = findViewById(R.id.txtTugas);
        editDescTugas = findViewById(R.id.descTugas);
        tvDateTugas = findViewById(R.id.textDate);
        spinnerKelas = findViewById(R.id.spinner_kelas);
        tvTimeTugas = findViewById(R.id.textTime);


        ref = FirebaseDatabase.getInstance().getReference("Tugas");
        refKelas = FirebaseDatabase.getInstance().getReference("Kelas");


        listKelas = new ArrayList<>();

        btn_tugas = findViewById(R.id.btnTugas);
        btn_tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaTugas = editNamaTugas.getText().toString();
                descTugas = editDescTugas.getText().toString();
                dateTugas = tvDateTugas.getText().toString();
                timeTugas = tvTimeTugas.getText().toString();
                Kelas kelas = (Kelas) spinnerKelas.getSelectedItem();
                kelasTugas = kelas.getKeyKelas();

                String id = ref.push().getKey();
                Tugas tugas = new Tugas(namaTugas, descTugas, dateTugas, timeTugas, kelasTugas);

                ref.child(id).setValue(tugas);
                Toast.makeText(TugasActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }
        });

        tvDateTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });


        tvTimeTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });


    }

    public void showTimePickerDialog() {
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int min = Calendar.getInstance().get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String time = hour + ":" + min;
                tvTimeTugas.setText(time);
            }
        }, hour, min, is24HourFormat);

        timePickerDialog.show();
    }



    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month += 1;
        String date = year + "/" + month + "/" + day;
        tvDateTugas.setText(date);
    }

    @Override
    protected void onStart() {
        super.onStart();
        refKelas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item:dataSnapshot.getChildren()) {
                    Kelas kelas = new Kelas(item.getKey(),item.getValue().toString());
                    listKelas.add(kelas);
                }
               loadDataKelas();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDataKelas() {
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listKelas);

        adapter.notifyDataSetChanged();
        spinnerKelas.setAdapter(adapter);
    }
}
