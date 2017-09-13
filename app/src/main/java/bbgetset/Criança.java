package bbgetset;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Clinica on 21/03/2017.
 */

public class Criança {

    private String idade;
    private String nome;
    private Date dataNascimento;
    private ArrayList<VacinaTomada> vacinasTomadas;
    private String[] notificações;
    private String sexo;
    private int cod;
    private int old;

    public Criança()
    {
        vacinasTomadas = new ArrayList<VacinaTomada>();
        notificações = new String[]{"nenhuma"};
        sexo = "";
    }

    public String getIdadeString() {
         old = 0;
        try{
            int anoNascimento = dataNascimento.getYear() +1900;
            int mesNascimento = dataNascimento.getMonth()+1;
            int diaNascimento = dataNascimento.getDate();

            Date date = new Date(System.currentTimeMillis());
            int anoAtual = date.getYear()+1900;
            int mesAtual = date.getMonth()+1;
            int diaAtual = date.getDate();


            old = anoAtual - anoNascimento;
            if(mesAtual < mesNascimento)
            {
                old--;
            }
            else if(mesAtual == mesNascimento){
                if(diaAtual < diaNascimento)
                {
                    old--;
                }
            }

            if(old < 1)
            {
                if(mesAtual > mesNascimento)
                {
                    old = mesAtual - mesNascimento;
                    if(old == 1)
                    {
                        idade = old + " mês de idade";
                    }else{
                        idade = old + " meses de idade";
                    }
                }else
                    {
                        if(mesAtual < mesNascimento) {
                            old = (12 - mesNascimento) + mesAtual;
                            if(old == 1)
                            {
                                idade = old + " mês de idade";
                            }else
                                {
                                    idade = old + " meses de idade";
                                }

                        }else
                            {
                                if (diaAtual >= diaNascimento) {
                                    old = diaAtual - diaNascimento;
                                    if(old == 1)
                                    {
                                        idade = old + " dia";
                                    }else
                                    {
                                        if(old == 0)
                                        {
                                            idade = " Criança recém-nascida";
                                        }else
                                            {
                                                idade = old + " dias";
                                            }

                                    }

                                }
                            }
                    }
            }else
                {
                    if(old == 1)
                    {
                        idade = old +" ano de idade";
                    }else
                        {
                            idade = old +" anos de idade";
                        }

                }
            return idade;

        }catch (Exception e)
        {
            return idade;
        }
    }
    public int getAnosIdade()
    {
        old = 0;
            int anoNascimento = dataNascimento.getYear() +1900;
            int mesNascimento = dataNascimento.getMonth()+1;
            int diaNascimento = dataNascimento.getDate();

            Date date = new Date(System.currentTimeMillis());
            int anoAtual = date.getYear()+1900;
            int mesAtual = date.getMonth()+1;
            int diaAtual = date.getDate();


            old = anoAtual - anoNascimento;
            if(mesAtual < mesNascimento)
            {
                old--;
            }
            else if(mesAtual == mesNascimento){
                if(diaAtual < diaNascimento)
                {
                    old--;
                }
            }
        return old;
    }
    public int getMesesIdade()
    {
            int mes = 0;
            int mesNascimento = dataNascimento.getMonth()+1;

            Date date = new Date(System.currentTimeMillis());
            int mesAtual = date.getMonth()+1;

            mes += getAnosIdade()*12;

            if(mesAtual < mesNascimento)
            {
                mes = 12-mesNascimento+mesAtual;
            }else
                {
                    if(mesAtual == mesNascimento)
                    {
                       return mes;
                    }else
                        {
                            mes = mesAtual - mesNascimento;
                        }
                }


            return mes;
    }
    public int getDiasNascimento()
    {
        int dias = 0;
        int anos = 0;
        DateTime nasc = new DateTime(getDataNascimento());
        DateTime hoje = DateTime.now();
        anos = hoje.minusYears(nasc.getYear()).minusDays(nasc.getDayOfYear()).getYear();
        dias = 365 * (anos) + (hoje.getDayOfYear()-nasc.getDayOfYear());

        return  dias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    public String getDataNascimentoString()
    {
        String data;
        Date d = getDataNascimento();
        String dia,mes,ano;
        if(d.getDate() < 10)
        {
            dia = "0"+d.getDate();
        }else{dia = String.valueOf(d.getDate());}
        if((d.getMonth()+1) < 10)
        {
            mes = "0"+(d.getMonth()+1);
        }else{mes = String.valueOf(d.getMonth()+1);}

        ano = String.valueOf(d.getYear()+1900);

        data = dia+"/"+mes+"/"+ano;

        return data;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<VacinaTomada> getVacinasTomadas() {
        return vacinasTomadas;
    }

    public void setVacinasTomadas(ArrayList<VacinaTomada> vacinasTomadas) {
        this.vacinasTomadas = vacinasTomadas;
    }

    public String[] getNotificações() {
        return notificações;
    }

    public void setNotificações(String[] notificações) {
        this.notificações = notificações;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
