package com.example.proga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button2);
        tv = findViewById(R.id.textView2);
        btn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                // Вызов наследника AsyncTask
                MyDownload md = new MyDownload();
                md.execute();
            }
        });
    }

    private class MyDownload extends AsyncTask<Void, Void, String> {

        HttpURLConnection httpurl1;

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://api.weatherstack.com/current?access_key=9ab553a92634b1a88b4eb66f479ccd78&query=Dubai");
                httpurl1 = (HttpURLConnection)url.openConnection();
                httpurl1.setRequestMethod("GET");
                httpurl1.connect();

                InputStream input = httpurl1.getInputStream();
                Scanner scan = new Scanner(input);
                StringBuilder buffer = new StringBuilder();

                while (scan.hasNextLine()) {
                    buffer.append(scan.nextLine());
                }

                return buffer.toString();
            } catch (IOException e) {
                Log.e("RRR", e.toString());
                //e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }
    }
}
