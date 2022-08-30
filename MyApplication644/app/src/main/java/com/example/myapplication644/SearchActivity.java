package com.example.myapplication644;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    EditText editText;
    Button button,button1;
    static String  name;
    static String nameG;
    public static String url;
    private FirebaseAuth mAuth;
    Document doc1;
    private ImageView signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mAuth = FirebaseAuth.getInstance();
        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button2);
        signOut = findViewById(R.id.signOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editText.getText().toString();
                init();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
    }




    private void init(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getSearch();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void getSearch() {
            try {

                //Log.d("MyTag", ""+SearchActivity.name.charAt(0));


                // Начало подключаюсь к сайту и беру нужные блоки
                doc1 = Jsoup.connect("https://funpay.com/").get();
                Elements containers = doc1.getElementsByClass("promo-game-list");
                Element container = containers.get(0); // все контейнеры
                Elements container_data = container.children(); // Буква и контейнеры в контейнерах
                Element our_data = container_data.get(1); // Первый контейнер
                Elements our_data_ch = our_data.children(); // Контейнеры в первом контейнере


                //Log.d("MyTag", ""+container.children().get(0).text());

                Map<String, Integer> letter = new HashMap<String, Integer>();

//            String[] alphavit = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",};

                Elements alp = container.getElementsByClass("promo-game-list-title");
                Log.d("MyTag", ""+alp.text());
                char[] alph = alp.text().toCharArray();

                ArrayList<String> alphav = new ArrayList<String>();
                for (int i = 0; i < alp.size(); i++){
                    alphav.add(alp.get(i).text());
                }

                int[] num = new int[alphav.size()];
                int c = 1;
                for (int i = 0; i < num.length; i++){
                    num[i] = c;
                    c+=2;
                }

                Log.d("MyTag","arr 0 "+alphav.get(0)+" num arr 0 "+num[0]);

                for(int i = 0; i < alphav.size(); i++){
                    letter.put(""+alphav.get(i), num[i]);
                }
                //Создаю алфафит

            /*String alphavit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char[] alph = alphavit.toCharArray();
            String[] alphv = new String[26];

            for (int i = 0; i < 26; i++){
                alphv[i] = String.valueOf(alph[i]);
            }

            int[] num = new int[26];
            int c = 1;
            for (int i = 0; i < 26; i++){
                num[i] = c;
                c+=2;
            }
            Log.d("MyTag","arr 0 "+alphv[25]+"num arr 0 "+num[0]);


            for(int i = 0; i < 26; i++){
                letter.put(""+alphv[i],num[i]);
            }*/

                for(Map.Entry<String, Integer> items:letter.entrySet()){
                    Log.d("MyTag","Key: "+items.getKey()+" Value: "+items.getValue());
                }
                ////

                char a = SearchActivity.name.charAt(0);
                for (Map.Entry<String, Integer> item:letter.entrySet()){
                    if(String.valueOf(a).equalsIgnoreCase(item.getKey())){
                        Log.d("MyTag", "YRA");
                        for (int i = 0; i < container_data.get(item.getValue()).childrenSize(); i++){
                            Log.d("MyTag","name "+i+" : "+container_data.get(item.getValue()).children().get(i).children().get(0).children().get(0).text());
                            if(SearchActivity.name.equalsIgnoreCase(container_data.get(item.getValue()).children().get(i).children().get(0).children().get(0).text())){
                                for (int j = 0; j < container_data.get(item.getValue()).children().get(i).getElementsByClass("list-inline").get(0).getElementsByTag("li").size(); j++) {
                                    if(container_data.get(item.getValue()).children().get(i).children().get(0).getElementsByClass("list-inline").get(0).getElementsByTag("li").get(j).text().equalsIgnoreCase("Аккаунты")){
                                        Log.d("MyTag","UrL "+container_data.get(item.getValue()).children().get(i).children().get(0).getElementsByClass("list-inline").get(0).getElementsByTag("li").get(j).getElementsByTag("a").attr("href"));
                                        url = container_data.get(item.getValue()).children().get(i).children().get(0).getElementsByClass("list-inline").get(0).getElementsByTag("li").get(j).getElementsByTag("a").attr("href");
                                        break;
                                    }
                                }
                                Log.d("MyTag",""+container_data.get(item.getValue()).children().get(i).children().get(0).getElementsByClass("list-inline").get(0).getElementsByTag("li").get(0));

                                nameG = container_data.get(item.getValue()).children().get(i).children().get(0).children().get(0).text();
                                //url = container_data.get(item.getValue()).children().get(i).children().get(0).children().get(0).getElementsByTag("a").attr("href");
                            }
                        }
                    }
                }
                //Log.d("MyTag", ""+String.valueOf(a).equalsIgnoreCase(container.children().get(0).text()));


                // Финальная часть
                //Log.d("MyLog","size "+our_data_ch.get(0).getElementsByTag("a").get(0).attr("href"));
                //String name = container_data.get(3).children().get(0).children().get(0).children().get(0).text();
                //url = container_data.get(3).children().get(0).children().get(0).children().get(0).getElementsByTag("a").attr("href").toString();
                Log.d("MyLog","size "+container_data.get(3).children().get(0).children().get(0).children().get(0).text());
                Log.d("MyTag", "name -> "+nameG+" url -> "+ url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Element container = containers.get(0);
        }
    }
