package ru.vsu.cs.loseva;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int s = readInt("Введите площадь s >= 3: ");
        printPicture(s);
    }

    private static void printPicture(int s) {
        for (int j = 0; j < s / 2; j++) {
            System.out.print(" ");
        }
        for (int j = s / 2 + 1; j <= s; j++) {
            System.out.print("*");
        }

        for (int i = 1; i < s / 2; i++) {
            System.out.println();

            for (int j = 0; j <= s / 2; j++) {
                if (j < s / 2) {
                    System.out.print(" ");
                }
                else if (j == s / 2) {
                    System.out.print("*");
                }
            }

            for (int j = s / 2 + 1; j < s - 1; j++) {
                if (j > s / 2 & j < s - i - 1) {
                    System.out.print("#");
                }
                else if (j == s - i - 1) {
                    System.out.print("*");
                }
            }
        }

        for (int i = s / 2; i < s - 1; i++) {
            System.out.println();

            for (int j = 0; j < s - i - 1; j++) {
                System.out.print(" ");
            }

            for (int j = s - i - 1; j <= s / 2; j++) {
                if (j == s - i - 1) {
                    System.out.print("*");
                }
                else if (j > s - i - 1 & j < s / 2 - 1) {
                    System.out.print("#");
                }
                else if (j == s / 2 - 1) {
                    System.out.print("*");
                }
            }
        }
        System.out.println();
        for (int j = 1; j <= s / 2; j++) {
            System.out.print("*");
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
