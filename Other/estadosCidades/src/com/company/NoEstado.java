package com.company;

public class NoEstado {
    private String nomeEstado;
    private Cidade cidade;
    private NoEstado prox;

    public NoEstado(String nomeEstado, Cidade cidade, NoEstado prox) {
        this.nomeEstado = nomeEstado;
        this.cidade = cidade;
        this.prox = prox;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public NoEstado getProx() {
        return prox;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void setProx(NoEstado prox) {
        this.prox = prox;
    }
}
