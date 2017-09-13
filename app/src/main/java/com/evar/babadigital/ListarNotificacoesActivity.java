package com.evar.babadigital;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.evar.babadigital.CRUD.DBRead;
import com.evar.babadigital.adptlists.AdapterNotificacoes;

import java.util.ArrayList;

import bbgetset.CalculadorNotificacoes;
import bbgetset.Criança;
import bbgetset.Notificacao;
import bbgetset.Usuário;

public class ListarNotificacoesActivity extends AppCompatActivity {

    private ArrayList<Criança> crianças;
    private ArrayList<Notificacao> notificacoes;
    private Usuário usuário;

    private CalculadorNotificacoes calculadorNotificacoes;
    private AdapterNotificacoes adapterNotificacoes;
    private RecyclerView.LayoutManager myLayoutMananger;
    private RecyclerView lista;
    private DBRead db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notificacoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBRead(this);

        calculadorNotificacoes = new CalculadorNotificacoes(this);

        getSupportActionBar().setTitle("Notificações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usuário = new Usuário(getSharedPreferences(PrefsTitles.prefsName, Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""));
        crianças = db.getCrianças(usuário);

        lista = (RecyclerView)findViewById(R.id.recyclerNotificacoes);
        lista.setHasFixedSize(true);
        myLayoutMananger = new LinearLayoutManager(this);
        lista.setLayoutManager(myLayoutMananger);

        carregarLista();
    }

    private void carregarLista()
    {
        notificacoes = new ArrayList<Notificacao>();
        for (int i = 0; i < crianças.size(); i++) {
            ArrayList<Notificacao> not = db.getNotificacoes(crianças.get(i));
            for (int j = 0; j < not.size() ; j++) {
                notificacoes.add(not.get(j));
            }
        }
        adapterNotificacoes = new AdapterNotificacoes(notificacoes);
        lista.setAdapter(adapterNotificacoes);
    }

    @Override
    protected void onResume() {
        calculadorNotificacoes.calcular();
        carregarLista();
        super.onResume();
    }
}
