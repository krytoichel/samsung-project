package com.example.myapplication644;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    EditText feature_acc, name_g, min_request;
    Button add;
    private DatabaseReference mDataBase;
    private String AD_KEY = "AD";
    public static FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();

        feature_acc = findViewById(R.id.editTextTextPersonName7);
        name_g = findViewById(R.id.editTextTextPersonName8);
        min_request = findViewById(R.id.editTextTextPersonName9);

        mDataBase = FirebaseDatabase.getInstance().getReference(AD_KEY);

        add = findViewById(R.id.button8);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(feature_acc.getText().toString().isEmpty() || name_g.getText().toString().isEmpty() || min_request.getText().toString().isEmpty()){
                    Log.d("MyTag","одно из полей пустое");
                }
                else {
                    TradeInfo tradeInfo = new TradeInfo(""+feature_acc.getText().toString(),""+name_g.getText().toString(),""+min_request.getText().toString(), ""+user.getUid(),""+user.getEmail());
                    mDataBase.push().setValue(tradeInfo);
                }
            }
        });
    }
}