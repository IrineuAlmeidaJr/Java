package com.company;

public class BTree implements Definicoes {
    private No raiz;

    public BTree() {
        raiz = null;
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
        // *** Desa forma se a raiz já for folha ele já retorna,
        // porqiue a ligação vai ser nula, assim que eu sei
        // quem é folha.

        /*
            Para inserir eu preciso localizar a folha, por isso
            tenho que navegar até a folha.
            Como eu sei que é folha? -> quando o primeiro é nulo,
            porque não temos árvore B com ligação nulo tem ligação depois
            isso é na arvore N-Área.
         */
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
        /*
            Localiza pai é que nem localizar pai de uma árvore binária.
            O pai da raiz é ele mesmo
         */
    }

    void split(No folha, No pai) {
        int fim, pos;
        No cx1 = new No();
        No cx2 = new No();
        // Copia para caixinha 1
        for(int i=0; i < M; i++) {
            cx1.setVInfo(i, folha.getVInfo(i));
            cx1.setVLig(i, folha.getVLig(i));
            cx1.setVPos(i, folha.getVPos(i));
        }
        // Quando termina tem que fazer a ligação do ultimo,
        // porque se não ficaria com duas informações e duas
        // ligações, não esquecer, sempre o número de ligações é
        // o número de informação mais um.
        cx1.setVLig(M, folha.getVLig(M));
        cx1.setTl(M);
        // Copia para caixinha 2
        fim = M*2+1;
        for(int i=M+1; i < fim; i++) {
            cx2.setVInfo(i - (M+1), folha.getVInfo(i));
            cx2.setVLig(i - (M+1), folha.getVLig(i));
            cx2.setVPos(i - (M+1), folha.getVPos(i));
        }
        cx2.setVLig(M, folha.getVLig(fim));
        cx2.setTl(M);
        // Agora faz o split
        if(folha == pai)
        {
            folha.setVInfo(0, folha.getVInfo(M));
            folha.setVPos(0, folha.getVPos(M));
            folha.setVLig(0, cx1);
            folha.setVLig(1, cx2);
            folha.setTl(1);
        } else {
            // Sempre para inserir :
            // - buscar posição;
            // - remaneja;
            // - inserir; *só aqui insere, depois de remanejar8
            pos = pai.procurarPosicao(folha.getVInfo(M));
            pai.remanejar(pos);
            pai.setVInfo(pos, folha.getVInfo(M));
            pai.setVPos(pos, folha.getVPos(M));
            pai.setVLig(pos, cx1);
            pai.setVLig(pos+1, cx2);
            pai.setTl(pai.getTl()+1);
            if(pai.getTl() > 2*M) { //OU pai.getTl() == 2*M+1
                folha = pai;
                pai = localizarPai(folha, folha.getVInfo(pos));
                // Não necessáriamente tenho que passa o pos, pode ser
                // qualquer outra posição, porque pode ser 0, qualquer,
                // posição que exista no pai.
                split(folha, pai);
            }
        }
        /*
            É o dividir e vai dar mais trabalho para fazermos, que é
            o dividir.
            Split vou fazendo de dois em dois.
         */
    }

    void inserir(int info, int posArq) {
        No folha, pai;
        int pos;
        if(raiz == null) {
            raiz = new No(info, posArq);
        } else {
            folha = navegarAteFolha(info);
            pos = folha.procurarPosicao(info);
            folha.remanejar(pos);
            folha.setVInfo(pos, info);
            folha.setVPos(pos, posArq);
            folha.setTl(folha.getTl() + 1);
            // 2 * M -> quer dizer que tenho elemento
            // por exemplo, se meu M é 2 o máximo de elementos
            // é 4, se tem 5 elementos então tem que dar split.
            if(folha.getTl() > 2 * M) {
                // Para passar o split tem que chamar o pai.
                pai = localizarPai(folha, info);
                split(folha, pai);
            }
        }
    }

     // -- Exlução
    public No localizarNo(int info) {
        boolean achou = false;
        int pos;
        No no = raiz;
        while(no != null && !achou) {
            pos = no.procurarPosicao(info);
            if(pos < no.getTl() && info == no.getVInfo(pos))  {
                achou = true;
            } else {
                no = no.getVLig(pos);
            }
        }
        return no;

    }

    public No localizarSubE(No no, int pos) {
        // Vai esquerda depois Direita (TL)
        no = no.getVLig(pos);
        while(no.getVLig(no.getTl()) != null) {
            no = no.getVLig(no.getTl());
        }
        return no;
    }

    public No localizarSubD(No no, int pos) {
        no = no.getVLig(pos+1);
        while (no.getVLig(0) != null) {
            no = no.getVLig(0);
        }
        return no;
    }

    public void exclusao(int info) {
        int pos;
        No subE, subD, folha;
        No no = localizarNo(info); // Vai retornar aonde está o nó
        // se achou vou vai excluir se não pode excluir
        if(no != null) {
            pos = no.procurarPosicao(info);
            if(no.getVLig(0) != null) { // não é folha -> só entra se não é folha ai sempre tem vLig esq e dir
                subE = localizarSubE(no, pos); // ***OBS: eu tava passando info é POS
                subD = localizarSubD(no, pos); // ***OBS: eu tava passando info é POS
                if(subE.getTl() > M || subD.getTl() == M) {
                    // pega da Direita se tiver substituto m+1 OU subD se for igual a
                    // M
                    no.setVInfo(pos, subE.getVInfo(subE.getTl() - 1));
                    no.setVPos(pos, subE.getVInfo(subE.getTl() - 1));

                    folha = subE;
                    pos = subE.getTl() - 1;

                } else {
                    no.setVInfo(pos, subD.getVInfo(0));
                    no.setVPos(pos, subD.getVInfo(0));
                    folha = subD;
                    pos = 0;
                }
            } else { // quando é folha
                folha = no;
            }

            // excluir folha
            folha.remanejarEx(pos);
            folha.setTl(folha.getTl() - 1);

            if(folha == raiz && folha.getTl() == 0) {
                raiz = null;
            } else if(folha != raiz && folha.getTl() < M){
                redistribui_concatena(folha);
            }
        }
    }

    public void redistribui_concatena(No folha) {
        // Temos achar pai da folha primeiro
        No pai = localizarPai(folha, folha.getVInfo(0));
        No irmaE, irmaD;
        int posP = pai.procurarPosicao(folha.getVInfo(0));
        // Saber se pega da irma da direita ou da esquerda
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

        if(irmaE != null && irmaE.getTl() > M) {
            // Pega irmã esquerda por que ela tem mais elemento ou tem algo e o da
            // esquerda
            folha.remanejar(0);
            folha.setVInfo(0, pai.getVInfo(posP-1));
            folha.setVPos(0, pai.getVInfo(posP-1));
            folha.setTl(folha.getTl() + 1);
            // posP - 1, porque peguei da Esquerda se fosse da direita é posP
            pai.setVInfo(posP - 1, irmaE.getVInfo(irmaE.getTl() - 1));
            pai.setVPos(posP - 1, irmaE.getVPos(irmaE.getTl() - 1));
            folha.setVLig(0, irmaE.getVLig(irmaE.getTl()));
            irmaE.setTl(irmaE.getTl() - 1);
        } else {
            if(irmaD != null && irmaD.getTl() > M) {
                // Pega o pai e coloca na folha, depois pega o subtituto que
                // estará na direita.
                folha.setVInfo(folha.getTl(), pai.getVInfo(posP));
                folha.setVPos(folha.getTl(), pai.getVPos(posP));
                folha.setTl(folha.getTl() + 1);
                pai.setVInfo(posP, irmaD.getVInfo(0));
                pai.setVPos(posP, irmaD.getVPos(0));
                // Tem que arrumar a ligação da folha, quando inserir e for excluir
                folha.setVLig(folha.getTl(), irmaD.getVLig(0));
                // Agora tem que arruma a irma da direita
                irmaD.remanejarEx(0); // Vou remaneja jogar vInfo na posição 0 para o final e diminuo o TL
                irmaD.setTl(irmaD.getTl() - 1);
            } else {
                // Concatenação
                // Fazer é troca de valores a concatencação, desenha para verificar, vou ter que ter um
                // for e copiar a ligações

                // Ou escolho da esquerda ou da Direita, agora tem que escolher, pois, verifico onde tem
                if(irmaE != null) {
                    irmaE.setVInfo(irmaE.getTl(), pai.getVInfo(posP-1));
                    irmaE.setVPos(irmaE.getTl(), pai.getVPos(posP-1));
                    irmaE.setTl(irmaE.getTl() + 1);
                    pai.remanejarEx(posP-1);
                    pai.setTl(pai.getTl()-1);
                    for(int i=0; i < folha.getTl(); i++) {
                        irmaE.setVInfo(irmaE.getTl(), folha.getVInfo(i));
                        irmaE.setVPos(irmaE.getTl(), folha.getVPos(i));
                        irmaE.setVLig(irmaE.getTl(), folha.getVLig(i));
                        irmaE.setTl(irmaE.getTl() + 1);
                    }
                    irmaE.setVLig(irmaE.getTl(), folha.getVLig(folha.getTl()));
                    pai.setVLig(posP-1, irmaE);
                } else { // Irimã da Direita
                    irmaD.remanejar(0);
                    irmaD.setVInfo(0, pai.getVInfo(posP));
                    irmaD.setVPos(0, pai.getVPos(posP));
                    irmaD.setTl(irmaD.getTl() + 1);
                    pai.remanejarEx(posP);
                    pai.setTl(pai.getTl() - 1);
                    irmaD.setVLig(0, folha.getVLig(folha.getTl()));

                    // Aqui estava -> for(int i=folha.getTl(); i >= 0; i--)
                    // não pode ser >= acho que tem que ser > tl
                    for(int i=folha.getTl(); i > 0; i--) {
                        irmaD.remanejar(0);
//                        irmaD.setVInfo(0, folha.getVInfo(i));
//                        irmaD.setVPos(0, folha.getVPos(i));
//                        irmaD.setVLig(0, folha.getVLig(i));
//                        irmaD.setTl(irmaD.getTl() + 1);
//                        ***OBS-> getVInfo(i), ia pegar lixo como ele começou em getTl
                        irmaD.setVInfo(0, folha.getVInfo(i-1));
                        irmaD.setVPos(0, folha.getVPos(i-1));
                        irmaD.setVLig(0, folha.getVLig(i-1));
                        irmaD.setTl(irmaD.getTl() + 1);
                    }
                }

                if(pai.getTl() == 0 ) {
                    if(irmaE != null) {
                        raiz = irmaE;
                    } else {
                        raiz = irmaD;
                    }
                } else {
                    if(pai != raiz && pai.getTl() < M) {
                      folha = pai;
                      redistribui_concatena(folha);
                    }
                }
            }
        }
    }

    public void in_ordem(No raiz) {
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
}
