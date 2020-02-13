package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private SharedPreferences minePrefs;
    private SharedPreferences.Editor minPrefsEditor;

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meny, menu);
        return true; //super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        //Bitmap defaultBilde = Bitmap.createBitmap(50, 50, conf);
        app = (minApplication) getApplication();
        minePrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Log.i("tag main start", minePrefs.getStringSet("navneBase", null).toString());
        String Ownernavn = sjekkSharedPreferences();
        if(app.firstTime){
            //Log.i("firstTime: ", "" + firstTime);

            app.populate();
            app.firstTime = false;
        }

        if(Ownernavn.isEmpty()){
            Intent intent = new Intent(MainActivity.this, RegistrerBruker.class);
            startActivity(intent);
        } else if (app.firstTime){
            Toast.makeText(this, (getResources().getString(R.string.welcome_msg) + ", " + Ownernavn), Toast.LENGTH_SHORT).show();
        }



    }
    private String sjekkSharedPreferences(){
        String navnEigar = minePrefs.getString(getString(R.string.owner_name), "");
        return navnEigar;
    }
    public void start_quiz(View view){
        if(app.profiler.size() < 2){
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsBtn:
                Intent intent = new Intent(this, RegistrerBruker.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
