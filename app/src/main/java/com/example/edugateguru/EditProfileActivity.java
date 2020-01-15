package com.example.edugateguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edugateguru.Models.Guru;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    Button btn_confirm;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;

    public static final String EXTRA_NAMA = "extra_nama";
    public static final String EXTRA_KOTA = "extra_kota";
    public static final String EXTRA_TELP = "extra_telp";
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_NIP = "extra_nip";
    public static final String EXTRA_PELAJARAN = "extra_pelajaran";

    private EditText edtNama, edtPhone, edtEmail, edtKota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btn_confirm = findViewById(R.id.btnConfirm);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtKota = findViewById(R.id.edtKota);
        edtPhone = findViewById(R.id.edtTelphone);

        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String kota = getIntent().getStringExtra(EXTRA_KOTA);
        String telp = getIntent().getStringExtra(EXTRA_TELP);
        String email = getIntent().getStringExtra(EXTRA_EMAIL);


        edtNama.setText(nama);
        edtEmail.setText(email);
        edtPhone.setText(telp);
        edtKota.setText(kota);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valNama = edtNama.getText().toString();
                String valEmail = edtEmail.getText().toString();
                String valPhone = edtPhone.getText().toString();
                String valKota = edtKota.getText().toString();
                String valPelajaran = getIntent().getStringExtra(EXTRA_PELAJARAN);
                String valNip = getIntent().getStringExtra(EXTRA_NIP);
                updateData(valNama, valEmail, valPhone, valKota, valNip, valPelajaran);
            }
        });
    }

    private void updateData(String valNama, String valEmail, String valPhone, String valKota, String valNip, String valPelajaran) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Guru").child(currentUser.getUid());
        Guru note = new Guru(valNama, valKota, valNip,valEmail,valPhone,valPelajaran);

    }

}
