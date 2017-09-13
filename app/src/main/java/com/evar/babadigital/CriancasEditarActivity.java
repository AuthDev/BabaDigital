package com.evar.babadigital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.evar.babadigital.CRUD.DBUpdate;
import com.evar.babadigital.utils.Mask;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bbgetset.BAPI;
import bbgetset.Criança;
import bbgetset.Sexo;
import bbgetset.Usuário;

public class CriancasEditarActivity extends AppCompatActivity {


    private EditText nomeCrianca,dataNascimento;
    private RadioButton rbBoy,rbGirl;
    private Button btnAdd;
    private Button deleteBtn;

    private Criança criança;
    private DBUpdate db;
    private DBUpdate dbUpdate;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criancas_editar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbUpdate = new DBUpdate(getApplicationContext());

        nomeCrianca = (EditText)findViewById(R.id.novaCrianca_editNome);
        dataNascimento = (EditText)findViewById(R.id.novaCrianca_editDataNascimento);
        rbBoy = (RadioButton)findViewById(R.id.novaCrianca_rbBoy);
        rbGirl = (RadioButton)findViewById(R.id.novaCrianca_rbGirl);
        btnAdd = (Button)findViewById(R.id.novaCrianca_btnAdd);
        deleteBtn = (Button)findViewById(R.id.removerBtn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        nomeCrianca.setText(intent.getStringExtra("nome"));
        dataNascimento.setText(intent.getStringExtra("dataNascimento"));
        if(intent.getStringExtra("sexo").contains(Sexo.MASCULINO))
        {
            rbBoy.setChecked(true);
        }else
            {
                rbGirl.setChecked(true);
            }

        dataNascimento.addTextChangedListener(Mask.insert("##/##/####",dataNascimento));

        rbBoy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    rbGirl.setChecked(false);
                }
            }
        });

        rbGirl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    rbBoy.setChecked(false);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomeCrianca.length() > 0)
                {
                    boolean dataValida = true;

                    Date dateNascimento = new Date();
                    DateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        dateNascimento = sf.parse(dataNascimento.getText().toString());

                    } catch (ParseException e) {
                        dataValida = false;
                    }

                    if(dataValida)
                    {
                        if(rbBoy.isChecked() || rbGirl.isChecked())
                        {
                            criança = new Criança();
                            criança.setNome(nomeCrianca.getText().toString());
                            criança.setDataNascimento(dateNascimento);
                            if(rbBoy.isChecked())
                            {
                                criança.setSexo(Sexo.MASCULINO);
                            }
                            else
                            {criança.setSexo(Sexo.FEMININO);}
                            int cod = intent.getIntExtra("cod",0);
                            if(cod > 0)
                            {
                                criança.setCod(cod);
                                if(dbUpdate.updateCrianca(criança,new Usuário(getSharedPreferences(PrefsTitles.prefsName, Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""))))
                                {
                                    PrefsTitles.novaNot = true;
                                    finish();
                                }
                                else
                                {
                                    sendErro(getResources().getString(R.string.falha),btnAdd);
                                }

                            }
                            //if(bapi.cadastrarCriança(criança,new Usuário(getSharedPreferences(PrefsTitles.prefsName, Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""))).isSucesso())
                            //{
                            //   finish();
                            // }
                            else
                            {
                                sendErro(getResources().getString(R.string.falha),btnAdd);
                            }
                        }else
                        {
                            sendErro(getResources().getString(R.string.escolherSexoCriança),btnAdd);
                        }

                    }else
                    {
                        sendErro(getResources().getString(R.string.dataNascimentoInvalida),dataNascimento);
                    }
                }
                else
                {
                    sendErro(getResources().getString(R.string.criancaNomeVazio),nomeCrianca);
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int cod = getIntent().getIntExtra("cod",0);
                db = new DBUpdate(getApplicationContext());
            if(db.deleteCrianca(cod))
               {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.deleteSucesso),Toast.LENGTH_LONG).show();
                   finish();
               }else
                   {
                       Toast.makeText(getApplicationContext(),getResources().getString(R.string.deleteFalha),Toast.LENGTH_LONG).show();
                   }

            }
        });

    }
    private void sendErro(String msgErro,View view)
    {
        Toast.makeText(getApplicationContext(),msgErro,Toast.LENGTH_LONG).show();
        view.requestFocus();
    }

}
