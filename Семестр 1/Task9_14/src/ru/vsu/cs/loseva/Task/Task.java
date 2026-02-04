package ru.vsu.cs.loseva.Task;

import java.util.List;

public class Task {
    public static void process (List<Integer> list) {
        int max = findMax(list), min = findMin(list);
        int indMax = firstIndexOf(list, max);
        int indMin = lastIndexOf(list, min);
        int replace;
        for (int i = indMax + 1, j = indMin - 1; i < (indMin - indMax) / 2; i++, j--) {
            replace = list.get(i);
            list.set(i, list.get(j));
            list.set(j, replace);
        }
        System.out.println(list);
    }

    public static int findMax(List<Integer> list) {
        int max = list.get(0);
        for (Integer i: list) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }

    public static int findMin(List<Integer> list) {
        int min = list.get(0);
        for (Integer i: list) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    public static int firstIndexOf(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); i++) {
            if (value == list.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(List<Integer> list, int value) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (value == list.get(i)) {
                return i;
            }
        }
        return -1;
    }
}
