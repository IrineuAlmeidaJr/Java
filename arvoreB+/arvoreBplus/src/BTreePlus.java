public class BTreePlus implements Definicoes {
    private No raiz;

    public BTreePlus() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public No navegarAteFolha(int info) {
        int pos;
        No aux;
        aux = raiz;
        while(aux.getVLig(0) != null) {
            pos = aux.procurarPosicao(info);
            aux = aux.getVLig(pos);
        }
        return aux;
    }

    No localizarPai(No folha, int info) {
        int pos;
        No ant, atual;
        ant = atual = raiz;
        while(atual != folha) {
            ant = atual;
            pos = ant.procurarPosicao(folha.getVInfo(0));
            atual = ant.getVLig(pos);
        }
        return ant;
    }

    void split(No folha, No pai) {
        int fim, pos, meio;
        boolean ehfolha = false;
        No cx1 = new No();
        No cx2 = new No();

        if(folha.getVLig(0) == null) { // É Folha
            ehfolha = true;
            meio = (int) Math.ceil((N-1)*1.0/2);
            // Copia para caixinha 1
            for(int i=0; i < meio; i++) {
                cx1.setVInfo(i, folha.getVInfo(i));
                cx1.setVLig(i, folha.getVLig(i));
            }
            cx1.setVLig(meio, folha.getVLig(meio));
            cx1.setTl(meio);

            // Copia para caixinha 2
            fim = N;
            for(int i=meio; i < fim; i++) {
                cx2.setVInfo(i - meio , folha.getVInfo(i));
                cx2.setVLig(i - meio, folha.getVLig(i));
            }
            cx2.setVLig(N, folha.getVLig(fim));
            cx2.setTl(N-meio);

            // Localiza IrmãEsq e IrmãDir
            No irmaE, irmaD;
            int posP = pai.procurarPosicao(folha.getVInfo(0));
            if(posP > 0) {
                irmaE = pai.getVLig(posP - 1);
            } else {
                irmaE = null;
            }
            if(posP < pai.getTl()) {
                irmaD =  pai.getVLig(posP + 1);
            } else {
                irmaD = null;
            }

            // Liga Caixas - Ant E Prox
            cx1.setProx(cx2);
            cx1.setAnt(irmaE);
            if(irmaE != null) { // Atualiza apontamento da IrmaE
                irmaE.setProx(cx1);
            }

            cx2.setAnt(cx1);
            cx2.setProx(irmaD);
            if(irmaD != null) { // Atualiza apontamento da IrmaD
                irmaD.setAnt(cx2);
            }

        } else { // Não é Folha
            // Nome da variável é meio, mas aqui pode ser que não pegue
            // o meio, pq ele pega levando e consideração a formula do Nó não folha
            meio = (int) (Math.ceil( (N * 1.0) / 2) - 1);
            // Copia para caixinha 1
            for (int i = 0; i < meio; i++) {
                cx1.setVInfo(i, folha.getVInfo(i));
                cx1.setVLig(i, folha.getVLig(i));
            }
            cx1.setVLig(meio, folha.getVLig(meio));
            cx1.setTl(meio);

            // Copia para caixinha 2
            for (int i = meio + 1; i < folha.getTl(); i++) {
                cx2.setVInfo(i - (meio+1), folha.getVInfo(i));
                cx2.setVLig(i - (meio+1), folha.getVLig(i));
            }
            cx2.setVLig(folha.getTl() - (meio+1), folha.getVLig(folha.getTl()));
            cx2.setTl(folha.getTl() - (meio+1));
        }

        // Agora faz o split
        if(folha == pai)
        {
            if (ehfolha)
                folha.setVInfo(0, cx2.getVInfo(0)); // Pega o Pos 0 da CX2 se é Folha
            else
            {
                folha.setVInfo(0, folha.getVInfo(meio)); // Pega o meio se não é Folha, isso porque
                // aqui quando não é folha as caixinhas não tem o meio como valor
            }

            folha.setVLig(0, cx1);
            folha.setVLig(1, cx2);
            folha.setTl(1);
        } else {
            // Sempre para inserir :
            // - buscar posição;
            // - remaneja;
            // - inserir; *só aqui insere, depois de remanejar
            pos = pai.procurarPosicao(folha.getVInfo(meio)); // Meio ver se pega as DUAS situações
            pai.remanejar(pos);
            pai.setVInfo(pos, folha.getVInfo(meio));
            pai.setVLig(pos, cx1);
            pai.setVLig(pos+1, cx2);
            pai.setTl(pai.getTl()+1);
            if(pai.getTl() > N - 1) { // OU pai.getTl() == N
                folha = pai;
                pai = localizarPai(folha, folha.getVInfo(pos));
                // Não necessáriamente tenho que passa o pos, pode ser
                // qualquer outra posição, porque pode ser 0, qualquer,
                // posição que exista no pai.
                split(folha, pai);
            }
        }
    }

    void inserir(int info) {
        No folha, pai;
        int pos;
        if(raiz == null) {
            raiz = new No(info);
        } else {
            folha = navegarAteFolha(info);
            pos = folha.procurarPosicao(info);
            folha.remanejar(pos);
            folha.setVInfo(pos, info);
            folha.setTl(folha.getTl() + 1);

            if(folha.getTl() > N - 1) {
                // Para passar o split tem que chamar o pai.
                pai = localizarPai(folha, info);
                split(folha, pai); // só no split
            }
        }
    }

    private void in_ordem(No raiz) {
        if(raiz != null) {
            for(int i=0; i < raiz.getTl(); i++) {
                in_ordem(raiz.getVLig(i));
                System.out.println(raiz.getVInfo(i));
            }
            in_ordem(raiz.getVLig(raiz.getTl()));
        }
    }

    public void in_ordem() {
        in_ordem(raiz);
    }

    public void exibir() {
        No aux = raiz;
        boolean exibir = true;
        if(aux != null) {
            while(aux.getVLig(0) != null) {
                aux = aux.getVLig(0);
            }

            while(aux != null) {
                for(int i=0; i<aux.getTl(); i++) {
                    System.out.println(aux.getVInfo(i));
                }
                aux = aux.getProx();
            }
        }
    }

}
