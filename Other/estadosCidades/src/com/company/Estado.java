package com.company;

import java.sql.SQLOutput;

public class Estado {
    private NoEstado inicio;

    public Estado() {
        inicio = null;
    }

    private boolean inserirEstado(String nomeEstado) {
        // Tem que instância Cidade como objeto
        NoEstado novoEstado = new NoEstado(nomeEstado, new Cidade(), null);
        Boolean inserido = false;

        if(inicio == null) {
            inicio = novoEstado;
            inserido = true;
        } else {
            if (nomeEstado.compareToIgnoreCase(inicio.getNomeEstado()) < 0) {
                novoEstado.setProx(inicio);
                inicio = novoEstado;
                inserido = true;
            } else {
                // Está no meio
                NoEstado ant, atual;
                ant = inicio;
                atual = ant.getProx();
                while (atual != null && nomeEstado.compareToIgnoreCase(atual.getNomeEstado()) > 0) {
                    ant = atual;
                    atual = atual.getProx();
                }
                if(! nomeEstado.equalsIgnoreCase(ant.getNomeEstado())) {
                    if (atual == null) {
                        ant.setProx(novoEstado);
                    } else {
                        novoEstado.setProx(atual);
                        ant.setProx(novoEstado);
                    }
                    inserido = true;
                }
            }
        }
        return inserido;
        // ***ATENÇÃO: no caso do 'Rio de Janeiro', em comparação com
        // 'Rio Grande do Norte', o correto seria Rio de Janeiro vir
        // antes, mas por ser minuscula ela vem depois, para solucionar
        // coloquei maiusculo no De de Rio de Janeiro.
        // --- Atualização: estava comparando com "compareTo", desta
        // forma considera se está maiusculo, assim, o melhor é colocar
        // compareToIgnoreCase(), *solucionado*.
    }

    public void inserirEstadoCidade(String nomeEstado, String nomeCidade) {
        NoEstado aux = buscarEstado(nomeEstado);
        if(aux != null){ // Achou
            aux.getCidade().inserirCidade(nomeCidade);
//            aux.getCidade().inserirCidade();

        } else { // Não Achou
            inserirEstado(nomeEstado);
            aux = buscarEstado(nomeEstado);
            // Agora tem inerir Cidade na classe Cidade
            aux.getCidade().inserirCidade(nomeCidade);
        }
    }

    public void carregarDadosEstado() {
        inserirEstado("Rio de Janeiro");
        inserirEstado("Amazonas");
        inserirEstado("Roraima");
        inserirEstado("Rio Grande do Norte");
        inserirEstado("Paraná");
        inserirEstado("Bahia");
        inserirEstado("Santa Catarina");
        inserirEstado("Alagoas");
        inserirEstado("Tocantins");
    }

    // ---> Busca Sequêncial
    public NoEstado buscarEstado(String nome) {
        NoEstado pos = inicio;
        while(pos != null && nome.compareToIgnoreCase(pos.getNomeEstado()) > 0) {
            pos = pos.getProx();
        }
        if(pos != null && nome.equalsIgnoreCase(pos.getNomeEstado())) {
            return pos;
        }
        return null;
    }

    public void exibir() {
        NoEstado aux = inicio;
        while(aux != null) {
            System.out.println("--> " + aux.getNomeEstado());
            aux = aux.getProx();
        }
    }

    public void exibirCidade(String nomeEstado) {
        NoEstado auxEst = buscarEstado(nomeEstado);
        if(auxEst != null) {
            Cidade auxCid = auxEst.getCidade();
            System.out.println("# # # Cidades do Estado de " + nomeEstado + " # # #");
            auxCid.exibirCidades();
        } else {
            System.out.println("# # # Estado de " + nomeEstado + " não encontrado # # #");
        }

        // *** Pode ter um getInicio() na cidade, para saber se tem cidade ou não
        // cadastrada ou deixa do jeito que está, só a classe Cidade sabe ?
//        if(auxCid.getInicio() != null)
//        {
//            System.out.println("# # # Cidades do Estado de " + nomeEstado + " # # #");
//            auxCid.exibirCidades();
//        } else {
//            System.out.println("Estado de " + nomeEstado + " sem Cidades cadastradas !!!");
//        }
    }

    public boolean buscaEstadoCidade(String nomeEstado, String nomeCidade) {
        NoEstado estado = buscarEstado(nomeEstado);
        Cidade cidade = estado.getCidade();
        boolean achouEstado = estado != null ? true : false;
        if(cidade != null){
            boolean achouCidade = cidade.buscaCidade(nomeCidade);
            return  achouEstado && achouCidade;
        }
        return  false;
    }

}
