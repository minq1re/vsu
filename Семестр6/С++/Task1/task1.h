#pragma once
double** createMatrix(int rows, int cols);
void deleteMatrix(double** matrix, int rows);
void fillMatrix(double** matrix, int rows, int cols);
void printMatrix(double** matrix, int rows, int cols);
void reverseMatrix(double** matrix, int rows);
void testSwapRows();
void testOddRows();