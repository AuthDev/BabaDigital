package bbgetset;

/**
 * Created by maxson on 16/04/17.
 */

public class Notificacoes {

    private String descricao;
    private int cod;
    private Criança criança;

    public Notificacoes(Criança criança, String descricao)
    {
        this.descricao = descricao;
        this.criança = criança;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Criança getCriança() {
        return criança;
    }

    public void setCriança(Criança criança) {
        this.criança = criança;
    }
}
