import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.preprocessing import StandardScaler
from sklearn.cluster import KMeans, AgglomerativeClustering
from scipy.cluster.hierarchy import dendrogram, linkage
from sklearn.metrics import pairwise_distances

import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

# 1. Загрузка данных
file_path = "D:\VSU\sem7\AI\lab1\candies.dat"
df = pd.read_csv(file_path, sep=";")

# Проверим данные
print(df.head())

# 2. Отбор переменных (V1–V11)
X = df.values

# 3. Стандартизация (нужна, т.к. шкалы одинаковые, но всё равно лучше нормализовать)
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# ИЕРАРХИЧЕСКАЯ КЛАСТЕРИЗАЦИЯ 

# 4. Расстояния и метод кластеризации
Z = linkage(X_scaled, method="ward", metric="euclidean")

# Дендрограмма
plt.figure(figsize=(12, 8))
dendrogram(Z, no_labels=True)
plt.title("Дендрограмма иерархической кластеризации")
plt.xlabel("Респонденты")
plt.ylabel("Расстояние")
plt.show()

# Выберем число кластеров (например, 3-5, потом уточним по локтю)
optimal_clusters_hc = 4
hc = AgglomerativeClustering(n_clusters=optimal_clusters_hc,
                             metric="euclidean",
                             linkage="ward")
hc_labels = hc.fit_predict(X_scaled)
df["HC_Cluster"] = hc_labels

# K-MEANS 

# 5. График локтя ("каменистая осыпь")
inertia = []
k_range = range(1, 11)
for k in k_range:
    kmeans = KMeans(n_clusters=k, random_state=42, n_init=10)
    kmeans.fit(X_scaled)
    inertia.append(kmeans.inertia_)

plt.figure(figsize=(8, 5))
plt.plot(k_range, inertia, marker="o", linestyle="--")
plt.xlabel("Количество кластеров")
plt.ylabel("Инерция")
plt.title("График 'каменистая осыпь'")
plt.grid(True)
plt.show()

# Оптимальное число кластеров (например, 3 или 4)
optimal_clusters_kmeans = 4
kmeans = KMeans(n_clusters=optimal_clusters_kmeans, random_state=42, n_init=10)
kmeans_labels = kmeans.fit_predict(X_scaled)
df["KMeans_Cluster"] = kmeans_labels

# МНОГОМЕРНОЕ ШКАЛИРОВАНИЕ

def cmdscale(D):
    # Преобразуем входные данные в массив NumPy для гарантированной работы с матрицами
    D = np.asarray(D)
    # Получаем размерность матрицы (количество объектов)
    n = len(D)
    
    # Создаем матрицу центрирования (центрации)
    # H = I - (1/n)*11^T, где I - единичная матрица, 1 - вектор из единиц
    # Эта матрица преобразует данные так, чтобы их среднее значение было равно 0
    H = np.eye(n) - np.ones((n, n)) / n
    
    # Вычисляем матрицу скалярных произведений (матрицу внутренних произведений)
    # B = -0.5 * H * (D^2) * H
    # Теоретически B = X * X^T, где X - искомая матрица координат:cite[6]
    B = -H.dot(D**2).dot(H) / 2
    
    # Вычисляем собственные значения и собственные векторы матрицы B
    eigvals, eigvecs = np.linalg.eigh(B)
    
    # Сортируем собственные значения и векторы в порядке убывания
    idx = np.argsort(eigvals)[::-1]  # индексы для сортировки по убыванию
    eigvals = eigvals[idx]           # отсортированные собственные значения
    eigvecs = eigvecs[:, idx]        # соответствующие собственные векторы
    
    # Возвращаем координаты в двумерном пространстве
    # Берем первые два собственных вектора и умножаем на корень из собственных значений
    return eigvecs[:, :2] * np.sqrt(eigvals[:2])

# Вычисляем матрицу попарных расстояний между стандартизированными данными
# X_scaled - стандартизированная матрица объектов-признаков
# metric="euclidean" - используем евклидово расстояние:cite[2]:cite[5]
distance_matrix = pairwise_distances(X_scaled, metric="euclidean")

# Применяем классическое MDS для получения 2D координат
# mds_coords будет содержать координаты каждого объекта в двумерном пространстве
# Эти координаты сохраняют структуру расстояний исходных данных:cite[7]
mds_coords = cmdscale(distance_matrix)

# Визуализация кластеров K-means
plt.figure(figsize=(10, 7))
scatter = plt.scatter(mds_coords[:, 0], mds_coords[:, 1],
                      c=df["KMeans_Cluster"], cmap="viridis",
                      s=60, alpha=0.8, edgecolors="black")
plt.colorbar(scatter, label="Кластер")
plt.title("Многомерное шкалирование (K-means)")
plt.xlabel("Компонента 1")
plt.ylabel("Компонента 2")
plt.show()

# Список всех переменных
variables = df.columns[:-2]  # исключаем колонки с кластерами

# Гистограммы распределения по кластерам
fig, axes = plt.subplots(4, 3, figsize=(18, 12))
axes = axes.flatten()

for i, var in enumerate(variables):
    sns.histplot(data=df, x=var, hue="KMeans_Cluster", multiple="stack", bins=5, ax=axes[i])
    axes[i].set_title(f"{var}: распределение по кластерам")

plt.tight_layout()
plt.show()

# АНАЛИЗ РЕЗУЛЬТАТОВ
# Анализ характеристик кластеров для K-means
cluster_means = df.groupby('KMeans_Cluster')[['V1','V2','V3','V4','V5','V6','V7','V8','V9','V10','V11']].mean()
cluster_std = df.groupby('KMeans_Cluster')[['V1','V2','V3','V4','V5','V6','V7','V8','V9','V10','V11']].std()

print("Средние значения по кластерам (K-means):")
print(cluster_means.round(2))

# Проверка согласованности результатов
agreement = np.mean(df['HC_Cluster'] == df['KMeans_Cluster'])
print(f"Согласованность методов: {agreement:.2%}")

