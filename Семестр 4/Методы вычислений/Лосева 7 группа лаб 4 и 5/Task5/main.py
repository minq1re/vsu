import numpy as np
import matplotlib.pyplot as plt

def f(x, y):
    return y * np.cos(x)

def exact_solution(x):
    return np.exp(np.sin(x))

def runge_kutta_4(f, x0, y0, xn, n):
    h = (xn - x0) / n
    x = np.linspace(x0, xn, n + 1)
    y = np.zeros(n + 1)
    y[0] = y0
    for i in range(n):
        k1 = h * f(x[i], y[i])
        k2 = h * f(x[i] + h / 2, y[i] + k1 / 2)
        k3 = h * f(x[i] + h / 2, y[i] + k2 / 2)
        k4 = h * f(x[i] + h, y[i] + k3)
        y[i + 1] = y[i] + (k1 + 2 * k2 + 2 * k3 + k4) / 6
    return x, y

def adams_4(f, x0, y0, xn, n):
    h = (xn - x0) / n
    x = np.linspace(x0, xn, n + 1)
    y = np.zeros(n + 1)
    x_rk, y_rk = runge_kutta_4(f, x0, y0, x0 + 3 * h, 3)
    y[:4] = y_rk
    for i in range(3, n):
        y[i + 1] = y[i] + h / 24 * (
            55 * f(x[i], y[i]) - 59 * f(x[i - 1], y[i - 1]) + 37 * f(x[i - 2], y[i - 2]) - 9 * f(x[i - 3], y[i - 3]))
    return x, y

x0, y0, xn = 0, 1, 10
n = 10

x_exact = np.linspace(x0, xn, 1000)
y_exact = exact_solution(x_exact)

x_rk, y_rk = runge_kutta_4(f, x0, y0, xn, n)
x_adams, y_adams = adams_4(f, x0, y0, xn, n)

plt.figure(figsize=(10, 6))
plt.plot(x_exact, y_exact, label='Точное решение', color='black')
plt.plot(x_rk, y_rk, label='Метод Рунге-Кутта 4-го порядка', linestyle='--', color='blue')
plt.plot(x_adams, y_adams, label='Метод Адамса 4-го порядка', linestyle='--', color='red')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.title('Сравнение точного решения и численных методов')
plt.grid(True)
plt.show()

def relative_error(y_exact, y_num):
    return np.abs((y_exact - y_num) / y_exact)

nodes = [10, 20, 50, 100, 200, 500]
errors_rk = []
errors_adams = []

for n in nodes:
    x_exact = np.linspace(x0, xn, n + 1)
    y_exact = exact_solution(x_exact)

    _, y_rk = runge_kutta_4(f, x0, y0, xn, n)
    _, y_adams = adams_4(f, x0, y0, xn, n)

    error_rk = relative_error(y_exact, y_rk)
    error_adams = relative_error(y_exact, y_adams)

    errors_rk.append(np.mean(error_rk))
    errors_adams.append(np.mean(error_adams))


errors_ratio = [rk_error / adams_error for rk_error, adams_error in zip(errors_rk, errors_adams)]

# Построение графиков
plt.figure(figsize=(10, 6))
plt.plot(nodes, errors_rk, label='Метод Рунге-Кутта 4-го порядка', marker='o', color='blue')
plt.plot(nodes, errors_adams, label='Метод Адамса 4-го порядка', marker='o', color='red')
plt.plot(nodes, errors_ratio, label='Отношение погрешностей', marker='o', color='green')

plt.xlabel('Количество узловых точек')
plt.ylabel('Средняя относительная погрешность')
plt.legend()
plt.title('Средняя относительная погрешность в зависимости от количества узловых точек')
plt.xscale('log')
plt.yscale('log')
plt.grid(True, which='both')
plt.show()
