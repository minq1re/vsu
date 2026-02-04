import numpy as np
import matplotlib.pyplot as plt


def f(x):
    return x ** 2 * np.sin(x)


def F(x):
    return 2 * x * np.sin(x) + (2 - x ** 2) * np.cos(x)


def F1(a, b):
    return F(b) - F(a)


def left_rect(func, a, b, n_interval):
    sum = 0
    dx = (b - a) / n_interval
    for i in range(n_interval):
        sum += dx * func(a + i * dx)
    return sum


def middle_rect(func, a, b, n_interval):
    sum = 0
    dx = (b - a) / n_interval
    x_start = a + dx / 2
    for i in range(n_interval):
        sum += dx * func(x_start + i * dx)
    return sum


def trapezoidal(func, a, b, n_interval):
    sum = 0
    dx = (b - a) / n_interval
    x_start = a
    for i in range(n_interval):
        sum += dx * (func(x_start + i * dx) + func(x_start + (i + 1) * dx)) / 2
    return sum


def simpson(func, a, b, n_interval):
    sum = func(a) + func(b)
    dx = (b - a) / n_interval
    for i in range(1, n_interval):
        k = 2 + 2 * (i % 2)
        sum += k * func(a + i * dx)
    return sum * dx / 3


def monte_carlo(func, a, b, epsilon):
    n = 1
    integral_prev = 0
    integral_curr = np.inf

    while np.abs(integral_curr - integral_prev) > epsilon:
        x = np.random.uniform(a, b, n)
        fx = func(x)
        mean_fx = np.mean(fx)
        integral_prev = integral_curr
        integral_curr = (b - a) * mean_fx
        n += 1
    return integral_curr, n


def integration(a, b, func, eps, type):
    k = 10
    i = 0
    result = 0
    while True:
        i += 1
        if type == 'left':
            diff = abs(left_rect(func, a, b, k * i) - left_rect(func, a, b, k * (i + 1)))
            result = left_rect(func, a, b, k * (i + 1))
        if type == 'middle':
            diff = abs(middle_rect(func, a, b, k * i) - middle_rect(func, a, b, k * (i + 1)))
            result = middle_rect(func, a, b, k * (i + 1))
        if type == 'trapeze':
            diff = abs(trapezoidal(func, a, b, k * i) - trapezoidal(func, a, b, k * (i + 1)))
            result = trapezoidal(func, a, b, k * (i + 1))
        if type == 'simpson':
            diff = abs(simpson(func, a, b, k * i) - simpson(func, a, b, k * (i + 1)))
            result = simpson(func, a, b, k * (i + 1))
        if not (diff > eps):
            print((i + 1))
            break
    return (result, (i + 1))


def err_fun(fun, real_int, a, b, N, type):
    num_integral = 0
    if type == 'left':
        num_integral = left_rect(f, a, b, N)
    if type == 'middle':
        num_integral = middle_rect(f, a, b, N)
    if type == 'trapeze':
        num_integral = trapezoidal(f, a, b, N)
    if type == 'simpson':
        num_integral = simpson(f, a, b, N)
    abs_error = abs(num_integral - real_int(a, b))
    relativ_error = abs_error / real_int(a, b)

    return (abs_error, relativ_error)


def paint_absolute_error(f, real_int, a, b):
    fig = plt.figure(figsize=(12, 7))
    N_max = 30
    n = [i for i in range(1, N_max + 1)]
    err_left = np.array([err_fun(f, real_int, a, b, n, 'left') for n in n])
    err_middle = np.array([err_fun(f, real_int, a, b, n, 'middle') for n in n])
    err_trap = np.array([err_fun(f, real_int, a, b, n, 'trapeze') for n in n])

    ax1 = fig.add_subplot(121)
    ax1.plot(n, err_left[:, 0], marker='*', label='left')
    ax1.plot(n, err_middle[:, 0], color='black', label='middle')
    ax1.plot(n, err_trap[:, 0], color='red', label='trapeze')
    ax1.set_xlabel('N', fontsize=16)
    ax1.set_ylabel('Абсолютная погрешность', fontsize=16)
    ax1.tick_params(axis='both', labelsize=16)
    ax1.legend(fontsize=16)
    ax1.grid(which='major', linestyle='--', color='gray')


def paint_absolute_Simpson(f, real_int, a, b):
    fig = plt.figure(figsize=(12, 7))
    N_max = 30
    n = [i for i in range(1, N_max + 1)]
    err_sympson = np.array([err_fun(f, real_int, a, b, n, 'simpson') for n in n])
    ax1 = fig.add_subplot(121)
    ax1.plot(n, err_sympson[:, 0], marker='*', color='black', label='simpson')
    ax1.set_xlabel('N', fontsize=16)
    ax1.set_ylabel('Абсолютная погрешность для Симпсона', fontsize=16)
    ax1.tick_params(axis='both', labelsize=16)
    ax1.legend(fontsize=16)
    ax1.grid(which='major', linestyle='--', color='gray')


def paint_relative_error(f, real_int, a, b):
    fig = plt.figure(figsize=(12, 7))
    N_max = 30
    n = [i for i in range(1, N_max + 1)]
    err_left = np.array([err_fun(f, real_int, a, b, n, 'left') for n in n])
    err_middle = np.array([err_fun(f, real_int, a, b, n, 'middle') for n in n])
    err_trap = np.array([err_fun(f, real_int, a, b, n, 'trapeze') for n in n])

    ax2 = fig.add_subplot(122)
    ax2.plot(n, err_left[:, 1], marker='*', color='g', label='left')
    ax2.plot(n, err_middle[:, 1], color='black', label='middle')
    ax2.plot(n, err_trap[:, 1], color='red', label='trapeze')
    ax2.set_xlabel('N', fontsize=16)
    ax2.set_ylabel('Относительная погрешность', fontsize=16)
    ax2.set_yscale('log')
    ax2.set_xscale('log')
    ax2.set_ylim(3 * 10 ** (-4), 3)
    ax2.tick_params(axis='both', labelsize=16)
    ax2.legend(fontsize=16)
    ax2.grid(which='major', linestyle='--', color='gray')


def paint_relative_Simpson(f, real_int, a, b):
    fig = plt.figure(figsize=(12, 7))
    N_max = 30
    n = [i for i in range(1, N_max + 1)]
    err_symp = np.array([err_fun(f, real_int, a, b, n, 'simpson') for n in n])

    ax2 = fig.add_subplot(122)
    ax2.plot(n, err_symp[:, 1], color='red', label='simpson')
    ax2.set_xlabel('N', fontsize=16)
    ax2.set_ylabel('Относительная погрешность', fontsize=16)
    ax2.set_yscale('log')
    ax2.set_xscale('log')
    ax2.set_ylim(3 * 10 ** (-4), 3)
    ax2.tick_params(axis='both', labelsize=16)
    ax2.legend(fontsize=16)
    ax2.grid(which='major', linestyle='--', color='gray')
    plt.show()


paint_absolute_error(f, F1, 5, 0)
paint_absolute_Simpson(f, F1, 5, 0)
paint_relative_error(f, F1, 5, 0)
paint_relative_Simpson(f, F1, 5, 0)