package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static byte menu() {
        Scanner scanner = new Scanner(System.in);
        byte op;
        System.out.printf("\n---------------------------------------------" +
                "\n\t*** MENU MÉTODOS DE BUSCA ****\n" +
                "\t    - Lista Encadeada -\n" +
                "[1] - Inserseção Direta\n" +
                "[2] - Inserseção Binaria\n" +
                "[3] - Seleção Direta\n" +
                "[4] - Bolha\n" +
                "[5] - Shake\n" +
                "[6] - Help\n" +
                "[7] - Shell\n" +
                "[8] - Quick Sem Pivo\n" +
                "[9] - Quick Sem Pivo Iterativo\n" +
                "[10] - Quick Com Pivo\n" +
                "[11] - Merge Sort 1\n" +
                "[12] - Merge Sort 2\n" +
                "[13] - Counting\n" +
                "[14] - Bucket Sort\n" +
                "[15] - Radix Sort\n" +
                "\n[0] - Exibir Lista\n" +
                "\n[20] - SAIR\n\n" +
                "Opção Desejada: ");
        op = scanner.nextByte();
        System.out.printf("\n---------------------------------------------\n");
        return op;
    }

    public static void main(String[] args) {
        byte op;
        Scanner scanner = new Scanner(System.in);
        Lista lista = new Lista();
        lista.carregarValores();

        do {
            op = menu();
            switch(op) {
                case 1:
                    System.out.println("\n\t- - - Inserseção Direta - - -" +
                            "\nDesordenado -> ");
                    lista.exibir();
                    lista.insercaoDireta();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\n\t- - - Inserseção Binaria - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.insersaoBinaria();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\n\t- - - Seleção Direta - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.selecaoDireta();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("\n\t- - - Bolha - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.bolha();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("\n\t- - - Shake - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.shake();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("\n\t- - - Heap - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.heap();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 7:
                    System.out.println("\n\t- - - Shell - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.shell();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 8:
                    System.out.println("\n\t- - - Quick S/Pivo - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.quickSemPivo();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 9:
                    System.out.println("\n   - - - Quick S/Pivo Iterativo - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.quickSemPivoIterativo();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 10:
                    System.out.println("\n\t- - - Quick C/Pivo - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.quickComPivo();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 11:
                    System.out.println("\n\t- - - Merge Sort 1 - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.mergeSort();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 13:
                    System.out.println("\n\t- - - Counting - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.countingSort();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 14:
                    System.out.println("\n\t- - - Bucket - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.bucket();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 15:
                    System.out.println("\n\t- - - Radix - - -" +
                            "\n\nDesordenado -> ");
                    lista.exibir();
                    lista.radixSort();
                    System.out.println("\nOrdenado -> ");
                    lista.exibir();
                    scanner.nextLine();
                    break;
                case 0:
                    lista.exibir();
                    scanner.nextLine();
                    break;
            }

        } while(op != 20);
    }
}
