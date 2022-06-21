package com.company;

public class No implements Definicoes {
    private int vInfo[];
    private int vPos[];
    private No vLig[];
    private int tl;

    public No() { // Esse é um No Vazio
        vInfo = new int[M * 2 + 1];
        vPos = new int[M * 2 + 1];
        vLig = new No[M * 2 + 2];
        tl = 0;
    }

    public No(int info, int posArq) { // Será chamdo quando for inserir em nossa árvore
        this(); // chama o No - OO
        vInfo[0] = info;
        vPos[0] = posArq;
        tl = 1;
    }

    public int procurarPosicao(int info) {
        // É uma busca sequêncial
        int pos = 0;
        while(pos < tl &&  vInfo[pos] < info) {
            pos++;
        }
        return pos;
    }

    public void remanejar(int pos) {
        // Aqui também tem que remanejar ligações além de informações
        vLig[tl+1] = vLig[tl];
        for(int i = tl; i > pos; i--) {
            vInfo[i] = vInfo[i-1];
            vPos[i] = vPos[i-1];
            vLig[i] = vLig[i-1];
        }
    }

    // Verificar se esse remaneja é desta forma
    public void remanejarEx(int pos) {
        for(int i=pos; i < tl-1; i++) {
            vInfo[i] = vInfo[i+1];
            vPos[i] = vPos[i+1];
            vLig[i] = vLig[i+1];
        }
        vLig[tl-1] = vLig[tl];
    }

    public int getVInfo(int p) {
        return vInfo[p];
    }

    public void setVInfo(int p, int info) {
        vInfo[p] = info;
    }

    public int getVPos(int p) {
        return vPos[p];
    }

    public void setVPos(int p, int posArq) {
        vPos[p] = posArq;
    }

    public No getVLig(int p) {
        return vLig[p];
    }

    public void setVLig(int p, No lig) {
        vLig[p] = lig;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }
}
