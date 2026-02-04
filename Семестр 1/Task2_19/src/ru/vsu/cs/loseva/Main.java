package ru.vsu.cs.loseva;

import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        double r = readDouble("Введите радиус r = ");
        double a = readDouble("Введите сторону а = ");

        if (oneS(a, r)) {
            System.out.println("Достаточно 1 круга");
        }
        else if (twoS(a, r)) {
            System.out.println("Достаточно 2 кругов");
        }
        else if (threeS(a, r)) {
            System.out.println("Достаточно 3 кругов");
        }
        else if (fourS(a, r)) {
            System.out.println("Достаточно 4 кругов");
        }
        else {
            System.out.println("Кругов не хватает");
        }
    }

    public static boolean oneS(double x1, double y1) {
        return (y1 >= x1 * Math.sqrt(2) / 2);
    }

    public static boolean twoS(double x2, double y2) {
        return (y2 >= x2 * Math.sqrt(5) / 4);
    }

    public static boolean threeS(double x3, double y3) {
        return (y3 >= x3 * 17 / 32);
    }

    public static boolean fourS(double x4, double y4) {
        return (y4 >= x4 * Math.sqrt(2) / 4 );
    }

    private static double readDouble(String message) {
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
}
