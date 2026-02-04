package ru.vsu.cs.Math;

public class Matrix4f {
    private static final int size = 4;
    private float[][] elements = new float[size][size];

    public Matrix4f(float[][] elements) {
        this.elements = elements;
    }

    /**
     * Сложение матриц
     * @param other - вторая матрица, которая будет добавляться к текущей
     * @return - новый экземпляр матрицы, полученый в результате сложения первой и второй
     */
    public Matrix4f add(Matrix4f other) {
        return new Matrix4f(Matrices.add(this.elements, other.elements));
    }

    /**
     * Вычитание матриц
     * @param other - вторая матрица, которая будет вычитать из первой
     * @return - новый экземпляр матрицы, полученный врезультате вычитаний из первой вторую матрицу
     */
    public Matrix4f subtract(Matrix4f other) {
        return new Matrix4f(Matrices.subtract(this.elements, other.elements));
    }

    /**
     * Умножение текущей матрицы на вектор
     * @param vector - вектор, на который нужно умножить матрицу
     * @return - вектор, полученный в результате умножения матрицы на вектор
     */
    public Vector4f multiply(Vector4f vector) {
        float[] v = Matrices.multiply(this.elements, new float [] {vector.getX(), vector.getY(), vector.getZ(), vector.getW()});
        return new Vector4f(v[0], v[1], v[2], v[3]);
    }

    /**
     * Перемножение двух матриц
     * @param other - матрица, на которую будет происхожит умножение
     * @return - матрица, размерности mxn * x*y = m*y
     */
    public Matrix4f multiply(Matrix4f other) {
        return new Matrix4f(Matrices.multiply(this.elements, other.elements));
    }

    /**
     * Создание единичной матрицы
     * @return - единичная матрица
     */
    public static Matrix4f createUnitMatrix() {
        return new Matrix4f(Matrices.createUnitMatrix(size));
    }

    /**
     * Создание нулевой матрицы
     * @return - нулевая матрица
     */
    public static Matrix4f createNullMatrix() {
        return new Matrix4f(Matrices.createNullMatrix(size));
    }

    /**
     * Получение определителя для текущей матрицы
     * @return - число float - determinant
     */
    public float getDet() {
        return Matrices.getDet(elements);
    }

    /**
     * Получить обратную матрицу данной матрицы, если такой возможно (т.е. det != 0)
     * @return - новый экземпляр матрицы 4x4, которая является обратной матрицой текущей
     */
    public Matrix4f inverse() {
        return new Matrix4f(Matrices.inverseMatrix(this.elements));
    }

    /**
     * Транспонирование матрицы
     * @return - транспонированная матрица
     */
    public Matrix4f transpose() {
        return new Matrix4f(Matrices.transpose(this.elements));
    }

    public void setMatrix(float[][] matrix) {
        this.elements = matrix;
    }

    public float[][] getMatrix() {
        return elements;
    }
}