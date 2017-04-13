package bbgetset;

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

    private int idade = 0;
    private String nome;
    private Date dataNascimento;
    private ArrayList<VacinaTomada> vacinasTomadas;
    private String[] notificações;
    private Sexo sexo;

    public Criança()
    {
        vacinasTomadas = new ArrayList<VacinaTomada>();
        notificações = new String[]{"nenhuma"};
        sexo = new Sexo();
        sexo.setMasculino();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

}
