package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);
        int notas;
        double valorSacar;
        valorSacar = sc.nextDouble();

        System.out.println("NOTAS:");

        notas = (int)valorSacar/100;
        valorSacar %= 100;
        System.out.println(notas + " nota(s) de R$ 100.00");

        notas = (int)valorSacar/50;
        valorSacar %= 50;
        System.out.println(notas + " nota(s) de R$ 50.00");

        notas = (int)valorSacar/20;
        valorSacar %= 20;
        System.out.println(notas + " nota(s) de R$ 20.00");

        notas = (int)valorSacar/10;
        valorSacar %= 10;
        System.out.println(notas + " nota(s) de R$ 10.00");

        notas = (int)valorSacar/5;
        valorSacar %= 5;
        System.out.println(notas + " nota(s) de R$ 5.00");

        notas = (int)valorSacar/2;
        valorSacar %= 2;
        System.out.println(notas + " nota(s) de R$ 2.00");

        valorSacar *= 100;

        System.out.println("MOEDAS:");
        notas = (int)valorSacar/100;
        valorSacar %= 100;
        System.out.println(notas + " moeda(s) de R$ 1.00");

        notas = (int)valorSacar/50;
        valorSacar %= 50;
        System.out.println(notas + " moeda(s) de R$ 0.50");

        notas = (int)valorSacar/25;
        valorSacar %= 25;
        System.out.println(notas + " moeda(s) de R$ 0.25");

        notas = (int)valorSacar/10;
        valorSacar %= 10;
        System.out.println(notas + " moeda(s) de R$ 0.10");

        notas = (int)valorSacar/5;
        valorSacar %= 5;
        System.out.println(notas + " moeda(s) de R$ 0.05");

        notas = (int)valorSacar/1;
        valorSacar %= 1;
        System.out.println(notas + " moeda(s) de R$ 0.01");


    }
}
