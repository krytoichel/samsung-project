package com.example.myapplication644;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText email, password, nickname;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private TextView loogin;
    private Button regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("Users");



        email = findViewById(R.id.editTextTextPersonName4);
        password = findViewById(R.id.editTextTextPersonName5);
        nickname = findViewById(R.id.editTextTextPersonName6);
        
        regist = findViewById(R.id.button4);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_usr =  email.getText().toString();
                String pass = password.getText().toString();
                String nick = nickname.getText().toString();

                User new_usr = new User(nick, email_usr, pass);
                myRef.push().setValue(new_usr);
            }
        });

        loogin = findViewById(R.id.textView10);
        loogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    /*private void createAcc() {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("MyTag", "Success register");
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    }
                });
    }*/
}