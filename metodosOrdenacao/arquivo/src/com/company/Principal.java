package com.company;

public class Principal {
    private Arquivo arqOrd, arqRev, arqRand, auxOrd, auxRev, auxRand;
    private Arquivo tabela;
    long tini, tfim, ttotalO, ttotalRev, ttotalRand, compO, compRev, compRand, movO, movRev, movRand;

    public Principal() {
        arqOrd = new Arquivo("arqOrd.dat");
        arqRev = new Arquivo("arqRev.dat");
        arqRand = new Arquivo("arqRand.dat");
        auxOrd = new Arquivo("auxOrd.dat");
        auxRev = new Arquivo("auxRev.dat");
        auxRand = new Arquivo("auxRand.dat");
    }

    private void gerarColunas() {
        String colunas = "Metodos Ordenacao  " +
                "|              Arquivo Ordenado                             " +
                "|                  Arquivo em Ordem Reversa                         " +
                "|                    Arquivo Randomico                            |" +
                "\r\n                   " +
                "| Comp.Prog. | Comp.Equa. |  Mov.Prog | Mov.Equa. |  Tempo  " +
                "|  Comp.Prog.  |  Comp.Equa.  |   Mov.Prog  |  Mov.Equa.  |  Tempo  " +
                "|  Comp.Prog.  |  Comp.Equa.  |   Mov.Prog |  Mov.Equa. |  Tempo  |";
        tabela.gravarString(colunas);
    }

    private void gravaLinhaTabela(String nome, long compO, double ordCompEqua, long movO, double ordMovEqua,
    long ttotalO,long compRev, double revCompEqua, long movRev, double revMovEqua,
    long ttotalRev,long compRand, double randCompEqua, long movRand, double randMovEqua, long ttotalRand) {
        String linha = (String.format("\r\n%18s |%10d  |%10f  |%9d  |%10f |%7s  |%12d  |%12f  |%11d  " +
                        "|%11f  |%7s  |%12d  |%12f  |%10d  |%10f  |%7s  |",
                nome, compO, ordCompEqua, movO, ordMovEqua, ttotalO + "",
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev + "",
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand + ""));
        tabela.gravarString(linha);
    }

    public void geraTabela() {
        int ordComp, ordMov, revComp,
                revMov, randComp, randMov;
        tabela = new Arquivo("tabela.txt");
        double ordMovEqua, ordCompEqua, revMovEqua, revCompEqua, randMovEqua, randCompEqua;
        gerarColunas();

        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();
        int numRegTotal = arqOrd.getNumRegTotal();

        // --- INSER????O DIRETA ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.isercaoDireta();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = numRegTotal-1;
        ordMovEqua = 3*(numRegTotal-1);

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.isercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = (numRegTotal*numRegTotal + numRegTotal - 4)/4;
        revMovEqua = (numRegTotal*numRegTotal + 3*numRegTotal - 4)/2;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.isercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = (numRegTotal*numRegTotal + numRegTotal - 2)/4;
        randMovEqua = (numRegTotal*numRegTotal + 9*numRegTotal - 10)/4;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Insercao Direta
        gravaLinhaTabela("Insercao Direta", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- INSER????O BIN??RIA ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.insercaoBinaria();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = numRegTotal*(Math.log10(numRegTotal)-Math.log(Math.E) + 0.5);
        ordMovEqua = 3*(numRegTotal-1);

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.insercaoBinaria();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = numRegTotal*(Math.log10(numRegTotal)-Math.log(Math.E) + 0.5);
        revMovEqua = (numRegTotal*numRegTotal + 3*numRegTotal - 4)/2;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.insercaoBinaria();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = numRegTotal*(Math.log10(numRegTotal)-Math.log(Math.E) + 0.5);
        randMovEqua = (numRegTotal*numRegTotal + 9*numRegTotal - 10)/4;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Inser????o Bin??ria
        gravaLinhaTabela("Insercao Binaria", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);
        // ---------------------------------------

        // --- SELECAO DIRETA ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.selecaoDireta();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        ordMovEqua = 3*(numRegTotal-1);

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.selecaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        revMovEqua = (numRegTotal*numRegTotal + 3*numRegTotal - 4)/2;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.selecaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        randMovEqua = (numRegTotal*(Math.log10(numRegTotal)+0.577216));

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Sele????o Direta
        gravaLinhaTabela("Selecao Direta", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- BUBBLE SORT ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.bubbleSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.bubbleSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        revMovEqua = 3*(numRegTotal*numRegTotal - numRegTotal)/4;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.bubbleSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        randMovEqua = 3*(numRegTotal*numRegTotal - numRegTotal)/2;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Bolha
        gravaLinhaTabela("Bolha", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);


        // ---------------------------------------

        // --- SHAKE SORT ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.shakeSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.shakeSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        revMovEqua = 3*(numRegTotal*numRegTotal - numRegTotal)/4;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.shakeSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = (numRegTotal*numRegTotal - numRegTotal)/2;
        randMovEqua = 3*(numRegTotal*numRegTotal - numRegTotal)/2;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Shake
        gravaLinhaTabela("Shake", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- SHELLSORT ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.shellSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.shellSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.shellSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Shell
        gravaLinhaTabela("Shell", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- HELPSORT ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.heapSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.heapSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.heapSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Help
        gravaLinhaTabela("Heap", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- QUICK S/ PIV?? ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.quickSemPivoIterativo();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.quickSemPivoIterativo();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.quickSemPivoIterativo();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Quick sem pivo
        gravaLinhaTabela("Quick s/pivo", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- QUICK C/ PIV?? ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.quickComPivoIterativo();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.quickComPivoIterativo();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.quickComPivoIterativo();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Quick com pivo
        gravaLinhaTabela("Quick c/pivo", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- MERGE 1?? IMPLEMENTA????O

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.mergeSort1();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.mergeSort1();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.mergeSort1();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Merge Sort 1?? Implement
        gravaLinhaTabela("Merge 1a Implemen", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- MERGE 2?? IMPLEMENTA????O -

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.mergeSort2();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.mergeSort2();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.mergeSort2();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Merge Sort 2?? Implement
        gravaLinhaTabela("Merge 2a Implemen", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- COUNTING --

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.countingSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.countingSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.countingSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Counting
        gravaLinhaTabela("Counting", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- BUCKED SORT ---

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.buckedSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.buckedSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.buckedSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Buked
        gravaLinhaTabela("Bucked", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- RADIX --

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.radixSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.radixSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.radixSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Gnome
        gravaLinhaTabela("Radix", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- COMB SORT --

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.combSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.combSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.combSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Gnome
        gravaLinhaTabela("Comb", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);

        // ---------------------------------------

        // --- GNOME --

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.gnomeSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.gnomeSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.gnomeSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Gnome
        gravaLinhaTabela("Gnome", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);


        // ---------------------------------------

        //  --- TIM --

        // - Arquivo Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        tini = System.currentTimeMillis();
        auxOrd.timSort();
        tfim = System.currentTimeMillis();
        compO = auxOrd.getComp();
        movO = auxOrd.getMov();
        ttotalO = (tfim - tini)/1000;
        ordCompEqua = 0;
        ordMovEqua = 0;

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.timSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (tfim - tini)/1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        revCompEqua = 0;
        revMovEqua = 0;

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.timSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (tfim-tini)/1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        randCompEqua = 0;
        randMovEqua = 0;

        //grava na tabela informacoes os dados extra??dos das execucoes do m??todo - Gnome
        gravaLinhaTabela("Tim", compO, ordCompEqua, movO, ordMovEqua,ttotalO,
                compRev, revCompEqua, movRev, revMovEqua, ttotalRev,
                compRand, randCompEqua, movRand, randMovEqua, ttotalRand);


        // ---------------------------------------

        // --- TESTE ---

        // - Arquivo Ordenado
//        System.out.println("\nSEM ORDENAR ->");
//        arqRev.exibirArq();
//        System.out.println("\nORDENADO -> ");
//        arqRev.timSort();
//        arqRev.exibirArq();
//
//        // --> TESTE COM TEMPO
//        auxRev.copiaArquivo(arqRev);
//        tini = System.currentTimeMillis();
//        auxRev.quickSemPivoIterativo();
//        tfim = System.currentTimeMillis();
//        System.out.println("\nORDENADO  Quick Sem Pivo ITERATIVO-> ");
//        System.out.println("TEMPO FINAL - " + (tfim-tini));
//
//        auxRev.copiaArquivo(arqRev);
//        tini = System.currentTimeMillis();
//        auxRev.quickSemPivo();
//        tfim = System.currentTimeMillis();
//        System.out.println("\nORDENADO  Quick Sem Pivo RECURSIVO-> ");
//        System.out.println("TEMPO FINAL - " + (tfim-tini));

    }
}
