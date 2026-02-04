package ru.vsu.cs.loseva;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import ru.vsu.cs.loseva.utils.SwingUtils;

public class Main {
    public static void main(String[] args) throws Exception {
        winMain();

    }
    public static void winMain() {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Calibre", 14);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }
    public static double[][] submatrix(double[][] matrix, int row, int column) {
        double[][] submatrix = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    submatrix[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return submatrix;
    }

    public static double determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("invalid dimensions");

        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i] * determinant(submatrix(matrix, 0, i));
        return det;
    }

    public static double[][] matrixInverse(double[][] matrix) {
        double[][] matrixInverse = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrixInverse[i][j] = Math.pow(-1, i + j)  * determinant(submatrix(matrix, i, j));

        double det = 1.0 / determinant(matrix);
        for (int i = 0; i < matrixInverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = matrixInverse[i][j];
                matrixInverse[i][j] = matrixInverse[j][i] * det;
                matrixInverse[j][i] = temp * det;
            }
        }
        if (det != 0) {
            return matrixInverse;
        } else {
            return matrix;
        }
    }
    public static double[][] check(double[][]matrix, double[][] matrixInverse) {
        double[][] matrix1 = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrixInverse[0].length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    matrix1[i][j] += matrix[i][k] * matrixInverse[k][j];
                }
            }
        }
        return matrix1;
    }

    public static double[] inverseResult(double[][] linear) {
        // матрица без результатов
        double[][] matrix = new double[linear.length][linear[0].length - 1];
        for (int i = 0; i < linear.length; i++) {
            for (int j = 0; j < linear[0].length - 1; j++) {
                matrix[i][j] = linear[i][j];
            }
        }
        // только результаты
        double[] resultMatrix = new double[linear.length];
        for (int i = 0; i < linear.length; i++) {
            resultMatrix[i] = linear[i][linear[i].length - 1];
        }
        double[] result;
        if (determinant(matrix) == 0) {
            return new double[]{0};
        } else {
            // обратная матрица
            double[][] matrixInv = matrixInverse(matrix);
            result = new double[resultMatrix.length]; // массив неизвестных
            for (int i = 0; i < matrixInv.length; i++) {
                double sum = 0;
                for (int j = 0; j < resultMatrix.length; j++) {
                    sum += matrixInv[i][j] * resultMatrix[j];
                }
                result[i] = sum;
            }
        }
        return result;
    }

    public static double[] kramerResult(double[][] linear) {
        double[][] matrix = new double[linear.length][linear[0].length - 1];
        for (int i = 0; i < linear.length; i++) {
            for (int j = 0; j < linear[0].length - 1; j++) {
                matrix[i][j] = linear[i][j];
            }
        }
        double[] resultMatrix = new double[linear.length];
        for (int i = 0; i < linear.length; i++) {
            resultMatrix[i] = linear[i][linear[i].length - 1];
        }
        double det = (determinant(matrix));
        double[] result;
        if (det == 0) {
            return new double[]{0};
        } else {
            result = new double[resultMatrix.length];
            for (int i = 0; i < resultMatrix.length; i++) {
                double[][] newMatrix = replaceCol(matrix, i, resultMatrix);
                double detNew = determinant(newMatrix);
                result[i] = detNew / det;
            }
        }
        return result;
    }

    public static double[][] replaceCol(double[][] matrix, int j, double[] resultMatrix) {
        double[][] reverseMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {
                if (k == j) {
                    reverseMatrix[i][k] = resultMatrix[i];
                } else {
                    reverseMatrix[i][k] = matrix[i][k];
                }
            }
        }
        return reverseMatrix;
    }

    public static StringBuilder gauss(double[][] matrix) throws Exception {

        reductionToTriangular(matrix);
        int length = matrix.length;
        List<Integer> freeVariable = new ArrayList<>();
        List<Integer> baseVariable = new ArrayList<>();
        StringBuilder answerMatrix = new StringBuilder();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if ((i == j) && (matrix[i][j] == 0) && (matrix[i][length] != 0)) {
                    answerMatrix.append("Система несовместима");
                    return answerMatrix;
                } else if ((i == j) && (matrix[i][j] == 0) && (matrix[i][length] == 0)) {
                    freeVariable.add(i);
                } else if ((i == j) && (matrix[i][j] != 0)) {
                    baseVariable.add(i);
                }
            }
        }

        answerMatrix.append("Бесконечное множество решений:" + "\n");
        answerMatrix.append("Главные переменные: " + "\n");
        baseVariable.forEach(System.out::println);

        for (Integer i : baseVariable) {
            answerMatrix.append((i + 1) + "\n");
        }

        answerMatrix.append("\n");
        answerMatrix.append("Свободные переменные:" + "\n");

        for (Integer i : freeVariable) {
            answerMatrix.append((i + 1) + "\n");
        }
        answerMatrix.append("\n");

        for (int i = freeVariable.get(0) - 1; i >= 0; i--) {
            List<Double> valuesList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < length; j++) {
                valuesList.add(matrix[i][j]);
            }
            answerMatrix.append("x" + (i + 1) + " = " + matrix[i][length] / valuesList.get(0));
            for (int k = valuesList.size() - 1; k > 0; k--) {
                if (valuesList.get(k) / valuesList.get(0) * -1 < 0) {
                    answerMatrix.append(valuesList.get(k) / valuesList.get(0) * -1 + "*x" + (k + i + 1));
                } else {
                    answerMatrix.append(" + " + -1 * valuesList.get(k) / valuesList.get(0) + "*x" + (k + i + 1));
                }
            }

            answerMatrix.append("\n");
        }

        return answerMatrix;
    }

    public static double[][] reductionToTriangular(double[][] matrix) throws Exception {
        for (int count = 1; count < matrix.length; count++) {
            for (int i = count; i < matrix.length; i++) {
                if (matrix[count - 1][count - 1] != 0) {
                    double coefficient = matrix[i][count - 1] / matrix[count - 1][count - 1];
                    for (int j = count - 1; j < matrix[0].length; j++) {
                        matrix[i][j] = matrix[i][j] - matrix[count - 1][j] * coefficient;
                    }
                }
            }
        }

        return matrix;
    }
}