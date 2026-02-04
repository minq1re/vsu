package ru.vsu.cs.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.Math.Matrix3f;
import ru.vsu.cs.Math.Vector3f;
import java.util.Arrays;

public class Matrix3fTest {
    @Test
    void add() {
        Matrix3f m3f = new Matrix3f(new float[][] {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        Matrix3f m = new Matrix3f(new float[][] {{9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}});
        Matrix3f result = m3f.add(m);
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{10, 10, 10},
                {10, 10, 10},
                {10, 10, 10}}, result.getMatrix()));

        Matrix3f m2 = new Matrix3f(new float[][] {{9, 8, 7, 1},
                {6, 5, 4, 1},
                {3, 2, 1, 1}});
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> m3f.add(m2));
    }

    @Test
    void subtract() {
        Matrix3f m3f = new Matrix3f(new float[][] {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        Matrix3f m = new Matrix3f(new float[][] {{9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}});
        Matrix3f result = m3f.subtract(m);
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{-8, -6, -4},
                {-2, 0, 2},
                {4, 6, 8}}, result.getMatrix()));

        Matrix3f m2 = new Matrix3f(new float[][] {{9, 8, 7, 1},
                {6, 5, 4, 1},
                {3, 2, 1, 1}});
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> m3f.subtract(m2));
    }

    @Test
    void multiply() {
        Matrix3f m3f = new Matrix3f(new float[][] {{1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}});
        Matrix3f m = new Matrix3f(new float[][] {{2, 1, 1},
                {1, 2, 1},
                {1, 1, 2}});
        Matrix3f result = m3f.multiply(m);
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{4, 4, 4},
                {8, 8, 8},
                {12, 12, 12}}, result.getMatrix()));

        Matrix3f m2 = new Matrix3f(new float[][] {{2, 1, 1, 5},
                {1, 2, 1, 4},
                {1, 1, 2, 4}});
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> m3f.multiply(m2));
    }

    @Test
    void multiplyVector() {
        Matrix3f m3f = new Matrix3f(new float[][] {{1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}});
        Vector3f vec = new Vector3f(new float[] {2, 1, 1});
        Vector3f result = m3f.multiply(vec);
        Assertions.assertArrayEquals(new float[]{4, 8, 12}, result.getElements());
    }

    @Test
    void createUnitMatrix() {
        Matrix3f m3f = Matrix3f.createUnitMatrix();
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}}, m3f.getMatrix()));
    }

    @Test
    void createNullMatrix() {
        Matrix3f m3f = Matrix3f.createNullMatrix();
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}}, m3f.getMatrix()));
    }

    @Test
    void getDet() {
        Matrix3f m3f = new Matrix3f(new float[][] {{1, 3, -15},
                {4, 5, 6},
                {2, 8, 9}});
        float det = m3f.getDet();
        Assertions.assertEquals(-405, det);
    }

    @Test
    void getInverseMatrix() {
        Matrix3f m3f = new Matrix3f(new float[][] {
                {0, 1, 3},
                {2, 3, 5},
                {3, 5, 7}});
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{
                {-1, 2, -1},
                {0.25f, -2.25f, 1.5f},
                {0.25f, 0.75f, -0.5f}
        }, m3f.inverse().getMatrix()));

        Matrix3f m3f2 = new Matrix3f(new float[][] {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}});
        Assertions.assertThrows(ArithmeticException.class, m3f2::inverse);
    }

    @Test
    void transpose() {
        Matrix3f m3f = new Matrix3f(new float[][] {{0, 1, 3},
                {2, 3, 5},
                {3, 5, 7}});
        Matrix3f transpose = m3f.transpose();
        Assertions.assertTrue(Arrays.deepEquals(new float[][]{{0, 2, 3},
                {1, 3, 5},
                {3, 5, 7}}, transpose.getMatrix()));
    }
}
