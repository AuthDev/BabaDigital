package com.evar.babadigital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.evar.babadigital.utils.Mask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bbgetset.BAPI;
import bbgetset.Endereço;
import bbgetset.Sexo;
import bbgetset.Usuário;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome,editEmail,editTelefone,editCelular,editDataNascimento,editRua,editBairro,editNumero,editCidade,editComplemento,editSenha,editConfirmarSenha,editCpf;
    private Button btnRegistrar;
    private Spinner spinSexo,spinEstado;
    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    private String celular;
    private Date dataNascimento;
    private String rua;
    private String bairro;
    private String numero;
    private String cidade;
    private String complemento;
    private LinearLayout progressCad;
    private LinearLayout formCad;


    private Usuário usuário;
    private Endereço endereço;
    private BAPI bapi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editNome = (EditText)findViewById(R.id.cadastro_editNome);
        editEmail = (EditText)findViewById(R.id.cadastro_editEmail);
        editTelefone = (EditText)findViewById(R.id.cadastro_editTelefone);
        editCelular = (EditText)findViewById(R.id.cadastro_editCelular);
        editDataNascimento = (EditText)findViewById(R.id.cadastro_editDataNascimento);
        spinSexo = (Spinner)findViewById(R.id.cadastro_spinnerSexo);
        editCpf = (EditText)findViewById(R.id.editCPF);
        editRua = (EditText)findViewById(R.id.cadastro_editRua);
        editBairro = (EditText)findViewById(R.id.cadastro_editBairro);
        editNumero = (EditText)findViewById(R.id.cadastro_editNumeroCasa);
        editCidade = (EditText)findViewById(R.id.cadastro_editCidade);
        spinEstado = (Spinner)findViewById(R.id.cadastro_spinnerEstado);
        editComplemento = (EditText)findViewById(R.id.cadastro_editComplemento);
        btnRegistrar = (Button)findViewById(R.id.cadastro_btnRegistrar);
        editSenha = (EditText)findViewById(R.id.cadastro_editSenha);
        editConfirmarSenha = (EditText)findViewById(R.id.cadastro_editConfirmarSenha);
        progressCad = (LinearLayout) findViewById(R.id.progressCad);
        formCad = (LinearLayout)findViewById(R.id.formCad);


        try
        {
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.sexo,R.layout.spinner);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinSexo.setAdapter(arrayAdapter);

        }catch (Exception e)
        {

        }
        try
        {
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.estados,R.layout.spinner);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinEstado.setAdapter(arrayAdapter);
            spinEstado.setSelection(4);
        }catch (Exception e)
        {

        }
        editDataNascimento.addTextChangedListener(Mask.insert("##/##/####",editDataNascimento));
        editCelular.addTextChangedListener(Mask.insert("(##)#####-####",editCelular));
        editTelefone.addTextChangedListener(Mask.insert("(##)####-####",editTelefone));


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                formCad.setVisibility(View.INVISIBLE);
                progressCad.setVisibility(View.VISIBLE);
                bapi = new BAPI();
                nome = editNome.getText().toString();
                email = editEmail.getText().toString().toLowerCase();
                senha = editSenha.getText().toString();
                confirmarSenha = editConfirmarSenha.getText().toString();
                celular = editCelular.getText().toString();
                rua = editRua.getText().toString();
                bairro = editBairro.getText().toString();
                numero = editNumero.getText().toString();
                cidade = editCidade.getText().toString();
                complemento = editComplemento.getText().toString();

                usuário = new Usuário();
                endereço = new Endereço();

                if(email.length() > 0 && email.contains("@"))
                {
                    usuário.setEmail(email);
                    if(bapi.usuarioExist(email))
                    {
                        campoErrado(editEmail,getResources().getString(R.string.emailExist));
                        return;
                    }
                }else
                {
                    campoErrado(editEmail,getResources().getString(R.string.error_invalid_email));
                    return;
                }

                if(nome.length() > 3)
                {
                    usuário.setNome(nome);
                }else
                    {

                        campoErrado(editNome,getResources().getString(R.string.cadastro_erroNomeIvalido));
                        return;

                    }

                if(editTelefone.getText().toString().length() > 0)
                {
                    usuário.setNumeroTelefone(editTelefone.getText().toString());
                }

                if(celular.length() > 0)
                {
                    usuário.setNumeroCelular(celular);
                }else
                    {
                        campoErrado(editCelular,getResources().getString(R.string.cadastro_erroCelularVazio));
                        return;
                    }
                if(editDataNascimento.getText().length() == 10)
                {
                    try {
                        DateFormat fs = new SimpleDateFormat("dd/MM/yyyy");
                        dataNascimento = fs.parse(editDataNascimento.getText().toString());
                        usuário.setDataNascimento(dataNascimento);

                    }catch (Exception e)
                    {
                        campoErrado(editDataNascimento,getResources().getString(R.string.cadastro_erroDataNascimentoInvalido));

                    }
                }else
                    {
                        campoErrado(editDataNascimento,getResources().getString(R.string.cadastro_erroDataNascimentoInvalido));
                    }



                switch (spinSexo.getSelectedItemPosition())
                {
                    case 0:  usuário.setSexo(Sexo.MASCULINO);
                        break;
                    case 1: usuário.setSexo(Sexo.FEMININO);
                        break;
                    default:usuário.setSexo(Sexo.MASCULINO);
                }


                if(editCpf.getText().toString().length() > 0)
                {
                    usuário.setCPF(editCpf.getText().toString());
                }else
                    {
                        campoErrado(editCpf,getResources().getString(R.string.cadastro_erroCPF));
                        return;
                    }

                if(rua.length() > 0)
                {
                    endereço.setRua(rua);
                }
                if(bairro.length() > 0)
                {
                    endereço.setBairro(bairro);
                }

                if(cidade.length() > 0)
                {
                    endereço.setCidade(editCidade.getText().toString());
                }else
                    {
                        campoErrado(editCidade,getResources().getString(R.string.cidadeObrigatorio));
                        return;
                    }
                if(spinEstado.isSelected())
                {
                    endereço.setEstado(spinEstado.getSelectedItem().toString());
                }
                if(complemento.length() > 0)
                {
                    endereço.setComplemento(complemento);
                }
                usuário.setEndereço(endereço);

                if(senha.length() > 0)
                {

                    if(senha.length() >= 6)
                    {
                        if(senha.contains(confirmarSenha))
                        {
                            usuário.setValidated(true);
                            if(bapi.usuarioExist(usuário))
                            {
                                campoErrado(editEmail,getResources().getString(R.string.emailExist));
                                return;
                            }else{

                                boolean cadastrado = bapi.cadastrarUsuario(usuário,senha);
                                if(cadastrado)
                                {
                                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.cadastradoSucesso),Toast.LENGTH_LONG).show();
                                    finish();
                                }else
                                {
                                    campoErrado(editEmail,getResources().getString(R.string.falha));
                                }

                            }


                        }else
                        {
                            campoErrado(editSenha,getResources().getString(R.string.cadastro_erroSenhaNaoConferem));
                            return;
                        }
                    }else
                    {
                        campoErrado(editSenha,getResources().getString(R.string.cadastro_erroSenhaMuitoCurta));
                        return;
                    }


                }else
                {
                    campoErrado(editSenha,getResources().getString(R.string.cadastro_erroCampoSenhaVazio));
                    return;
                }



            }
        });

    }

    public void campoErrado(View view,String menssagem)
    {
        view.requestFocus();
        Toast.makeText(getApplicationContext(),menssagem,Toast.LENGTH_LONG).show();
        formCad.setVisibility(View.VISIBLE);
        progressCad.setVisibility(View.INVISIBLE);
    }
}
