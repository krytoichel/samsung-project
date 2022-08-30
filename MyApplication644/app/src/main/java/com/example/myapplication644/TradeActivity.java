package com.example.myapplication644;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TradeActivity extends AppCompatActivity {
    ArrayList<TradeInfo> tradeInfos;
    ArrayList<String> feature;
    ArrayList<String> name;
    ArrayList<String> min_req;
    ArrayList<String> uid;
    ArrayList<String> name_trader;
    TradeInfoAdapter tradeInfoAdapter;
    RecyclerView rv;

    int i;

    TradeInfoAdapter.OnTradeClickListener onTradeClickListener;

    private DatabaseReference mDataBase;
    private String AD_KEY = "AD";

//    private DatabaseReference chatDataBase;
//    private String chat_key = "Chat";

    TradeInfo tradeInfo;
    FirebaseUser usr;

    static String sel_trade;
    public static boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        usr = FirebaseAuth.getInstance().getCurrentUser();
        mDataBase = FirebaseDatabase.getInstance().getReference(AD_KEY);

        init();
        setInitialData();
        getFromDB();


    }

    private void init() {
        rv = findViewById(R.id.rv1);
        tradeInfos = new ArrayList<TradeInfo>();
        feature = new ArrayList<String>();
        name = new ArrayList<String>();
        min_req = new ArrayList<String>();
        uid = new ArrayList<String>();




        onTradeClickListener = new TradeInfoAdapter.OnTradeClickListener() {
            @Override
            public void onTradeClick(TradeInfo tradeInfo, int position) {
                startActivity(new Intent(TradeActivity.this, ChatListActivity.class));
                ChatListActivity.chat_id = usr.getUid()+tradeInfos.get(TradeInfoAdapter.i).getuId();
                Log.d("MyTag",ChatListActivity.chat_id);
                sel_trade = tradeInfos.get(0).getName_trader();
                Log.d("MyTag", ""+sel_trade.toString());

                isPressed = true;
                //mDataBase.push().setValue()
            }
        };
        isPressed = false;

        tradeInfoAdapter = new TradeInfoAdapter(tradeInfos, onTradeClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(tradeInfoAdapter);

        i = 0;
    }


    public void getFromDB() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    tradeInfo = ds.getValue(TradeInfo.class);
                    Log.d("MyTag", "" + tradeInfo.getName_trade());

                    feature.add(tradeInfo.getFeature_acc());

                    Log.d("MyTag", "" + feature.get(0));

                    name.add(tradeInfo.getName_trade().toString());
                    min_req.add(tradeInfo.getMin_request().toString());
                    //uid.add(tradeInfo.getuId().toString());
                    //name_trader.add(tradeInfo.getName_trader().toString());

                    //tradeInfos.add(new TradeInfo(""+feature.get(i), ""+tradeInfo.getName_trade(),""+tradeInfo.getMin_request()));
                    tradeInfos.add(new TradeInfo(""+tradeInfo.getFeature_acc(), ""+tradeInfo.getName_trade(), ""+tradeInfo.getMin_request(), ""+tradeInfo.getuId(), ""+tradeInfo.getName_trader()));
                }
                tradeInfoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(valueEventListener);
    }

    private void setInitialData() {
        tradeInfos.add(new TradeInfo("15 mmr, 5 skin, more case", "brawl stars", "1 legend brawler", "" + usr.getUid(),""+usr.getEmail()));
        tradeInfos.add(new TradeInfo("15 mmr, 5 skin, more case", "brawl stars", "1 legend brawler", "" + usr.getUid(),""+usr.getEmail()));
        tradeInfos.add(new TradeInfo("15 mmr, 5 skin, more case", "brawl stars", "1 legend brawler", "" + usr.getUid(),""+usr.getEmail()));
        //tradeInfos.add(new TradeInfo(""+feature.get(0)));
        //Log.d("MyTag",feature.get(0));
        //tradeInfos.add(new TradeInfo(""+feature.get(0).toString(), ""+name.get(0).toString(), ""+min_req.get(0).toString()));

        //Log.d("MyTag",""+feature.get(0));
    }
}