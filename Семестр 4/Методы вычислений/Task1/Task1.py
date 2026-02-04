import numpy as np
import datetime

#Метод для нахождения середины интервала
def half(a, b):
    return (a + b) / 2

#Функция, с которой будем работать
def f(x):
    return 2*(x**3) + (x**2) - 5

#Производная этой функции
def df(x):
    return 6*(x**2) + 2*x

#Вторая производная
def d2f(x):
    return 12*x + 2

#Метод бисекции
def bisectionMethod(a, b, eps):
    if np.sign(f(a)) == np.sign(f(b)):
         raise ValueError("Функция не меняет знак на заданном интервале")
        
    x = half(a, b)
    
    while ((b - a) / 2 > eps):
        if np.sign(f(a)) != np.sign(f(x)):
            b = x
        elif np.sign(f(b)) != np.sign(f(x)):
            a = x
        x = half(a, b)

    return round(x, 8)

#Метод хорд
def chordMethod(a, b, eps):
    if np.sign(f(a)) == np.sign(f(b)):
         raise ValueError("Функция не меняет знак на заданном интервале")

    while (abs(b - a) > eps):
        x = a - ((b - a) / (f(b) - f(a))) * f(a)
        
        if f(x) == 0:
            return round(x, 8)
        elif np.sign(f(a)) != np.sign(f(x)):
            b = x
        elif np.sign(f(b)) != np.sign(f(x)):
            a = x
            
    return round(half(a, b), 8)

#Функция, необходимая для метода Ньютона. В этой ф-ции мы находим x0, которая
#соответствует тому концу интервала,которому отвечает ориданата того же знака,
#что и вторая роизводная от него

def choose_start_point(a, b):
    if d2f(a) * f(a) > 0:
        return a
    elif d2f(b) * f(b) > 0:
        return b
    else:
        return None

#Метод Ньютона
def newtonMethod(a, b, eps):
    x0 = choose_start_point(a, b)
    
    if x0 is None:
        raise ValueError("Не удалось выбрать начальную точку согласно условию")

    x = x0 - (f(x0) / df(x0))

    while abs(x - x0) > eps:
        x0 = x
        x = x0 - (f(x0) / df(x0))

    return round(x, 8)

#Функция для нахождения времени работы метода Бисекции в 100 повторениях.
#Находим и начало и конец работы с помощью datetime.datetime.now()
#Затем вычитаем их друг из друга и вышедший рез-тат суммируем
def measure_bisection_time(a, b, eps):
    total_time = datetime.timedelta(0)  
    for _ in range(100): 
        start_time = datetime.datetime.now()  
        bisection_result = bisectionMethod(a, b, eps)  
        end_time = datetime.datetime.now() 
        total_time += end_time - start_time
    return total_time

#Функция для нахождения времени работы метода Хорд в 100 повторениях.
def measure_chord_time(a, b, eps):
    total_time = datetime.timedelta(0)
    for _ in range(100):
        start_time = datetime.datetime.now()
        chord_result = chordMethod(a, b, eps)
        end_time = datetime.datetime.now()
        total_time += end_time - start_time
    return total_time

#Функция для нахождения времени работы метода Ньютона в 100 повторениях.
def measure_newton_time(a, b, eps):
    total_time = datetime.timedelta(0)
    for _ in range(100):
        start_time = datetime.datetime.now()
        newton_result = newtonMethod(a, b, eps)
        end_time = datetime.datetime.now()
        total_time += end_time - start_time
    return total_time

#Входные данные
#В итоге мы получаем результат, который демонстрирует, что метод Ньютона самый быстрый
eps = 0.0001
a = 1
b = 1.5

print('Решение методом бисекции: ', bisectionMethod(a, b, eps))
print('Решение методом хорд: ', chordMethod(a, b, eps))
print('Решение методом Ньютона: ', newtonMethod(a, b, eps))

bisection_time = measure_bisection_time(a, b, eps)
print("Время выполнения метода бисекции:", bisection_time, "секунд")

chord_time = measure_chord_time(a, b, eps)
print("Время выполнения метода хорд:", chord_time, "секунд")

newton_time = measure_newton_time(a, b, eps)
print("Время выполнения метода Ньютона:", newton_time, "секунд")
