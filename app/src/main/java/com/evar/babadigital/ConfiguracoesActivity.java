package com.evar.babadigital;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class ConfiguracoesActivity extends AppCompatActivity {

    private Button salvarBtn;
    private Switch notifSwitch;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences(PrefsTitles.prefsName, Context.MODE_PRIVATE);

        salvarBtn = (Button)findViewById(R.id.btnSalvar);
        notifSwitch = (Switch)findViewById(R.id.switchMostrarNotificacoes);
        notifSwitch.setChecked(preferences.getBoolean(PrefsTitles.NOTIFICACOES,true));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();

            }
        });



    }

    private void salvar()
    {
       preferences.edit().putBoolean(PrefsTitles.NOTIFICACOES,notifSwitch.isChecked()).commit();
        finish();
    }
}
