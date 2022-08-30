package com.example.myapplication644;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    EditText send_msg;
    ImageView send_bt;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ArrayList<Chats.Chat> chatList;
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    DatabaseReference mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();
        send_bd();
        getDataDB();
        mDB.child(ChatListActivity.path_chat).child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("MyTag","сущ "+snapshot.hasChildren());
                    String name_usr = mDB.child(snapshot.getKey()).child("chat").child(snapshot.getKey()).child("name_user").toString();
                    Log.d("MyTag","nick: "+name_usr);
                    Chats.Chat chat = snapshot .getValue(Chats.Chat.class);
                    chatList.add(new Chats.Chat(""+chat.getName_user(),""+chat.getMsg()));
                    chatAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(chatList.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init(){
        mDB = FirebaseDatabase.getInstance().getReference(ChatListActivity.chat_key);
        send_msg = findViewById(R.id.editTextTextPersonName10);
        send_bt = findViewById(R.id.imageView4);

        chatList = new ArrayList<Chats.Chat>();
        recyclerView = findViewById(R.id.rv_chat);
        chatAdapter = new ChatAdapter(chatList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(chatAdapter);
    }

    private void send_bd(){
        send_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(send_msg.getText().toString().isEmpty()) Log.d("MyTag","вы не написали сообщение");
                else{
                    //ChatListActivity.chat.add(send_msg.getText().toString());
                    //ChatListActivity.mDataBase.child(ChatListActivity.path_chat).child("chat").push().setValue(ChatListActivity.chats1.setChat(ChatListActivity.chat));
                    ChatListActivity.mDataBase.child(ChatListActivity.path_chat).child("chat").push().setValue(new Chats.Chat(""+user.getEmail(),""+send_msg.getText().toString()));
                    //Log.d("MyTag",""+ChatListActivity.chats1.getChat());
                }
            }
        });
    }

    private void getDataDB(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    for(DataSnapshot dataSnapshot1: dataSnapshot.child("chat").getChildren()){
//                        String name_usr = mDB.child(dataSnapshot.getKey()).child("chat").child(dataSnapshot1.getKey()).child("name_user").toString();
//                        Log.d("MyTag","nick: "+name_usr);
////                        Chats.Chat chat = dataSnapshot1.getValue(Chats.Chat.class);
////                        chatList.add(new Chats.Chat(""+chat.getName_user(),""+chat.getMsg()));
//                    }
                    Log.d("MyTag", ""+dataSnapshot.child("chat").hasChildren());

                }
//                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDB.addValueEventListener(valueEventListener);


    }
}