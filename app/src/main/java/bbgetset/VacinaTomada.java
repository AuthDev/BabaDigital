package bbgetset;

import java.util.Date;

/**
 * Created by evar on 23/03/17.
 */

public class VacinaTomada {

    private Vacina vacina;
    private Date data;
    private Date validade;
    private String localTomado;
    private int criancaCod;
    private int vacinaCod;

    public VacinaTomada()
    {
        vacina = new Vacina();
        data = new Date();
        localTomado = "nunhum";
        validade = new Date();
    }

    public VacinaTomada(Vacina vacina,Date data,Date validade,String localTomado)
    {
        this.setVacina(vacina);
        this.setData(data);
        this.setValidade(validade);
        this.setLocalTomado(localTomado);
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
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

    public int getVacinaCod() {
        return vacinaCod;
    }

    public void setVacinaCod(int vacinaCod) {
        this.vacinaCod = vacinaCod;
    }
}
