package com.company;

public class No implements Definicoes {
    private int vInfo[];
    private No vLig[];
    private int tl; // Esse TL é interno do nó

    public No(int info) {
        vInfo = new int[N-1];
        vLig = new No[N];
        vInfo[0] = info;
        tl = 1;
    }

    public int getvInfo(int p) {
        return vInfo[p];
    }

    public void setvInfo(int p, int info) {
       vInfo[p] = info;
    }

    public No getvLig(int p) {
        return vLig[p];
    }

    public void setvLig(int p, No lig) {
        vLig[p] = lig;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public int buscarPos(int info) {
        int pos = 0;
        while(pos < tl && info > vInfo[pos]) {
            pos++;
        }
        return pos;
    }

    public void reaneja(int pos) {
        for(int i = tl; i > pos; i--) {
            vInfo[i] = vInfo[i-1];
        }
    }
}
