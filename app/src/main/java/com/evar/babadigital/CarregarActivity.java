package com.evar.babadigital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CarregarActivity extends AppCompatActivity {

    Handler handler = new Handler();
    private boolean logado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregar);



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);

                }catch (Exception e)
                {

                }
                stopWating();
            }
        });
        thread.start();

        Log.d("onCreate: ","Iniciado em Activity CarregarActivity");

        boolean logado = false;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        SharedPreferences prefs = getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE);
        this.logado = prefs.getBoolean(PrefsTitles.logado,logado);

    }
    public void stopWating()
    {
        handler.post(new Runnable() {
            @Override
            public void run() {

                if(logado)
                {
                    startActivity(new Intent(getApplicationContext(),InicioActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }


            }
        });
    }
}
