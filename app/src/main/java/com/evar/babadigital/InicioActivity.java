package com.evar.babadigital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evar.babadigital.CRUD.DBRead;

import java.util.ArrayList;

import bbgetset.Criança;
import bbgetset.Usuário;

public class InicioActivity extends AppCompatActivity {

    private TextView nomeCompleto,criançasQt,notificaçoesQt;
    private Usuário usuário;
    private SharedPreferences prefs;
    private LinearLayout btnCriancas;
    private LinearLayout btnCaderneta;
    private LinearLayout btnNoticias;
    private LinearLayout btnInformacoes;
    private LinearLayout btnConfiguracoes;
    DBRead dbRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbRead = new DBRead(getApplicationContext());
        int qtOfCriancas = dbRead.getCrianças(new Usuário(getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""))).size();

        nomeCompleto = (TextView)findViewById(R.id.inicio_nomeCompleto);
        criançasQt = (TextView)findViewById(R.id.inicio_criancasQt);
        notificaçoesQt = (TextView)findViewById(R.id.inicio_noticacoesQt);

        prefs = getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE);
        usuário = new Usuário(prefs.getString(PrefsTitles.jsUsuario,""));
        nomeCompleto.setText(usuário.getNome());
        //criançasQt.setText(new BAPI().getCrianças(usuário).size());




        btnCriancas = (LinearLayout)findViewById(R.id.inicio_criancas);
        btnCaderneta = (LinearLayout)findViewById(R.id.inicio_caderneta);
        btnInformacoes = (LinearLayout)findViewById(R.id.inicio_informacoes);
        btnNoticias = (LinearLayout)findViewById(R.id.inicio_noticias);
        btnConfiguracoes = (LinearLayout)findViewById(R.id.inicio_configuracoes);

        criançasQt.setText(" "+qtOfCriancas);


        btnCriancas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CriancasActivity.class));
            }
        });


        btnCaderneta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CadernetaActivity.class));
            }
        });

        btnInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),informacoesActivity.class));
            }
        });


        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NoticiasActivity.class));
            }
        });


        btnConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ConfiguracoesActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inicio,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.sairItem:
                getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE).edit().putBoolean(PrefsTitles.logado,false).commit();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        if(!getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE).getBoolean(PrefsTitles.logado,false))
        {
            finish();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {

        dbRead = new DBRead(getApplicationContext());
        int qtOfCriancas = dbRead.getCrianças(new Usuário(getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""))).size();

        criançasQt.setText(""+qtOfCriancas);
        super.onResume();
    }
}
