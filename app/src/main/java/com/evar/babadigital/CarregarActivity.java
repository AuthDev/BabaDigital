package com.evar.babadigital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evar.babadigital.CRUD.DBCreate;
import com.evar.babadigital.CRUD.DBUpdate;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import bbgetset.BAPI;
import bbgetset.Crianças;
import bbgetset.Noticia;
import bbgetset.StaticoInstanciados;
import bbgetset.Vacina;
import webdo.WebPG;

public class CarregarActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private boolean logado = false;
    private boolean firstTime = true;
    private DBCreate dbCreate;
    private DBUpdate db;
    private BAPI bapi;
    private ArrayList<Vacina> vacinas;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregar);

        dbCreate = new DBCreate(getApplicationContext());

        dbCreate.createTable();

        preferences = getSharedPreferences(PrefsTitles.prefsName, Context.MODE_PRIVATE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                vacinas = new ArrayList<>();
                try {
                    bapi = new BAPI();
                    try {
                        ArrayList<Vacina> vac = bapi.getVacinas();
                        stopWating(vac);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    Thread.sleep(2000);

                }catch (Exception e) {

                }

            }
        });
        thread.start();
        carregarNoticia();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        SharedPreferences prefs = getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE);
        this.logado = prefs.getBoolean(PrefsTitles.logado,false);
        this.firstTime = prefs.getBoolean(PrefsTitles.FIRST_TIME,true);

    }
    public void stopWating(final ArrayList<Vacina>listVacinas)
    {
        handler.post(new Runnable() {
            @Override
            public void run() {

                db = new DBUpdate(getApplicationContext());
                if(listVacinas!=null)
                {
                    db.addVacinas(listVacinas);
                }

                if(firstTime)
                {
                    preferences.edit().putBoolean(PrefsTitles.FIRST_TIME,false).commit();
                    startActivity(new Intent(getApplicationContext(),BoasVindas.class));
                    finish();
                }else
                    {
                        if(logado)
                        {
                            startActivity(new Intent(getApplicationContext(),InicioActivity.class));
                            finish();
                        }
                        else
                        {
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            preferences.edit().putBoolean(PrefsTitles.NOTIFICACOES,true).commit();
                            finish();
                        }
                    }



            }
        });
    }
    private void carregarNoticia()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                bapi = new BAPI();
                try {

                    Noticia noticia;
                    if (WebPG.isConnected()) {
                        StaticoInstanciados.isNoticiasOn = true;
                        Looper.prepare();
                        JSONArray jsonArray = bapi.getJsonNoticias();
                        int qt = jsonArray.length();
                        int size = 0;
                        if (StaticoInstanciados.NOTICIAS != null) {
                            size = StaticoInstanciados.NOTICIAS.size();
                        }
                        if (qt > 1 && size != qt) {
                            for (int i = size; i < qt; i++) {
                                int pos = (qt - 1) - i;
                                noticia = bapi.getNoticia(pos, jsonArray);
                                if (noticia.getTitulo().length() > 0) {
                                    if(StaticoInstanciados.NOTICIAS==null)
                                    {
                                        StaticoInstanciados.NOTICIAS = new ArrayList<Noticia>();
                                    }
                                    StaticoInstanciados.NOTICIAS.add(i,noticia);
                                }
                            }
                        }
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();

                }finally {
                    StaticoInstanciados.isNoticiasOn = false;
                }


            }
        }).start();


    }
}
