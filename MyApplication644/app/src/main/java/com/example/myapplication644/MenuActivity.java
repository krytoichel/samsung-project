package com.example.myapplication644;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
        Button trade_activ = findViewById(R.id.button6);
        trade_activ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, TradeActivity.class));
            }
        });

        Button search_activ = findViewById(R.id.button5);
        search_activ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SearchActivity.class));
            }
        });

        Button chat = findViewById(R.id.button7);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, ChatListActivity.class));
            }
        });

        ImageView profile = findViewById(R.id.imageView2);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, ProfileActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(this,"Success",Toast.LENGTH_LONG);
            Log.d("MyTag", "You register");
        }
        else startActivity(new Intent(MenuActivity.this, LoginActivity.class));
    }

    /*protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(this,"Success",Toast.LENGTH_LONG);
            Log.d("MyTag", "You register");
        }
        else startActivity(new Intent(MenuActivity.this, LoginActivity.class));
    }*/
}