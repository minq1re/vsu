package ru.vsu.cs.loseva;

import java.awt.*;
import java.awt.geom.Path2D;

public class Bee {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Bee(final int x, final int y, final int width, final int height, final Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    void drawBee(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        g.setStroke(new BasicStroke(7, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        //flyes
        g.setColor(new Color(184, 228, 255));
        Path2D.Double c1 = new Path2D.Double();
        c1.moveTo((int)(this.x + 0.15 * this.width), (int)(this.y + 0.35 * this.height));
        c1.curveTo((int)(this.x + 0.1 * this.width), (int)(this.y + 0.2 * this.height),
                (int)(this.x + 0.2 * this.width), (int)(this.y - 0.1 * this.height),
                (int)(this.x + 0.15 * this.width), (int)(this.y + 0.35 * this.height));
        g.draw(c1);
        g.setColor(new Color(220, 246, 255));
        g.fill(c1);

        //body
        g.setColor(this.color);
        g.fillRect((int)(this.x + 0.1 * this.width), (int) (this.y + 0.3 * this.height),
                (int)(0.1 * this.width), (int)(0.4 * this.height));

        g.setColor(new Color(102, 51, 0));
        g.fillRect((int)(this.x + 0.1 * this.width), (int) (this.y + 0.3 * this.height),
                (int)(0.03 * this.width), (int)(0.4 * this.height));
        g.drawLine((int)(this.x + 0.14 * this.width), (int) (this.y + 0.31 * this.height),
                (int)(this.x + 0.14 * this.width), (int)(this.y + 0.69 * this.height));
        g.drawLine((int)(this.x + 0.16 * this.width), (int) (this.y + 0.31 * this.height),
                (int)(this.x + 0.16 * this.width), (int)(this.y + 0.69 * this.height));

        //flyes
        g.setColor(new Color(184, 228, 255));
        Path2D.Double c2 = new Path2D.Double();
        c2.moveTo((int)(this.x + 0.15 * this.width), (int)(this.y + 0.35 * this.height));
        c2.curveTo((int)(this.x + 0.05 * this.width), (int)(this.y + 0.2 * this.height),
                (int)(this.x + 0.12 * this.width), (int)(this.y - 0.1 * this.height),
                (int)(this.x + 0.15 * this.width), (int)(this.y + 0.35 * this.height));
        g.draw(c2);
        g.setColor(new Color(220, 246, 255));
        g.fill(c2);

        //eyes
        g.setColor(Color.BLACK);
        g.fillRect((int)(this.x + 0.18 * this.width), (int) (this.y + 0.3 * this.height),
                (int)(0.02 * this.width), (int)(0.1 * this.height));
        g.setColor(Color.WHITE);
        g.fillRect((int)(this.x + 0.19 * this.width), (int) (this.y + 0.305 * this.height),
                (int)(0.01 * this.width), (int)(0.03 * this.height));
    }
}
