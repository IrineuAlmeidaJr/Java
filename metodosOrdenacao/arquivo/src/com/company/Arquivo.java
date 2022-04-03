package com.company;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivo {

    private int numRegTotal = 3;
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

    public RandomAccessFile getArquivo() {
        return arquivo;
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
        for(int i=1; i < numRegTotal; i++)   {
            registro.setNumero(i);
            registro.gravaNoArq(arquivo);
        }
    }

    public void geraArquivoReverso() {
        Registro registro = new Registro(numRegTotal);
        truncate(0);
        for(int i=numRegTotal-1; i > 0; i--) {
            registro.setNumero(i);
            registro.gravaNoArq(arquivo);
        }
    }
    public void geraArquivoRandomico() {
        Registro registro = new Registro(1);
        truncate(0);
        for(int i=1; i < numRegTotal; i++) {
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

    public void insersaoBinaria() {
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

    // TESTE EXIBIR
    public void exibirArq()
    {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof())
        {
            aux.leDoArq(arquivo);
            System.out.println(aux.getNumero());
            i++;
        }
    }




}
