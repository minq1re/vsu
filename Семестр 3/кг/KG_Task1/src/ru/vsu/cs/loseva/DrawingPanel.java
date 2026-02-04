package ru.vsu.cs.loseva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DrawingPanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;
    private Timer timer;
    private int ticksFromStart = 50;

    private Steve steve;
    private Enderman enderman;
    private Background background;
    private Bee bee;

    public DrawingPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        timer = new Timer(timerDelay, this);
        timer.start();

        this.steve = new Steve(0, 0, 500, 500);
        this.enderman = new Enderman(0, 0, 800, 800, Color.BLACK);
        this.background = new Background(0,0, 1000, 800);
        this.bee = new Bee(ticksFromStart, 0, 1000, 200, Color.ORANGE);
    }

    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        try {
            background.drawBackground(gr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        steve.drawSteve(gr);
        bee.setX(ticksFromStart);
        bee.drawBee(gr);
        enderman.drawEnderman(gr);

    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            ticksFromStart += 1;
        }
    }
}