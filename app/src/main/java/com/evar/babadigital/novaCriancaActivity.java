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
import com.evar.babadigital.CRUD.DBCreate;
import com.evar.babadigital.CRUD.DBUpdate;
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
    private BAPI bapi;
    private DBUpdate dbUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_crianca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbUpdate = new DBUpdate(getApplicationContext());

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
                   if(dataNascimento.getText().toString().length()!= 10)
                   {
                       sendErro(getResources().getString(R.string.dataNascimentoInvalida),dataNascimento);
                       return;
                   }
                   Log.e("Nascimento Digitada: ", dataNascimento.getText().toString());
                   try {
                       dateNascimento = sf.parse(dataNascimento.getText().toString());
                   } catch (ParseException e) {
                      dataValida = false;
                       Log.e("dataParse: ",e.toString());
                       sendErro(getResources().getString(R.string.dataNascimentoInvalida),dataNascimento);
                       return;
                   }
                   Log.e("Nascimento Digitada2: ",dateNascimento.getDay()+"/"+dateNascimento.getMonth()+"/"+dateNascimento.getYear());
                   Log.e("Nascimento Digitada3: ",dateNascimento.getTime()+" "+dateNascimento.getDate()+" "+dateNascimento.getTimezoneOffset());
                   Log.e("Nascimento Digitada4: "," "+dateNascimento.toGMTString());

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

                            bapi = new BAPI();

                            if(dbUpdate.addCrianca(criança,new Usuário(getSharedPreferences(PrefsTitles.prefsName,Context.MODE_PRIVATE).getString(PrefsTitles.jsUsuario,""))))
                            {
                                finish();
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

    }
    private void sendErro(String msgErro,View view)
    {
        Toast.makeText(getApplicationContext(),msgErro,Toast.LENGTH_LONG).show();
        view.requestFocus();
    }

}
