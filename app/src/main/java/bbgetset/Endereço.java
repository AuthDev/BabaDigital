package bbgetset;

/**
 * Created by Clinica on 21/03/2017.
 */

public class Endereço {

    private String cidade;
    private String estado;
    private String rua;
    private String bairro;
    private int numeroCasa;
    private int cepNumber;
    private String cepString;
    private String país;
    private String complemento;

    public Endereço()
    {

    }

    public Endereço(String cidade, String estado, String rua, String bairro, int numeroCasa, int cepNumber, String pais, String complemento)
    {
        this.cidade = cidade;
        this.estado = estado;
        this.rua = rua;
        this.bairro = bairro;
        this.numeroCasa = numeroCasa;
        this.país = pais;
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public int getCepNumber() {
        return cepNumber;
    }

    public void setCepNumber(int cepNumber) {
        this.cepNumber = cepNumber;
    }

    public String getCepString() {
        return cepString;
    }

    public void setCepString(String cepString) {
        this.cepString = cepString;
    }

    public String getPaís() {
        return país;
    }

    public void setPaís(String país) {
        this.país = país;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
