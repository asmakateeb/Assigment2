package edu.cs.birzeit.myinformation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;

import java.util.Locale;


public class Timer extends AppCompatActivity {
    private TextView txtmasssege;
    private int seconds = 0;
    private boolean running=false;
    private TextView countTimer;
    private Button start;
    private Button pause;
    private Button Rest;
    private EditText Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Timer =findViewById(R.id.txtTime);
        countTimer=findViewById(R.id.count);
        Intent intent =getIntent();
        txtmasssege =findViewById(R.id.txtmasssege);
        String msg =intent.getStringExtra("data");
        txtmasssege.setText(msg);
        checkSaved(savedInstanceState);
        runTimer();


    }
    private void checkSaved(Bundle savedInstanceState){
        if(savedInstanceState !=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("seconds", seconds);
        bundle.putBoolean("running", running);
    }


    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        seconds = 0;
        running = false;

    }
    private void runTimer(){
        final TextView txtView = (TextView) findViewById(R.id.txtTime);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = seconds % 3600 /60;
                int snds = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, snds);
                txtView.setText(time);
                if(running){
                    ++seconds;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }



}