package com.company;

public class Item {

    private int codigo;
    private String nome;
    private String tipo;
    private double preco;
    private boolean porPeso;

    public Item(int codigo, String nome, String tipo, double preco, boolean porPeso) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.porPeso = porPeso;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }
}
