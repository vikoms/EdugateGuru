package com.example.edugateguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    EditText email,pass;
    FirebaseAuth mAuth;
    String getEmail,getPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.btn_login);
        email = findViewById(R.id.txtEmailLogin);
        pass = findViewById(R.id.txtPassLogin);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            Intent main = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(main);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmail = email.getText().toString().trim();
                getPass = pass.getText().toString().trim();
                if(getEmail.isEmpty() || getPass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Isi field dengan lengkap", Toast.LENGTH_SHORT).show();
                } else if (!getEmail.isEmpty() || !getPass.isEmpty()) {
                    login();
                }
            }
        });
    }

    private void login() {
        mAuth.signInWithEmailAndPassword(getEmail, getPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()) {
                  Intent main = new Intent(LoginActivity.this, HomeActivity.class);
                  startActivity(main);
              } else {
                  Toast.makeText(LoginActivity.this, "Username Dan Password Tidak Cocok", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }

}
