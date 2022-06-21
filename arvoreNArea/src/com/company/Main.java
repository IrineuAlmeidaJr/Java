package com.company;

public class Main {

    public static void main(String[] args) {
        NArea tree = new NArea();

        tree.inserir(1);
        tree.inserir(200);
        tree.inserir(3);
        tree.inserir(5);
        tree.inserir(7);
        tree.inserir(9);
        tree.inserir(100);
        tree.inserir(10);
        tree.inserir(12);
        tree.inserir(13);
        tree.inserir(14);
        tree.inserir(16);

        tree.in_ordem();

    }
}
