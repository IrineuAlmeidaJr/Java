package com.company;

public class Lista {

    private No inicio;
    private No fim;

    public Lista() {
        inicio = fim = null;
    }

    public void inicializa() {
        inicio = fim = null;
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

    // *** Estrutura Desordenada ***
    // busca_exaustiva
    // busca_sentinela --> lembrar de remover
    public boolean buscaExaustiva(int info) {
        No aux = inicio;
        while(aux != null && aux.getInfo() != info) {
            aux = aux.getProx();
        }
        return aux != null;
    }


    // Retornar Elemento
    public boolean buscaSentinela(int info) {
        inserirNoFinal(info);
        No aux = inicio;
        while(aux.getInfo() != info) {
            aux = aux.getProx();
        }
        // Posso verificar, pq mesmo que a informação
        // estiver na ultima posição foi inserido mais
        // uma na sua frente. Ai só será próximo == null
        // quando parar no sentinela mesmo
        if(aux.getProx() != null) {
            removerFim();
            return true;
        } else {
            removerFim();
            return false;
        }
    }

    public void removerFim() {
        // ** VERIFICAR
        if(inicio == null) {
            System.out.println("Lista Vazia");
        } else if(inicio == fim) {
            inicio = fim = null;
        } else {
            fim = fim.getAnt();
            fim.setProx(null);
        }
    }

    // Remover por Posição
    public boolean remover(int info) {
        boolean achou = true;
        if(inicio == fim && inicio.getInfo() == info) {
            inicio = fim = null;
        } else {
            if(inicio.getInfo() == info) {
                inicio = inicio.getProx();
                inicio.setAnt(null);
            } else {
                if(fim.getInfo() == info) {
                    fim = fim.getAnt();
                    fim.setProx(null);
                } else {
                    No aux = inicio;
                    while(aux != null && aux.getInfo() != info) {
                        aux = aux.getProx();
                    }
                    if(aux != null) {
                        aux.getAnt().setProx(aux.getProx());
                        aux.getProx().setAnt(aux.getAnt());
                    } else  {
                        achou = false;
                    }
                }
            }
        }
        return achou;
    }

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

    private No retornaCaixa(No caixa, int deslocamento) {
        for(int i=0; i < deslocamento; i++) {
            caixa = caixa.getProx();
        }
        return caixa;
    }

    private No buscaBinaria(int chave, int TL, No valor) {
        int ini = 0, fim = TL-1, meio = TL/2;
        No pInicio = inicio;
        No pFim = valor.getAnt();
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

    public void exibir() {
        No aux = inicio;
        while(aux != null) {
            System.out.print(aux.getInfo() + " - ");
            aux = aux.getProx();
        }
    }

}
