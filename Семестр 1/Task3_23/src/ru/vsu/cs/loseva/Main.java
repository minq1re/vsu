package ru.vsu.cs.loseva;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final Line L1 = new Line(0, 0, -2.0);
    public static final VerticalParabola P1 = new VerticalParabola(0, -5, 0.5);
    public static final Rectangle R1 = new Rectangle(-6, -2, -1, 4);

    public static SimpleColor getColor(double x, double y) {

        if (L1.isPointAboveLine(x, y) && R1.isPointInRectangle(x, y)) {
            return SimpleColor.ORANGE;
        }

        else if (P1.isPointAboveParabola(x, y) && L1.isPointAboveLine(x, y) && !R1.isPointInRectangle(x, y)) {
            return SimpleColor.YELLOW;
        }

        else if (!L1.isPointAboveLine(x, y) && P1.isPointAboveParabola(x, y)  && R1.isPointBellowRectanglesLine(y) || !P1.isPointAboveParabola(x, y) && L1.isPointAboveLine(x, y)) {
            return SimpleColor.GRAY;

        } else {
            return SimpleColor.BLUE;
        }
    }

    public static void printColorForPoint(double x, double y) {
        System.out.printf("(%.3f, %.3f) -> %s%n", x, y, getColor(x, y));
    }

    public static void tests() {
        printColorForPoint(-1.5, 3.5);
        printColorForPoint(7, 7);
        printColorForPoint(1, 5);
        printColorForPoint(-8, -3);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        tests();

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите x: ");
        double x = sc.nextDouble();
        System.out.print("Введите y: ");
        double y = sc.nextDouble();

        printColorForPoint(x, y);
    }
}