import math

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv('1.csv', sep=';')
x = data['x']
y = data['y']

a = -5
b = 5

def coeff(x, y):
    m = len(x)
    x = np.copy(x)
    a = np.copy(y)
    for k in range(1, m):
        a[k:m] = (a[k:m] - a[k - 1]) / (x[k:m] - x[k - 1])
    return a

def newton_polynomial(x_data, y_data, x):
    a = coeff(x_data, y_data)
    n = len(x_data) - 1
    p = a[n]

    for k in range(1, n + 1):
        p = a[n - k] + (x - x_data[n - k]) * p
    return p

plt.figure(figsize=(10, 5))

n = 20
x_cheb = [5 * math.cos(math.pi * (2 * k - 1) / (2 * n)) for k in range(1, n + 1)]
y_cheb = [-math.sin(math.pi * 1.5 * t) / (math.pi * 1.5 * t) for t in x_cheb]

x_2 = [x_cheb[0]]
for i in range(1, len(x_cheb)):
    x_2.append((x_cheb[i - 1] + x_cheb[i]) / 2)
    x_2.append(x_cheb[i])

y_2 = [newton_polynomial(x_cheb, y_cheb, xx) for xx in x_2]

full_func_x = np.linspace(-5, 5, 200)
full_func_y = [-math.sin(math.pi * 1.5 * t) / (math.pi * 1.5 * t) for t in full_func_x]

plt.plot(full_func_x, full_func_y, linestyle='--', c='green', label='Исходная функция')
plt.plot(x_2, y_2, c='black', marker="*", label='Интерполяция Ньютона', linewidth=0.7)
plt.scatter(x_cheb, y_cheb, c='red', label='Узлы')

plt.legend()
plt.show()