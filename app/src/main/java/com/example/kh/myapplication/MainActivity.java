package com.example.kh.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] a={"df","df","df","df","df","df","df","df","df","df","df","df","df","df","df","df","df","df","df","df","df",};
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        new MyTask().execute();
    }
    class MyTask extends AsyncTask<Void, String, Void>{
        ArrayAdapter<String> adapter;
        private ProgressDialog dialog;
        int count=0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter  = (ArrayAdapter<String>) listview.getAdapter();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.setProgress(0);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for(String dulieu: a){
                publishProgress(dulieu);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            adapter.add(values[0]);
            count++;
            dialog.setProgress(count*100/a.length);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }
}

