package ru.vsu.cs.loseva;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final DrawingPanel panel;

    public MainWindow() throws HeadlessException {
        panel = new DrawingPanel(this.getWidth(), this.getHeight(), 1);
        this.add(panel);
    }
}
