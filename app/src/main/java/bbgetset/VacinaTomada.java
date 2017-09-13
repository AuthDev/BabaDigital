package bbgetset;

import java.util.Date;

/**
 * Created by evar on 23/03/17.
 */

public class VacinaTomada extends Vacina {

    private Date data;
    private Date validade;
    private String localTomado;
    private int criancaCod;
    private int dose;
    private boolean isTomada;
    private int vacinaTomadaCod;
    private boolean personalizada;

    public VacinaTomada()
    {
        data = new Date();
        localTomado = "nenhum";
        validade = new Date();
    }
    public void setVacina(Vacina vacina)
    {
        setNome(vacina.getNome());
        setDescricao(vacina.getDescricao());
        setDoses(vacina.getDoses());
        setIdade(vacina.getDias());
        setParticular(vacina.isParticular());
        setPublica(vacina.isPublica());
        setIntervalo(vacina.getIntervalo());
        setCod(vacina.getCod());

    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getLocalTomado() {
        return localTomado;
    }

    public void setLocalTomado(String localTomado) {
        this.localTomado = localTomado;
    }

    public int getCriancaCod() {
        return criancaCod;
    }

    public void setCriancaCod(int criancaCod) {
        this.criancaCod = criancaCod;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public boolean isTomada() {
        return isTomada;
    }

    public void setTomada(boolean tomada) {
        isTomada = tomada;
    }

    public int getVacinaTomadaCod() {
        return vacinaTomadaCod;
    }

    public void setVacinaTomadaCod(int vacinaTomadaCod) {
        this.vacinaTomadaCod = vacinaTomadaCod;
    }

    public boolean isPersonalizada() {
        return personalizada;
    }

    public void setPersonalizada(boolean personalizada) {
        this.personalizada = personalizada;
    }
    public String dataString()
    {
        String data ="";
        Date date = getData();
        if(date.getDate() < 10)
        {
            data+="0"+date.getDate();
        }else
        {
            data+= date.getDate();
        }
        if(date.getMonth() < 10)
        {
            data+="/0"+date.getMonth()+1;
        }else
        {
            data+="/"+date.getMonth()+1;
        }
        data+="/"+(date.getYear()+1900);
        return  data;

    }
}
