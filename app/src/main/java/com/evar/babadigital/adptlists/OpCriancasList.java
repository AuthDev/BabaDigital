package com.evar.babadigital.adptlists;

/**
 * Created by Maxson Jordan on 23/01/2017.
 */

public class OpCriancasList {

    private int imagem;
    private String nome;
    private String idade;
    private String notificacoes;

    public OpCriancasList()
    {

    }
    public OpCriancasList(int imagem, String nome, String idade, String notificacoes)
    {
        this.imagem = imagem;
        this.nome = nome;
        this.idade = idade;
        this.notificacoes = notificacoes;
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

    public void setNotificacoes(String notificacoes) {
        this.notificacoes = notificacoes;
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

    public String getNotificacoes() {
        return notificacoes;
    }
}
