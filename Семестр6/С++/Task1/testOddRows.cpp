#include <cassert>
#include "task1.h"
#include <iostream>
using namespace std;

void testOddRows() {
    int rows = 3, cols = 2;
    double** matrix = createMatrix(rows, cols);

    matrix[0][0] = 1; matrix[0][1] = 2;
    matrix[1][0] = 3; matrix[1][1] = 4;
    matrix[2][0] = 5; matrix[2][1] = 6;

    reverseMatrix(matrix, rows;

    assert(matrix[0][0] == 3 && matrix[0][1] == 4);
    assert(matrix[1][0] == 1 && matrix[1][1] == 2);
    assert(matrix[2][0] == 5 && matrix[2][1] == 6);

    cout << "Тест пройден!\n";

    deleteMatrix(matrix, rows);
    
}