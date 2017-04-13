package com.evar.babadigital.adptlists;

/**
 * Created by Clinica on 23/01/2017.
 */

public class InformacoesList {
    private String titulo;
    private String conteudo;

    public InformacoesList()
    {

    }
    public InformacoesList(String titulo, String conteudo)
    {
        this.titulo = titulo;
        this.conteudo = conteudo;

    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

}
