
public class No implements Definicoes {
    private int vInfo[];
    private No vLig[];
    private int tl;
    private No ant;
    private No prox;

    public No() { // Esse é um No Vazio
        vInfo = new int[N];
        vLig = new No[N + 1];
        tl = 0;
        ant = prox = null;
    }

    public No(int info) { // Será chamado quando for inserir em nossa árvore
        this();
        vInfo[0] = info;
        tl = 1;
        ant = prox = null;
    }

    public int procurarPosicao(int info) {
        // É uma busca sequêncial
        int pos = 0;
        while(pos < tl &&  vInfo[pos] <= info) {
            pos++;
        }
        return pos;
    }

    public void remanejar(int pos) {
        // Aqui também tem que remanejar ligações além de informações
        vLig[tl+1] = vLig[tl];
        for(int i = tl; i > pos; i--) {
            vInfo[i] = vInfo[i-1];
            vLig[i] = vLig[i-1];
        }
    }

    // Verificar se esse remaneja é desta forma
    public void remanejarEx(int pos) {
        for(int i=pos; i < tl-1; i++) {
            vInfo[i] = vInfo[i+1];
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

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}
