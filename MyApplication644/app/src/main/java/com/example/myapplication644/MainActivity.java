package com.example.myapplication644;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Document doc;
    //private Document doc1;
    public static List<Account> arrayList;
    private ListView lv;
    private AccountAdapter adapter;
    private Thread secThread;
    private Thread secThread2;
    //SearchActivity searchActivity = new SearchActivity();
//    static String url;
//    static String nameG;
    Map<String, String> games = new HashMap<String, String>();
    static String url_for_info;
    List<String> urlInfo = new ArrayList<String>();
    public static  int poz = 0;
    // games.put("among us", 693);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        lv = (ListView) findViewById(R.id.lv);
        arrayList = new ArrayList<>();
        adapter = new AccountAdapter(this,R.layout.list_item,arrayList,getLayoutInflater());
        lv.setAdapter(adapter);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
                Log.d("MyTag", "Нажали на элемент: "+i);
                poz = i;
                url_for_info = urlInfo.get(i);
                Log.d("MyTag","url_for_info: "+url_for_info);
            }
        });

    }



    private void getWeb(){
        try {

            /*for(Map.Entry<String, Integer> item:games.entrySet()){
                if(SearchActivity.name.equals(item.getKey())){
                    url = "https://funpay.com/lots/"+item.getValue().toString()+"/";
                }
            }*/
            Log.d("MyTag", "URL "+ SearchActivity.url);
            doc = Jsoup.connect(""+SearchActivity.url).get();
            Elements tables = doc.getElementsByClass("content-with-cd-wide showcase");
            Element table = tables.get(0); // выбираем все табл
            Elements elements = table.children(); //
            Element usr = elements.get(1); // таблица с аками
            Elements usr_elem = usr.children(); // каждый элемент табл
            Log.d("MyTag","url id: "+usr_elem.get(1).getElementsByTag("a").attr("href"));
            Log.d("MyLog","Title: "+ usr_elem.get(1).text());
            Log.d("MyLog","Title: "+ usr.children().get(1).children().get(1).text());
            Log.d("MyLog","Title: "+ usr.childrenSize());
            Log.d("MyLog","Введенное пользователем: "+ SearchActivity.name);
            Log.d("MyLog","url: "+ SearchActivity.url);
            Log.d("MyLog","size array: "+ usr.children().get(1).childrenSize());
            Log.d("MyLog","size arr : "+ String.valueOf(usr.children().get(1).childrenSize()-2));
            Log.d("MyLog","size arr : "+ String.valueOf(usr.children().get(1).childrenSize()-2));
            //url_for_info = usr_elem.get(i).getElementsByTag("a").attr("href");
            for(int i = 1;i < usr.childrenSize();i++ )
            {
                Account items = new Account();
                items.setDir(usr_elem.get(i).getElementsByClass("tc-desc-text").text());
                items.setName(usr_elem.get(i).getElementsByClass("media-user-name").text());
                items.setPrice(usr_elem.get(i).getElementsByClass("tc-price").text());
                urlInfo.add(usr_elem.get(i).getElementsByTag("a").attr("href"));
                arrayList.add(items);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}