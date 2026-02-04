#include <iostream>
#include <cassert>
#include "Task1.h"

void test1() {
    int rows = 3, cols = 3;
    int** matrix = new int*[rows];
    for (int i = 0; i < rows; i++) {
        matrix[i] = new int[cols]{ i * cols + 1, i * cols + 2, i * cols + 3 };
    }
    
    int** reversed = reverse_rows(matrix, rows, cols);
    int expected[3][3] = {{7, 8, 9}, {4, 5, 6}, {1, 2, 3}};
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            assert(reversed[i][j] == expected[i][j]);
        }
    }
    
    freeMatrix(matrix, rows);
    freeMatrix(reversed, rows);
}

void test2() {
    int rows = 2, cols = 4;
    int** matrix = new int*[rows];
    for (int i = 0; i < rows; i++) {
        matrix[i] = new int[cols]{ i * cols + 1, i * cols + 2, i * cols + 3, i * cols + 4 };
    }
    
    int** reversed = reverse_rows(matrix, rows, cols);
    int expected[2][4] = {{5, 6, 7, 8}, {1, 2, 3, 4}};
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            assert(reversed[i][j] == expected[i][j]);
        }
    }
    
    freeMatrix(matrix, rows);
    freeMatrix(reversed, rows);
}

void test3() {
    int rows = 1, cols = 5;
    int** matrix = new int*[rows];
    matrix[0] = new int[cols]{1, 2, 3, 4, 5};
    
    int** reversed = reverse_rows(matrix, rows, cols);
    int expected[1][5] = {{1, 2, 3, 4, 5}};
    for (int j = 0; j < cols; j++) {
        assert(reversed[0][j] == expected[0][j]);
    }
    
    freeMatrix(matrix, rows);
    freeMatrix(reversed, rows);
}
