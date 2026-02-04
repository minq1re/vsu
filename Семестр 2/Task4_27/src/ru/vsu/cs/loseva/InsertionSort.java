package ru.vsu.cs.loseva;

public class InsertionSort {
    public static <T> void sort(T[] data, int[] orderValues) {
        int n = orderValues.length;
        for (int i = 1; i < n; i++) {
            T keyT = data[i];
            int key = orderValues[i];
            int j = i - 1;
            while (j >= 0 && orderValues[j] > key) {
                orderValues[j+1] = orderValues[j];
                data[j+1] = data[j];
                j--;
            }
            orderValues[j+1] = key;
            data[j+1] = keyT;
        }
    }
}
