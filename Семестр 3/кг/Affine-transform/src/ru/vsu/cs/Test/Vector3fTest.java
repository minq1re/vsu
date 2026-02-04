package ru.vsu.cs.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.Math.Vector3f;

public class Vector3fTest {
    @Test
    void add() {
        Vector3f v3f = new Vector3f(2,2,2);
        Vector3f vec = new Vector3f(1,1,1);
        vec = v3f.add(vec);
        Assertions.assertEquals(3,vec.getX());
        Assertions.assertEquals(3,vec.getY());
        Assertions.assertEquals(3,vec.getZ());
    }

    @Test
    void subtract() {
        Vector3f v3f = new Vector3f(3,3,3);
        Vector3f vec = new Vector3f(1,1,1);
        vec = v3f.subtract(vec);
        Assertions.assertEquals(2,vec.getX());
        Assertions.assertEquals(2,vec.getY());
        Assertions.assertEquals(2,vec.getZ());
    }

    @Test
    void multiply() {
        Vector3f vector3f = new Vector3f(3,3,3);
        int scalar = 2;
        Vector3f vec = vector3f.multiplyScalar(scalar);
        Assertions.assertEquals(6,vec.getX());
        Assertions.assertEquals(6,vec.getY());
        Assertions.assertEquals(6,vec.getZ());
    }

    @Test
    void divide() {
        Vector3f vector3f = new Vector3f(6,6,6);
        int scalar = 2;
        Vector3f vec = vector3f.divide(scalar);
        Assertions.assertEquals(3,vec.getX());
        Assertions.assertEquals(3,vec.getY());
        Assertions.assertEquals(3,vec.getZ());
        Assertions.assertThrows(ArithmeticException.class,()-> vector3f.divide(0));
    }

    @Test
    void normalization() {
        Vector3f v3f = new Vector3f(6,6,6);
        Vector3f vec = v3f.normalization();
        Assertions.assertEquals(0.5773503184318542, vec.getX());
        Assertions.assertEquals(0.5773503184318542, vec.getY());
        Assertions.assertEquals(0.5773503184318542, vec.getZ());
    }

    @Test
    void multiplyScalar() {
        Vector3f v3f = new Vector3f(6,6,6);
        Vector3f vec = new Vector3f(2,2,2);
        float x = v3f.multiply(vec);
        Assertions.assertEquals(36, x);
    }

    @Test
    void multiplyVector() {
        Vector3f v3f = new Vector3f(6,6,6);
        Vector3f vec = new Vector3f(2,2,2);
        vec = v3f.multiplyVector(vec);
        Assertions.assertEquals(0,vec.getX());
        Assertions.assertEquals(0,vec.getY());
        Assertions.assertEquals(0,vec.getZ());
        v3f = new Vector3f(1,2,3);
        vec = new Vector3f(3,2,1);
        vec = v3f.multiplyVector(vec);
        Assertions.assertEquals(-4,vec.getX());
        Assertions.assertEquals(8,vec.getY());
        Assertions.assertEquals(-4,vec.getZ());
    }
}
