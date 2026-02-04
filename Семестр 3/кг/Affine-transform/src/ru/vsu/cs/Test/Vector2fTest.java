package ru.vsu.cs.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.Math.Vector2f;

public class Vector2fTest {
    @Test
    void add() {
        Vector2f v2f = new Vector2f(2,2);
        Vector2f vec = new Vector2f(1,1);
        vec = v2f.add(vec);
        Assertions.assertEquals(3,vec.getX());
        Assertions.assertEquals(3,vec.getY());
    }

    @Test
    void subtract(){
        Vector2f v2f = new Vector2f(3,3);
        Vector2f vec = new Vector2f(1,1);
        vec = v2f.subtract(vec);
        Assertions.assertEquals(2,vec.getX());
        Assertions.assertEquals(2,vec.getY());
    }

    @Test
    void multiply() {
        Vector2f v2f = new Vector2f(3,3);
        int scalar = 2;
        Vector2f vec = v2f.multiplyScalar(scalar);
        Assertions.assertEquals(6,vec.getX());
        Assertions.assertEquals(6,vec.getY());
    }

    @Test
    void divide() {
        Vector2f v2f = new Vector2f(6,6);
        int scalar = 2;
        Vector2f vec = v2f.divide(scalar);
        Assertions.assertEquals(3,vec.getX());
        Assertions.assertEquals(3,vec.getY());
        Assertions.assertThrows(ArithmeticException.class,()-> v2f.divide(0));
    }

    @Test
    void normalization() {
        Vector2f v2f = new Vector2f(6,6);
        Vector2f vec = v2f.normalization();
        Assertions.assertEquals(0.7071068286895752,vec.getX());
        Assertions.assertEquals(0.7071068286895752,vec.getY());
    }

    @Test
    void multiplyScalar() {
        Vector2f v2f = new Vector2f(6,6);
        Vector2f vec = new Vector2f(2,2);
        float x = v2f.multiply(vec);
        Assertions.assertEquals(24,x);
    }
}
