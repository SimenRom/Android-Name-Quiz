package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    minApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        //Bitmap defaultBilde = Bitmap.createBitmap(50, 50, conf);
        app = (minApplication) getApplication();

        if(app.firstTime){
            //Log.i("firstTime: ", "" + firstTime);

            app.populate();
            app.firstTime = false;
        }

    }

    public void start_quiz(View view){
        if(app.profiler.size() < 1){
            Toast.makeText(this, getResources().getString(R.string.tooFewError), Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, TheQuizGameActivity.class);
            startActivity(intent);
        }

    }
    public void open_list_all(View view){
        Intent intent = new Intent(this, ListAllActivity.class);
        //intent.putExtra("Profiler", profiler);
        startActivity(intent);
    }
    public void add_new(View view){
        Intent intent = new Intent(this, AddNewActivity.class);
        startActivity(intent);
    }
}
