package com.example.edugateguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TugasActivity extends AppCompatActivity {

    Button btn_tugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);

        btn_tugas = findViewById(R.id.btnTugas);
        btn_tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TugasActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });

    }
}
