import tkinter as tk
from tkinter import filedialog
from PIL import Image, ImageTk, ImageOps
import numpy as np

class ColorObjectHighlighterApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Color Object Highlighter")

        # Create a frame for the buttons
        self.button_frame = tk.Frame(root)
        self.button_frame.pack()

        # Create a button to open an image file
        self.open_button = tk.Button(self.button_frame, text="Open Image", command=self.open_image)
        self.open_button.pack(side=tk.LEFT, padx=5, pady=5)

        # Create buttons to apply color object highlighting
        self.highlight_red_button = tk.Button(self.button_frame, text="Highlight Red Objects", command=self.highlight_red_objects, state=tk.DISABLED)
        self.highlight_red_button.pack(side=tk.LEFT, padx=5, pady=5)

        self.highlight_green_button = tk.Button(self.button_frame, text="Highlight Green Objects", command=self.highlight_green_objects, state=tk.DISABLED)
        self.highlight_green_button.pack(side=tk.LEFT, padx=5, pady=5)

        self.highlight_blue_button = tk.Button(self.button_frame, text="Highlight Blue Objects", command=self.highlight_blue_objects, state=tk.DISABLED)
        self.highlight_blue_button.pack(side=tk.LEFT, padx=5, pady=5)

        # Create a canvas to display the image
        self.canvas = tk.Canvas(root)
        self.canvas.pack()

        # Initialize the image and display variables
        self.original_image = None
        self.display_image = None

    def open_image(self):
        # Open a file dialog to select an image
        file_path = filedialog.askopenfilename()
        if file_path:
            self.original_image = Image.open(file_path)
            self.display_image = ImageTk.PhotoImage(self.original_image)

            # Update the canvas with the selected image
            self.canvas.config(width=self.original_image.width, height=self.original_image.height)
            self.canvas.create_image(0, 0, anchor=tk.NW, image=self.display_image)

            # Enable the highlight buttons
            self.highlight_red_button.config(state=tk.NORMAL)
            self.highlight_green_button.config(state=tk.NORMAL)
            self.highlight_blue_button.config(state=tk.NORMAL)

    def highlight_red_objects(self):
        self.highlight_objects(color='red')

    def highlight_green_objects(self):
        self.highlight_objects(color='green')

    def highlight_blue_objects(self):
        self.highlight_objects(color='blue')

    def highlight_objects(self, color):
        if self.original_image:
            # Convert the image to numpy array
            img_array = np.array(self.original_image)

            # Separate the channels
            r, g, b = img_array[:, :, 0], img_array[:, :, 1], img_array[:, :, 2]

            # Create masks for different colors
            if color == 'red':
                mask = (r > 50) & (g < 70) & (b < 70)
            elif color == 'green':
                mask = (r < 100) & (g > 50) & (b < 100)
            elif color == 'blue':
                mask = (r < 70) & (g < 120) & (b > 120)

            # Convert the original image to grayscale
            grayscale_image = ImageOps.grayscale(self.original_image)
            grayscale_array = np.array(grayscale_image)

            # Create an output image that is initially grayscale
            output_image = np.stack((grayscale_array, grayscale_array, grayscale_array), axis=-1)

            # Place the colored areas back into the output image
            output_image[mask] = img_array[mask]

            # Convert the output image to a format that Tkinter can display
            output_image = Image.fromarray(output_image)
            self.display_image = ImageTk.PhotoImage(output_image)

            # Update the canvas with the highlighted image
            self.canvas.create_image(0, 0, anchor=tk.NW, image=self.display_image)

# Create the main window
root = tk.Tk()
app = ColorObjectHighlighterApp(root)
root.mainloop()
