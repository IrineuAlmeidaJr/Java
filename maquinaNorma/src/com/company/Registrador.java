package com.company;

public class Registrador {
    String nome;
    int sinal;
    int valor;


    public Registrador(String nome, int sinal, int valor) {
        this.nome = nome;
        this.sinal = sinal;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSinal() {
        return sinal;
    }

    public void setSinal(int sinal) {
        this.sinal = sinal;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
