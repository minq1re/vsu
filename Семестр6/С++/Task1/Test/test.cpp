#include "pch.h"
#include "gtest/gtest.h"
#include "../Task1/Task1.cpp"

TEST(MatrixTest, ReverseRows) {
    int rows = 3, cols = 3;
    int** matrix = new int* [rows];
    for (int i = 0; i < rows; ++i) {
        matrix[i] = new int[cols];
    }

    matrix[0][0] = 1; matrix[0][1] = 2; matrix[0][2] = 3;
    matrix[1][0] = 4; matrix[1][1] = 5; matrix[1][2] = 6;
    matrix[2][0] = 7; matrix[2][1] = 8; matrix[2][2] = 9;

    int** reversed = reverse_rows(matrix, rows, cols);

    EXPECT_EQ(reversed[0][0], 7);
    EXPECT_EQ(reversed[0][1], 8);
    EXPECT_EQ(reversed[0][2], 9);
    EXPECT_EQ(reversed[1][0], 4);
    EXPECT_EQ(reversed[1][1], 5);
    EXPECT_EQ(reversed[1][2], 6);
    EXPECT_EQ(reversed[2][0], 1);
    EXPECT_EQ(reversed[2][1], 2);
    EXPECT_EQ(reversed[2][2], 3);

    for (int i = 0; i < rows; ++i) {
        delete[] matrix[i];
        delete[] reversed[i];
    }
    delete[] matrix;
    delete[] reversed;
}

TEST(MatrixTest, SingleRow) {
    int rows = 1, cols = 4;
    int** matrix = new int* [rows];
    matrix[0] = new int[cols] {1, 2, 3, 4};

    int** reversed = reverse_rows(matrix, rows, cols);
    EXPECT_EQ(reversed[0][0], 1);
    EXPECT_EQ(reversed[0][1], 2);
    EXPECT_EQ(reversed[0][2], 3);
    EXPECT_EQ(reversed[0][3], 4);

    delete[] matrix[0];
    delete[] reversed[0];
    delete[] matrix;
    delete[] reversed;
}

TEST(MatrixTest, SingleColumn) {
    int rows = 4, cols = 1;
    int** matrix = new int* [rows];
    for (int i = 0; i < rows; ++i) {
        matrix[i] = new int[cols] {i + 1};
    }

    int** reversed = reverse_rows(matrix, rows, cols);
    EXPECT_EQ(reversed[0][0], 4);
    EXPECT_EQ(reversed[1][0], 3);
    EXPECT_EQ(reversed[2][0], 2);
    EXPECT_EQ(reversed[3][0], 1);

    for (int i = 0; i < rows; ++i) {
        delete[] matrix[i];
        delete[] reversed[i];
    }
    delete[] matrix;
    delete[] reversed;
}

TEST(MatrixTest, EmptyMatrix) {
    int rows = 0, cols = 0;
    int** matrix = nullptr;
    int** reversed = reverse_rows(matrix, rows, cols);
    EXPECT_EQ(reversed, nullptr);
}

TEST(MatrixTest, LargeMatrix) {
    int rows = 1000, cols = 1000;
    int** matrix = new int* [rows];
    for (int i = 0; i < rows; ++i) {
        matrix[i] = new int[cols];
        for (int j = 0; j < cols; ++j) {
            matrix[i][j] = i * cols + j;
        }
    }

    int** reversed = reverse_rows(matrix, rows, cols);
    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            EXPECT_EQ(reversed[i][j], (rows - 1 - i) * cols + j);
        }
    }

    for (int i = 0; i < rows; ++i) {
        delete[] matrix[i];
        delete[] reversed[i];
    }
    delete[] matrix;
    delete[] reversed;
}