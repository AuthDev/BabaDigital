package bbgetset;

import android.util.Log;
import android.widget.DatePicker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;


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
            int mesNascimento = dataNascimento.getMonth();
            int diaNascimento = dataNascimento.getDay();

            Date date = new Date(System.currentTimeMillis());
            int anoAtual = date.getYear()+1900;
            int mesAtual = date.getMonth();
            int diaAtual = date.getDay();


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
                    idade = old + " meses de idade";
                }else
                    {
                        if(diaAtual > diaNascimento)
                        {
                            old = diaAtual - diaNascimento;
                            idade = old + " dias";
                        }
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
            int mesNascimento = dataNascimento.getMonth();
            int diaNascimento = dataNascimento.getDay();

            Date date = new Date(System.currentTimeMillis());
            int anoAtual = date.getYear()+1900;
            int mesAtual = date.getMonth();
            int diaAtual = date.getDay();


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
            old = 0;
            int anoNascimento = dataNascimento.getYear() +1900;
            int mesNascimento = dataNascimento.getMonth();
            int diaNascimento = dataNascimento.getDay();

            Date date = new Date(System.currentTimeMillis());
            int anoAtual = date.getYear()+1900;
            int mesAtual = date.getMonth();
            int diaAtual = date.getDay();


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
        if(d.getDay() < 10)
        {
            dia = "0"+d.getDay();
        }else{dia = String.valueOf(d.getDay());}
        if(d.getMonth() < 10)
        {
            mes = "0"+d.getDay();
        }else{mes = String.valueOf(d.getMonth());}

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
