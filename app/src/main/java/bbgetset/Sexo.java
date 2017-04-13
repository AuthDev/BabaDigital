package bbgetset;

/**
 * Created by Clinica on 21/03/2017.
 */

public class Sexo  {

    public static final String MASCULINO = "Masculino";
    public static final String FEMININO = "Feminino";
    private String indefinido = "Indefinido";
    private String sexo;

    public Sexo()
    {
        sexo = indefinido;
    }
    public void setMasculino()
    {
        this.sexo = MASCULINO;
    }
    public void setFeminino()
    {
        this.sexo = FEMININO;
    }
    public String toString()
    {
        return  sexo;
    }

}

