package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //static public ArrayList<Profil> profiler = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap defaultBilde = Bitmap.createBitmap(50, 50, conf);
        minApplication app = (minApplication) getApplication();
        /*
        app.profiler.add(new Profil("Simen", defaultBilde));
        app.profiler.add(new Profil("Knut", defaultBilde));
        app.profiler.add(new Profil("A", defaultBilde));
        app.profiler.add(new Profil("B", defaultBilde));
        app.profiler.add(new Profil("C", defaultBilde));
        */
      /*  TextView tekst1 = new TextView(this);
        tekst1.setText("Hei ein");
        tekst1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        TextView tekst2 = new TextView(this);
        tekst2.setText("Hei to12345678909811341341341341347654321");
        tekst2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        LinearLayout minLayout = new LinearLayout(this);
        minLayout.setOrientation(LinearLayout.VERTICAL);

        minLayout.addView(tekst1);
        minLayout.addView(tekst2); */


    }

    public void start_quiz(View view){
        Intent intent = new Intent(MainActivity.this, TheQuizGameActivity.class);
        startActivity(intent);
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
