# Импорт библиотек
import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import matplotlib.mlab as mlab
import matplotlib

import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

# Настройки отображения графиков
plt.style.use('ggplot')
matplotlib.rcParams['figure.figsize'] = (12,8)
pd.options.mode.chained_assignment = None

# Загрузка данных и выбор второй тысячи записей
df = pd.read_csv('D:\VSU\sem7\AI\lab4\data.csv')
df = df.iloc[1000:2000]  # Вторая тысяча записей (1000-1999)
df = df.reset_index(drop=True)

# Просмотр структуры данных
print("Размер данных:", df.shape)
print("\nТипы данных:")
print(df.dtypes)

# Тепловая карта пропущенных значений (первые 30 колонок)
cols = df.columns[:30]
colours = ['#000099', '#ffff00']  # синий = данные, желтый = пропуски
sns.heatmap(df[cols].isnull(), cmap=sns.color_palette(colours))
plt.title('Тепловая карта пропущенных значений (первые 30 колонок)')
plt.tight_layout()
plt.show()

# Процент пропусков по каждому признаку
print("Процент пропусков по признакам:")
dict_missing_percentages = {}
for col in df.columns:
    pct_missing = np.mean(df[col].isnull())
    if pct_missing > 0:  # Показываем только колонки с пропусками
        print(f'{col} - {round(pct_missing*100)}%')
        dict_missing_percentages[col] = pct_missing

# Создаем индикаторы пропусков
for col in df.columns:
    if df[col].isnull().any():
        df[f'{col}_ismissing'] = df[col].isnull()

# Гистограмма количества пропусков на строку
ismissing_cols = [col for col in df.columns if 'ismissing' in col]
df['num_missing'] = df[ismissing_cols].sum(axis=1)

plt.figure(figsize=(10, 6))
df['num_missing'].value_counts().sort_index().plot.bar()
plt.title('Количество пропусков на строку')
plt.xlabel('Число пропусков')
plt.ylabel('Количество строк')
plt.tight_layout()
plt.show()

# Гистограмма количества пропусков по признакам
missing_counts = df[ismissing_cols].sum(axis=0)
missing_counts.index = missing_counts.index.str.replace('_ismissing', '')
plt.figure(figsize=(12, 6))
missing_counts.sort_values().plot.bar()
plt.ylabel('Количество пропусков')
plt.xlabel('Признаки')
plt.title('Количество пропусков по признакам')
plt.xticks(rotation=90)
plt.tight_layout()
plt.show()

# Принятие решения о пропусках
print(f"Исходное количество строк: {len(df)}")

# Отбрасываем строки, где пропусков слишком много (>2)
ind_missing = df[df['num_missing'] > 2].index
df = df.drop(ind_missing, axis=0)
print(f"Осталось строк после удаления строк с >2 пропусками: {len(df)}")

# Удаляем признак с наибольшим числом пропусков (если процент пропусков > 50%)
if dict_missing_percentages:
    col_with_max_missing = max(dict_missing_percentages, key=dict_missing_percentages.get)
    if dict_missing_percentages[col_with_max_missing] > 0.5:
        df = df.drop(columns=[col_with_max_missing])
        print(f"Удален столбец с наибольшим количеством пропусков: {col_with_max_missing}")

# Замена пропусков (числовые - медианой, категориальные - модой)
for col in df.select_dtypes(include=[np.number]):
    if df[col].isnull().any():
        df[col] = df[col].fillna(df[col].median())

for col in df.select_dtypes(exclude=[np.number]):
    if df[col].isnull().any():
        df[col] = df[col].fillna(df[col].mode()[0])

print(f"Итоговое количество строк после обработки пропусков: {len(df)}")

# Визуализация распределений числовых признаков
numeric_cols = df.select_dtypes(include=[np.number]).columns

# Гистограммы для первых 5 числовых признаков
for col in numeric_cols[:5]:
    plt.figure(figsize=(10, 6))
    df[col].hist(bins=50)
    plt.title(f'Гистограмма признака {col}')
    plt.xlabel(col)
    plt.ylabel('Частота')
    plt.tight_layout()
    plt.show()

# Коробчатые диаграммы
for col in numeric_cols[:5]:
    plt.figure(figsize=(10, 6))
    df.boxplot(column=[col])
    plt.title(f'Boxplot признака {col}')
    plt.tight_layout()
    plt.show()

# Описательная статистика
print("Описательная статистика числовых признаков:")
print(df[numeric_cols].describe())

# Функция для обработки выбросов методом IQR
def handle_outliers_iqr(df, column):
    Q1 = df[column].quantile(0.25)
    Q3 = df[column].quantile(0.75)
    IQR = Q3 - Q1
    lower_bound = Q1 - 1.5 * IQR
    upper_bound = Q3 + 1.5 * IQR
    
    outliers = df[(df[column] < lower_bound) | (df[column] > upper_bound)]
    print(f"Выбросы в {column}: {len(outliers)}")
    
    # Заменяем выбросы граничными значениями
    df[column] = np.where(df[column] < lower_bound, lower_bound, df[column])
    df[column] = np.where(df[column] > upper_bound, upper_bound, df[column])
    
    return df

# Обрабатываем выбросы для ключевых числовых признаков
key_numeric_cols = ['full_sq', 'life_sq', 'floor', 'price_doc']  # замените на актуальные для вашего датасета
for col in key_numeric_cols:
    if col in df.columns:
        df = handle_outliers_iqr(df, col)

# Поиск неинформативных признаков (более 95% одинаковых значений)
num_rows = len(df)
low_info_cols = []

for col in df.columns:
    cnts = df[col].value_counts(dropna=False, normalize=True)
    if len(cnts) > 0:
        top_pct = cnts.iloc[0]
        if top_pct > 0.95:
            low_info_cols.append(col)
            print(f'{col}: {top_pct:.2%} одинаковых значений')

# Удаляем неинформативные столбцы
if low_info_cols:
    df = df.drop(columns=low_info_cols)
    print(f"Удалены неинформативные столбцы: {low_info_cols}")

# Удаляем дубликаты строк
initial_shape = df.shape
df = df.drop_duplicates()
final_shape = df.shape

if initial_shape[0] != final_shape[0]:
    print(f"Удалено дубликатов: {initial_shape[0] - final_shape[0]}")
print(f"После удаления дубликатов: {df.shape}")

# Нормализация строковых признаков (нижний регистр)
string_cols = df.select_dtypes(include=['object']).columns
for col in string_cols:
    df[col] = df[col].astype(str).str.lower().str.strip()

# Преобразование даты (если есть столбец timestamp)
if 'timestamp' in df.columns:
    df['timestamp'] = pd.to_datetime(df['timestamp'], errors='coerce')
    df['year'] = df['timestamp'].dt.year
    df['month'] = df['timestamp'].dt.month
    df['weekday'] = df['timestamp'].dt.weekday

# Стандартизация адресов (пример)
if 'address' in df.columns:
    df['address_clean'] = df['address'].str.lower().str.strip()
    df['address_clean'] = df['address_clean'].str.replace(r'\.', '', regex=True)
    df['address_clean'] = df['address_clean'].str.replace(r'\bstreet\b', 'st', regex=True)
    df['address_clean'] = df['address_clean'].str.replace(r'\bavenue\b', 'ave', regex=True)
    df['address_clean'] = df['address_clean'].str.replace(r'\broad\b', 'rd', regex=True)

print("Очистка данных завершена!")
print(f"Итоговый размер данных: {df.shape}")