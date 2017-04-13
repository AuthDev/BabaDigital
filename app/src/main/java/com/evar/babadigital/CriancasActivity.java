package com.evar.babadigital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.evar.babadigital.adptlists.AdapterCriancasList;

import java.util.ArrayList;

import bbgetset.BAPI;
import bbgetset.Criança;
import bbgetset.Usuário;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class CriancasActivity extends AppCompatActivity {

    private Handler handler;
    private BAPI bapi;
    private AdapterCriancasList adapterCriancasList;
    private ListView listViewCrianças;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criancas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewCrianças = (ListView)findViewById(R.id.criancas_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),novaCriancaActivity.class));
            }
        });

        carregarCrianças();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    private void carregarCrianças()
    {
        Usuário usuário = new Usuário(getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""));
        String jsonCrianças = getSharedPreferences(usuário.getEmail(), Context.MODE_PRIVATE).getString(PrefsTitles.jsCrianças,"");

        if(jsonCrianças.length() > 2)
        {
            ArrayList<Criança> listCriancas = bapi.getCrianças(jsonCrianças);
            adapterCriancasList = new AdapterCriancasList(getApplicationContext(),listCriancas);
            listViewCrianças.setAdapter(adapterCriancasList);
        }
    }
}
