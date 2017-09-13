package com.evar.babadigital;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.evar.babadigital.CRUD.DBRead;
import com.evar.babadigital.adptlists.AdapterCriancasList;

import java.util.ArrayList;

import bbgetset.CalculadorNotificacoes;
import bbgetset.Criança;
import bbgetset.Notificacao;
import bbgetset.Usuário;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class CriancasActivity extends AppCompatActivity {

    private AdapterCriancasList adapterCriancasList;
    private RecyclerView.LayoutManager myLayoutMananger;
    private RecyclerView lista;
    private DBRead dbRead;
    private CalculadorNotificacoes calculador;
    private NotificationCompat.Builder nCBuilder;
    private NotificationManager nManager;
    private Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private Handler handler;
    private static final String TAG = "CriancasAddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criancas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler = new Handler();

        lista = (RecyclerView) findViewById(R.id.listarCriancas);
        lista.setHasFixedSize(true);
        myLayoutMananger = new LinearLayoutManager(this);
        lista.setLayoutManager(myLayoutMananger);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CriancasAddActivity.class));
            }
        });

        dbRead = new DBRead(getApplicationContext());

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
        ArrayList<Criança> crianças = dbRead.getCrianças(usuário);
        if(crianças != null)
        {
            if(crianças.size() == 0)
            {
                startActivity(new Intent(this,CriancasAddActivity.class));
            }

            adapterCriancasList = new AdapterCriancasList(crianças);
            lista.setAdapter(adapterCriancasList);

        }


    }
    private void atualizarLista()
    {
        Usuário usuário = new Usuário(getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""));
        ArrayList<Criança> crianças = dbRead.getCrianças(usuário);
        if(crianças != null)
        {
            adapterCriancasList = new AdapterCriancasList(crianças);
            lista.setAdapter(adapterCriancasList);

            Thread t =  new Thread(new Runnable() {
                @Override
                public void run() {
                    inicializar();
                }
            });
            //t.start();
          //  inicializar();

        }
    }

    @Override
    protected void onResume() {
        atualizarLista();
        super.onResume();
    }

    public void inicializar()
    {
        try {
            calculador = new CalculadorNotificacoes(this);
            calculador.calcular();
            if(calculador.hasNotificacao() && getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE).getBoolean(PrefsTitles.NOTIFICACOES,true))
            {
                post(calculador.getNotificaoGeral());
            }
            else
            {
                Log.d(TAG, "inicializar: Não notificou "+String.valueOf(calculador.hasNotificacao()));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void notificar(String titulo,String descricao,int drawable,Intent intent)
    {
        nCBuilder.setContentTitle(titulo);
        nCBuilder.setContentText(descricao);
        nCBuilder.setSmallIcon(drawable);
        nCBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
        nCBuilder.setSound(alarmSound);
        nCBuilder.setColor(getResources().getColor(R.color.colorPrimary));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        nCBuilder.setContentIntent(pendingIntent);
        nManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(0,nCBuilder.build());


    }

    private void post(final Notificacao notificacao)
    {

                notificar(notificacao.getTitulo(),notificacao.getDescricao(),R.drawable.ico,new Intent(getApplicationContext(),ListarNotificacoesActivity.class));

    }
}
