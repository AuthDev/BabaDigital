package com.evar.babadigital;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.evar.babadigital.adptlists.AdapterNoticiasList;

import java.util.ArrayList;
import java.util.List;

import bbgetset.BAPI;
import bbgetset.Noticia;
import webdo.WebPG;

public class NoticiasActivity extends AppCompatActivity {

    private BAPI bapi;
    private ArrayList<Noticia> noticias;
    private Handler handler;
    private ProgressBar progressBar;
    private AdapterNoticiasList adapterNoticiasList;
    private ListView listNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar)findViewById(R.id.noticias_pb);
        listNoticias = (ListView)findViewById(R.id.noticias_listView);



        new Thread(new Runnable() {
            @Override
            public void run() {
                bapi = new BAPI();
                if(WebPG.isConnected())
                {
                    noticias = bapi.getNoticias();
                    carregar(noticias);

                }else
                    {
                        carregar(true);
                    }
            }
        }).start();



    }
    private void carregar(Boolean falha)
    {
        if(falha)
        {
            handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.falha),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    getSupportActionBar().setTitle(getResources().getString(R.string.Noticias)+"("+ getResources().getString(R.string.falha)+")");
                }
            });
        }
    }

    private void carregar(final ArrayList<Noticia> bapiNoticias)
    {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                adapterNoticiasList = new AdapterNoticiasList(getApplicationContext(),bapiNoticias);
                listNoticias.setAdapter(adapterNoticiasList);
            }
        });
    }
}
