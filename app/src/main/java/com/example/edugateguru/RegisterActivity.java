package com.example.edugateguru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.edugateguru.Models.Guru;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtNama, edtEmail, edtKota, edtPelajaran, edtPassword, edtTelp,edtNip;
    ProgressBar pgRegister;
    Button btnRegister;
    private String nama, email, kota, pelajaran, password, telp,nip;
    FirebaseAuth mAuth;
    DatabaseReference refGuru;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();
        btnRegister.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        refGuru = FirebaseDatabase.getInstance().getReference("Users").child("Guru");

    }


    public void initComponent() {

        edtNama = findViewById(R.id.tv_nama_guru);
        edtEmail = findViewById(R.id.tv_email_guru);
        edtKota = findViewById(R.id.tv_kota_guru);
        edtPelajaran = findViewById(R.id.tv_pelajaran_guru);
        edtPassword = findViewById(R.id.tv_password_guru);
        edtTelp = findViewById(R.id.tv_telp_guru);
        edtNip = findViewById(R.id.tv_nip_guru);
        pgRegister = findViewById(R.id.progressBar_register);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_register) {
            register();
        }
    }

    private void register() {
        pgRegister.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.INVISIBLE);
        nama = edtNama.getText().toString();
        email = edtEmail.getText().toString();
        kota = edtKota.getText().toString();
        pelajaran = edtPelajaran.getText().toString();
        password = edtPassword.getText().toString();
        telp = edtTelp.getText().toString();
        nip = edtNip.getText().toString();

        if (nama.isEmpty() || email.isEmpty() || kota.isEmpty() || pelajaran.isEmpty() || password.isEmpty() || telp.isEmpty() || nip.isEmpty()) {
            Toast.makeText(this, "Isi Data Dengan Lengkap", Toast.LENGTH_SHORT).show();

            pgRegister.setVisibility(View.INVISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {

                        currentUser = mAuth.getCurrentUser();
                        saveToDatabase(currentUser);

                    } else {
                        Toast.makeText(RegisterActivity.this, String.valueOf(task.getException()), Toast.LENGTH_SHORT).show();
                        pgRegister.setVisibility(View.INVISIBLE);
                        btnRegister.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


    }

    private void saveToDatabase(FirebaseUser currentUser) {

        String uid = currentUser.getUid();
        Guru guru = new Guru(nama,kota,nip,email,telp,pelajaran,uid);
        refGuru.child(uid).setValue(guru).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                Intent moveLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(moveLogin);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();
                pgRegister.setVisibility(View.INVISIBLE);
                btnRegister.setVisibility(View.VISIBLE);
            }
        });
    }
}
