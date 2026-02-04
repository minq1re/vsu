import numpy as np
import matplotlib.pyplot as plt
from statsmodels.tsa.ar_model import AutoReg
from sklearn.metrics import mean_squared_error

# Чтение данных из файла
file_path = '42342.txt'
with open(file_path, 'r') as file:
    lines = file.readlines()[2:]  # Пропускаем первые две строки заголовка
rr_intervals = [int(line.strip()) for line in lines]

# Подготовка данных для моделирования
rr_intervals = np.array(rr_intervals)

# Разделение данных на обучающий и тестовый набор
train_size = int(len(rr_intervals) * 0.7)
train, test = rr_intervals[:train_size], rr_intervals[train_size:]

# Выбор порядка авторегрессии
p = 2  # Примерно выбранный порядок авторегрессии

# Построение и обучение модели авторегрессии
model = AutoReg(train, lags=p)
model_fit = model.fit()

# Прогнозирование на тестовом наборе
predictions = model_fit.predict(start=len(train), end=len(train)+len(test)-1, dynamic=False)

# Оценка модели
mse = mean_squared_error(test, predictions)

# Визуализация результатов
plt.figure(figsize=(12, 6))
plt.plot(np.arange(len(rr_intervals)), rr_intervals, label='Исходные данные', color='b')
plt.plot(np.arange(train_size, len(rr_intervals)), predictions, label='Прогноз', color='r')
plt.xlabel('Временные отметки')
plt.ylabel('R-R интервалы')
plt.title('Прогноз динамики R-R интервалов с авторегрессией\nMSE: %.2f' % mse)
plt.legend()
plt.show()