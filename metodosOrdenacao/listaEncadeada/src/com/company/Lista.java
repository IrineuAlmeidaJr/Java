package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Lista {
    private No inicio;
    private No fim;

    public Lista() {
        inicio = fim = null;
    }

    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    public No getFim() {
        return fim;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }

    public void inserirNoInicio(int info) {
        No novoNo = new No(null, null, info);
        /*
            Poderia no constructor já passar o proximo ???
         */
        // Basta pergunta uma vez né? não precisa
        // inicio == null && fim == null
        if(inicio == null) { // Vazio
            inicio = fim = novoNo;
        } else { // Mais de um elemento
            novoNo.setProx(inicio);
            inicio.setAnt(novoNo);
            inicio = novoNo;
        }
    }

    public void inserirNoFinal(int info) {
        No novoNo = new No(null, null, info);
        if(inicio == null) {
            inicio = fim = novoNo;
        } else {
            fim.setProx(novoNo);
            novoNo.setAnt(fim);
            fim = novoNo;
        }
    }

    public void carregarValores() {
        Random random = new Random();
        inicio = fim = null;
        for(int i=0; i < 16; i++){
            inserirNoInicio(random.nextInt(10*3));
        }
    }

    private int buscaMaior() {
        int maior;
        No i = inicio;
        maior = inicio.getInfo();
        i = i.getProx();
        while(i != fim.getProx()) { //fim.getProx()
            if(i.getInfo() > maior){
                maior = i.getInfo();
            }
            i = i.getProx();
        }
        return maior;
    }

    public void exibir() {
        No aux = inicio;
        while(aux != null) {
            System.out.print(aux.getInfo() + " - ");
            aux = aux.getProx();
        }
    }

    // MÉTODOS DE ORDENAÇÃO

    // - Inserção Direta
    public void insercaoDireta() {
        int aux;
        No i, pos;
        i = inicio.getProx();
        while(i != null) {
            aux = i.getInfo();
            pos = i;
            while(pos != inicio && aux < pos.getAnt().getInfo()) {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(aux);
            i = i.getProx();
        }
    }

    // - Inserção Binária
    private No retornaCaixa(No caixa, int deslocamento) {
        for(int i=0; i < deslocamento; i++) {
            caixa = caixa.getProx();
        }
        return caixa;
    }

    private No buscaBinaria(int chave, int TL, No valor) {
        int ini = 0, fim = TL-1, meio = TL/2;
        No pInicio = inicio;
        No pFim = valor;
        No pMeio = retornaCaixa(pInicio, meio);
        while(ini < fim && chave != pMeio.getInfo()) {
            if(chave < pMeio.getInfo()) {
                pFim = pMeio.getAnt();
                fim = meio-1;
            } else {
                pInicio = pMeio.getProx();
                ini = meio+1;
            }
            meio = (ini+fim) / 2;
            pMeio = retornaCaixa(pInicio, meio - ini);
            // *** Deslocamento é do início, por isso meio - inicio
        }

        if(chave > pMeio.getInfo()) {
            return pMeio.getProx();
        } else {
            return pMeio;
        }
    }

    public void insersaoBinaria() {
        int i=1, auxInfo;
        No aux = inicio.getProx();
        No temp, pos;
        while(aux != null) {
            pos = buscaBinaria(aux.getInfo(), i, aux);
            auxInfo = aux.getInfo(); // Guarda Valor Inteiro
            temp = aux;
            while(temp != pos) {
                temp.setInfo(temp.getAnt().getInfo());
                temp = temp.getAnt();
            }
            pos.setInfo(auxInfo);
            aux = aux.getProx();
            i++;
        }
    }

    // - Seleção Direta
    public void selecaoDireta() {
        int menor;
        No i, j, posMenor;
        i = inicio;
        while(i.getProx() != null) {
            menor = i.getInfo(); /// Achou não pega primeira posição testar
            posMenor = i;
            j = i.getProx();
            while(j != null) {
                if(j.getInfo() < menor) {
                    menor = j.getInfo();
                    posMenor = j;
                }
                j = j.getProx();
            }
            posMenor.setInfo(i.getInfo());
            i.setInfo(menor);
            i = i.getProx();
        }
    }

    // - Bolha
    public void bolha() {
        int aux;
        No i;
        No TL = fim;
        while(TL.getAnt() != null) {
            for(i=inicio; i != TL; i=i.getProx()) {
                if(i.getInfo() > i.getProx().getInfo()) {
                    aux = i.getInfo();
                    i.setInfo(i.getProx().getInfo());
                    i.getProx().setInfo(aux);
                }
            }
            TL = TL.getAnt();
        }
    }

    // - Shake
    public void shake() {
        int aux;
        No Inicio, Fim, i;
        Inicio = inicio;
        Fim = fim;
        boolean continua = true;
        // Ele avança um endereço e passa, ai pega Null Point,
        // deveria ser enquanto é menor.
        while(continua) { // (Inicio != Fim)
            for(i = Inicio; i != Fim; i = i.getProx()) {
                if(i.getInfo() > i.getProx().getInfo()) {
                    aux = i.getInfo();
                    i.setInfo(i.getProx().getInfo());
                    i.getProx().setInfo(aux);
                }
            }
            Fim = Fim.getAnt();
            if(Inicio == Fim)
                continua = false;
            for(i = Fim; i != Inicio; i = i.getAnt()) {
                if(i.getInfo() < i.getAnt().getInfo()) {
                    aux = i.getInfo();
                    i.setInfo(i.getAnt().getInfo());
                    i.getAnt().setInfo(aux);
                }
            }
            Inicio = Inicio.getProx();
            if(Inicio == Fim)
                continua = false;
            // Tive colocar o código abaixo ou teria contar como TL?,
            // Pq ele avançava
        }
    }

    // - Heap
    private int contaElem(){
        int tam = 0;
        No caixa = inicio;
        while(caixa != null) {
            tam++;
            caixa = caixa.getProx();
        }
        return tam;
    }

    public void heap() {
        int TL, pai, filhoE, filhoD, aux;
        No NoPai, NoFilhoE, NoFilhoD, maiorF;
        No Fim = fim;
        TL = contaElem();
        while(TL > 1){
            pai = TL/2 - 1;
            while(pai >= 0) {
                NoPai = retornaCaixa(inicio, pai);
                filhoE = 2*pai + 1;
                filhoD = filhoE + 1;
                if(filhoD < TL) {
                    NoFilhoE = retornaCaixa(inicio, filhoE);
                    NoFilhoD = retornaCaixa(inicio, filhoD);
                    if(NoFilhoE.getInfo() > NoFilhoD.getInfo()) {
                        maiorF = NoFilhoE;
                    } else {
                        maiorF = NoFilhoD;
                    }
                } else {
                    maiorF = NoFilhoE = retornaCaixa(inicio, filhoE);
                }
                if(maiorF.getInfo() > NoPai.getInfo()) {
                    aux = maiorF.getInfo();
                    maiorF.setInfo(NoPai.getInfo());
                    NoPai.setInfo(aux);
                }
                pai--;
            }
            // Terminar final
            aux = inicio.getInfo();
            inicio.setInfo(Fim.getInfo());
            Fim.setInfo(aux);
            Fim = Fim.getAnt();
            TL--;
        }
    }

    // - Shell
    public void shell() {
        int dist = 4, aux, k;
        int TL = contaElem();
        No temp1, temp2;
        while(dist > 0) {
            for(int i=0; i<dist; i++) {
                for(int j=0; j+dist < TL; j++) {
                    temp1 = retornaCaixa(inicio, j);
                    temp2 = retornaCaixa(inicio, j+dist);
                    if(temp1.getInfo() > temp2.getInfo()) {
                        aux = temp1.getInfo();
                        temp1.setInfo(temp2.getInfo());
                        temp2.setInfo(aux);

                        k = j;
                        temp1 = retornaCaixa(inicio, k);
                        temp2 = retornaCaixa(inicio, k-dist);
                        while(k-dist >= 0 && temp1.getInfo() < temp2.getInfo()) {
                            aux = temp1.getInfo();
                            temp1.setInfo(temp2.getInfo());
                            temp2.setInfo(aux);

                            k--;
                            temp1 = retornaCaixa(inicio, k);
                            temp2 = retornaCaixa(inicio, k-dist);
                        }
                    }
                }
            }
            dist /= 2;
        }
    }

    // - Quick Sem Pivo
    private void quickSemPivo(int ini, int fim) {
        int i = ini, j = fim, aux;
        No elemI = retornaCaixa(inicio, i);
        No elemJ= retornaCaixa(inicio, j);
        while(i < j) {
            while(i<j && elemI.getInfo() <= elemJ.getInfo()) {
                elemI = elemI.getProx();
                i++;
            }
            if(i < j) {
                aux = elemI.getInfo();
                elemI.setInfo(elemJ.getInfo());
                elemJ.setInfo(aux);
            }

            while (i < j && elemI.getInfo() <= elemJ.getInfo()) {
                elemJ = elemJ.getAnt();
                j--;
            }
            if(i < j) {
                aux = elemI.getInfo();
                elemI.setInfo(elemJ.getInfo());
                elemJ.setInfo(aux);
            }
        }
        if(ini < i-1) {
            quickSemPivo(ini, i-1);
        }
        if(j+1 < fim) {
            quickSemPivo(j+1, fim);
        }
    }

    public void quickSemPivo() {
        int TL = contaElem();
        quickSemPivo(0, TL - 1);

    }

    // -> QuickSP Iterativo
    public void quickSemPivoIterativo(){
        Stack<Integer> pilha = new Stack<Integer>();
        int ini = 0, fim = contaElem() - 1;
        int i, j, aux;
        No elemI, elemJ;

        pilha.push(ini);
        pilha.push(fim);
        while(!pilha.isEmpty()) {
            fim = pilha.pop();
            ini = pilha.pop();

            i = ini;
            j = fim;
            elemI = retornaCaixa(inicio, i);
            elemJ= retornaCaixa(inicio, j);
            while(i < j) {
                while(i<j && elemI.getInfo() <= elemJ.getInfo()) {
                    elemI = elemI.getProx();
                    i++;
                }
                if(i < j) {
                    aux = elemI.getInfo();
                    elemI.setInfo(elemJ.getInfo());
                    elemJ.setInfo(aux);
                }
                while (i < j && elemI.getInfo() <= elemJ.getInfo()) {
                    elemJ = elemJ.getAnt();
                    j--;
                }
                if(i < j) {
                    aux = elemI.getInfo();
                    elemI.setInfo(elemJ.getInfo());
                    elemJ.setInfo(aux);
                }
            }

            if (ini < i-1) {
                pilha.push(ini);
                pilha.push(i-1);
            }
            if (j+1 < fim) {
                pilha.push(j+1);
                pilha.push(fim);
            }
        }
    }

    // - Quick Com Pivo
    public void quickComPivo(int ini, int fim) {
        int i = ini, j = fim, pivo, aux;
//        No elemPivo = retornaCaixa(inicio, (i + j) / 2);
//        pivo = elemPivo.getInfo();
        pivo = retornaCaixa(inicio, (i + j) / 2 ).getInfo();
        No elemI = retornaCaixa(inicio, i);
        No elemJ = retornaCaixa(inicio, j);
        while(i < j) {
            // *** Lembrar: tem que colocar o pivo em uma variável,
            // pois, se pegar ele como elemento pode ser que mude
            // ai estava dando problema. Pivo é um elemento, não,
            // a posição para pegar o elemento.
            while(elemI.getInfo() < pivo) {
                elemI = elemI.getProx();
                i++;
            }
            while(elemJ.getInfo() > pivo) {
                elemJ = elemJ.getAnt();
                j--;
            }
            if(i <= j) {
                aux = elemI.getInfo();
                elemI.setInfo(elemJ.getInfo());
                elemJ.setInfo(aux);

                elemI = elemI.getProx();
                i++;
                elemJ = elemJ.getAnt();
                j--;
            }
        }

        if(ini < j)
            quickComPivo(ini, j);
        if(i < fim)
            quickComPivo(i, fim);
    }

    public void quickComPivo() {
        int TL = contaElem();
        quickComPivo(0, TL - 1);
    }

    // - Merge 1
    public void particao(Lista part1, Lista part2, int TL) {
        int tam = TL/2;
        part1.inicio = part1.fim = null;
        part2.inicio = part2.fim = null;
        for(int i=0; i<tam; i++) {
            part1.inserirNoFinal(retornaCaixa(inicio, i).getInfo());
            part2.inserirNoFinal(retornaCaixa(inicio, i + tam).getInfo());
        }
    }

    public void fusao(No part1, No part2, int seq, int TL) {
        int i, j, k, T_seq;
        No aux = inicio;
        i = j = k = 0;
        T_seq = seq;
        while(k < TL) {
            while(i < seq && j < seq) {
                if(part1.getInfo() < part2.getInfo()) {
                    aux.setInfo(part1.getInfo());
                    aux = aux.getProx(); k++;
                    part1 = part1.getProx(); i++;
                } else {
                    aux.setInfo(part2.getInfo());
                    aux = aux.getProx(); k++;
                    part2 = part2.getProx(); j++;
                }
            }
            while(i < seq) {
                aux.setInfo(part1.getInfo());
                aux = aux.getProx(); k++;
                part1 = part1.getProx(); i++;
            }
            while(j < seq) {
                aux.setInfo(part2.getInfo());
                aux = aux.getProx(); k++;
                part2 = part2.getProx(); j++;
            }
            seq += T_seq;
        }
    }

    public void mergeSort() {
        int TL = contaElem();
        int seq = 1;
        Lista part1 = new Lista();
        Lista part2 = new Lista();
        while(seq < TL) {
            particao(part1, part2, TL);
            fusao(part1.inicio, part2.inicio, seq, TL);
            seq *= 2;
        }

    }

    // - Merge 2 --> Ver melhor depois
    private void inicializaLista(int i, int TL, Lista lista) {
        while(i<TL)
            lista.inserirNoFinal(retornaCaixa(inicio,i++).getInfo());
    }

    private void fusao2(int ini1, int fim1, int ini2, int fim2, Lista lista) {
        int i = ini1, j = ini2, k = 0;
        while(i<=fim1 && j<=fim2){
            if(retornaCaixa(inicio,i).getInfo() < retornaCaixa(inicio, j).getInfo())
                lista.retornaCaixa(lista.inicio, k++).setInfo(retornaCaixa(inicio, i++).getInfo());
            else
                lista.retornaCaixa(lista.inicio, k++).setInfo(retornaCaixa(inicio, j++).getInfo());
        }
        while(i<=fim1)
            lista.retornaCaixa(lista.inicio, k++).setInfo(retornaCaixa(inicio, i++).getInfo());
        while(j<=fim2)
            lista.retornaCaixa(lista.inicio, k++).setInfo(retornaCaixa(inicio, j++).getInfo());
        i=0;
        while (i < k)
            retornaCaixa(inicio, ini1 + i).setInfo(lista.retornaCaixa(lista.inicio, i++).getInfo());
    }

    private void mergeSort2(int esq, int dir, Lista lista) {
        if(esq<dir) {
            int meio = (esq+dir)/2;
            mergeSort2(esq, meio, lista);
            mergeSort2(meio+1, dir, lista);
            fusao2(esq, meio, meio+1, dir, lista);
        }
    }

    public void mergeSort2(){
        int TL = contaElem();
        Lista lista = new Lista();
        inicializaLista(0,TL,lista);
        mergeSort2(0,TL-1,lista);
    }

    // - Counting
    public void countingSort() {
        int maiorNum, posFreq, numAux, TL = contaElem();
        No noAtual = inicio;
        No noAux, noFreq;
        Lista freq = new Lista();
        Lista saida = new Lista();

        // Pega maior número dentro da lista
        maiorNum = buscaMaior();

        // Inicializa lista de frequência, tamanho do maior elemento
        for(int i=0; i < maiorNum; i++) {
            freq.inserirNoInicio(0);
        }

        // Inicializa lista de saida
        for(int i=0; i < TL; i++) {
            saida.inserirNoInicio(-1);
        }

        // Verifica frequência
        noAtual = inicio;
        for(int i=0; i<TL; i++) {
            posFreq = noAtual.getInfo() - 1;
            noAux = freq.retornaCaixa(freq.inicio, posFreq);
            noAux.setInfo(noAux.getInfo()+1);
            noAtual = noAtual.getProx();
        }
        noAux = freq.inicio.getProx();
        for(int i=1; i < maiorNum; i++) {
            noAux.setInfo(noAux.getInfo() + noAux.getAnt().getInfo());
            noAux = noAux.getProx();
        }

        // Inserir na nova lista ordenado
        noAtual = inicio;
        while(noAtual.getProx() != null) {
            noFreq = freq.retornaCaixa(freq.inicio, noAtual.getInfo() - 1);
            saida.retornaCaixa(saida.inicio, noFreq.getInfo()-1).setInfo(noAtual.getInfo());
            noFreq.setInfo(noFreq.getInfo()-1);

            noAtual = noAtual.getProx();
        }

        inicio = saida.inicio;
        fim = saida.fim;
    }

    // - Bucked
    public void bucket() {
        int TL = contaElem();
        ArrayList<Lista> lista = new ArrayList();
        if(TL > 0){
            int i = 0;
            int maior = buscaMaior();
            // Cria Bucked, chapeus/recipiente
            for(int j=0; i < maior; j++){
                Lista aux = new Lista();
                for(int k=0; k<TL; k++) {
                    if(retornaCaixa(inicio, k).getInfo() >= i
                            && retornaCaixa(inicio, k).getInfo() < i+5){
                        aux.inserirNoFinal(retornaCaixa(inicio, k).getInfo());
                    }
                }
                i+=5;
                if(aux.contaElem() > 0)
                    lista.add(aux);
            }

            inicio = fim = null;
            for(Lista l : lista){
                //Ordenação dentro do Recipiente
                l.insercaoDireta();
                // Insersão na Lista Original
                No aux = l.inicio;
                while(aux != null) {
                    inserirNoFinal(aux.getInfo());
                    aux = aux.getProx();
                }
            }
        }
    }

    // - Radix
    private void countingSortRadix(int divisor, int base) {
        // Vai ter que fazer o counting numero por número,
        // pega a unidade, depois na segunda passagem pega a
        // dezena e depois pega o resto, assim, por diante,
        // -> utilizar de resto para isso

        int maiorNum, posFreq, numAux, TL = contaElem();
        // TL = 9, porque o Range 0...9
        No noAtual = inicio;
        No noAux, noFreq;
        Lista freq = new Lista();
        Lista saida = new Lista();

        // Inicializa lista de frequência, tamanho agora aqui é o
        // range dos números 0...9
        for(int i=0; i < base; i++) {
            freq.inserirNoInicio(0);
        }

        // Inicializa lista de saida
        for(int i=0; i < TL; i++) {
            saida.inserirNoInicio(-1);
        }

        // Verifica frequência
        noAtual = inicio;
        for(int i=0; i < TL; i++) {
//            posFreq = noAtual.getInfo() - 1;
            posFreq = (noAtual.getInfo() / divisor) % base; // Base aqui nossa sempre 10
            // Acima faz pegar unidade, dezena, centena e etc.
            noAux = freq.retornaCaixa(freq.inicio, posFreq);
            noAux.setInfo(noAux.getInfo()+1);
            noAtual = noAtual.getProx();
        }
        noAux = freq.inicio.getProx();
        for(int i=1; i < base; i++) { // Aqui para nós o range é de 0 a 9 = base 10
            // Soma anterior com o próximo
            noAux.setInfo(noAux.getInfo() + noAux.getAnt().getInfo());
            noAux = noAux.getProx();
        }

        // Inserir na nova lista ordenado
        noAtual = fim; // ***OBS -> lembra de iniciar do fim, pois, estava dando erro se eu
        // inicia-se com fim, no counting normal estava OK, mas, aqui estava dando erro,
        // então, o melhor é iniciar o counting sempre do final quando eu for fazer a saída/output
        for(int i=TL; i>0; i--) {
            noFreq = freq.retornaCaixa(freq.inicio, (noAtual.getInfo() / divisor) % base);
            saida.retornaCaixa(saida.inicio, noFreq.getInfo()-1).setInfo(noAtual.getInfo());
            noFreq.setInfo(noFreq.getInfo()-1);

            noAtual = noAtual.getAnt();
        }

        inicio = saida.inicio;
        fim = saida.fim;

    }


    public void radixSort(){
        // Vou comparando por unidade, dezena, centena e etc, para
        // ir aplicando o couting ou bucked, ai pode escolher
        int maior, divisor = 1;
        No i = inicio;
        maior = buscaMaior();
        while(maior > 0) {
            countingSortRadix(divisor, 10);
            maior /= divisor;
            divisor *= 10;
        }
        // Vou dividindo o maior até ele chegar a ser menor que 0,
        // essa é a forma de saber quantas vezes eu irei repetir
    }

    // - Comb Sort
    public void combSort() {
        // Bolha melhorado
        int gap, aux, TL = contaElem();
        No reg1, reg2;

        gap = (int) (TL / 1.3);
        while(gap != 0) {
            for (int i = 0; i < TL - gap; i++) {
                reg1 = retornaCaixa(inicio, i);
                reg2 = retornaCaixa(inicio, i + gap);
                if (reg1.getInfo() > reg2.getInfo()) {
                    aux = reg1.getInfo();
                    reg1.setInfo(reg2.getInfo());
                    reg2.setInfo(aux);
                }
            }
            gap /= 1.3;
        }
    }

    // - Gnome
    public void gnomeSort() {
        int i, aux, TL = contaElem();
        No reg1, reg2;
        i = 0;
        while (i < TL) {
            if(i == 0) {
                i++;
            }
            reg1 = retornaCaixa(inicio, i);
            reg2 = retornaCaixa(inicio, i-1);
            if(reg1.getInfo() >= reg2.getInfo()) {
                i++;
            } else {
                aux = reg1.getInfo();
                reg1.setInfo(reg2.getInfo());
                reg2.setInfo(aux);
                i--;
            }
        }
    }

    // - Tim
    private void insercaoDiretaTim(No ini, No fim){
        int temp;
        No j, aux = null;
        for(No i = ini.getProx(); i != fim.getProx(); i = i.getProx()){
            temp = i.getInfo();
            j = i.getAnt();
            while(j != inicio && temp <= j.getInfo()){
                j.getProx().setInfo(j.getInfo());
                j = j.getAnt();
            }
            if(temp <= j.getInfo()){
                j.getProx().setInfo(j.getInfo());
                j.setInfo(temp);
            } else {
                j.getProx().setInfo(temp);
            }
        }
    }

    private void mergeTim(int ini, int meio, int fim){
        int i,j,k;
        int n1 = meio - ini + 1;
        int n2 = fim - meio;
        Lista lista = new Lista();
        Lista lista2 = new Lista();
        inicializaLista(0, n1, lista);
        inicializaLista(0, n2, lista2);
        for(i = 0; i < n1; i++) {
            lista.retornaCaixa(lista.inicio, i).setInfo(retornaCaixa(inicio, ini+1).getInfo());
        }
        for(j = 0; j < n2; j++) {
            lista2.retornaCaixa(lista2.inicio, j)
                    .setInfo(retornaCaixa(inicio, meio + 1 + j).getInfo());
        }
        i = j = 0;
        k = ini;
        while(i < n1 && j < n2) {
            if(lista.retornaCaixa(lista.inicio, i).getInfo()
                    <= lista2.retornaCaixa(lista2.inicio, j).getInfo()) {
                retornaCaixa(inicio, k).setInfo(lista.retornaCaixa(lista.inicio, i++).getInfo());
            } else {
                retornaCaixa(inicio, k).setInfo(lista2.retornaCaixa(lista2.inicio, j++).getInfo());
            }
            k++;
        }
        while(i < n1) {
            retornaCaixa(inicio, k++).setInfo(lista.retornaCaixa(lista.inicio, i).getInfo());
        }
        while(j < n2) {
            retornaCaixa(inicio, k++).setInfo(lista2.retornaCaixa(lista2.inicio, j).getInfo());
        }
    }


    public void timSort(){
        int divisor = 32, TL = contaElem();
        for(int i = 0; i<TL; i+=divisor) {
            insercaoDiretaTim(retornaCaixa(inicio, i), retornaCaixa(inicio,
                    Math.min(i+divisor-1, (TL-1))));
        }
        for(int n = divisor; n < TL; n = 2*n) {
            for(int ini = 0; ini < TL; ini += 2*n){
                int meio = ini + n - 1;
                int fim = Math.min(ini + 2 * n - 1, TL-1);
                if(meio < fim)
                    mergeTim(ini, meio, fim);
            }
        }

    }

}
