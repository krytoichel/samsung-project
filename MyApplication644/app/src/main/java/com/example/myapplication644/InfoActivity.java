package com.example.myapplication644;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class InfoActivity extends AppCompatActivity {
    private Document doc2;
    TextView dir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ImageView imageView = findViewById(R.id.imageView1);
        String url = "https://s.funpay.com/s/avatar/oc/ni/ocnin5ttsjadib3f52ua.jpg";
        Picasso.with(this).load(url).resize(350,350).into(imageView);

        TextView name = findViewById(R.id.textView);
        name.setText(MainActivity.arrayList.get(MainActivity.poz).getName());

        dir = findViewById(R.id.textView4);
        init();
    }
    private void init(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getInfo();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void getInfo() {
        try {
            doc2 = Jsoup.connect(MainActivity.url_for_info).get();
            Log.d("MyTag","title: "+doc2.title());
            Elements paramList = doc2.getElementsByClass("param-list");
            Element ourParamList = paramList.get(0);
            Elements dataParamList = ourParamList.children();
            Log.d("MyTag","Описание: "+dataParamList.get(2).children().get(1).text());
            dir.setText(dataParamList.get(2).children().get(1).text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}