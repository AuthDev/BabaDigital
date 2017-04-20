package com.evar.babadigital;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.evar.babadigital.adptlists.AdapterNoticiasList;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import bbgetset.BAPI;
import bbgetset.Noticia;
import bbgetset.StaticoInstanciados;
import webdo.WebPG;

public class NoticiasActivity extends AppCompatActivity {

    private BAPI bapi;
    private ArrayList<Noticia> noticias;
    private Handler handler;
    private ProgressBar progressBar;
    private AdapterNoticiasList adapterNoticiasList;
    private RecyclerView listNoticias;
    private RecyclerView.LayoutManager layoutManager;
    private boolean isFirstime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler = new Handler();
        noticias = new ArrayList<Noticia>();


        progressBar = (ProgressBar)findViewById(R.id.noticias_pb);
        listNoticias = (RecyclerView) findViewById(R.id.noticias_recycler);
        listNoticias.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listNoticias.setLayoutManager(layoutManager);

        if(StaticoInstanciados.isNoticiasOn)
        {
            carregar(StaticoInstanciados.NOTICIAS);

            new Thread(new Runnable() {
                @Override
                public void run() {
                while (StaticoInstanciados.isNoticiasOn)
                {
                    try {
                        Thread.sleep(100);
                    }catch (Exception e){}
                }
                    carregarNoticia(true);
                }
            }).start();
        }else {
            carregarNoticia();
        }

    }
    private void carregar(Boolean falha)
    {
        if(falha)
        {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.falha),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    getSupportActionBar().setTitle(getResources().getString(R.string.noticias)+" ("+ getResources().getString(R.string.falhaConexao)+")!");
                }
            });
        }
    }

    private void carregar(final Noticia noticia, final int position)
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isFirstime)
                {
                    progressBar.setVisibility(View.GONE);
                    isFirstime = false;
                }
                noticias.add(position,noticia);
                adapterNoticiasList = new AdapterNoticiasList(noticias);
                listNoticias.setAdapter(adapterNoticiasList);
                StaticoInstanciados.NOTICIAS = noticias;
            }
        });
    }
    private void carregar(final ArrayList<Noticia> noticias)
    {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapterNoticiasList = new AdapterNoticiasList(noticias);
                    listNoticias.setAdapter(adapterNoticiasList);
                    progressBar.setVisibility(View.GONE);
                }
            });

    }
    private void carregarNoticia()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                bapi = new BAPI();
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
                        if (size > 0) {
                            carregar(StaticoInstanciados.NOTICIAS);
                        }
                        for (int i = size; i < qt; i++) {
                            int pos = (qt - 1) - i;
                            noticia = bapi.getNoticia(pos, jsonArray);
                            if (noticia.getTitulo().length() > 0) {
                                carregar(noticia, i);
                            }
                        }
                        StaticoInstanciados.isNoticiasOn = false;
                    } else {
                        if (size > 0) {
                            carregar(StaticoInstanciados.NOTICIAS);

                        } else {
                            carregar(true);
                            Log.e("getNotiias: ", "Nada de noticias");
                        }


                    }
                } else {
                    carregar(true);
                }

            }
        }).start();


    }
    public void carregarNoticia(boolean inThread)
    {
        if(inThread)
        {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    carregarNoticia();
                }
            });
        }else
            {
                carregarNoticia();
            }

    }
}
