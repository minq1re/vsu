import numpy as np
import matplotlib.pyplot as plt
from numpy.fft import rfftfreq
import tkinter as tk
from tkinter import ttk

AMPLITUDE = 1
PHASE = 0
BASE_FREQUENCY = 2
HIGH_FREQ = 16
LOW_FREQ = 8
time_points = np.linspace(0, 1, num=1000, endpoint=False)


def generate_harmonic(frequency, time):
    angular_velocity = 2 * np.pi * frequency
    return AMPLITUDE * np.sin(angular_velocity * time + PHASE)


def digital_signal(frequency, time):
    return step_function(generate_harmonic(frequency, time))


def step_function(value):
    return 1 if value > 0 else 0


def amplitude_modulate(frequency, time):
    base_signal = digital_signal(BASE_FREQUENCY, time)
    return base_signal * generate_harmonic(frequency, time)


def frequency_modulate(freq_high, time, freq_low):
    base_signal = digital_signal(BASE_FREQUENCY, time)
    return generate_harmonic(freq_high, time) if base_signal == 1 else generate_harmonic(freq_low, time)


def phase_modulate(frequency, time):
    base_signal = digital_signal(BASE_FREQUENCY, time)
    return generate_harmonic(frequency, time) if base_signal == 1 else -generate_harmonic(frequency, time)


def get_spectrum(signal):
    return np.fft.rfft(signal) / len(signal)


def spectrum_frequencies():
    return rfftfreq(1000, 0.001)


def filter_spectrum(spectrum, threshold=0.1):
    magnitude = np.abs(spectrum)
    filtered_spectrum = spectrum.copy()
    filtered_spectrum[magnitude < threshold] = 0
    return np.fft.irfft(filtered_spectrum)


amplitude_modulated = [amplitude_modulate(HIGH_FREQ, t) for t in time_points]
frequency_modulated = [frequency_modulate(HIGH_FREQ, t, LOW_FREQ) for t in time_points]
phase_modulated = [phase_modulate(HIGH_FREQ, t) for t in time_points]

spectrum_amplitude = get_spectrum(amplitude_modulated)
spectrum_frequency = get_spectrum(frequency_modulated)
spectrum_phase = get_spectrum(phase_modulated)

filtered_signal = filter_spectrum(spectrum_amplitude)
binary_signal = np.array([digital_signal(BASE_FREQUENCY, t) for t in time_points])


def plot_selected(event):
    choice = dropdown.get()
    fig, axs = plt.subplots(2, 2, figsize=(12, 8), gridspec_kw={'height_ratios': [1, 0.5]})
    axs = axs.ravel()

    for i in range(4):
        axs[i].axis('off')

    if choice == "Амплитудная модуляция и ее спектр":
        axs[0].plot(time_points, amplitude_modulated, color='black')
        axs[0].set_title("Амплитудная модуляция")
        axs[1].plot(spectrum_frequencies(), np.abs(spectrum_amplitude), color='black')
        axs[1].set_title("Спектр амплитудной модуляции")
        axs[1].set_xlim(0, 60)
        axs[0].axis('on')
        axs[1].axis('on')

    elif choice == "Частотная модуляция и ее спектр":
        axs[0].plot(time_points, frequency_modulated, color='blue')
        axs[0].set_title("Частотная модуляция")
        axs[1].plot(spectrum_frequencies(), np.abs(spectrum_frequency), color='blue')
        axs[1].set_title("Спектр частотной модуляции")
        axs[1].set_xlim(0, 60)
        axs[0].axis('on')
        axs[1].axis('on')

    elif choice == "Фазовая модуляция и ее спектр":
        axs[0].plot(time_points, phase_modulated, color='red')
        axs[0].set_title("Фазовая модуляция")
        axs[1].plot(spectrum_frequencies(), np.abs(spectrum_phase), color='red')
        axs[1].set_title("Спектр фазовой модуляции")
        axs[1].set_xlim(0, 60)
        axs[0].axis('on')
        axs[1].axis('on')

    elif choice == "Синтезированный и отфильтрованный сигналы":
        axs[0].plot(spectrum_frequencies(), np.abs(spectrum_amplitude), color='blue')
        axs[0].set_title("Спектр амплитудной модуляции")
        axs[0].set_xlim(0, 60)

        axs[1].plot(time_points, filtered_signal, color='pink')
        axs[1].set_title("Синтезированный сигнал")

        axs[2].plot(time_points, binary_signal, color='orange')
        axs[2].set_title("Отфильтрованный сигнал")
        axs[0].axis('on')
        axs[1].axis('on')
        axs[2].axis('on')

    plt.tight_layout()
    plt.show()


root = tk.Tk()
root.title("Task 2")
label = ttk.Label(root)
label.pack(pady=10)

options = ["Амплитудная модуляция и ее спектр",
           "Частотная модуляция и ее спектр",
           "Фазовая модуляция и ее спектр",
           "Синтезированный и отфильтрованный сигналы"]

dropdown = ttk.Combobox(root, values=options, width=50)
dropdown.set("Выберите графики:")
dropdown.pack(pady=10)
dropdown.bind("<<ComboboxSelected>>", plot_selected)

root.mainloop()
