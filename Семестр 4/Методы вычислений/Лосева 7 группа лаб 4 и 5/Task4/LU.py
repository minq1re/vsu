import numpy as np


def get_L(m):
    L = np.identity(m.shape[0])
    for i in range(1, L.shape[0]):
        for j in range(i):
            L[i, j] = m[i, j] / m[j, j]
    return L


def get_U(m):
    U = m.copy()
    for i in range(U.shape[0]):
        for j in range(i):
            U[i, j] = 0
    return U


def LU(A, B):
    n = len(A)
    L = get_L(np.array(A))
    U = get_U(np.array(A))

    y = np.zeros(n)
    for i in range(n):
        y[i] = B[i]
        for j in range(i):
            y[i] -= L[i, j] * y[j]

    x = np.zeros(n)
    for i in reversed(range(n)):
        x[i] = y[i]
        for j in range(i+1, n):
            x[i] -= U[i, j] * x[j]
        x[i] /= U[i, i]

    return x


A = [[8, -1, -1, 2], [1, 6, -2, -2], [2, 1, -5, 1], [1, -1, 1, -4]]
B = [11, -7, 2, -2]
print(LU(A, B))