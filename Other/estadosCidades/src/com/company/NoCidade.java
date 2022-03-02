package com.company;

public class NoCidade {
    private String nomeCidade;
    private NoCidade prox;

    public NoCidade(String nomeCidade, NoCidade prox) {
        this.nomeCidade = nomeCidade;
        this.prox = prox;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public NoCidade getProx() {
        return prox;
    }

    public void setProx(NoCidade prox) {
        this.prox = prox;
    }
}
