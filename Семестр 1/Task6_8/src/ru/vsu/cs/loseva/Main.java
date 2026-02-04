package ru.vsu.cs.loseva;

import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static double readIntervalR() {
        while (true) {
            double x = readDouble("x = ");
            if (Math.abs(x) < 1) {
                return x;
            } else {
                System.out.println("Абсолютная величина > 1");
            }
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        double x = readIntervalR();
        int n = readInt("n = ");
        double e = Math.abs(readDouble("e = "));

        System.out.println("1) Сумма n слагаемых: " + cycleN(x, n));
        System.out.println("2) Сумма слагаемых больше e: " + cycleE(x, e));
        System.out.println("3) Сумма слагаемых больше e/10: " + cycleE(x, e / 10));

        System.out.println("4) Значение функции с помощью метода Math: " + 1 / Math.sqrt(1 + x));
    }

    public static double cycleN(double x, int n) {
        double sum = 1;
        double current, previous = 1;

        for (int i = 1; i < n; i++) {
            current = previous * (-x) * (2 * i - 1) / (2 * i);
            sum += current;
            previous = current;
        }
        return sum;
    }

    public static double cycleE(double x, double e) {
        double sum = 1;
        double current = 1, previous = 1;

        for (int i = 1; Math.abs(current) > e; i++) {
            current = previous * (-x) * (2 * i - 1) / (2 * i);
            sum += current;
            previous = current;
        }
        return sum;
    }

    public static double readDouble(String message) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(message);
                String input = sc.nextLine();
                return Double.parseDouble(input);
            } catch (Exception ex) {
                System.out.println("Неккоректные данные");
            }
        }
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