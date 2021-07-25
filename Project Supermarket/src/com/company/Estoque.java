package com.company;

import java.util.LinkedList;
import java.util.List;

public class Estoque {

    private List<Item> armagem;

    public Estoque(List<Item> linkedList) {
        this.armagem = new LinkedList<>();
    }

    public boolean addItem(int codigo, String nome, String tipo, double preco, boolean porPeso){
        if(buscaItem(codigo) != null && buscaItem(nome) != null) {
            if(preco > 0) {
                this.armagem.add(new Item(codigo, nome, tipo, preco, porPeso));
                return true;
            }
        }
        return false;
    }

    private Item buscaItem(int codigo) {
        for(int i=0; i < this.armagem.size(); i++) {
            if(this.armagem.get(i).getCodigo() == codigo) {
                return this.armagem.get(i);
            }
        }
        return null;
    }

    private Item buscaItem(String nome) {
        for(Item item : this.armagem) {
            if(item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }
}
