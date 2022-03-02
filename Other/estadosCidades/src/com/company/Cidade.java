package com.company;

public class Cidade {
    private NoCidade inicio;

    public Cidade() {
        this.inicio = null;
    }

    public boolean inserirCidade(String nomeCidade) {
        NoCidade novaCidade = new NoCidade(nomeCidade, null);
        Boolean inserido = false;

        if(inicio == null) {
            inicio = novaCidade;
            inserido = true;
        } else {
            if(nomeCidade.compareToIgnoreCase(inicio.getNomeCidade()) < 0) {
                novaCidade.setProx(inicio);
                inicio = novaCidade;
                inserido = true;
            } else {
                NoCidade ant, atual;
                ant = inicio;
                atual = ant.getProx();
                while(atual != null && nomeCidade.compareToIgnoreCase(atual.getNomeCidade()) > 0) {
                    ant = atual;
                    atual =ant.getProx();
                }
                if(! nomeCidade.equalsIgnoreCase(ant.getNomeCidade())) {
                    if (atual == null) {
                        ant.setProx(novaCidade);
                    } else {
                        ant.setProx(novaCidade);
                        novaCidade.setProx(atual);
                    }
                    inserido = true;
                }
            }
        }
        return inserido;
    }

    public boolean buscaCidade(String nomeCidade) {
        NoCidade aux = inicio;
        while(aux != null && nomeCidade.compareToIgnoreCase(aux.getNomeCidade()) > 0) {
            aux = aux.getProx();
        }
        if(aux != null && nomeCidade.equalsIgnoreCase(aux.getNomeCidade())) {
            return true;
        }
        return false;
    }

    public void exibirCidades() {
        NoCidade aux = inicio;
        if(aux == null) {
            System.out.println("\t - Nenhum Cidade Cadastrada");
        } else {
            while(aux != null) {
                System.out.println("\t - " + aux.getNomeCidade());
                aux = aux.getProx();
            }
        }

    }

}
