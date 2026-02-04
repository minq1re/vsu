import numpy as np
import matplotlib.pyplot as plt
from scipy.fft import fft, fftfreq
import tkinter as tk
from tkinter import ttk


sampling_rate = 200
duration = 1
t = np.linspace(0, duration, int(sampling_rate * duration), endpoint=False)
frequencies = [1, 2, 4, 8]


def harmonic_signal(frequency):
    return np.sin(2 * np.pi * frequency * t)


def digital_signal(frequency):
    return (t * frequency % 1) < 0.5


def update_plots(selected_frequency):
    f = int(selected_frequency)
    harmonic = harmonic_signal(f)
    square = digital_signal(f)
    N = len(t)
    yf_harmonic = fft(harmonic)
    yf_square = fft(square)
    xf = fftfreq(N, 1 / sampling_rate)

    plt.figure(figsize=(12, 6))

    plt.subplot(2, 2, 1)
    plt.plot(t, harmonic, color='red', linestyle='-.')
    plt.title(f'Гармонический сигнал {f} Герц')
    plt.xlabel('Время (с)')
    plt.ylabel('Амплитуда')

    plt.subplot(2, 2, 2)
    plt.plot(xf[:N // 2], np.abs(yf_harmonic)[:N // 2], color='green', linestyle='--')
    plt.title(f'Спектр гармонического сигнала {f} Герц')
    plt.xlabel('Частота (Герц)')
    plt.ylabel('Магнитуда')

    plt.subplot(2, 2, 3)
    plt.plot(t, square, color='red', linestyle='-.')
    plt.title(f'Цифровой сигнал {f} Герц')
    plt.xlabel('Время (с)')

    plt.subplot(2, 2, 4)
    yf_square[0] = 0
    plt.plot(xf[:N // 2], np.abs(yf_square)[:N // 2], color='green', linestyle='--')
    plt.title(f'Спектр цифрового сигнала {f} Герц')
    plt.xlabel('Частота (Герц)')
    plt.ylabel('Магнитуда')

    plt.tight_layout()
    plt.show()


def on_frequency_change(event):
    selected_frequency = frequency_combobox.get()
    update_plots(selected_frequency)

root = tk.Tk()
root.title("Сигналы")

frequency_combobox = ttk.Combobox(root, values=frequencies)
frequency_combobox.set("Выберите частоту")
frequency_combobox.pack(pady=20)
frequency_combobox.bind("<<ComboboxSelected>>", on_frequency_change)

root.mainloop()

