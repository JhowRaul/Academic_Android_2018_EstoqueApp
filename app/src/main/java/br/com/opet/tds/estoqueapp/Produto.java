package br.com.opet.tds.estoqueapp;

import java.util.Date;

/**
 * Created by Diego on 27/03/2018.
 */

public class Produto {
    private String nome;
    private Date vencimento;

    public Produto() {
    }

    public Produto(String nome, Date vencimento) {
        this.nome = nome;
        this.vencimento = vencimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }
}
