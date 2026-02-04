package ru.vsu.cs.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.Math.Matrix4f;
import ru.vsu.cs.Math.Vector4f;
import java.util.Arrays;

public class Matrix4fTest {
    @Test
    void add() {
        Matrix4f m4f = new Matrix4f(new float[][] {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        Matrix4f m = new Matrix4f(new float[][] {{16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}});
        Matrix4f result = m4f.add(m);
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{17, 17, 17, 17},
                {17, 17, 17, 17},
                {17, 17, 17, 17},
                {17, 17, 17, 17}}, result.getMatrix()));

        Matrix4f m2 = new Matrix4f(new float[][] {{8, 7, 1},
                {6, 5, 4},
                {3, 2, 1}});
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> m4f.add(m2));
    }

    @Test
    void subtract() {
        Matrix4f m4f = new Matrix4f(new float[][] {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        Matrix4f m = new Matrix4f(new float[][] {{16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}});
        Matrix4f result = m4f.subtract(m);
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{-15, -13, -11, -9},
                {-7, -5, -3, -1},
                {1, 3, 5, 7},
                {9, 11, 13, 15}}, result.getMatrix()));

        Matrix4f m2 = new Matrix4f(new float[][] {{8, 7, 1},
                {6, 5, 4},
                {3, 2, 1}});
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> m4f.subtract(m2));
    }

    @Test
    void multiply() {
        Matrix4f m4f = new Matrix4f(new float[][] {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        Matrix4f m = new Matrix4f(new float[][] {{16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}});
        Matrix4f result = m4f.multiply(m);
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{80, 70, 60, 50},
                {240, 214, 188, 162},
                {400, 358, 316, 274},
                {560, 502, 444, 386}}, result.getMatrix()));

        Matrix4f m2 = new Matrix4f(new float[][] {{8, 7, 1},
                {6, 5, 4},
                {3, 2, 1}});
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> m4f.multiply(m2));
    }

    @Test
    void multiplyVector() {
        Matrix4f m4f = new Matrix4f(new float[][] {{1, 1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3},
                {4, 4, 4, 4}});
        Vector4f vec = new Vector4f(new float[] {2, 1, 1, 2});
        Vector4f result = m4f.multiply(vec);
        Assertions.assertArrayEquals(new float[]{6, 12, 18, 24}, result.getElements());
    }

    @Test
    void createUnitMatrix() {
        Matrix4f m4f = Matrix4f.createUnitMatrix();
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}}, m4f.getMatrix()));
    }

    @Test
    void createNullMatrix() {
        Matrix4f m4f = Matrix4f.createNullMatrix();
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}}, m4f.getMatrix()));
    }

    @Test
    void getDet() {
        Matrix4f m4f = new Matrix4f(new float[][] {{1, 3, -15, 2},
                {4, 5, 6, 41},
                {2, 8, 9, 0},
                {1, 2, -5, 11}});
        float det = m4f.getDet();
        Assertions.assertEquals(-1714, det);
    }

    @Test
    void getInverseMatrix() {
        Matrix4f m4f = new Matrix4f(new float[][] {{0, 1, 3, 1},
                {2, 3, 5, 1},
                {3, 5, 7, 2},
                {1, 1, 1, 2}});

        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{-0.75f, 1.75f, -1, 0.5f},
                {0, -2, 1.5f, -0.5f},
                {0.25f, 0.75f, -0.5f, 0},
                {0.25f, -0.25f, 0, 0.5f}}, m4f.inverse().getMatrix()));

        Matrix4f m4f2 = new Matrix4f(new float[][] {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        Assertions.assertThrows(ArithmeticException.class, m4f2::inverse);
    }

    @Test
    void transpose() {
        Matrix4f m4f = new Matrix4f(new float[][] {{0, 1, 3, 1},
                {2, 3, 5, -5},
                {3, 5, 7, -1},
                {0, 0, 0, 0}});
        Matrix4f transpose = m4f.transpose();
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{0, 2, 3, 0},
                {1, 3, 5, 0},
                {3, 5, 7, 0},
                {1, -5, -1, 0}}, transpose.getMatrix()));
    }
}