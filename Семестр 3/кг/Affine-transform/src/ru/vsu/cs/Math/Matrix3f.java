package ru.vsu.cs.Math;

public class Matrix3f {
    private static final int size = 3;
    private float[][] elements = new float[size][size];

    public Matrix3f(float[][] elements) {
        this.elements = elements;
    }

    /**
     * Сложение матриц
     * @param other - вторая матрица, которая будет добавляться к текущей
     * @return - новый экземпляр матрицы, полученый в результате сложения первой и второй
     */
    public Matrix3f add(Matrix3f other) {
        return new Matrix3f(Matrices.add(this.elements, other.elements));
    }

    /**
     * Вычитание матриц
     * @param other - вторая матрица, которая будет вычитать из первой
     * @return - новый экземпляр матрицы, полученный врезультате вычитаний из первой вторую матрицу
     */
    public Matrix3f subtract(Matrix3f other) {
        return new Matrix3f(Matrices.subtract(this.elements, other.elements));
    }

    /**
     * Умножение текущей матрицы на вектор
     * @param vector - вектор, на который нужно умножить матрицу
     * @return - вектор, полученный в результате умножения матрицы на вектор
     */
    public Vector3f multiply(Vector3f vector) {
        float[] v = Matrices.multiply(this.elements, new float [] {vector.getX(), vector.getY(), vector.getZ()});
        return new Vector3f(v[0], v[1], v[2]);
    }

    /**
     * Перемножение двух матриц
     * @param other - матрица, на которую будет происхожит умножение
     * @return - матрица, размерности mxn * x*y = m*y
     */
    public Matrix3f multiply(Matrix3f other) {
        return new Matrix3f(Matrices.multiply(this.elements, other.elements));
    }

    /**
     * Создание единичной матрицы
     * @return - единичная матрица
     */
    public static Matrix3f createUnitMatrix() {
        return new Matrix3f(Matrices.createUnitMatrix(size));
    }

    /**
     * Создание нулевой матрицы
     * @return - нулевая матрица
     */
    public static Matrix3f createNullMatrix() {
        return new Matrix3f(Matrices.createNullMatrix(size));
    }

    /**
     * Получение определителя для текущей матрицы
     * @return - число float - determinant
     */
    public float getDet() { return Matrices.getDet(elements); }

    /**
     * Получить обратную матрицу данной матрицы, если такой возможно (т.е. det != 0)
     * @return - новый экземпляр матрицы 3x3, которая является обратной матрицой текущей
     */
    public Matrix3f inverse() {
        return new Matrix3f(Matrices.inverseMatrix(this.elements));
    }

    /**
     * Транспонирование матрицы
     * @return - транспонированная матрица
     */
    public Matrix3f transpose() {
        return new Matrix3f(Matrices.transpose(this.elements));
    }

    public void setMatrix(float[][] matrix) {
        this.elements = matrix;
    }

    public float[][] getMatrix() {
        return elements;
    }
}
