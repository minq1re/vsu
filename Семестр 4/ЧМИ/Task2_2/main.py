import pyautogui
import time
import matplotlib.pyplot as plt

# Open the EMG data file
emg_file = open('sakun.asc', 'r')

data = []
for line in emg_file:
    # Split the line into individual data points
    data.append(int(line.split()[0]))

fig, ax = plt.subplots()

# Plot the EMG data
ax.plot(data)

# Set the x-axis label
ax.set_xlabel('Time (samples)')

# Set the y-axis label
ax.set_ylabel('EMG value')

# Adjust the layout to make space for the button below the plot
plt.subplots_adjust(bottom=0.2)

# Create a button below the plot
button_ax = plt.axes([0.35, 0.1, 0.3, 0.05])  # Left, bottom, width, height
button = plt.Button(button_ax, 'Change Volume')


# Connect the button to the volume change function
def on_click(event):
    # Restart reading the EMG data from the beginning
    emg_file.seek(0)

    # Read the EMG data line by line
    for line in emg_file:
        # Split the line into individual data points
        data = line.split()

        # Get the current EMG value
        emg = int(data[0])

        # Check if the EMG value is above a certain threshold (e.g., 30)
        if emg > 30:
            # Increase the volume
            pyautogui.hotkey('volumeup')
        else:
            # Decrease the volume
            pyautogui.hotkey('volumedown')

        # Sleep for a short period of time (e.g., 0.1 seconds)
        time.sleep(0.1)


button.on_clicked(on_click)

# Show the plot with the button below it
plt.show()
