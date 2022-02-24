package com.company;

import java.util.Scanner;

public class Main {

    private static Lista lista = new Lista();

    public static void main(String[] args) {
        executar();


//        Scanner scanner = new Scanner(System.in);

    }

    public static void executar() {
        byte op;
        do {
            op = menu();
            switch (op) {
                case 0: carregarValores();
                        break;
                case 1: lista.inicializa();
                // Quando da NEW não inicializa?
                // não entendi essa função, faz
                // início e fim apontar para nulo?
                // se fizer isso deleta tudo
                        break;
                case 2: inserirInicio();
                        break;
                case 3: inserirFim();
                        break;
                case 4: buscaExaustiva();
                        break;
                case 5: buscaSentinela();
                        break;
                case 6: remover();
                        break;
                case 7: ordenarInsDireta();
                        break;
                case 8: ordenarInsDiretaBinaria();
                    break;
                case 9: selecaoDireta();
                    break;
                case 10: exibir();
                    break;
                default:
                    System.out.println("Opções de 1 - 7");
            }

        } while (op != 11);

        System.out.println("*** FIM ***");
    }

    public static byte menu() {
        Scanner scanner = new Scanner(System.in);
        byte num;
        System.out.printf("\t*** M E N U ****\n" +
                "[0] - Carregar Valores\n" +
                "[1] - Inicializar Lista\n" +
                "[2] - Inserir no Início\n" +
                "[3] - Inserir no Fim\n" +
                "[4] - Busca Exaustiva\n" +
                "[5] - Busca Sentinela\n" +
                "[6] - Remover Elemento\n"+
                "[7] - Ordenar Inserseção Direta\n" +
                "[8] - Ordenar Inserseção Direta Binaria\n" +
                "[9] - Ordenar Seleção Direta\n" +
                "[10] - Exibir Lista\n" +
                "[11] - SAIR\n\n" +
                "Opção Desejada: ");

        // --- Tratamento de Exceção
        // *** Primeira Forma
//        while(true) {
//            try {
//                return scanner.nextByte();
//            } catch (Exception e) {
//                System.out.println("ERRO - Digite um Número " + e);
//            }
//        }
        // *** Segunda Form
//        try {
//            return scanner.nextByte();
//        } catch (Exception e) {
//            System.out.println("ERRO - Digite um Número " + e);
//            menu();
//        }
        byte op = scanner.nextByte();
        System.out.println("------------------------------");
        return op;
    }

    public static void carregarValores() {
        lista.inserirNoInicio(2);
        lista.inserirNoInicio(3);
        lista.inserirNoInicio(1);
        lista.inserirNoInicio(39);
        lista.inserirNoInicio(2);
        lista.inserirNoInicio(7);
        lista.inserirNoInicio(20);
        lista.inserirNoInicio(6);
        lista.inserirNoInicio(5);
        lista.inserirNoInicio(22);
        lista.inserirNoInicio(12);
        lista.inserirNoInicio(10);
        lista.inserirNoInicio(12);
        lista.inserirNoInicio(26);
        lista.inserirNoInicio(25);
        lista.inserirNoInicio(30);
    }

    public static void inserirInicio() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\t*** I N S E R I R ****\n" +
                "Informe Elemento no inicio: ");
        // --- Fazer Tratamento Exceção
        lista.inserirNoInicio(scanner.nextInt());
        System.out.println("### Inserido com Sucesso");
        System.out.println("------------------------------");
    }

    public static void inserirFim() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\t*** I N S E R I R ****\n" +
                "Informe Elemento no fim: ");
        // --- Fazer Tratamento Exceção
        lista.inserirNoFinal(scanner.nextInt());
        System.out.println("### Inserido com Sucesso");
        System.out.println("------------------------------");
    }

    public static void buscaExaustiva() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\t*** B U S C A R ****\n" +
                "Informe Elemento que deseja buscar: ");
        int valorBuscar = scanner.nextInt();

        // Podia ser um retorno de valor booleano
        if(lista.buscaExaustiva(valorBuscar)) {
            System.out.println("Elementos encontrado !!!");
        } else {
            System.out.println("Elemento não encontrado");
        }
        scanner.nextLine();
        scanner.nextLine();
        System.out.println("------------------------------");
    }

    public static void buscaSentinela() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\t*** B U S C A R ****\n" +
                "Informe Elemento que deseja buscar: ");
        int valorBuscar = scanner.nextInt();

        // Podia ser um retorno de valor booleano
        if(lista.buscaSentinela(valorBuscar)) {
            System.out.println("Elementos encontrado !!!");
        } else {
            System.out.println("Elemento não encontrado");
        }
        scanner.nextLine();
        scanner.nextLine();
        System.out.println("------------------------------");
    }

    public static void remover() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\t*** R E M O V E R ****\n" +
                "Informe elemento que deseja remover: ");
        int valorRemover = scanner.nextInt();

        // Podia ser um retorno de valor booleano
        if(lista.remover(valorRemover)) {
            System.out.println("Elementos removido !!!");
        } else {
            System.out.println("Elemento não encontrado");
        }
        scanner.nextLine();
        scanner.nextLine();
        System.out.println("------------------------------");
    }

    public static void ordenarInsDireta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t*** O R D E N A D O ****\n");
        System.out.println("\tInserção Direta\n\nSem ordenar -> ");
        lista.exibir();
        lista.insercaoDireta();
        System.out.println("\nOrdenado -> ");
        lista.exibir();
        scanner.nextLine();
        System.out.println("------------------------------");
    }

    public static void ordenarInsDiretaBinaria() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t*** O R D E N A D O ****\n");
        System.out.println("\tInserção Direta Binaria\n\nSem ordenar -> ");
        lista.exibir();
        lista.insersaoBinaria();
        System.out.println("\nOrdenado -> ");
        lista.exibir();
        scanner.nextLine();
        System.out.println("------------------------------");
    }

    public static void selecaoDireta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t*** O R D E N A D O ****\n");
        System.out.println("\tInserção Seleção Direta\n\nSem ordenar -> ");
        lista.exibir();
        lista.selecaoDireta();
        System.out.println("\nOrdenado -> ");
        lista.exibir();
        scanner.nextLine();
        System.out.println("------------------------------");
    }

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t*** E X I B I Ç Ã O ****\n");
        lista.exibir();
        scanner.nextLine();
        System.out.println("------------------------------");
    }



}
