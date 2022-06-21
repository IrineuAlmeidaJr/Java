package com.company;

public class Main {

    public static void main(String[] args) {
        BTree tree = new BTree();

        for(int i=1; i <= 100; i++) {
            tree.inserir(i, i);
        }

        tree.exclusao(2);
        tree.exclusao(4);
        tree.exclusao(5);
        tree.exclusao(6);

        tree.in_ordem();

    }
}
