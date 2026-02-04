package ru.vsu.cs.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.Math.Vector4f;

public class Vector4fTest {
    @Test
    void add() {
        Vector4f v4f = new Vector4f(2,2,2,2);
        Vector4f vec = new Vector4f(1,1,1,1);
        vec = v4f.add(vec);
        Assertions.assertEquals(3,vec.getX());
        Assertions.assertEquals(3,vec.getY());
        Assertions.assertEquals(3,vec.getZ());
        Assertions.assertEquals(3,vec.getW());
    }

    @Test
    void subtract() {
        Vector4f v4f = new Vector4f(3,3,3,3);
        Vector4f vec = new Vector4f(1,1,1,1);
        vec = v4f.subtract(vec);
        Assertions.assertEquals(2, vec.getX());
        Assertions.assertEquals(2, vec.getY());
        Assertions.assertEquals(2, vec.getZ());
        Assertions.assertEquals(2, vec.getW());
    }

    @Test
    void multiply() {
        Vector4f v4f = new Vector4f(3,3,3,3);
        int scalar = 2;
        Vector4f vec = v4f.multiplyScalar(scalar);
        Assertions.assertEquals(6, vec.getX());
        Assertions.assertEquals(6, vec.getY());
        Assertions.assertEquals(6, vec.getZ());
        Assertions.assertEquals(6, vec.getW());
    }

    @Test
    void divide() {
        Vector4f v4f = new Vector4f(6,6,6,6);
        int scalar = 2;
        Vector4f vec = v4f.divide(scalar);
        Assertions.assertEquals(3, vec.getX());
        Assertions.assertEquals(3, vec.getY());
        Assertions.assertEquals(3, vec.getZ());
        Assertions.assertEquals(3, vec.getW());
        Assertions.assertThrows(ArithmeticException.class,()-> v4f.divide(0));
    }

    @Test
    void normalize() {
        Vector4f vector4f = new Vector4f(6,6,6,6);
        Vector4f vec = vector4f.normalization();
        Assertions.assertEquals(0.5, vec.getX());
        Assertions.assertEquals(0.5, vec.getY());
        Assertions.assertEquals(0.5, vec.getZ());
        Assertions.assertEquals(0.5, vec.getW());
    }

    @Test
    void multiplyScalar() {
        Vector4f vector4f = new Vector4f(6,6,6,6);
        Vector4f vec = new Vector4f(2,2,2,2);
        float x = vector4f.multiply(vec);
        Assertions.assertEquals(48, x);
    }
}
