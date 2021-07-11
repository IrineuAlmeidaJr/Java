package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int count, min, max, num;
        boolean hasNextInt, firtTime = true;
        count = max = min = 0;

        while (true) {
            System.out.println("Enter number = ");
            hasNextInt = scanner.hasNextInt();
            if(hasNextInt) {
                num = scanner.nextInt();
                count++;
                if(firtTime){
                    firtTime = false;
                    max = min = num;
                }
                if(num > max) {
                    max = num;
                }
                if(num < min) {
                    min = num;
                }
                if(count == 5) {
                    break;
                }
            } else {
                System.out.println("Inavild number");
            }
            scanner.nextLine();
        }

        scanner.close();
        System.out.println("MAX = " + max + "\nMIN = " + min);
    }
}
