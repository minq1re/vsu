package ru.vsu.cs.Math;

public class Vector3f {
    private float x, y, z;
    private final float length;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.length = (float)Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f(float[] elements) {
        this.x = elements[0];
        this.y = elements[1];
        this.z = elements[2];
        this.length = (float)Math.sqrt(x * x + y * y + z * z);
    }

    // add - add vectors (+)
    public Vector3f add(Vector3f vector) {

        return new Vector3f(this.x + vector.x, this.y + vector.y, this.z + vector.z);
    }

    // subtract vectors (-)
    public Vector3f subtract(Vector3f vector) {

        return new Vector3f(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }

    // multiply vector (*)
    public Vector3f multiplyScalar(float scalar) {

        return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    // divide vector (/)
    public Vector3f divide(float scalar) {
        if (scalar == 0) {
            throw new ArithmeticException("Exception: scalar is null!");
        } else {
            return new Vector3f(this.x / scalar, this.y / scalar, this.z / scalar);
        }
    }

    // get length
    public float getLength() { return length; }


    // get normalize vector
    public Vector3f normalization() {

        return new Vector3f(this.x / length, this.y / length, this.z / length);
    }

    // get multiply scalar (result = scalar)
    public float multiply(Vector3f vector) {

        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    // get multiply vector (result = vector)
    public Vector3f multiplyVector(Vector3f vector) {

        return new Vector3f(this.y * vector.z - this.z * vector.y, this.z * vector.x - this.x * vector.z, this.x * vector.y - this.y * vector.x);
    }

    public float getElement(int index) {
        return switch (index) {
            case 0 -> x;
            case 1 -> y;
            case 2 -> z;
            default -> throw new IndexOutOfBoundsException("Invalid index!");
        };
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float[] getElements() { return new float[] {x, y, z}; };
}
