package com.company;

public class NArea implements Definicoes {
    private No raiz;

    public NArea() {
        this.raiz = null;
    }

    public void inserir(int info) {
        int pos;
        boolean flag = false;
        No aux = raiz;

        if(raiz == null) {
            raiz = new No(info);
        } else {
            while(!flag) {
                pos = aux.buscarPos(info);
                if(aux.getTl() < N - 1) {  // É N - 1 porque meu N pode ser outro valor
                   aux.reaneja(pos); // Remaneja, copia o de traz para frente
                   aux.setvInfo(pos, info); // Agora insere na posição correta
                   aux.setTl(aux.getTl() + 1);
                   flag = true;
                } else {
                    // Aqui agora vou andar para sabe em que caixa eu irei colocar
                    if(aux.getvLig(pos) == null) {
                        aux.setvLig(pos, new No(info));
                        flag = true;
                    }
                    else {
                        aux = aux.getvLig(pos);
                    }
                }
            }
        }
    }

    public void in_ordem(No raiz) {
        if(raiz != null) {
            for(int i=0; i < raiz.getTl(); i++) {
                in_ordem(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i));
            }
            in_ordem(raiz.getvLig(raiz.getTl()));
        }
    }

    public void in_ordem() {
        in_ordem(raiz);
    }
}
