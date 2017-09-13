package bbgetset;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;

import com.evar.babadigital.PrefsTitles;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webdo.WebPG;

/**
 * Created by evar on 23/03/17.
 */

public class BAPI{

    private Usuário usuário;
    private ArrayList<Criança> crianças;
    private ArrayList<Noticia> noticias;
    private ArrayList<Vacina> vacinas;
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
                if(jsonObject.getString("sexo").contains("asculino"))
                {
                    criança.setSexo(Sexo.MASCULINO);
                }else
                {
                    criança.setSexo(Sexo.FEMININO);
                }


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
                    vacina.setDescricao(jsVacinasTomadas.getJSONObject(j).getString("informaçao"));
                  //  vacina.setVacinaIdade("10 - 20");
                //    vacinaTomada.setVacina(vacina);

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
                //Set Sexo #######################################################
                if(jsonObject.getString("sexo").contains("asculino"))
                {
                    criança.setSexo(Sexo.MASCULINO);
                }else
                {
                    criança.setSexo(Sexo.FEMININO);
                }

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
                    vacina.setDescricao(jsVacinasTomadas.getJSONObject(j).getString("informaçao"));
                    VacinaIdade vacinaIdade = new VacinaIdade();
                    JSONArray jsVacinaIdades = jsVacinasTomadas.getJSONArray(j).getJSONArray(j);

                    for (int k = 0; k < jsVacinaIdades.length(); k++) {
                        vacinaIdade.add(jsVacinaIdades.getInt(k));
                    }
               //     vacina.setVacinaIdade(jsVacinasTomadas.getJSONObject(0).getString("vacinaIdade"));
         //           vacinaTomada.setVacina(vacina);

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
        }

        return crianças;
    }



    public ArrayList<Noticia> getNoticias() {
        noticias = new ArrayList<Noticia>();

        bdFabGet = new BDFabGet();
        String result;
        result = bdFabGet.sentGet("getNoticias","news=news");

        JSONArray array;
        Noticia noticia;
        Elements dElements;
        InputStream input;
        Bitmap bitmap;

        try {
            array = new JSONArray(result);
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                Document document = Jsoup.connect(jsonObject.getString("link")).get();
                noticia = new Noticia();
                dElements = document.select("meta[property=\"og:title\"]");
                noticia.setTitulo(dElements.attr("content"));
                dElements = document.select("meta[property=\"og:description\"]");
                noticia.setConteudo(dElements.attr("content"));
                noticia.setFonte(jsonObject.getString("link"));
                dElements = document.select("meta[property=\"og:site_name\"]");
                noticia.setAutor(dElements.attr("content"));
                dElements = document.select("meta[property=\"og:image\"]");
                noticia.setSrcImagem(dElements.attr("content"));

                try {
                    input = new  java.net.URL(noticia.getSrcImagem()).openStream();
                    bitmap = BitmapFactory.decodeStream(input);
                    noticia.setBitmap(bitmap);
                }catch (Exception e)
                {

                }
                noticias.add(noticia);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noticias;
    }

    public Noticia getNoticia(int position,JSONArray jsonArrayNoticias) {
        Noticia noticia = new Noticia();

        JSONArray array;
        Elements dElements;
        InputStream input;
        Bitmap bitmap;

        try {
            array = jsonArrayNoticias;
            JSONObject jsonObject;
            jsonObject = array.getJSONObject(position);
            Document document = Jsoup.connect(jsonObject.getString("link")).get();
            noticia = new Noticia();
            dElements = document.select("meta[property=\"og:title\"]");
            noticia.setTitulo(dElements.attr("content"));
            dElements = document.select("meta[property=\"og:description\"]");
            noticia.setConteudo(dElements.attr("content"));
            noticia.setFonte(jsonObject.getString("link"));
            dElements = document.select("meta[property=\"og:site_name\"]");
            noticia.setAutor(dElements.attr("content"));
            dElements = document.select("meta[property=\"og:image\"]");
            noticia.setSrcImagem(dElements.attr("content"));

            try {
                input = new java.net.URL(noticia.getSrcImagem()).openStream();
                bitmap = BitmapFactory.decodeStream(input);
                noticia.setBitmap(bitmap);
            } catch (Exception e) {

            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    return noticia;

    }

    public JSONArray getJsonNoticias() {
        Noticia noticia = new Noticia();


        bdFabGet = new BDFabGet();
        String result;
        result = bdFabGet.sentGet("getNoticias", "news=news");

        JSONArray array = new JSONArray();
        try {
            array = new JSONArray(result);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
     return array;

    }


    public ArrayList<Vacina> getVacinas() {

        vacinas = new ArrayList<Vacina>();
        vacina = new Vacina();
        bdFabGet = new BDFabGet();
        String result = bdFabGet.sentGet("getVacinas","vacinas=vacinas");
        try {
            JSONArray jsVacinas = new JSONArray(result);
            JSONObject jsonObject;
            for (int i = 0; i < jsVacinas.length(); i++) {
                jsonObject = jsVacinas.getJSONObject(i);

                vacina = new Vacina();
                vacina.setNome(jsonObject.getString("vacina"));

                if(jsonObject.getInt("particular") == 1){
                    vacina.setParticular(true);
                } else {
                    vacina.setParticular(false);
                }


                if(jsonObject.getInt("publica") == 1)
                {
                    vacina.setPublica(true);
                }else{
                    vacina.setPublica(false);
                }

                vacina.setDescricao(jsonObject.getString("descricao"));
                vacina.setIdade(jsonObject.getInt("idade"));
                vacina.setDoses(jsonObject.getInt("dose"));
                vacina.setIntervalo(jsonObject.getInt("intervalo"));
                vacina.setCod(jsonObject.getInt("cod"));

                vacinas.add(i,vacina);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
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
           {
               List<NameValuePair>valuePairs = new ArrayList<NameValuePair>();
               valuePairs.add(new BasicNameValuePair("email",usuário.getEmail()));
               valuePairs.add(new BasicNameValuePair("senha",senha));
               valuePairs.add(new BasicNameValuePair("nome",usuário.getNome()));
               valuePairs.add(new BasicNameValuePair("telefone",usuário.getNumeroTelefone()));
               valuePairs.add(new BasicNameValuePair("celular",usuário.getNumeroCelular()));
               valuePairs.add(new BasicNameValuePair("dataNascimento",usuário.dataNascimentoString()));
               valuePairs.add(new BasicNameValuePair("rua",usuário.getEndereço().getRua()));
               valuePairs.add(new BasicNameValuePair("bairro",usuário.getEndereço().getBairro()));
               valuePairs.add(new BasicNameValuePair("numeroCasa",String.valueOf(usuário.getEndereço().getNumeroCasa())));
               valuePairs.add(new BasicNameValuePair("cidade",usuário.getEndereço().getCidade()));
               valuePairs.add(new BasicNameValuePair("estado",usuário.getEndereço().getEstado()));
               valuePairs.add(new BasicNameValuePair("pais",usuário.getEndereço().getPaís()));
               valuePairs.add(new BasicNameValuePair("cpf",usuário.getCPF()));

               try {
                   result = WebPG.post("cadastrarUsuarioPOST",valuePairs);
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
        result = bdFabGet.sentGet("usuarioExist","email="+user.getEmail());
        try {
            JSONObject jsonObject = new JSONObject(result);
            exist = jsonObject.getBoolean("exist");
        }catch (Exception e)
        {

        }
        return exist;
    }
    public boolean usuarioExist(String email)
    {
        bdFabGet = new BDFabGet();
        boolean exist = false;
        String result;
        result = bdFabGet.sentGet("usuarioExist","email="+email);
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
