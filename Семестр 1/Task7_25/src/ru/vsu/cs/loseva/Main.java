package ru.vsu.cs.loseva;

import java.util.Scanner;
import static ru.vsu.cs.loseva.ArrayUtils.readIntArrayFromConsole;

public class Main {
    public static void main(String[] args) {
        tests();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] mas = readIntArrayFromConsole();
        System.out.print(solution(n, mas));
    }

    public static int solution(int n, int[] mas) {
        int k1 = 0;
        int k2 = 0;
        for (int i = 0; i < n; i++) {
            if (isprime(mas[i])) {
                k1 = i;
                break;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (isprime(mas[i])) {
                k2 = i;
                break;
            }
        }
        int sum = 0;
        for (int i = k1 + 1; i < k2; i++) {
            if (mas[i] % 2 == 0) {
                sum += mas[i];
            }
        }
        return sum;
    }

    public static boolean isprime(int mas) {
        int d = 2;
            while (d * d <= mas) {
                if (mas % d == 0){
                    return false;
                } else {
                    d += 1;
                }
            }
        return true;
    }

    public static void tests() {
        System.out.println(solution(10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})); // просто числа
        System.out.println(solution(9, new int[]{-1, -2, -3, -4, -5, -6, -7, -8, -9})); // отрицательные числа
        System.out.println(solution(3, new int[]{1, 3, 4})); // между простыми числами нет чисел
        System.out.println(solution(4, new int[]{4, 6, 8, 9})); // нет простых чисел
        System.out.println(solution(8, new int[]{0, 4, 8, 12, 16, 7, 24, 32})); // 0 как простое число
        System.out.println(solution(5, new int[]{1, 2, 3, 5, 7})); // все числа простые
        System.out.println(solution(8, new int[]{4, -3, -8, 12, -4, 8, 3, 32})); // и отриц, и полож
        System.out.println(solution(4, new int[]{2, 12, 8, 7})); //начинается с простого ЧЕТНОГО числа
    }
}

