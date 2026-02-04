package ru.vsu.cs.loseva;

import java.awt.*;

public class Enderman {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Enderman(final int x, final int y, final int width, final int height, final Color color) {
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

    void drawEnderman(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        //head
        g.setColor(new Color(20, 20, 20));
        g.fillRect((int)(this.x + 0.7 * this.width), (int) (this.y + 0.1 * this.height),
                (int)(0.06 * this.width), (int)(0.06 * this.height));
        g.setColor(this.color);
        g.drawLine((int)(this.x + 0.7 * this.width), (int) (this.y + 0.1 * this.height),
                (int)(this.x + 0.76 * this.width), (int)(this.y + 0.1 * this.height));
        g.drawLine((int)(this.x + 0.76 * this.width), (int) (this.y + 0.1 * this.height),
                (int)(this.x + 0.76 * this.width), (int)(this.y + 0.16 * this.height));
        g.drawLine((int)(this.x + 0.7 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(this.x + 0.7 * this.width), (int)(this.y + 0.1 * this.height));

        //eyes
        g.setColor(new Color(222, 122, 250));
        g.fillRect((int)(this.x + 0.705 * this.width), (int) (this.y + 0.13 * this.height),
                (int)(0.02 * this.width), (int)(0.007 * this.height));
        g.fillRect((int)(this.x + 0.735 * this.width), (int) (this.y + 0.13 * this.height),
                (int)(0.02 * this.width), (int)(0.007 * this.height));

        g.setColor(new Color(200, 0, 250));
        g.fillRect((int)(this.x + 0.7135 * this.width), (int) (this.y + 0.13 * this.height),
                (int)(0.02 / 3 * this.width), (int)(0.007 * this.height));
        g.fillRect((int)(this.x + 0.7435 * this.width), (int) (this.y + 0.13 * this.height),
                (int)(0.02 / 3 * this.width), (int)(0.007 * this.height));

        //body
        g.setColor(new Color(20, 20, 20));
        g.fillRect((int)(this.x + 0.7 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(0.06 * this.width), (int)(0.1 * this.height));

        //hands
        g.setColor(new Color(20, 20, 20));
        g.fillRect((int)(this.x + 0.76 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(0.02 * this.width), (int)(0.2 * this.height));

        g.fillRect((int)(this.x + 0.68 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(0.02 * this.width), (int)(0.2 * this.height));

        g.setColor(this.color);
        g.drawLine((int)(this.x + 0.68 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(this.x + 0.78 * this.width), (int)(this.y + 0.16 * this.height));;
        g.drawLine((int)(this.x + 0.68 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(this.x + 0.68 * this.width), (int)(this.y + 0.36 * this.height));
        g.drawLine((int)(this.x + 0.68 * this.width), (int) (this.y + 0.36 * this.height),
                (int)(this.x + 0.7 * this.width), (int)(this.y + 0.36 * this.height));
        g.drawLine((int)(this.x + 0.7 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(this.x + 0.7 * this.width), (int)(this.y + 0.36 * this.height));

        g.drawLine((int)(this.x + 0.76 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(this.x + 0.76 * this.width), (int)(this.y + 0.36 * this.height));
        g.drawLine((int)(this.x + 0.76 * this.width), (int) (this.y + 0.36 * this.height),
                (int)(this.x + 0.78 * this.width), (int)(this.y + 0.36 * this.height));
        g.drawLine((int)(this.x + 0.78 * this.width), (int) (this.y + 0.16 * this.height),
                (int)(this.x + 0.78 * this.width), (int)(this.y + 0.36 * this.height));



        //legs
        g.setColor(new Color(20, 20, 20));
        g.fillRect((int)(this.x + 0.705 * this.width), (int) (this.y + 0.26 * this.height),
                (int)(0.02 * this.width), (int)(0.225 * this.height));

        g.fillRect((int)(this.x + 0.735 * this.width), (int) (this.y + 0.26 * this.height),
                (int)(0.02 * this.width), (int)(0.225 * this.height));

        g.setColor(this.color);
        g.drawLine((int)(this.x + 0.7 * this.width), (int) (this.y + 0.26 * this.height),
                (int)(this.x + 0.76 * this.width), (int)(this.y + 0.26 * this.height));
        g.drawLine((int)(this.x + 0.725 * this.width), (int) (this.y + 0.26 * this.height),
                (int)(this.x + 0.725 * this.width), (int)(this.y + 0.485 * this.height));
        g.drawLine((int)(this.x + 0.725 * this.width), (int) (this.y + 0.485 * this.height),
                (int)(this.x + 0.705 * this.width), (int)(this.y + 0.485 * this.height));
        g.drawLine((int)(this.x + 0.705 * this.width), (int) (this.y + 0.26 * this.height),
                (int)(this.x + 0.705 * this.width), (int)(this.y + 0.485 * this.height));

        g.drawLine((int)(this.x + 0.755 * this.width), (int) (this.y + 0.26 * this.height),
                (int)(this.x + 0.755 * this.width), (int)(this.y + 0.485 * this.height));
        g.drawLine((int)(this.x + 0.755 * this.width), (int) (this.y + 0.485 * this.height),
                (int)(this.x + 0.735 * this.width), (int)(this.y + 0.485 * this.height));
        g.drawLine((int)(this.x + 0.735 * this.width), (int) (this.y + 0.26 * this.height),
                (int)(this.x + 0.735 * this.width), (int)(this.y + 0.485 * this.height));

    }
}
