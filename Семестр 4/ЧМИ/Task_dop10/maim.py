import tkinter as tk
from tkinter import ttk
import numpy as np


def gram_schmidt(V):
    num_vectors = V.shape[0]
    vector_size = V.shape[1]
    U = np.zeros((num_vectors, vector_size))

    for i in range(num_vectors):
        U[i] = V[i]
        for j in range(i):
            proj = np.dot(V[i], U[j]) / np.dot(U[j], U[j])
            U[i] -= proj * U[j]

    return U


def compute_gram_schmidt():
    try:
        input_text = input_textbox.get("1.0", tk.END).strip()
        V = np.array(eval(input_text), dtype=float)
        U = gram_schmidt(V)
        result_textbox.delete("1.0", tk.END)
        result_textbox.insert(tk.END, str(U))
    except Exception as e:
        result_textbox.delete("1.0", tk.END)
        result_textbox.insert(tk.END, f"Ошибка: {e}")


# Создание главного окна
root = tk.Tk()
root.title("Преобразование Грама-Шмидта")
root.geometry("1000x700")
root.state('zoomed')  # Открытие окна на весь экран

# Применение стиля
style = ttk.Style()
style.configure("TFrame", background="#282c34")
style.configure("TLabel", background="#282c34", foreground="#61dafb", font=("Helvetica", 14))
style.configure("TButton", background="#61dafb", foreground="#282c34", font=("Helvetica", 14))
style.configure("TText", background="#1c1e24", foreground="#abb2bf", font=("Helvetica", 14))

# Создание фреймов для ввода и вывода
input_frame = ttk.Frame(root, padding="10")
input_frame.pack(fill=tk.BOTH, expand=True, padx=20, pady=20)

result_frame = ttk.Frame(root, padding="10")
result_frame.pack(fill=tk.BOTH, expand=True, padx=20, pady=20)

# Ввод текстового поля
input_label = ttk.Label(input_frame, text="Введите матрицу векторов (пример: [[1, 1, 1], [1, 0, 2], [1, 1, 0]]):")
input_label.pack(anchor=tk.W)

input_textbox = tk.Text(input_frame, height=10, wrap=tk.WORD, bg="#1c1e24", fg="#abb2bf", font=("Helvetica", 14))
input_textbox.pack(fill=tk.BOTH, expand=True)
input_textbox.insert(tk.END, "[[1, 1, 1], [1, 0, 2], [1, 1, 0]]")  # Пример ввода по умолчанию

# Кнопка для запуска вычислений
compute_button = ttk.Button(input_frame, text="Выполнить преобразование", command=compute_gram_schmidt)
compute_button.pack(pady=10)

# Вывод текстового поля
result_label = ttk.Label(result_frame, text="Результат:")
result_label.pack(anchor=tk.W)

result_textbox = tk.Text(result_frame, height=10, wrap=tk.WORD, bg="#1c1e24", fg="#abb2bf", font=("Helvetica", 14))
result_textbox.pack(fill=tk.BOTH, expand=True)
result_textbox.insert(tk.END, str(gram_schmidt(
    np.array([[1, 1, 1], [1, 0, 2], [1, 1, 0]], dtype=float))))  # Пример результата по умолчанию

# Запуск главного цикла обработки событий
root.mainloop()
