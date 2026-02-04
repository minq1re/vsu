package ru.vsu.cs.Math;

import java.io.IOException;

public class Vector2f {
    private float x, y;
    private final float length;

    /**
     * Конструктор  вектора
     * @param x - первое значение
     * @param y - второе значение
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
        this.length = (float)Math.sqrt(x * x + y * y);
    }

    public Vector2f(float[] elements) {
        this.x = elements[0];
        this.y = elements[1];
        this.length = (float)Math.sqrt(x * x + y * y);
    }

    /**
     * Сложение двух векторов
     * @param vector - вектор, который будет прибавляться к текущему
     * @return - новый вектор, получившийся в результате this vector + vector
     */
    public Vector2f add(Vector2f vector) {

        return new Vector2f(this.x + vector.x, this.y + vector.y);
    }

    /**
     * Вычитание векторов
     * @param vector - вектор, который будет вычитаться из текущего
     * @return - новый вектор, получившийся в результате this vector - vector
     */
    public Vector2f subtract(Vector2f vector) {

        return new Vector2f(this.x - vector.x, this.y - vector.y);
    }

    /**
     * Умножение текущего вектора на скалярное значение
     * @param scalar - скаляр
     * @return - новый вектор, получившийся в результате this vector * scalar
     */
    public Vector2f multiplyScalar(float scalar) {

        return new Vector2f(this.x * scalar, this.y * scalar);
    }

    /**
     * Деление текущего вектора на скалярное значение
     * @param scalar - скаляр
     * @return - новый вектор, получившийся в результате this vector / scalar
     */
    public Vector2f divide(float scalar) {
        if (scalar == 0) {
            throw new ArithmeticException("Exception: scalar is zero!");
        } else {
            return new Vector2f(this.x / scalar, this.y / scalar);
        }
    }

    /**
     * Метод, который возвращает длину вектора (не кол-во элементов!)
     * @return - длина вектора
     */
    public float getLength() { return length; }


    /**
     * Метод, нормализующий текущий вектор
     * @return - новый вектор, нормализованный текущему
     */
    public Vector2f normalization() {

        return new Vector2f(this.x / length, this.y / length);
    }

    /**
     * Умножение вектора на вектор
     * @param vector - вектор на который происходит умножение
     * @return - новый вектор, получившийся в результате перемножения векторов this vector * vector
     */
    public float multiply(Vector2f vector) {

        return this.x * vector.x + this.y * vector.y;
    }

    /**
     * Получить элемент по его индексу
     * @param index - номер элемента
     * @return - элемент
     */
    public float getElement(int index) {
        return switch (index) {
            case 0 -> x;
            case 1 -> y;
            default -> throw new IndexOutOfBoundsException("Invalid index!");
        };
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
