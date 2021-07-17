package com.company;

public class Main {

    public static void main(String[] args) {

        int num = 9;

        System.out.println("The sum of the digits in number 125 is  " + sumDigits(125));
        System.out.println("The sum of the digits in number -125 is  " + sumDigits(-125));
        System.out.println("The sum of the digits in number 4 is  " + sumDigits(4));
        System.out.println("The sum of the digits in number 32123 is  " + sumDigits(32123));

        System.out.println(isPalindrome(-111));
    }


    public static int sumDigits(int num) {

        if(num < 10) {
            return -1;
        }

        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static boolean isPalindrome (int number) {

        int aux = number;
        int reverse = 0;
        while (aux != 0) {
            reverse = reverse * 10 + aux % 10;
            aux /= 10;
        }
       return number == reverse;

    }

}
