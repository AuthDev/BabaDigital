package bbgetset;


import org.joda.time.DateTime;


/**
 * Created by Clinica on 21/03/2017.
 */

public class Vacina {

    private int cod;
    private int dias = 0;
    private String nome;
    private int doses;
    private String descricao;
    private boolean publica;
    private boolean particular;
    private int intervalo;;
    private Idade idade;

    public Vacina()
    {

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getDias() {

        return dias;
    }

    public void setIdade(int idade) {


        this.dias = idade;
    }

    public int getDoses() {
        return doses;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean isPublica) {
        this.publica = isPublica;
    }

    public boolean isParticular() {
        return particular;
    }

    public void setParticular(boolean isParticular) {
        this.particular = isParticular;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public String getRede() {

        String retorno= "vazio";

        if(publica && particular)
        {
            retorno = "Publica/Particular";
        }else
        {
            if(particular)
            {
                retorno = "Particular";
            }
            if(publica)
            {
                retorno = "Publica";
            }
        }
        return retorno;
    }

    public void setRede(boolean isPublica,boolean isParticular) {
        setPublica(isPublica);
        setParticular(isParticular);
    }
    public String getIdadeString()
    {
        int anos;
        int meses;
        int dias;
        String idade  = "";

        DateTime hoje = DateTime.now();
        DateTime time = new DateTime();
        time = time.plusDays(getDias());


        anos = time.getYear() - hoje.getYear();
        if(anos > 0)
        {
            if(anos == 1)
            {
                idade = anos+" ano ";
            }else
                {
                    idade = anos+" anos ";
                }

            meses = time.getMonthOfYear() - hoje.getMonthOfYear();
            if( meses > 0)
            {
                if(meses == 1)
                    idade += "e "+meses+" mês";
                else
                    idade += "e "+meses+" meses";
            }
        }
        else
        {
            meses = time.getMonthOfYear() - hoje.getMonthOfYear();
            if(meses > 0 && meses < 12)
            {
                idade += meses+" meses";

            }
            else
            {
                dias = time.getDayOfMonth() - hoje.getDayOfMonth();
                if(dias > 1)
                {
                    idade += dias+" dias";
                }
                else
                {

                    if(dias == 0)
                    {
                        idade = "criança recém-nascida";
                    }else
                        {
                            idade += dias+ " dia";
                        }

                }
            }
        }
        return idade;
    }
}
