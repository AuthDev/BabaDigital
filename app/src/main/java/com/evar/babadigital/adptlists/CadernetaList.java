package com.evar.babadigital.adptlists;

/**
 * Created by Clinica on 24/01/2017.
 */

public class CadernetaList {
    private int imagem;
    private String nome;
    private String idade;
    private String notificacoes;

    public CadernetaList()
    {

    }
    public CadernetaList(int imagem, String nome, String idade)
    {
        this.imagem = imagem;
        this.nome = nome;
        this.idade = idade;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }


    public int getImagem() {
        return imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getIdade() {
        return idade;
    }

}
