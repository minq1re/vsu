import tkinter as tk
from tkinter import filedialog
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

def read_data_from_file():
    root = tk.Tk()
    root.withdraw()

    file_path = filedialog.askopenfilename(title="Выберите файл с данными")

    with open(file_path, 'r') as file:
        data = [int(line.strip()) for line in file]

    return data

def calculate_sensitivity_specificity(data):
    threshold = 750  # Пороговое значение для классификации аритмии

    TP = sum(1 for interval in data if interval < threshold)  # True Positive
    FN = sum(1 for interval in data if interval >= threshold)  # False Negative
    TN = sum(1 for interval in data if interval >= threshold)  # True Negative
    FP = sum(1 for interval in data if interval < threshold)  # False Positive

    sensitivity = TP / (TP + FN)
    specificity = TN / (TN + FP)

    return sensitivity, specificity

def show_results():
    data = read_data_from_file()
    sensitivity, specificity = calculate_sensitivity_specificity(data)

    result_window = tk.Toplevel()
    result_window.title("Результаты чувствительности и специфичности")

    sensitivity_label = tk.Label(result_window, text=f"Чувствительность: {sensitivity}")
    sensitivity_label.pack()

    specificity_label = tk.Label(result_window, text=f"Специфичность: {specificity}")
    specificity_label.pack()

def find_optimal_window(data):
    total_length = len(data)

    def calculate_optimal_window(start, end):
        min_avg = float('inf')  # Инициализируем минимальное среднее значение
        optimal_window = None

        for window_size in range(10, total_length // 2):  # Изменяем размер окна от 10 до половины длины записи
            for i in range(start, end - window_size):
                window = data[i:i + window_size]
                diffs = [abs(window[j] - window[j + 1]) for j in range(len(window) - 1)]
                avg_diff = sum(diffs) / len(diffs)
                if avg_diff < min_avg:
                    min_avg = avg_diff
                    optimal_window = (i, i + window_size)

        if optimal_window:
            ax.axvline(x=optimal_window[0], color='g', linestyle='--')
            ax.axvline(x=optimal_window[1], color='g', linestyle='--')
            canvas.draw()

    calculate_optimal_window(0, int(0.6 * total_length))
    calculate_optimal_window(int(0.6 * total_length), int(0.75 * total_length))
    calculate_optimal_window(int(0.75 * total_length), total_length)

def load_data():
    file_path = filedialog.askopenfilename(filetypes=[("Text files", "*.rr")])
    if file_path:
        with open(file_path, 'r') as file:
            data = [int(line.strip()) for line in file.readlines()[2:]]
        ax.clear()
        ax.plot(data)

        split_index1 = int(len(data) * 0.6)
        split_index2 = int(len(data) * 0.75)

        ax.axvline(x=split_index1, color='r', linestyle='--')
        ax.axvline(x=split_index2, color='r', linestyle='--')
        ax.set_xlabel('Time')
        ax.set_ylabel('R-R interval')
        ax.set_title('R-R Interval Graph')

        canvas.draw()

        button_optimal = tk.Button(root, text="Найти оптимальное окно", command=lambda: find_optimal_window(data))
        button_optimal.pack()

root = tk.Tk()
root.title("R-R Interval Graph")

fig, ax = plt.subplots()
canvas = FigureCanvasTkAgg(fig, master=root)
canvas.get_tk_widget().pack()

button_load_data = tk.Button(root, text="Загрузить данные", command=load_data)
button_load_data.pack()

button_show_results = tk.Button(root, text="Показать результаты", command=show_results)
button_show_results.pack()

def on_closing():
    root.quit()
    root.destroy()

root.protocol("WM_DELETE_WINDOW", on_closing)
root.mainloop()