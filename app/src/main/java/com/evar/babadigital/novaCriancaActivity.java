package com.evar.babadigital;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.evar.babadigital.utils.Mask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bbgetset.BAPI;
import bbgetset.Criança;
import bbgetset.Sexo;
import bbgetset.Usuário;

public class novaCriancaActivity extends AppCompatActivity {

    private EditText nomeCrianca,dataNascimento;
    private RadioButton rbBoy,rbGirl;
    private Button btnAdd;
    private Criança criança;
    private Sexo sexo;
    BAPI bapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_crianca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nomeCrianca = (EditText)findViewById(R.id.novaCrianca_editNome);
        dataNascimento = (EditText)findViewById(R.id.novaCrianca_editDataNascimento);
        rbBoy = (RadioButton)findViewById(R.id.novaCrianca_rbBoy);
        rbGirl = (RadioButton)findViewById(R.id.novaCrianca_rbGirl);
        btnAdd = (Button)findViewById(R.id.novaCrianca_btnAdd);

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
                       Log.e("dataParse: ",e.toString());
                   }

                    if(dataValida)
                    {
                        if(rbBoy.isChecked() || rbGirl.isChecked())
                        {
                            criança = new Criança();
                            criança.setNome(nomeCrianca.getText().toString());
                            criança.setDataNascimento(dateNascimento);
                            sexo = new Sexo();
                            if(rbBoy.isChecked())
                            {
                                sexo.setMasculino();
                            }
                            else
                            {sexo.setFeminino();}
                            criança.setSexo(sexo);
                            bapi = new BAPI();
                            if(bapi.cadastrarCriança(criança,new Usuário(getSharedPreferences(PrefsTitles.prefsName, Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""))).isSucesso())
                            {
                                finish();
                            }
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

    }
    private void sendErro(String msgErro,View view)
    {
        Toast.makeText(getApplicationContext(),msgErro,Toast.LENGTH_LONG).show();
        view.requestFocus();
    }

}
