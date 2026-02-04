import math
import time
import matplotlib.pyplot as plt
import numpy as np


def jacobi(A, B, e):
    k = 1
    begin_time = time.perf_counter_ns()
    n = len(A)
    x_next = [B[i] for i in range(n)]

    while True:
        x = x_next.copy()
        x_next = [B[i] for i in range(n)]

        for i in range(n):
            for j in range(n):
                if i != j:
                    x_next[i] -= A[i][j] * x[j]
            x_next[i] /= A[i][i]

        if math.sqrt(sum([(x_next[i] - x[i]) ** 2 for i in range(len(x))])) <= e:
            break

        k += 1

    work_time = time.perf_counter_ns() - begin_time
    return x, k, work_time / 1e6


def gauss_zeudel(A, B, e):
    k = 1
    begin_time = time.perf_counter_ns()
    n = len(A)
    x = [1] * n
    x_next = [B[i] for i in range(n)]

    while True:
        for i in range(n):
            x_next[i] = B[i] - sum([A[i][j] * x_next[j] for j in range(n) if j != i]) / A[i][i]

        if math.sqrt(sum([(x_next[i] - x[i]) ** 2 for i in range(len(x))])) <= e:
            break

        k += 1
        x = x_next.copy()

    work_time = time.perf_counter_ns() - begin_time
    return x, k, work_time / 1e6


def gauss_matrix(A, B, e):
    k = 1
    A = np.array(A)
    B = np.array(B)
    begin_time = time.perf_counter_ns()
    n = len(A)
    x = np.array([1] * n)

    while True:
        m1 = np.linalg.inv(np.diag(np.diag(A)) + np.tril(A))
        x_next = np.dot(m1, B) - np.dot(np.dot(m1, np.triu(A)), x)
        dx = sum((x_next[i] - x[i]) ** 2 for i in range(n))

        if np.sqrt(dx) <= e:
            break

        k += 1
        x = x_next.copy()

    work_time = time.perf_counter_ns() - begin_time
    return x, k, work_time / 1e6


A = [[8, -1, -1, 2], [1, 6, -2, -2], [2, 1, -5, 1], [1, -1, 1, -4]]
B = [11, -7, 2, -2]

e_vals = [i * 1e-6 for i in range(1, 10000)]
jc = [jacobi(A, B, e) for e in e_vals]
gz = [gauss_zeudel(A, B, e) for e in e_vals]
gm = [gauss_matrix(A, B, e) for e in e_vals]

plt.plot(e_vals, [sol[1] for sol in jc], label='Якоби')
plt.plot(e_vals, [sol[1] for sol in gz], label='Гаусс-Зейдель')
plt.xlabel("Точность")
plt.ylabel("Количество итераций")
plt.legend()
plt.show()

plt.plot(e_vals, [sol[2] for sol in jc], label='Якоби')
plt.plot(e_vals, [sol[2] for sol in gz], label='Гаусс-Зейдель')
plt.xlabel("Точность")
plt.ylabel("Время выполнения, мс")
plt.legend()
plt.show()

plt.plot(e_vals, [sol[2] for sol in gz], label='Гаусс-Зейдель')
plt.plot(e_vals, [sol[2] for sol in gm], label='Гаусс-Зейдель в матричном виде')
plt.xlabel("Точность")
plt.ylabel("Время выполнения, мс")
plt.legend()
plt.show()