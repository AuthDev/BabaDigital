package com.evar.babadigital;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.evar.babadigital.CRUD.DBRead;
import com.evar.babadigital.adptlists.AdapterVacinasTomadasList;

import java.util.ArrayList;

import bbgetset.Criança;
import bbgetset.Sexo;
import bbgetset.VacinaTomada;

public class CadernetaActivity extends AppCompatActivity {

    private AppBarLayout app_bar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<VacinaTomada> vacinas;
    private AdapterVacinasTomadasList adapterLCList;
    private CollapsingToolbarLayout toolbarLayout;
    private Criança criança;
    private DBRead db;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caderneta);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),VacinasAddListActivity.class);
                intent.putExtra("cod",criança.getCod());
                startActivity(intent);
            }
        });

        carregarLista();



    }

    private void carregarLista()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerCaderneta);
        recyclerView.invalidate();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        db = new DBRead(this);
        criança = db.getCriança(getIntent().getIntExtra("cod",0));


        app_bar = (AppBarLayout)findViewById(R.id.app_bar);
        if(criança != null) {
            if (criança.getSexo().contains(Sexo.MASCULINO)) {
                app_bar.setBackgroundColor(getResources().getColor(R.color.menino));
                toolbar.setBackgroundColor(getResources().getColor(R.color.menino));
                toolbarLayout.setContentScrimColor(getResources().getColor(R.color.menino));
                fab.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.menino));
            } else {
                app_bar.setBackgroundColor(getResources().getColor(R.color.menina));
                toolbar.setBackgroundColor(getResources().getColor(R.color.menina));
                toolbarLayout.setContentScrimColor(getResources().getColor(R.color.menina));
                fab.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.menina));


            }

            toolbar.setTitle(criança.getNome());
        }
        vacinas = db.getVacinasTomadas(criança.getCod());
        if(vacinas != null)
        {
            adapterLCList= new AdapterVacinasTomadasList(vacinas,criança);
            recyclerView.setAdapter(adapterLCList);

        }else
        {

        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        carregarLista();
        super.onResume();
    }

}



