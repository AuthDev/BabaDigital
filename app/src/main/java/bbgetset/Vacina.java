package bbgetset;

/**
 * Created by Clinica on 21/03/2017.
 */

public class Vacina {

    private String nome;
    private String informaçao;
    private VacinaIdade vacinaIdade;

    public Vacina()
    {
        nome = "sem Nome";
        informaçao = "sem informaçao";
        vacinaIdade = new VacinaIdade();
        vacinaIdade.add(0);

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

    public VacinaIdade getVacinaIdade() {
        return vacinaIdade;
    }

    public void setVacinaIdade(VacinaIdade vacinaIdade) {
        this.vacinaIdade = vacinaIdade;
    }
}
