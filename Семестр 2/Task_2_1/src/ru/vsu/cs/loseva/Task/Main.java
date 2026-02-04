package ru.vsu.cs.loseva.Task;

import ru.vsu.cs.loseva.FrameMain;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() { new FrameMain().setVisible(true);}
        });
    }
}
