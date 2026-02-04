package ru.vsu.cs.Math;

public class Vector4f {
    private float x, y, z, w;
    private final float length;

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.length = (float)Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Vector4f(float[] elements) {
        this.x = elements[0];
        this.y = elements[1];
        this.z = elements[2];
        this.w = elements[3];
        this.length = (float)Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Vector4f add(Vector4f vector) {

        return new Vector4f(this.x + vector.x, this.y + vector.y, this.z + vector.z, this.w + vector.w);
    }

    public Vector4f subtract(Vector4f vector) {

        return new Vector4f(this.x - vector.x, this.y - vector.y, this.z - vector.z, this.w - vector.w);
    }

    public Vector4f multiplyScalar(float scalar) {

        return new Vector4f(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    public Vector4f divide(float scalar) {
        if (scalar == 0) {
            throw new ArithmeticException("Exception: scalar is null!");
        } else {
            return new Vector4f(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
        }
    }

    public float getLength() { return length; }

    public Vector4f normalization() {

        return new Vector4f(this.x / length, this.y / length, this.z / length, this.w / length);
    }

    public float multiply(Vector4f vector) {

        return this.x * vector.x + this.y * vector.y + this.z * vector.z + this.w * vector.w;
    }

    public float getElement(int index) {
        return switch (index) {
            case 0 -> x;
            case 1 -> y;
            case 2 -> z;
            case 3 -> w;
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

    public void setW(float w) {
        this.w = w;
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

    public float getW() {
        return w;
    }

    public float[] getElements() { return new float[] {x, y, z, w}; }
}
