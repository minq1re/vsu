import pyaudio
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.patches as mpatches
import matplotlib.cm as cm

CHUNK = 1024
FORMAT = pyaudio.paInt16
CHANNELS = 1
RATE = 44100

p = pyaudio.PyAudio()

stream = p.open(format=FORMAT,
                channels=CHANNELS,
                rate=RATE,
                input=True,
                frames_per_buffer=CHUNK)

plt.ion()
fig, (ax_rect, ax_spectrum) = plt.subplots(2, 1)

min_energy = float('inf')
max_energy = float('-inf')

cmap = cm.get_cmap('cool')

def update_plot(energy):
    ax_rect.clear()
    ax_rect.set_xlim(0, 10)
    ax_rect.set_ylim(0, 10)
    color = cmap(energy)
    rect = mpatches.Rectangle((0, 0), 10, 10, color=color)
    ax_rect.add_patch(rect)

    data = np.frombuffer(stream.read(CHUNK), dtype=np.int16)
    fft_data = np.fft.fft(data)
    amplitudes = np.abs(fft_data)
    ax_spectrum.clear()
    ax_spectrum.plot(np.linspace(0, RATE // 2, CHUNK // 2), amplitudes[:CHUNK // 2])

    plt.draw()
    plt.pause(0.001)


while True:
    data = np.frombuffer(stream.read(CHUNK), dtype=np.int16)
    fft_data = np.fft.fft(data)
    amplitudes = np.abs(fft_data)
    energy = np.sum(amplitudes) / len(amplitudes)

    if energy < min_energy:
        min_energy = energy
    if energy > max_energy:
        max_energy = energy

    if max_energy == min_energy:
        normalized_energy = 0.0
    else:
        normalized_energy = (energy - min_energy) / (max_energy - min_energy)

    update_plot(normalized_energy)

    if not plt.fignum_exists(fig.number):
        break

stream.stop_stream()
stream.close()
p.terminate()
