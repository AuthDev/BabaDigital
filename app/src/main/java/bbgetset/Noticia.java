package bbgetset;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Clinica on 21/03/2017.
 */

public class Noticia {

    private String titulo;
    private String conteudo;
    private String autor;
    private Date data;
    private String fonte;
    private String srcImagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getImagem() {
        return srcImagem;
    }

    public void setImagem(String srcImagem) {
        this.srcImagem = srcImagem;
    }
}
