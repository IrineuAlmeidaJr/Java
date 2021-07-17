package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int i = 1, sum = 0;
        boolean hasNextInt;

//        while(i <= 10) {
//            System.out.println("Enter number #" + i + ": ");
//            hasNextInt = scanner.hasNextInt();
//            if(hasNextInt) {
//                sum += scanner.nextInt();
//                i++;
//            } else {
//                System.out.println("Erro");
//            }
//            scanner.nextLine();
//        }

        while(true) {
            System.out.println("Enter number #" + i + ": ");
            hasNextInt = scanner.hasNextInt();
            if(hasNextInt) {
                sum += scanner.nextInt();
                i++;
                if(i > 10) {
                    break;
                }
            } else {
                System.out.println("Invalid number");
            }
            scanner.nextLine();
        }

        scanner.close();
        System.out.println("Sum = " + sum);
    }
}
