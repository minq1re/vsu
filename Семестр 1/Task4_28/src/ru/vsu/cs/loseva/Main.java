package ru.vsu.cs.loseva;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a = readInt("Введите начальное число: ");
        int b = readInt("Введите конечное число: ");
        System.out.println(count(a, b));
    }

    public static int count(int a, int b) {
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (isProgressAl(i) || isProgressGeom(i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isProgressAl(int n) {
        if (n < 100) {
            return true;
        }
        int first = n % 10 - n / 10 % 10;
        n /= 10;
        while (n / 10 > 0) {
            int second = n % 10 - n / 10 % 10;
            if (second == first && second != 0) {
                first = second;
                n /= 10;
            }
            else {
                return false;
            }
        }
        return true;
    }
    public static boolean isProgressGeom(int n){
       if (n < 100) {
           return true;
       }
       if (isItZero(n)) {
           return false;
       }
       int n10 = n % 10;
       int n101 = n / 10 % 10;
       double first = (double) n10 / n101;
       n /= 10;
       while (n / 10 > 0) {
           n10 = n % 10;
           n101 = n / 10 % 10;
           double second = (double) n10 / n101;
           if (second == first && second != 1) {
               first = second;
               n /= 10;
           }
           else {
               return false;
           }
       }
       return true;
    }

    public static boolean isItZero(int n) {
        while (n > 0) {
            if (n % 10 == 0) {
                return true;
            }
            n /= 10;
        }
        return false;
    }
    private static int readInt(String message) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(message);
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (Exception ex) {
                System.out.println("Неккоректные данные");
            }
        }
    }
}
