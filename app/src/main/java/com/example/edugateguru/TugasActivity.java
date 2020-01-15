package com.example.edugateguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edugateguru.Models.Tugas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TugasActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button btn_tugas;
    DatabaseReference ref;
    String namaTugas,descTugas,dateTugas,timeTugas,kelasTugas;
    EditText editNamaTugas,editDescTugas,editTimeTugas,editKelasTugas;
    TextView editDateTugas;
    ImageView dateChooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);


        editNamaTugas = findViewById(R.id.txtTugas);
        editDescTugas = findViewById(R.id.descTugas);
        editDateTugas = findViewById(R.id.textDate);
        editTimeTugas = findViewById(R.id.textTime);
        editKelasTugas = findViewById(R.id.textKelas);


        ref = FirebaseDatabase.getInstance().getReference("Tugas");

        btn_tugas = findViewById(R.id.btnTugas);
        btn_tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                namaTugas = editNamaTugas.getText().toString();
                descTugas = editDescTugas.getText().toString();
                dateTugas = editDateTugas.getText().toString();
                timeTugas = editTimeTugas.getText().toString();
                kelasTugas= editKelasTugas.getText().toString();

                String id = ref.push().getKey();
                Tugas tugas = new Tugas(namaTugas,descTugas,dateTugas,timeTugas,kelasTugas);

                ref.child(id).setValue(tugas);
                Toast.makeText(TugasActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }
        });

        editDateTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month+=1;
        String date = month + "/" + day + "/" + year;
        editDateTugas.setText(date);
    }
}
