#include <iostream>
#include "task1.h"
using namespace std;

double** createMatrix(int rows, int cols) {
    double** matrix = new double* [rows];  // Выделяем память для строк
    for (int i = 0; i < rows; i++) {
        matrix[i] = new double[cols];  // Выделяем память для каждого столбца
    }
    return matrix;
}

void deleteMatrix(double** matrix, int rows) {
    for (int i = 0; i < rows; i++) {
        delete[] matrix[i]; 
    }
    delete[] matrix; 
}

void fillMatrix(double** matrix, int rows, int cols) {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            matrix[i][j] = rand() % 100;  
        }
    }
}

void printMatrix(double** matrix, int rows, int cols) {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            cout << matrix[i][j] << "\t";
        }
        cout << endl;
    }
}

void reverseMatrix(double** matrix, int rows) {
    for (int i = 0; i < rows / 2; i++) {
        double* temp = matrix[i];
        matrix[i] = matrix[rows - i - 1];
        matrix[rows - i - 1] = temp;
    }
}

int main() {
    setlocale(LC_ALL, "");

    testSwapRows();
    testOddRows();
    cout << "Все тесты пройдены успешно!" << endl;
    cout << "\n";

    int rows, cols;
    cout << "Введите количество строк и столбцов: ";
    cin >> rows >> cols;

    double** matrix = createMatrix(rows, cols);
    fillMatrix(matrix, rows, cols);

    cout << "Исходная матрица:\n";
    printMatrix(matrix, rows, cols);

    reverseMatrix(matrix, rows);

    cout << "Матрица после обмена чётных и нечётных строк:\n";
    printMatrix(matrix, rows, cols);

    deleteMatrix(matrix, rows);  // Освобождаем память
    return 0;
}

