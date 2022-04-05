package com.company;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Stack;

public class Arquivo {

    private int numRegTotal = 8;
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public Arquivo(String nomearquivo) {
        try {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
            comp = mov = 0;
        } catch (IOException e) {
        }
    }

    public String getNomearquivo() {
        return nomearquivo;
    }

    public int getNumRegTotal() {
        return numRegTotal;
    }

    public void setNumRegTotal(int numRegTotal) {
        this.numRegTotal = numRegTotal;
    }

    public String getNomeArquivo() {
        return nomearquivo;
    }

    public void setNomearquivo(String nomearquivo) {
        this.nomearquivo = nomearquivo;
    }

    public void setArquivo(RandomAccessFile arquivo) {
        this.arquivo = arquivo;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public void setMov(int mov) {
        this.mov = mov;
    }

    public void copiaArquivo(Arquivo arquivoOrigem){
        Registro reg = new Registro();
        seekArq(0);
        arquivoOrigem.seekArq(0);
        while (!arquivoOrigem.eof()) {
            reg.leDoArq(arquivoOrigem.getFile());
            reg.gravaNoArq(arquivo);
        }
    }

    public void gravarString(String frase){
        try {
            arquivo.writeBytes(frase);
        } catch (IOException e) {
            System.out.println("ERRO: " + e);
        }
    }

    public RandomAccessFile getFile() {
        return arquivo;
    }

    //desloca oef
    public void truncate(long pos) {
        try {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc) { }
    }

    //semelhante ao feof() da linguagem C
    //verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof() {
        boolean retorno = false;
        try {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e) { }
        return (retorno);
    }

    public void seekArq(int pos) {
        try {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e) { }
    }

    public int filesize() {
        try {
            return (int) arquivo.length() / Registro.length();
        } catch (IOException e) {
            return 0;
        }
    }

    public void initComp() {
        comp = 0;
    }

    public void initMov() {
        mov = 0;
    }

    public int getComp() {
        return this.comp;
    }

    public int getMov() {
        return this.mov;
    }

    public void geraArquivoOrdenado() {
        Registro registro = new Registro(0);
        truncate(0);
        for(int i=1; i <= numRegTotal; i++)   {
            registro.setNumero(i);
            registro.gravaNoArq(arquivo);
        }
    }

    public void geraArquivoReverso() {
        Registro registro = new Registro(numRegTotal);
        truncate(0);
        for(int i=numRegTotal; i > 0; i--) {
            registro.setNumero(i);
            registro.gravaNoArq(arquivo);
        }
    }
    public void geraArquivoRandomico() {
        Registro registro = new Registro(1);
        truncate(0);
        for(int i=1; i <= numRegTotal; i++) {
            registro.setNumero((int) (Math.random() * (numRegTotal * 4) + 1));
            registro.gravaNoArq(arquivo);
        }
    }

    // Métodos Ordenação

    //    [1] - Inserção Direta
    public void isercaoDireta() {
        int pos, TL;
        TL = filesize();
        Registro reg = new Registro();
        Registro aux = new Registro();
        for(int i=1; i <TL; i++) {
            pos = i;
            seekArq(pos - 1);
            reg.leDoArq(arquivo);
            aux.leDoArq(arquivo);
            comp++;
            while(pos > 0 && aux.getNumero() < reg.getNumero()) {
               mov++;
               seekArq(pos);
               reg.gravaNoArq(arquivo);
               pos--;
               if(pos > 0)
               {
                   seekArq(pos - 1);
                   reg.leDoArq(arquivo);
                   comp++;
               }
            }
            if(pos != i) {
                mov++;
                seekArq(pos);
                aux.gravaNoArq(arquivo);
            }
        }
    }

    //    [2] - Inserção Binária
    private int buscaBinaria(int chave, int TL) {
        int ini = 0, fim = TL, meio = TL/2;
        Registro regIni = new Registro();
        Registro regMeio = new Registro();
        Registro regFim = new Registro();
//        seekArq(ini);
//        regIni.leDoArq(arquivo);
        seekArq(meio);
        regMeio.leDoArq(arquivo);
//        seekArq(fim);
//        regFim.leDoArq(arquivo);
        comp++;
        while(ini < fim && chave != regMeio.getNumero()) {
            comp++;
            if(chave > regMeio.getNumero()) {
                ini = meio+1;
//                seekArq(ini);
//                regIni.leDoArq(arquivo);
            } else {
                fim = meio-1;
//                seekArq(fim);
//                regFim.leDoArq(arquivo);
            }
            meio = (ini+fim)/2;
            seekArq(meio);
            regMeio.leDoArq(arquivo);
            comp++; // Coloquei pq depois ele volta comparando
        }

        comp++;
        if(chave > regMeio.getNumero()) {
            return meio+1;
        } else {
            return meio;
        }
    }

    public void insercaoBinaria() {
        int i, j, pos, TL, auxInfo;
        Registro regAux = new Registro();
        Registro regTemp = new Registro();
        i = 1;
        TL = filesize();
        while(i < TL) {
            seekArq(i);
            regAux.leDoArq(arquivo);
            pos = buscaBinaria(regAux.getNumero(), i);
            j = i;
            while(j > pos) {
                mov++;
                seekArq(j-1);
                regTemp.leDoArq(arquivo);
                regTemp.gravaNoArq(arquivo);
                j--;
            }
            if(pos < i) {
                mov++;
                seekArq(pos);
                regAux.gravaNoArq(arquivo);
            }
            i++;
        }
    }

    //    [3] - Seleção Direta
    public void selecaoDireta() {
        int i, j, posMenor, TL;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        Registro regMenor = new Registro();
        TL = filesize();
        i=0;
        while(i < TL-1) {
            seekArq(i);
            regI.leDoArq(arquivo);
            posMenor = i;
            regMenor.setNumero(regI.getNumero());
            j=i+1;

            while(j < TL) {
                seekArq(j);
                regJ.leDoArq(arquivo);
                comp++;
                if(regJ.getNumero() < regMenor.getNumero()) {
                    posMenor = j;
                    regMenor.setNumero(regJ.getNumero());
                }
                j++;
            }

            if(posMenor != i) {
                mov++;
                seekArq(posMenor);
                regI.gravaNoArq(arquivo);
                mov++;
                seekArq(i);
                regMenor.gravaNoArq(arquivo);
            }

            i++;
        }
    }

    //    [4] - Bolha
    public void bubbleSort() {
        int aux, TL;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        TL = filesize() - 1;
        while(TL > 0) {
            for(int i=0; i < TL; i++) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getNumero() > reg2.getNumero()) {
                    seekArq(i);
                    mov++;
                    reg2.gravaNoArq(arquivo);
                    mov++;
                    reg1.gravaNoArq(arquivo);
                }
            }
            TL--;
        }
    }

    //    [5] - Shake
    public void shakeSort() {
        int aux, ini, fim;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        ini = 0;
        fim = filesize() - 1;
        while(ini < fim) {
            for(int i=ini; i < fim; i++) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getNumero() > reg2.getNumero()) {
                    seekArq(i);
                    mov++;
                    reg2.gravaNoArq(arquivo);
                    mov++;
                    reg1.gravaNoArq(arquivo);
                }
            }
            fim--;
            for(int j=fim; j > ini; j--) {
                seekArq(j-1);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getNumero() > reg2.getNumero()) {
                    seekArq(j-1);
                    mov++;
                    reg2.gravaNoArq(arquivo);
                    mov++;
                    reg1.gravaNoArq(arquivo);
                }
            }
            ini++;
        }
    }

    //    [6] - Shell
    public void shellSort() {
        int dist = 4, aux, k, TL;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        TL = filesize();
        while(dist > 0) {
            for(int i=0; i < dist; i++) {
                for(int j=0; j+dist < TL; j++) {
                    seekArq(j);
                    reg1.leDoArq(arquivo);
                    seekArq(j+dist);
                    reg2.leDoArq(arquivo);

                    comp++;
                    if(reg1.getNumero() > reg2.getNumero()) {
                        mov++;
                        seekArq(j);
                        reg2.gravaNoArq(arquivo);
                        mov++;
                        seekArq(j+dist);
                        reg1.gravaNoArq(arquivo);

                        k = j;
                        seekArq(k);
                        reg1.leDoArq(arquivo);
                        seekArq(k-dist);
                        reg2.leDoArq(arquivo);
                        comp++;
                        while(k-dist >= 0 && reg1.getNumero() < reg2.getNumero()) {
                            mov++;
                            seekArq(k);
                            reg2.gravaNoArq(arquivo);
                            mov++;
                            seekArq(k-dist);
                            reg1.gravaNoArq(arquivo);

                            k--;
                            seekArq(k);
                            reg2.leDoArq(arquivo);
                            seekArq(k-dist);
                            reg1.leDoArq(arquivo);
                            comp++; // Coloquei pq depois ele volta comparando
                        }
                    }
                }
            }
            dist/=2;
        }
    }

    //    [7] - Heap
    public void heapSort() {
        int TL, posPai, posFilhoE, posFilhoD, posMaiorF;
        TL = filesize();
        Registro regPai = new Registro();
        Registro regFilhoE = new Registro();
        Registro regFilhoD = new Registro();
        Registro regMaiorF = new Registro();
        Registro aux = new Registro();

        while(TL > 1) {
            posPai = TL/2 - 1;
            while(posPai >= 0) {
                posFilhoE = 2*posPai + 1;
                posFilhoD = posFilhoE +1;
                seekArq(posFilhoE);
                regFilhoE.leDoArq(arquivo);
                if(posFilhoD < TL) {
                    regFilhoD.leDoArq(arquivo);
                    comp++;
                    if(regFilhoE.getNumero() > regFilhoD.getNumero()) {
                        posMaiorF = posFilhoE;
                        regMaiorF.setNumero(regFilhoE.getNumero());
                    } else {
                        posMaiorF = posFilhoD;
                        regMaiorF.setNumero(regFilhoD.getNumero());
                    }
                } else {
                    posMaiorF = posFilhoE;
                    regMaiorF.setNumero(regFilhoE.getNumero());
                }

                seekArq(posPai);
                regPai.leDoArq(arquivo);
                comp++;
                if(regMaiorF.getNumero() > regPai.getNumero()) {
                        mov++;
                        seekArq(posMaiorF);
                        regPai.gravaNoArq(arquivo);
                        mov++;
                        seekArq(posPai);
                        regMaiorF.gravaNoArq(arquivo);
                }
                posPai--;
            }
            seekArq(0);
            regMaiorF.leDoArq(arquivo);
            seekArq(TL-1);
            aux.leDoArq(arquivo);

            mov++;
            seekArq(0);
            aux.gravaNoArq(arquivo);
            mov++;
            seekArq(TL-1);
            regMaiorF.gravaNoArq(arquivo);
            TL--;
        }
    }

    //    [8] - QUICK S/PIVÔ
    private void quickSemPivo(int ini, int fim) {
        int i = ini, j = fim;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        while(i < j) {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            comp++;
            while(i < j && regI.getNumero() <= regJ.getNumero()) {
                i++;
                regI.getNumero();
            }
            if(i < j) { // Evita gravações redundantes
                mov++;
                seekArq(i);
                regJ.gravaNoArq(arquivo);
                mov++;
                seekArq(j);
                regI.gravaNoArq(arquivo);
            }
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            comp++;
            while(i < j && regI.getNumero() <= regJ.getNumero()) {
                j--;
                seekArq(j);
                regJ.leDoArq(arquivo);
            }
            if(i < j) { // Evita gravações redundantes
                mov++;
                seekArq(i);
                regJ.gravaNoArq(arquivo);
                mov++;
                seekArq(j);
                regI.gravaNoArq(arquivo);
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
        int TL = filesize();
        quickSemPivo(0, TL-1);
    }

    //    [8] - QUICK S/PIVÔ - Iterativo
    public void quickSemPivoIterativo() {
        Stack<Integer> pilha = new Stack<Integer>();
        int ini = 0, fim = filesize() - 1;
        int i, j;
        Registro regI = new Registro();
        Registro regJ = new Registro();

        pilha.push(ini);
        pilha.push(fim);
        while(!pilha.isEmpty()) {
            fim = pilha.pop();
            ini = pilha.pop();

            i = ini;
            j = fim;
            while(i < j) {
                seekArq(i);
                regI.leDoArq(arquivo);
                seekArq(j);
                regJ.leDoArq(arquivo);
                comp++;
                while(i < j && regI.getNumero() <= regJ.getNumero()) {
                    i++;
                    regI.getNumero();
                }
                if(i < j) { // Evita gravações redundantes
                    mov++;
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    mov++;
                    seekArq(j);
                    regI.gravaNoArq(arquivo);
                }
                seekArq(i);
                regI.leDoArq(arquivo);
                seekArq(j);
                regJ.leDoArq(arquivo);
                comp++;
                while(i < j && regI.getNumero() <= regJ.getNumero()) {
                    j--;
                    seekArq(j);
                    regJ.leDoArq(arquivo);
                }
                if(i < j) { // Evita gravações redundantes
                    mov++;
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    mov++;
                    seekArq(j);
                    regI.gravaNoArq(arquivo);
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

    //    [9] - QUICK C/PIVÔ
    private void quickComPivo(int ini, int fim) {
        int i = ini, j = fim, pivo;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        seekArq((ini+fim)/2);
        regI.leDoArq(arquivo);
        pivo = regI.getNumero();
        while(i < j) {
            seekArq(i);
            regI.leDoArq(arquivo);
            comp++;
            while(regI.getNumero() < pivo) {
                i++;
                regI.leDoArq(arquivo);
            }
            seekArq(j);
            regJ.leDoArq(arquivo);
            comp++;
            while(regJ.getNumero() > pivo) {
                j--;
                seekArq(j);
                regJ.leDoArq(arquivo);
            }
            if(i <= j) {
                if(i < j) {
                    mov++;
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    mov++;
                    seekArq(j);
                    regI.gravaNoArq(arquivo);
                }
                i++;
                j--;
            }
        }

        if(ini < j) {
            quickComPivo(ini, j);
        }
        if(i < fim) {
            quickComPivo(i, fim);
        }
    }

    public void quickComPivo() {
        int TL = filesize();
        quickComPivo(0, TL-1);
    }

    //    [9.1] - QUICK C/PIVÔ - Iterativo
    public void quickComPivoIterativo() {
        Stack<Integer> pilha = new Stack<Integer>();
        int ini = 0, fim = filesize() - 1;
        int i, j, pivo;
        Registro regI = new Registro();
        Registro regJ = new Registro();

        pilha.push(ini);
        pilha.push(fim);
        while (!pilha.isEmpty()) {
            fim = pilha.pop();
            ini = pilha.pop();

            i = ini;
            j = fim;
            seekArq((i+j)/2);
            regI.leDoArq(arquivo);
            pivo = regI.getNumero();
            while (i < j) {
                seekArq(i);
                regI.leDoArq(arquivo);
                comp++;
                while (regI.getNumero() < pivo) {
                    i++;
                    regI.leDoArq(arquivo);
                }
                seekArq(j);
                regJ.leDoArq(arquivo);
                comp++;
                while (regJ.getNumero() > pivo) {
                    j--;
                    seekArq(j);
                    regJ.leDoArq(arquivo);
                }

                if (i <= j) {
                    if (i < j) {
                        mov++;
                        seekArq(i);
                        regJ.gravaNoArq(arquivo);
                        mov++;
                        seekArq(j);
                        regI.gravaNoArq(arquivo);
                    }
                    i++;
                    j--;
                }
            }
            if (ini < j) {
                pilha.push(ini);
                pilha.push(j);
            }
            if (i < fim) {
                pilha.push(i);
                pilha.push(fim);
            }
        }
    }

    //    [10] - MERGE 1ª IMPLEMENTAÇÃO
    private void particao(RandomAccessFile arq1, RandomAccessFile arq2, int TL) {
        int tam = TL / 2;
        Registro aux = new Registro();
        for(int i =0; i < TL; i++) {
            seekArq(i);
            aux.leDoArq(arquivo);
            if(i < tam) {
                mov++;
                aux.gravaNoArq(arq1);
            } else {
                mov++;
                aux.gravaNoArq(arq2);
            }
        }
    }

    private void fusao(Arquivo arq1, Arquivo arq2, int seq, int TL) {
        int i, j, k, T_seq = seq;
        i = j = k = 0;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        Registro regK = new Registro();
        truncate(0);
        while (k < TL) {
            while (i < seq && j < seq) {
                arq1.seekArq(i);
                regI.leDoArq(arq1.getFile());
                arq2.seekArq(j);
                regJ.leDoArq(arq2.getFile());
                if (regI.getNumero() < regJ.getNumero()) {
                    seekArq(k);
                    regI.gravaNoArq(arquivo);
                    k++;
                    i++;
                } else {
                    seekArq(k);
                    regJ.gravaNoArq(arquivo);
                    k++;
                    j++;
                }
            }

            while (i < seq) {
                arq1.seekArq(i);
                regI.leDoArq(arq1.getFile());
                seekArq(k);
                regI.gravaNoArq(arquivo);
                k++;
                i++;
            }

            while (j < seq) {
                arq2.seekArq(j);
                regJ.leDoArq(arq2.getFile());
                seekArq(k);
                regJ.gravaNoArq(arquivo);
                k++;
                j++;
            }

            seq += T_seq;
        }
    }

    public void mergeSort1() {
        int TL, seq = 1;
        Arquivo arq1 = new Arquivo("merge1.dat");
        Arquivo arq2 = new Arquivo("merge2.dat");
        Registro reg2 = new Registro();
        TL = filesize();
        while(seq < TL) {
            arq1.truncate(0);
            arq2.truncate(0);
            particao(arq1.getFile(), arq2.getFile(), TL);
            fusao(arq1, arq2, seq, TL);

            seq *= 2;
        }
    }

    //    [11] - MERGE 2ª IMPLEMENTAÇÃO - Não está funcionando
    private void fusaoMerge(int ini1, int fim1, int ini2, int fim2, Arquivo arqAux) {
        int i, j, k;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        i = ini1;
        j = ini2;
        k = 0;
        while(i <= fim1 && j <= fim2) {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            if(regI.getNumero() < regJ.getNumero()) {
                seekArq(k);
                regI.gravaNoArq(arqAux.getFile());
                k++;
                i++;
            } else {
                seekArq(k);
                regJ.gravaNoArq(arqAux.getFile());
                k++;
                j++;
            }
        }
        while(i <= fim1) {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(k);
            regI.gravaNoArq(arqAux.getFile());
            k++;
            i++;
        }
        while(j <= fim2) {
            seekArq(j);
            regJ.leDoArq(arquivo);
            seekArq(k);
            regJ.gravaNoArq(arqAux.getFile());
            k++;
            j++;
        }

        for(i = 0; i < k; i++) {
            seekArq(i);
            regI.leDoArq(arqAux.getFile());
            seekArq(i + ini1);
            regI.gravaNoArq(arquivo);

        }

//        System.out.println("ARQUIVO PARCIAL ---> ");
//        arqAux.exibirArq();
//        System.out.println("----------------------");

    }

    private void mergeSort2(int esq, int dir, Arquivo arqAux)
    {
        int meio;
        if (esq < dir)
        {
            meio = (esq + dir) / 2;
            mergeSort2(esq, meio, arqAux);
            mergeSort2(meio + 1, dir, arqAux);
            fusaoMerge(esq, meio, meio + 1, dir, arqAux);
        }
    }

    public void mergeSort2()
    {
        int TL = filesize();
        Arquivo arqAux = new Arquivo("auxMerge.dat");
        arqAux.truncate(0);
        mergeSort2(0, TL - 1, arqAux);
    }

    //    [12] - COUNTING
    public void countingSort(){
        int j, posFreq, posSaida, numAux, maiorElem, TL, soma;
        Arquivo freq = new Arquivo("tempCounting.dat");
        Arquivo saida = new Arquivo("saidaCounting.dat");
        Registro reg = new Registro();
        TL = filesize();

        // Encontra maior número
        seekArq(0);
        reg.leDoArq(arquivo);
        maiorElem = reg.getNumero();
        for(int i=1; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            if(reg.getNumero() > maiorElem) {
                maiorElem = reg.getNumero();
            }
        }

        // Cria arquivo com número da maior posição encontrada
        freq.truncate(0);
        for(int i = 0; i < maiorElem; i ++) {
            reg.setNumero(0);
            reg.gravaNoArq(freq.getFile());
        }

        // Verifica frequência
        for(int i = 0; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            posFreq = reg.getNumero() - 1;
            freq.seekArq(posFreq);
            reg.leDoArq(freq.getFile());
            reg.setNumero(reg.getNumero() + 1);
            freq.seekArq(posFreq);
            reg.gravaNoArq(freq.getFile());
        }

        for(int i = 1; i < maiorElem; i++) {
            freq.seekArq(i-1);
            reg.leDoArq(freq.getFile());
            soma = 0;
            soma += reg.getNumero();
            reg.leDoArq(freq.getFile());
            soma += reg.getNumero();
            // Posição recebe a soma do anterior com atual
            reg.setNumero(soma);
            freq.seekArq(i);
            reg.gravaNoArq(freq.getFile());
        }

        for(int i = TL-1; i >= 0; i--) {
            seekArq(i);
            reg.leDoArq(arquivo);
            numAux = reg.getNumero();
            posFreq = numAux - 1;
            freq.seekArq(posFreq);
            reg.leDoArq(freq.getFile());
            posSaida = reg.getNumero() -1;
            saida.seekArq(posSaida);
            reg.setNumero(numAux);
            reg.gravaNoArq(saida.getFile());

            freq.seekArq(posFreq);
            reg.leDoArq(freq.getFile());
            numAux = reg.getNumero();
            numAux--;
            reg.setNumero(numAux);
            freq.seekArq(posFreq);
            reg.gravaNoArq(freq.getFile());
        }

        for(int i=0; i<TL; i++){
            saida.seekArq(i);
            reg.leDoArq(saida.getFile());
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }

////         ABAIXO FOI DA INTERNET -- NÃO ENTENDI -->
//        for(int i = 0; i < filesize(); i++) {
//            seekArq(i);
//            reg.leDoArq(arquivo);
//            posTemp = reg.getNumero();
//            temp.seekArq(posTemp);
//            reg.leDoArq(temp.getFile());
//            reg = new Registro(reg.getNumero()+1);
//            temp.seekArq(posTemp);
//            mov++;
//            reg.gravaNoArq(temp.getFile());
//        }
//        int i = 0;
//        for(j = 0; j <= maiorElem+1; j++) {
//            temp.seekArq(j);
//            reg.leDoArq(temp.getFile());
//            for(int k = reg.getNumero(); k > 0; k--) {
//                seekArq(i);
//                reg = new Registro(j);
//                mov++;
//                reg.gravaNoArq(arquivo);
//                seekArq(j);
//                reg.leDoArq(arquivo);
//                i++;
//            }
//        }
//        truncate(TL);
    }

    //    [13] - BUCKED SORT
    public void isercaoDiretaBucked() {
        int pos, TL;
        Registro reg = new Registro();
        Registro aux = new Registro();

        seekArq(0);
        reg.leDoArq(arquivo);
        TL = reg.getNumero();

        for(int i=1; i <= TL; i++) {
            pos = i;
            seekArq(pos - 1);
            reg.leDoArq(arquivo);
            aux.leDoArq(arquivo);
            comp++;
            while(pos > 0 && aux.getNumero() < reg.getNumero()) {
                mov++;
                seekArq(pos);
                reg.gravaNoArq(arquivo);
                pos--;
                if(pos > 0)
                {
                    seekArq(pos - 1);
                    reg.leDoArq(arquivo);
                    comp++;
                }
            }
            if(pos != i) {
                mov++;
                seekArq(pos);
                aux.gravaNoArq(arquivo);
            }
        }
    }

    public int buscaMaior() {
        int maior;
        Registro reg = new Registro();
        seekArq(0);
        reg.leDoArq(arquivo);
        maior = reg.getNumero();
        for(int i=1; !eof(); i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            if(reg.getNumero() > maior) {
                maior = reg.getNumero();
            }
        }

        return maior;
    }

    public void gravarArqBucked(Arquivo arq, int[] posGravar) {
        Registro reg = new Registro();
        seekArq(posGravar[0]);
        arq.seekArq(1);
        while (!arq.eof()) {
            reg.leDoArq(arq.getFile());
            System.out.println(reg.getNumero());
            reg.gravaNoArq(arquivo);
            posGravar[0]++;
        }
    }

    public void buckedSort() {
        // Tentar utilizando um arrayList, ai depois fazar uma inserção direta dentro desses
        // array lista, vou ter fazer uma função de inserção direta para arrayList
        int maiorValor = -1, TL = filesize();
        Registro reg = new Registro();
        Registro regBucked = new Registro();
        Arquivo bucked1 = new Arquivo("bucked1.dat");
        Arquivo bucked2 = new Arquivo("bucked2.dat");
        Arquivo bucked3 = new Arquivo("bucked3.dat");
        Arquivo bucked4 = new Arquivo("bucked4.dat");

        int qtdeArq = 4;
        reg.setNumero(0);
        bucked1.truncate(0);
        bucked1.seekArq(0);
        reg.gravaNoArq(bucked1.getFile());
        bucked2.truncate(0);
        bucked2.seekArq(0);
        reg.gravaNoArq(bucked2.getFile());
        bucked3.truncate(0);
        bucked3.seekArq(0);
        reg.gravaNoArq(bucked3.getFile());
        bucked4.truncate(0);
        bucked4.seekArq(0);
        reg.gravaNoArq(bucked4.getFile());


        if(TL > 0) {
            maiorValor = buscaMaior();
            int incremento = maiorValor / qtdeArq;

            // Dividir recipientes
            // -> a primeira posição do arquivo guarda seu TL,
            // isso faz evitar ter que ficar fazendo filesize() para
            // toda hora ir pegando onde vai inserir um novo registro.
            for(int j = 0; j < TL; j++) {
                seekArq(j);
                reg.leDoArq(arquivo);
                if(reg.getNumero() <= incremento) {
                    bucked1.seekArq(0);
                    regBucked.leDoArq(bucked1.getFile());

                    bucked1.seekArq(regBucked.getNumero()+1);
                    reg.gravaNoArq(bucked1.getFile());

                    bucked1.seekArq(0);
                    regBucked.setNumero(regBucked.getNumero()+1);
                    regBucked.gravaNoArq(bucked1.getFile());
                } else if(reg.getNumero() <= incremento*2) {
                    bucked2.seekArq(0);
                    regBucked.leDoArq(bucked2.getFile());

                    bucked2.seekArq(regBucked.getNumero()+1);
                    reg.gravaNoArq(bucked2.getFile());

                    bucked2.seekArq(0);
                    regBucked.setNumero(regBucked.getNumero()+1);
                    regBucked.gravaNoArq(bucked2.getFile());
                }  else if(reg.getNumero() <= incremento*3) {
                    bucked3.seekArq(0);
                    regBucked.leDoArq(bucked3.getFile());

                    bucked3.seekArq(regBucked.getNumero()+1);
                    reg.gravaNoArq(bucked3.getFile());

                    bucked3.seekArq(0);
                    regBucked.setNumero(regBucked.getNumero()+1);
                    regBucked.gravaNoArq(bucked3.getFile());
                } else if(reg.getNumero() <= incremento*4 + 3) {
                    // Tive colocar + 3 para funcionar, em tese iria
                    // funcionar com + 1 para caso em que a divisão desse
                    // número inteiro
                    bucked4.seekArq(0);
                    regBucked.leDoArq(bucked4.getFile());

                    bucked4.seekArq(regBucked.getNumero()+1);
                    reg.gravaNoArq(bucked4.getFile());

                    bucked4.seekArq(0);
                    regBucked.setNumero(regBucked.getNumero()+1);
                    regBucked.gravaNoArq(bucked4.getFile());
                }
            }


            bucked1.isercaoDiretaBucked();
            bucked2.isercaoDiretaBucked();
            bucked3.isercaoDiretaBucked();
            bucked4.isercaoDiretaBucked();

//            bucked1.exibirArq();
//            bucked2.exibirArq();
//            bucked3.exibirArq();
//            bucked4.exibirArq();

            truncate(0);
            int posGravar[] = {0};
            seekArq(posGravar[0]);
            gravarArqBucked(bucked1, posGravar);
            gravarArqBucked(bucked2, posGravar);
            gravarArqBucked(bucked3, posGravar);
            gravarArqBucked(bucked4, posGravar);

//            System.out.println("\n\nMAIOR => " + maiorValor + "\n\n");

        }
    }


    //    [15] - GNOME
    public void gnomeSort() {
        int i = 0;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        while(i < filesize()) {
            if(i == 0) {
                i++;
            }
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(i-1);
            reg2.leDoArq(arquivo);
            comp++;
            if(reg1.getNumero() >= reg2.getNumero()) {
                i++;
            } else {
                seekArq(i-1);
                mov++;
                reg1.gravaNoArq(arquivo);
                mov++;
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                i--;
            }
        }
    }



    // TESTE EXIBIR
    public void exibirArq()
    {
        Registro aux = new Registro();
        seekArq(0);
        while (!this.eof()) {
            aux.leDoArq(arquivo);
            System.out.println(aux.getNumero());
        }
    }




}
