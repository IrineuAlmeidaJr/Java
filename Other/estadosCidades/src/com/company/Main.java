package com.company;

public class Main {

    public static void main(String[] args) {
        Estado listaEstado = new Estado();
        listaEstado.carregarDadosEstado();
        listaEstado.exibir();

        // --> a) Inserir os pares Estado,Cidade em ordem crescente!
        listaEstado.inserirEstadoCidade("Santa Catarina", "Florianopolis");
        listaEstado.inserirEstadoCidade("Santa Catarina", "Blumenau");
        listaEstado.inserirEstadoCidade("Santa Catarina", "Agrolândia");
        listaEstado.inserirEstadoCidade("Santa Catarina", "Itajaí");
        listaEstado.inserirEstadoCidade("Santa Catarina", "Chapecó");
        listaEstado.inserirEstadoCidade("Santa Catarina", "São Miguel do Oeste");
        listaEstado.inserirEstadoCidade("São Paulo", "Presidente Prudente");
        listaEstado.inserirEstadoCidade("São Paulo", "Presidente Venceslau");
        listaEstado.inserirEstadoCidade("São Paulo", "Presidente Bernardes");
        listaEstado.inserirEstadoCidade("São Paulo", "Marabá Paulista");
        listaEstado.inserirEstadoCidade("São Paulo", "Andradina");

        // --> b) Buscar um Estado retornando o endereço do nodo;
        NoEstado auxEstado = listaEstado.buscarEstado("São Paulo");
        System.out.println("\nb) Buscar um Estado retornando o endereço do nodo;");
        System.out.println(auxEstado.getNomeEstado());

        // -- c) Listar todas as Cidade de um dado Estado;
        System.out.println("\nc) Listar todas as Cidade de um dado Estado;");
        listaEstado.exibirCidade("São Paulo");
        listaEstado.exibirCidade("Tocantins");

        // -- d) Verificar se um par Estado,Cidade está inserido nas Listas,
        // retornando True/False.
        System.out.println("\nd) Verificar se um par Estado,Cidade está inserido nas Listas," +
                "retornando True/False.");
        System.out.println("São Paulo - Presidente Prudente = " + listaEstado.buscaEstadoCidade(
                "São Paulo", "Presidente Prudente"));
        System.out.println("São Paulo - Prudente = " + listaEstado.buscaEstadoCidade(
                "São Paulo", "Prudente"));
        System.out.println("São Paulo - Andradina = " + listaEstado.buscaEstadoCidade(
                "São Paulo", "Andradina"));

    }
}
