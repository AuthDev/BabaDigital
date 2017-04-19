package bbgetset;

/**
 * Created by Clinica on 21/03/2017.
 */

public class Vacina {

    private String nome;
    private String informaçao;
    private String vacinaIdade;;

    public Vacina()
    {
        nome = "Sem Nome";
        informaçao = "sem informaçao";
        vacinaIdade = " ";

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInformaçao() {
        return informaçao;
    }

    public void setInformaçao(String informaçao) {
        this.informaçao = informaçao;
    }

    public String getVacinaIdade() {
        return this.vacinaIdade;
    }

    public void setVacinaIdade(String vacinaIdade)
    {
        this.vacinaIdade = vacinaIdade;
    }
}
