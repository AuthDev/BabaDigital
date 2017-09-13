package com.evar.babadigital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.evar.babadigital.CRUD.DBRead;
import com.evar.babadigital.adptlists.AdapterInformacoesList;

import java.util.ArrayList;

import bbgetset.Vacina;

public class InformacoesActivity extends AppCompatActivity {


    private RecyclerView listRecycler;
    private RecyclerView.LayoutManager LayoutManager;
    private DBRead dbRead;
    private ArrayList<Vacina> vacinas;
    private AdapterInformacoesList listVacinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.informacoes));

        listRecycler = (RecyclerView)findViewById(R.id.listInformacoesRecycler);
        listRecycler.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        listRecycler.setLayoutManager(LayoutManager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbRead = new DBRead(getApplicationContext());

        vacinas = dbRead.getVacinas();

        if(vacinas != null)
        {
            listVacinas = new AdapterInformacoesList(vacinas);
            listRecycler.setAdapter(listVacinas);
        }
        else
            {
            }



    }
}
