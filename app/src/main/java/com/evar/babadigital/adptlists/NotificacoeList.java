package com.evar.babadigital.adptlists;

/**
 * Created by Clinica on 23/01/2017.
 */

public class NotificacoeList {

    private String nome;
    private String not1;
    private String not2;
    private String not3;

    public NotificacoeList()
    {

    }
    public NotificacoeList(String nome, String not1, String not2,String not3)
    {
        this.nome = nome;
        this.not1 = not1;
        this.not2 = not2;
        this.not2 = not3;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNot1(String not1) {
        this.not1 = not1;
    }

    public void setNot2(String not2) {
        this.not2 = not2;
    }

    public void setNot3(String not3) {
        this.not3 = not3;
    }


    public String getNome() {
        return nome;
    }

    public String getNot1() {
        return not1;
    }

    public String getNot2() {
        return not2;
    }

    public String getNot3() {
        return not3;
    }
}
