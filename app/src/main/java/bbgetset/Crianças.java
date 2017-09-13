package bbgetset;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Clinica on 21/03/2017.
 */

public class Crianças {

    private ArrayList<Criança> crianças;

    public Crianças()
    {
        crianças = new ArrayList<Criança>();
    }

    public Crianças(String jsonString)
    {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(jsonString);
            ArrayList<Criança> criançasArray = new ArrayList<Criança>();
            Criança criancaUn;
            Sexo sexo;
            for (int i = 0; i < jsonArray.length(); i++) {
                criancaUn = new Criança();
                criancaUn.setNome(jsonArray.getJSONObject(i).getString("nome"));
                if(jsonArray.getJSONObject(i).getString("sexo").contains(Sexo.FEMININO))
                {
                    criancaUn.setSexo(Sexo.FEMININO);

                }else
                    {
                        criancaUn.setSexo(Sexo.MASCULINO);
                    }
                Date data;
                DateFormat fs = new SimpleDateFormat("dd/MM/yyyy");
                data = fs.parse(jsonArray.getJSONObject(i).getString("dataNascimento"));
                criancaUn.setDataNascimento(data);

                String[]Notificaçoes = new String[jsonArray.getJSONObject(i).getJSONArray("notificacoes").length()];
                try {
                    for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("notificacoes").length() ; j++) {
                        Notificaçoes[j]=jsonArray.getJSONObject(i).getJSONArray("notificacoes").getString(j);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Vacina vacina;
                VacinaTomada vacinaTomada;
                VacinaIdade vacinaIdade;
                ArrayList<VacinaTomada> VacinasTomadas = new ArrayList<VacinaTomada>();
                for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").length() ; j++) {

                    vacina = new Vacina();
                    vacinaIdade = new VacinaIdade();

                    vacina.setNome(jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).getString("nome"));
                    vacina.setDescricao(jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).getString("informacao"));

                   // vacina.setVacinaIdade(jsonArray.getJSONObject(i).getJSONObject("vacinasTomadas").getString("vacinaIdade"));
                    vacinaTomada = new VacinaTomada();
                  //  vacinaTomada.setVacina(vacina);

                    Date date;
                    DateFormat vl = new SimpleDateFormat("dd/MM/yyyy");
                    date = vl.parse(jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).getString("validade"));
                    vacinaTomada.setValidade(date);

                    date = vl.parse(jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).getString("data"));
                    vacinaTomada.setData(date);

                    vacinaTomada.setLocalTomado(jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).getString("local"));

                    VacinasTomadas.add(vacinaTomada);
                }

                criancaUn.setVacinasTomadas(VacinasTomadas);

            }
        }catch (Exception e)
        {

        }
    }

    public ArrayList<Criança> getCrianças() {
        return crianças;
    }

    public void setCrianças(ArrayList<Criança> crianças) {
        this.crianças = crianças;
    }

    public void addCriança(Criança criança)
    {
        crianças.add(criança);
    }
    public JSONArray toJsonArray()
    {
        JSONArray jsonArray = new JSONArray();
        try {
            ArrayList<Criança> criançasArray = this.crianças;
            Criança criancaUn;
            Sexo sexo;
            for (int i = 0; i < criançasArray.size(); i++) {
                criancaUn = criançasArray.get(i);
                jsonArray.getJSONObject(i).put("nome",criancaUn.getNome());


                jsonArray.getJSONObject(i).put("sexo",criancaUn.getSexo());


                jsonArray.getJSONObject(i).put("dataNascimento",criancaUn.getDataNascimento().toString());

                for (int j = 0; j < criancaUn.getNotificações().length ; j++) {
                    jsonArray.getJSONObject(i).getJSONArray("notificacoes").put(j,criancaUn.getNotificações()[j]);
                }



                for (int j = 0; j < criancaUn.getVacinasTomadas().size() ; j++) {

                   // jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).put("nome",criancaUn.getVacinasTomadas().get(j).getVacina().getNome());
                    //jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).put("informacao",criancaUn.getVacinasTomadas().get(j).getVacina().getDescricao());


                    for (int k = 0; k < jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).getJSONArray("vacinaIdade").length();k++) {
                     //   jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).getJSONArray("vacinaIdade").put(criancaUn.getVacinasTomadas().get(j).getVacina().getVacinaIdade());
                    }

                    jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).put("validade",criancaUn.getVacinasTomadas().get(j).getValidade());
                    jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).put("data",criancaUn.getVacinasTomadas().get(j).getData());


                   jsonArray.getJSONObject(i).getJSONArray("vacinasTomadas").getJSONObject(j).put("local",criancaUn.getVacinasTomadas().get(j).getLocalTomado());


                }

            }
        }catch (Exception e)
        {

        }

        return jsonArray;
    }
}
