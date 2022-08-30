package com.example.myapplication644;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {
    private ArrayList<Chats> chats;
    public static DatabaseReference mDataBase;
    public static String chat_key = "Chat";
    private FirebaseUser user;
    RecyclerView recyclerView;
    //public static ArrayList<String> chat;
    public static Chats.Chat chat;

    private String name_trader = TradeActivity.sel_trade;
    static String chat_id;

    Chats chats3;
    ChatListAdapter chatListAdapter;

    int i = 0;
    public static Chats chats1;

    ChatListAdapter.OnChatClickListener onChatClickListener;
    //Chats chats5;

    boolean isExist;
    ArrayList<String> equalsChats;

    public static String path_chat;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        user = FirebaseAuth.getInstance().getCurrentUser();


        init();
        setInitialData();
        getDataDB();
        //Log.d("MyTag","i: ", i);



        //setInitialData();
        //Log.d("MyTag", "siz: "+chat.size());
//        for (int j = 0; j < chat.size(); j++) {
//            Log.d("MyTag", ""+chat.get(j));
//        }

    }


    private void init(){
        assert user != null;
        chats = new ArrayList<Chats>();
        //chat_id = user.getUid()+ProfileActivity.user.getUid();
        //Log.d("MyTag",chat_id);

        mDataBase = FirebaseDatabase.getInstance().getReference(chat_key);

        //chat = new ArrayList<String>();
        equalsChats = new ArrayList<String>();

        recyclerView = findViewById(R.id.chat_list);

        onChatClickListener = new ChatListAdapter.OnChatClickListener() {
            @Override
            public void OnChatClick(Chats chats, int position) {
                startActivity(new Intent(ChatListActivity.this, ChatActivity.class));
                path_chat = equalsChats.get(position);
                Log.d("MyTag", path_chat);
            }
        };
        chatListAdapter = new ChatListAdapter(chats, onChatClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(chatListAdapter);
        //mDataBase.push().setValue(chat_id);



    }

     private void setInitialData(){
        //chats.add(new Chats(""+name_trader,""+chat_id));
         //chat.add("Hello");
         //chat.add("fuck off ogr typoi");
         //Log.d("MyTag", ""+chat.get(0));
         chats1 = new Chats(""+name_trader,""+chat_id,chat);
         //Chats.Chat chat2 = new Chats.Chat("hohol",chat);
         //Log.d("MyTag","1: "+chat2.getMessages());
         mDataBase.push().setValue(chats1);
         //String userId = "user";
         //mDataBase.push().setValue(chat2);
         //mDataBase.push().setValue(new Chats.Chat("sss","hohol"));


         Log.d("MyTag", "keey: "+mDataBase.child(chat_key));

         Log.d("MyTag","size chats: "+chats.size());
    }

    public void getDataDB(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    chats3 = dataSnapshot.getValue(Chats.class);
                    Log.d("MyTag", chats3.getName());
                    Log.d("MyTag", chats3.getId());
                    Log.d("MyTag", "key: "+mDataBase.child(dataSnapshot.getKey()));
                    String ch = String.valueOf(mDataBase.child(dataSnapshot.getKey()));
                    //chat.add(ch);

                    isExist = false;
                    if(chats3.getId().equals(chat_id)){
                        Log.d("MyTag","key ch > "+dataSnapshot.getKey());
                        equalsChats.add(dataSnapshot.getKey());
                        Log.d("MyTag","size chats Eq > "+equalsChats.size());
                        i++;
                        
                        if (equalsChats.size() > 1){
                            removeEqChats();

                            /*for (int i = 0; i < equalsChats.size()-1; i++){
                                equalsChats.remove(i);
                            }*/
                            Log.d("MyTag","size chats eq > "+equalsChats.size());

                        }
                        
                        
                        
                        
//                        Log.d("MyTag","i: "+i);
//                        isExist = true;
//                        if(isExist == true){
//                            Log.d("MyTag", "Такой чат уже существуюет "+dataSnapshot.getKey());
//                            mDataBase.child(dataSnapshot.getKey()).removeValue();
//
//                            i = 0;
//                            continue;
//                        }
                        else {
                            Log.d("MyTag", "Такого чата не существуюет");
                            chats.add(new Chats(""+chats3.getName(),""+chats3.getId(),chat));
                            Log.d("MyTag",""+isExist);
                        }
                    }

//                    Log.d("MyTag", "Такого чата не существуюет");
//                    chats.add(new Chats(""+chats3.getName(),""+chats3.getId(),chat));
//                    Log.d("MyTag",""+isExist);

                }
                chatListAdapter.notifyDataSetChanged();
            }

            private void removeEqChats() {
                for(int i = 0; i < equalsChats.size(); i++){
                    Log.d("MyTag","chaats: "+equalsChats.get(i));
                    //mDataBase.child(String.valueOf(equalsChats.get(i))).removeValue();

                    //Log.d("MyTag","deleted "+equalsChats.get(i)+" element");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(valueEventListener);
    }
}