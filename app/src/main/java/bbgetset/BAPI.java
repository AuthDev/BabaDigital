package bbgetset;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.evar.babadigital.PrefsTitles;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.prefs.Preferences;

/**
 * Created by evar on 23/03/17.
 */

public class BAPI{

    private Usuário usuário;
    private ArrayList<Criança> crianças;
    private ArrayList<Noticia> noticias;
    private List<Vacina> vacinas;
    private Vacina vacina;
    BDFabGet bdFabGet;
    private AppCompatActivity activity;


    public Usuário getUsuário(String email, String senha) {
        bdFabGet = new BDFabGet();
        String result;
        result = bdFabGet.sentGet("getUsuario","email="+email+"&senha="+senha);
        usuário = new Usuário(result);
        return  usuário;
    }

    public ArrayList<Criança> getCrianças(Usuário usuário) {

        crianças = new ArrayList<Criança>();
        String result;
        result = bdFabGet.sentGet("getCrianças","email="+usuário.getEmail());
        Criança criança = new Criança();
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //Set data de Nascimento #########################################
                Date dataNascimento = new Date();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                dataNascimento = df.parse(jsonObject.getString("dataNascimento"));
                criança.setDataNascimento(dataNascimento);

                //Set Sexo #######################################################
                Sexo sexo = new Sexo();
                if(jsonObject.getString("sexo").contains("asculino"))
                {
                    sexo.setMasculino();
                }else
                {
                    sexo.setFeminino();
                }
                criança.setSexo(sexo);

                //Set nome ########################################################
                criança.setNome(jsonObject.getString("nome"));

                //Set Notificaçoes  ###############################################
                JSONArray jsNotificaçoes = jsonObject.getJSONArray("notificaçoes");
                String[] notificaçoes = new String[jsNotificaçoes.length()];
                for (int j = 0; j < jsNotificaçoes.length(); j++) {

                    notificaçoes[i] = jsNotificaçoes.getString(i);
                }
                criança.setNotificações(notificaçoes);

                //Set Vacinas Tomadas  #############################################
               ArrayList<VacinaTomada> vacinasTomadas = new ArrayList<VacinaTomada>();
                VacinaTomada vacinaTomada =  new VacinaTomada();
                JSONArray jsVacinasTomadas = jsonObject.getJSONArray("vacinasTomadas");

                for (int j = 0; j < jsVacinasTomadas.length(); j++) {

                    vacina = new Vacina();

                    vacina.setNome(jsVacinasTomadas.getJSONObject(j).getString("nome"));
                    vacina.setInformaçao(jsVacinasTomadas.getJSONObject(j).getString("informaçao"));
                    VacinaIdade vacinaIdade = new VacinaIdade();
                    JSONArray jsVacinaIdades = jsVacinasTomadas.getJSONArray(j).getJSONArray(j);

                    for (int k = 0; k < jsVacinaIdades.length(); k++) {
                        vacinaIdade.add(jsVacinaIdades.getInt(k));
                    }
                    vacina.setVacinaIdade(vacinaIdade);
                    vacinaTomada.setVacina(vacina);

                    // Set data
                    SimpleDateFormat fs =  new SimpleDateFormat("dd/mm/yyyy");
                    Date data = fs.parse(jsVacinasTomadas.getJSONObject(j).getString("data"));
                    vacinaTomada.setData(data);

                    // Set Validade
                    Date validade = fs.parse(jsVacinasTomadas.getJSONObject(j).getString("validade"));
                    vacinaTomada.setValidade(validade);
                    vacinasTomadas.add(vacinaTomada);
                }

                criança.setVacinasTomadas(vacinasTomadas);
                crianças.add(i,criança);
            }
        }catch (Exception e)
        {
            Log.e("getCriança :", e.toString() );
        }

        return crianças;
    }
    public ArrayList<Criança> getCrianças(String jsonString) {

        crianças = new ArrayList<Criança>();
        Criança criança = new Criança();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //Set data de Nascimento #########################################
                Date dataNascimento = new Date();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                dataNascimento = df.parse(jsonObject.getString("dataNascimento"));
                criança.setDataNascimento(dataNascimento);

                //Set Sexo #######################################################
                Sexo sexo = new Sexo();
                if(jsonObject.getString("sexo").contains("asculino"))
                {
                    sexo.setMasculino();
                }else
                {
                    sexo.setFeminino();
                }
                criança.setSexo(sexo);

                //Set nome ########################################################
                criança.setNome(jsonObject.getString("nome"));

                //Set Notificaçoes  ###############################################
                JSONArray jsNotificaçoes = jsonObject.getJSONArray("notificaçoes");
                String[] notificaçoes = new String[jsNotificaçoes.length()];
                for (int j = 0; j < jsNotificaçoes.length(); j++) {

                    notificaçoes[i] = jsNotificaçoes.getString(i);
                }
                criança.setNotificações(notificaçoes);

                //Set Vacinas Tomadas  #############################################
                ArrayList<VacinaTomada> vacinasTomadas = new ArrayList<VacinaTomada>();
                VacinaTomada vacinaTomada =  new VacinaTomada();
                JSONArray jsVacinasTomadas = jsonObject.getJSONArray("vacinasTomadas");

                for (int j = 0; j < jsVacinasTomadas.length(); j++) {

                    vacina = new Vacina();

                    vacina.setNome(jsVacinasTomadas.getJSONObject(j).getString("nome"));
                    vacina.setInformaçao(jsVacinasTomadas.getJSONObject(j).getString("informaçao"));
                    VacinaIdade vacinaIdade = new VacinaIdade();
                    JSONArray jsVacinaIdades = jsVacinasTomadas.getJSONArray(j).getJSONArray(j);

                    for (int k = 0; k < jsVacinaIdades.length(); k++) {
                        vacinaIdade.add(jsVacinaIdades.getInt(k));
                    }
                    vacina.setVacinaIdade(vacinaIdade);
                    vacinaTomada.setVacina(vacina);

                    // Set data
                    SimpleDateFormat fs =  new SimpleDateFormat("dd/mm/yyyy");
                    Date data = fs.parse(jsVacinasTomadas.getJSONObject(j).getString("data"));
                    vacinaTomada.setData(data);

                    // Set Validade
                    Date validade = fs.parse(jsVacinasTomadas.getJSONObject(j).getString("validade"));
                    vacinaTomada.setValidade(validade);
                    vacinasTomadas.add(vacinaTomada);
                }

                criança.setVacinasTomadas(vacinasTomadas);
                crianças.add(i,criança);
            }
        }catch (Exception e)
        {
            Log.e("getCriança :", e.toString() );
        }

        return crianças;
    }

    public ArrayList<Noticia> getNoticias() {
        noticias = new ArrayList<Noticia>();
        bdFabGet = new BDFabGet();
        String result;
        result = bdFabGet.sentGet("getNoticias","news=news");
        try {

            JSONArray jsNoticias = new JSONArray(result);
            Noticia noticia = new Noticia();

            for (int i = 0; i < jsNoticias.length(); i++) {

                noticia.setAutor(jsNoticias.getJSONObject(i).getString("autor"));

                noticia.setConteudo(jsNoticias.getJSONObject(i).getString("conteudo"));

                SimpleDateFormat fs = new SimpleDateFormat("dd/MM/yyyy");
                Date data = fs.parse(jsNoticias.getJSONObject(i).getString("data"));
                noticia.setData(data);

                noticia.setFonte(jsNoticias.getJSONObject(i).getString("fonte"));

                noticia.setTitulo(jsNoticias.getJSONObject(i).getString("titulo"));

                noticia.setImagem(jsNoticias.getJSONObject(i).getString("imagem"));

                noticias.add(i,noticia);
            }



        }catch (Exception e)
        {
            Log.e("getNoticias :", e.toString() );
        }
        return noticias;
    }

    public List<Vacina> getVacinas() {

        vacinas = new ArrayList<Vacina>();
        vacina = new Vacina();
        String result = bdFabGet.sentGet("getVacinas","vacinas=vacinas");
        try {
            JSONObject jsVacinas = new JSONObject(result);
            for (int i = 0; i < jsVacinas.length(); i++) {

                vacina.setNome(jsVacinas.getString("nome"));

                VacinaIdade vacinaIdade = new VacinaIdade();
                for (int j = 0; j < jsVacinas.getJSONArray("idade").length(); j++) {
                    vacinaIdade.add(jsVacinas.getJSONArray("idade").getInt(j));
                }

                vacina.setVacinaIdade(vacinaIdade);
                vacinas.add(i,vacina);
            }

        }catch (Exception e)
        {

        }
        return vacinas;
    }

    public boolean isEmpty()
    {
        if(usuário.getEmail().length() < 1)
        {
            return true;
        }
        return false;
    }
    //////////////////////////////////////////////////////////////////

    public BAPIResult cadastrarCriança(Criança criança,Usuário usuário)
    {
        BAPIResult bapiResult = new BAPIResult();
        if(criança.getNome().length() > 0)
        {
            if (usuário.getEmail().length() > 0)
            {
                if(usuário.isValidated())
                {
                    if(activity.getSharedPreferences(usuário.getEmail(),Context.MODE_PRIVATE).getString(PrefsTitles.jsCrianças,"").length() > 0)
                    {
                        activity = new AppCompatActivity();
                        Crianças crianças = new Crianças(activity.getSharedPreferences(usuário.getEmail(), Context.MODE_PRIVATE).getString(PrefsTitles.jsCrianças,""));

                        crianças.addCriança(criança);
                        activity.getSharedPreferences(usuário.getEmail(),Context.MODE_PRIVATE).edit().putString(PrefsTitles.jsCrianças,crianças.toJsonArray().toString()).commit();
                    }
                    else
                        {
                            Crianças crianças = new Crianças();
                            crianças.addCriança(criança);
                            activity.getSharedPreferences(usuário.getEmail(),Context.MODE_PRIVATE).edit().putString(PrefsTitles.jsCrianças,crianças.toJsonArray().toString()).commit();
                        }

                }else
                    {
                        bapiResult.setSucesso(false);
                        bapiResult.setResult("Usuario nao validado!");
                    }

            }else
                {
                    bapiResult.setSucesso(false);
                    bapiResult.setResult("Usuario sem email!");
                }

        }else
            {
                bapiResult.setSucesso(false);
                bapiResult.setResult("Criança sem nome");
            }

        return bapiResult;

    }

    public boolean cadastrar()
    {

        return true;
    }

    public boolean atualizarNoticia(Criança criança)
    {

        return true;
    }
    public boolean removerNoticia(Criança criança)
    {
        return true;
    }

    public boolean cadastrarNoticia(Noticia noticia)
    {
        return true;
    }
    public boolean atualizarNoticia(Noticia noticia)
    {
        return true;
    }
    public boolean removerNoticia(Noticia noticia)
    {
        return true;
    }


    public boolean cadastrarUsuario(Usuário usuário,String senha)
    {
        String result;
        if(usuário.isValidated())
        {
           if(!usuarioExist(usuário))
           {    bdFabGet = new BDFabGet();
               String dataNascimento = usuário.getDataNascimento().getDay()+"/"+usuário.getDataNascimento().getMonth()+"/"+usuário.getDataNascimento().getYear();
               result = bdFabGet.sentGet("cadastrarUsuario","email="+usuário.getEmail()+"&senha="+senha+"&nome="+usuário.getNome()+ "&telefone="+usuário.getNumeroTelefone()+"&celular="+usuário.getNumeroCelular()+"&dataNascimento="+dataNascimento+"&rua="+usuário.getEndereço().getRua()+ "&bairro="+usuário.getEndereço().getBairro()+"&numeroCasa"+usuário.getEndereço().getNumeroCasa()+"&estado="+usuário.getEndereço().getEstado()+ "&pais=BR"+"&cidade+"+usuário.getEndereço().getCidade());
               try {
                   JSONObject jsonObject = new JSONObject(result);
                   return jsonObject.getBoolean("sucess");
               }catch (Exception e)
               {
                return  false;
               }


           }

        }else{return false;}

        return true;
    }
    public boolean usuarioExist(Usuário user)
    {
        bdFabGet = new BDFabGet();
        boolean exist = false;
        String result;
        Log.d("onClick: ",user.getEmail());
        result = bdFabGet.sentGet("usuarioExist","email="+user.getEmail());
        try {
            JSONObject jsonObject = new JSONObject(result);
            exist = jsonObject.getBoolean("exist");
        }catch (Exception e)
        {

        }
        return exist;
    }
    public boolean emailExist(String email)
    {
        boolean exist;
        Usuário user = new Usuário();
        user.setEmail(email);
        exist = usuarioExist(user);
        return exist;
    }
    public boolean atualizarUsuario(Usuário usuário)
    {
        return true;
    }
    public boolean removerUsuario(Usuário usuário)
    {
        return true;
    }

    public boolean cadastrarVacina(Vacina vacina)
    {
        return true;
    }
    public boolean atualizarVacina(Vacina vacina)
    {
        return true;
    }
    public boolean removerVacina(Vacina vacina)
    {
        return true;
    }



}
