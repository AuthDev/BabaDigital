package com.evar.babadigital.adptlists;

/**
 * Created by Clinica on 23/01/2017.
 */

public class NoticiasList {

    private String imagem;
    private String titulo;
    private String conteudo;
    private String autor;
    private String fonte;

    public NoticiasList()
    {

    }
    public NoticiasList(String imagem, String titulo, String conteudo)
    {
        this.imagem = imagem;
        this.titulo = titulo;
        this.conteudo = conteudo;

    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setNome(String titulo) {
        this.titulo = titulo;
    }

    public void setIdade(String conteudo) {
        this.conteudo = conteudo;
    }


    public String getImagem() {
        return imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
}
