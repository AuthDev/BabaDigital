package bbgetset;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Clinica on 21/03/2017.
 */

public class Usuário {

    private String nome;
    private String email;
    private String senha;
    private String numeroCelular;
    private String numeroTelefone;
    private Date dataNascimento;
    private Endereço endereço;
    private int idade;
    private Sexo sexo;
    private boolean validated = false;
    private String stringJSON = "";

    public Usuário(){

    }

    public Usuário(String jsonString)
    {
        this.stringJSON = jsonString;
        JSONObject jsonObject;
        try {
            //Setar Email
            jsonObject= new JSONObject(jsonString);
            Log.d("Json to String = ",jsonObject.toString());

            if(jsonObject.has("email")) {
                setEmail(jsonObject.getString("email"));
            }

            //Setar data de Nascimento
            if(jsonObject.has("dataNascimento")) {
                Date dataNascimento = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dataNascimento = simpleDateFormat.parse(jsonObject.getString("dataNascimento"));
                setDataNascimento(dataNascimento);
            }

            //Setar Endereço
            Endereço endereço = new Endereço(jsonObject.getString("cidade"),jsonObject.getString("estado"),jsonObject.getString("rua"),jsonObject.getString("bairro"),jsonObject.getInt("numeroCasa"),jsonObject.getInt("cepNumber"),jsonObject.getString("pais"),jsonObject.getString("complemento"));
            setEndereço(endereço);

            //Set Nome
            setNome(jsonObject.getString("nome"));

            //Set Sexo
            Sexo sexo = new Sexo();
            if(jsonObject.getString("sexo").contains("m"))
            {
                sexo.setMasculino();
            }else
            {
                sexo.setFeminino();
            }
            setSexo(sexo);

            //Set numero celular
            setNumeroCelular(jsonObject.getString("numeroCelular"));

            //Set numero Telefone
            setNumeroTelefone(jsonObject.getString("numeroTelefone"));

            if(getEmail().length() > 0)
            {
                setValidated(true);
            }



            Log.d("getUsuario validated = ", String.valueOf(isValidated()));

        }catch (Exception e)
        {
            Log.e("getUsuario = " ,e.toString() );
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereço getEndereço() {
        return endereço;
    }

    public void setEndereço(Endereço endereço) {
        this.endereço = endereço;
    }

    public int getIdade() {
        try{
            int anoNascimento = dataNascimento.getYear();
            int mesNascimento = dataNascimento.getMonth();
            int diaNascimento = dataNascimento.getDay();

            Date date = new Date(System.currentTimeMillis());
            int anoAtual = date.getYear();
            int mesAtual = date.getMonth();
            int diaAtual = date.getDay();

            idade = anoAtual - anoNascimento;
            if(mesAtual < mesNascimento)
            {
                idade--;
            }
            else if(mesAtual == mesNascimento){
                if(diaAtual < diaNascimento)
                {
                    idade--;
                }
            }
            return idade;

        }catch (Exception e)
        {
            return idade;
        }
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public JSONObject toJsonObject()
    {
        String json = "";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nome",nome);
            jsonObject.put("email",email);
            jsonObject.put("senha",senha);
            jsonObject.put("numeroCelular",numeroCelular);
            jsonObject.put("numeroTelefone",numeroTelefone);
            jsonObject.put("dataNascimento",dataNascimento);
            jsonObject.put("bairro",getEndereço().getBairro());
            jsonObject.put("cepNumber",getEndereço().getCepNumber());
            jsonObject.put("cidade",getEndereço().getCidade());
            jsonObject.put("complemento",getEndereço().getComplemento());
            jsonObject.put("estado",getEndereço().getEstado());
            jsonObject.put("numeroCasa",getEndereço().getNumeroCasa());
            jsonObject.put("rua",getEndereço().getRua());
            jsonObject.put("pais",getEndereço().getPaís());
            jsonObject.put("idade",idade);
            jsonObject.put("sexo",sexo);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }
    public String toStringJson()
    {
        return stringJSON;
    }
}
