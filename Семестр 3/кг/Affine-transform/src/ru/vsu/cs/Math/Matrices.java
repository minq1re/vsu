package ru.vsu.cs.Math;

public abstract class Matrices {
    /**
     * Получение определителя матрицы
     * @param matrix - исходная матрица
     * @return - значение определителя (type - float)
     */
    protected static float getDet(float[][] matrix) {
        float result = 0;

        if (matrix.length == 1) {
            return matrix[0][0];
        }

        for (int j = 0; j < matrix[0].length; j++) {
            result += (float) (Math.pow(-1, j) * matrix[0][j] * getDet(cutLineAndColumn(matrix, 0, j)));
        }

        return result;
    }

    /**
     * Вырезание строки и столбца из двумерно массива
     * @param matrix - исходный массив
     * @param indexLine - номер строки
     * @param indexColumn - номер столбца
     * @return - исходная матрица, где будет отсутствовать indexLine строка и indexColumn столбец
     */
    private static float[][] cutLineAndColumn(float[][] matrix, int indexLine, int indexColumn) {
        return cutColumn(cutLine(matrix, indexLine), indexColumn);
    }

    private static float[][] cutLine(float[][] matrix, int index) {
        float[][] newMatrix = new float[matrix.length - 1][matrix[0].length];

        for (int i = 0; i < index; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, newMatrix[i].length);
        }
        for (int i = index; i < newMatrix.length; i++) {
            System.arraycopy(matrix[i + 1], 0, newMatrix[i], 0, newMatrix[i].length);
        }

        return newMatrix;
    }

    private static float[][] cutColumn(float[][] matrix, int index) {
        float[][] newMatrix = new float[matrix.length][matrix[0].length - 1];

        for (int i = 0; i < newMatrix.length; i++) {
            if (index >= 0) System.arraycopy(matrix[i], 0, newMatrix[i], 0, index);
            if (newMatrix[i].length - index >= 0)
                System.arraycopy(matrix[i], index + 1, newMatrix[i], index, newMatrix[i].length - index);
        }

        return newMatrix;
    }

    /**
     * Метод для реализации сложения матриц (должны быть одинаковой размерности)
     * @param elements - элементы первой матрицы
     * @param elements2 - элементы второй матрицы
     * @return - матрица, получившаяся в результате сложения elements AND elements2
     */
    protected static float[][] add(float[][] elements, float[][] elements2) {
        if ((elements.length != elements2.length) || (elements[0].length != elements2[0].length)) {
            throw new ArrayIndexOutOfBoundsException("Матрицы разной размерности! Операция невозможна!");
        } else {
            float[][] result = new float[elements.length][elements.length];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result.length; j++) {
                    result[i][j] = elements[i][j] + elements2[i][j];
                }
            }

            return result;
        }
    }

    /**
     * Метод для реализации вычитания матриц (должны быть одинаковой размерности)
     * @param elements - элементы первой матрицы
     * @param elements2 - элементы второй матрицы
     * @return - матрица, получившаяся в результате вычитания из elements -> elements2
     */
    protected static float[][] subtract(float[][] elements, float[][] elements2) {
        if ((elements.length != elements2.length) || (elements[0].length != elements2[0].length)) {
            throw new ArrayIndexOutOfBoundsException("Матрицы разной размерности! Операция невозможна!");
        } else {
            float[][] result = new float[elements.length][elements.length];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result.length; j++) {
                    result[i][j] = elements[i][j] - elements2[i][j];
                }
            }

            return result;
        }
    }

    /**
     * Метод, перемножающий матрицу на вектор
     * @param elements - элементы матрицы
     * @param elements2 - вектор
     * @return - вектор (type - float)
     */
    protected static float[] multiply(float[][] elements, float[] elements2) {
        float[] result = new float[elements.length];

        if (elements[0].length != elements2.length) {
            throw new ArrayIndexOutOfBoundsException("Кол-во строк первого массива не равно кол-ву столбцов второго!");
        } else {
            for (int i = 0; i < result.length; i++) {
                float element = 0;
                for (int j = 0; j < elements.length; j++) {
                    element += elements[i][j] * elements2[j];
                }
                result[i] = element;
            }

            return result;
        }
    }

    /**
     * Метод, перемножающий матрицы друг на друга (важно, чтобы кол-во элементов строк 1 матрицы = кол-во элементам столбцов второй матрицы!
     * Иначе перемножение НЕВОЗМОЖНО!)
     * @param elements - элементы первой матрицы
     * @param elements2 - элементы второй матрцы
     * @return - матрица, получившаяся в результате перемножения elements на elements2 (elements * elements2 != elements2 * elemenets)
     */
    protected static float[][] multiply(float[][] elements, float[][] elements2) {
        float[][] result = new float[elements.length][elements.length];

        if (elements.length != elements2[0].length) {
            throw new ArrayIndexOutOfBoundsException("Кол-во строк первого массива не равно кол-ву столбцов второго!");
        } else {
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result.length; j++) {
                    float element = 0;
                    for (int k = 0; k < result.length; k++) {
                        element += elements[i][k] * elements2[k][j];
                    }

                    result[i][j] = element;
                }
            }

            return result;
        }
    }

    /**
     * Метод для реализации умножения матрицы на скалярное значение
     * @param elements - элементы матрциы
     * @param scalar - скалярное значение, на которое нужно умножить матрицу
     * @return - матрица elements, умноженная на scalar
     */
    protected static float[][] multiplyScalar(float[][] elements, float scalar) {
        float[][] result = new float[elements.length][elements.length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[i][j] = elements[i][j] * scalar;
            }
        }

        return result;
    }

    /**
     * Создать нулевую матрицу
     * @param size - размер матрицы
     * @return - нулевая матрица размерности size X size
     */
    protected static float[][] createNullMatrix(int size) {
        float[][] nullMatrix = new float[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                nullMatrix[i][j] = 0;
            }
        }

        return nullMatrix;
    }

    /**
     * Создать единичную матрицу
     * @param size - размер матрицы
     * @return - единичная матрица размерности size X size
     */
    protected static float[][] createUnitMatrix(int size) {
        float[][] unitMatrix = new float[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                unitMatrix[i][j] = (i == j) ? 1 :  0;
            }
        }

        return unitMatrix;
    }

    /**
     * Метод для получение обратной матрицы
     * @param elements - исходная матрица
     * @return - обратная матрица исходной матрицы
     */
    protected static float[][] inverseMatrix(float[][] elements) {
        float det = getDet(elements);
        if (det == 0) {
            throw new ArithmeticException("Matrix is not invertible");
        }

        float[][] adjMatrix = new float[elements.length][elements.length];

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                float adj = (float) Math.pow(-1, i + j) * getDet(cutLineAndColumn(elements, i, j));
                if (Math.abs(adj) == 0) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = adj;
                }
            }
        }

        return transpose(multiplyScalar(adjMatrix, 1 / det));
    }

    /**
     * Транспонирование матрицы
     * @param elements - исходная матрица
     * @return - транспонированная матрица
     */
    protected static float[][] transpose(float[][] elements) {
        float[][] result = new float[elements.length][elements.length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[j][i] = elements[i][j];
            }
        }

        return result;
    }
}
