package com.example.edugateguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edugateguru.Models.Tugas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TugasActivity extends AppCompatActivity {

    Button btn_tugas;
    DatabaseReference ref;
    String namaTugas,descTugas,dateTugas,timeTugas,kelasTugas;
    EditText editNamaTugas,editDescTugas,editDateTugas,editTimeTugas,editKelasTugas;
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

    }
}
