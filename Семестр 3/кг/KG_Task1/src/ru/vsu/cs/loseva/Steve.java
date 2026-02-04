package ru.vsu.cs.loseva;

import java.awt.*;
import java.awt.geom.Path2D;

public class Steve {

    private int x;
    private int y;
    private int width;
    private int height;

    public Steve(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    void drawSteve(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        g.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // head
        g.setColor(new Color(169, 125, 100));
        g.fillRect((int)(this.x + 0.5 * this.width), (int) (this.y + 0.5 * this.height),
                (int)(0.2 * this.width), (int)(0.2 * this.height));

        Path2D.Double p = new Path2D.Double();
        p.moveTo((int)(this.x + 0.5 * this.width), (int) (this.y + 0.5 * this.height));
        p.lineTo((int)(this.x + 0.7 * this.width), (int) (this.y + 0.5 * this.height));
        p.lineTo((int)(this.x + 0.7 * this.width), (int) (this.y + 0.575 * this.height));
        p.lineTo((int)(this.x + 0.67 * this.width), (int) (this.y + 0.575 * this.height));
        p.lineTo((int)(this.x + 0.67 * this.width), (int)(this.y + 0.55 * this.height));
        p.lineTo((int)(this.x + 0.53 * this.width), (int)(this.y + 0.55 * this.height));
        p.lineTo((int)(this.x + 0.53 * this.width), (int) (this.y + 0.575 * this.height));
        p.lineTo((int)(this.x + 0.5 * this.width), (int) (this.y + 0.575 * this.height));
        p.closePath();
        g.setColor(new Color(49, 48, 43));
        g.fill(p);

        // face
        g.setColor((Color.WHITE));
        g.fillRect((int)(this.x + 0.53 * this.width), (int)(this.y + 0.6 * this.height),
                (int)(0.045 * this.width), (int)(0.025 * this.height));
        g.setColor(new Color(73, 70, 151));
        g.fillRect((int)(this.x + 0.552 * this.width), (int)(this.y + 0.6 * this.height),
                (int)(0.0225 * this.width), (int)(0.025 * this.height));

        g.setColor((Color.WHITE));
        g.fillRect((int)(this.x + 0.625 * this.width), (int)(this.y + 0.6 * this.height),
                (int)(0.045 * this.width), (int)(0.025 * this.height));
        g.setColor(new Color(73, 70, 151));
        g.fillRect((int)(this.x + 0.625 * this.width), (int)(this.y + 0.6 * this.height),
                (int)(0.0225 * this.width), (int)(0.025 * this.height));

        g.setColor(new Color(99, 70, 49));
        g.fillRect((int)(this.x + 0.575 * this.width), (int)(this.y + 0.625 * this.height),
                (int)(0.05 * this.width), (int)(0.02 * this.height));

        Path2D.Double c = new Path2D.Double();
        c.moveTo((int)(this.x + 0.57 * this.width), (int)(this.y + 0.67 * this.height));
        c.curveTo((int)(this.x + 0.59 * this.width), (int)(this.y + 0.69 * this.height),
                (int)(this.x + 0.61 * this.width), (int)(this.y + 0.69 * this.height),
                (int)(this.x + 0.63 * this.width), (int)(this.y + 0.67 * this.height));
        g.draw(c);



        // body
        g.setColor(new Color(14, 174, 174));
        g.fillRect((int)(this.x + 0.5 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(0.2 * this.width), (int)(0.24 * this.height));
        g.setColor(new Color(32, 160, 150));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(this.x + 0.7 * this.width), (int)(this.y + 0.7 * this.height));
        g.drawLine((int)(this.x + 0.7 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(this.x + 0.7 * this.width), (int)(this.y + 0.94 * this.height));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(this.x + 0.5 * this.width),(int)(this.y + 0.7 * this.height));

        // hands
        g.setColor(new Color(14, 174, 174));
        g.fillRect((int)(this.x + 0.435 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(0.065 * this.width), (int)(0.1 * this.height));

        g.setColor(new Color(32, 160, 150));
        g.drawLine((int)(this.x + 0.435 * this.width), (int)(this.y + 0.795 * this.height),
                (int)(this.x + 0.5 * this.width), (int)(this.y + 0.795 * this.height));
        g.drawLine((int)(this.x + 0.435 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(this.x + 0.435 * this.width), (int)(this.y + 0.8 * this.height));
        g.drawLine((int)(this.x + 0.435 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(this.x + 0.5 * this.width), (int)(this.y + 0.7 * this.height));

        g.setColor(new Color(169, 125, 100));
        g.fillRect((int)(this.x + 0.4355 * this.width), (int)(this.y + 0.8 * this.height),
                (int)(0.065 * this.width), (int)(0.14 * this.height));

        g.setColor(new Color(138, 98, 77));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + 0.8 * this.height),
                (int)(this.x + 0.5 * this.width), (int)(this.y + 0.94 * this.height));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(this.x + 0.435 * this.width), (int)(this.y + 0.94 * this.height));
        g.drawLine((int)(this.x + 0.435 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(this.x + 0.435 * this.width), (int)(this.y + 0.8 * this.height));

        g.setColor(new Color(14, 174, 174));
        g.fillRect((int)(this.x + 0.7 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(0.065 * this.width), (int)(0.1 * this.height));
        g.setColor(new Color(32, 160, 150));
        g.drawLine((int)(this.x + 0.7 * this.width), (int)(this.y + 0.795 * this.height),
                (int)(this.x + 0.765 * this.width), (int)(this.y + 0.795 * this.height));
        g.drawLine((int)(this.x + 0.765 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(this.x + 0.765 * this.width), (int)(this.y + 0.8 * this.height));
        g.drawLine((int)(this.x + 0.765 * this.width), (int)(this.y + 0.7 * this.height),
                (int)(this.x + 0.7 * this.width), (int)(this.y + 0.7 * this.height));


        g.setColor(new Color(169, 125, 100));
        g.fillRect((int)(this.x + 0.7 * this.width), (int)(this.y + 0.8 * this.height),
                (int)(0.065 * this.width), (int)(0.14 * this.height));

        g.setColor(new Color(138, 98, 77));
        g.drawLine((int)(this.x + 0.7 * this.width), (int)(this.y + 0.8 * this.height),
                (int)(this.x + 0.7 * this.width), (int)(this.y + 0.94 * this.height));
        g.drawLine((int)(this.x + 0.7 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(this.x + 0.765 * this.width), (int)(this.y + 0.94 * this.height));
        g.drawLine((int)(this.x + 0.765 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(this.x + 0.765 * this.width), (int)(this.y + 0.8 * this.height));

        // legs
        g.setColor(new Color(73, 70, 151));
        g.fillRect((int)(this.x + 0.5 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(0.2 * this.width), (int)(0.275 * this.height));
        g.setColor(new Color(42, 40, 97));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(this.x + 0.7 * this.width),(int)(this.y + 0.94 * this.height));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + 0.98 * this.height),
                (int)(this.x + 0.7 * this.width),(int)(this.y + 0.98 * this.height));
        g.drawLine((int)(this.x + 0.7 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(this.x + 0.7 * this.width),(int)(this.y + 1.215 * this.height));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + 0.94 * this.height),
                (int)(this.x + 0.5 * this.width),(int)(this.y + 1.215 * this.height));
        g.drawLine((int)(this.x + 0.6 * this.width), (int)(this.y + 0.98 * this.height),
                (int)(this.x + 0.6 * this.width), (int)(this.y + 1.215 * this.height));

        g.setColor(new Color(107, 107, 107));
        g.fillRect((int)(this.x + 0.5 * this.width), (int)(this.y + 1.215 * this.height),
                (int)(0.2 * this.width), (int)(0.275 * 2 / 7 * this.height));
        g.setColor(new Color(80, 80, 80));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + 1.215 * this.height),
                (int)(this.x + 0.7 * this.width),(int)(this.y + 1.215 * this.height));
        g.drawLine((int)(this.x + 0.7 * this.width), (int)(this.y + 1.215 * this.height),
                (int)(this.x + 0.7 * this.width),(int)(this.y + (1.215 + 0.275 * 2 / 7) * this.height));
        g.drawLine((int)(this.x + 0.7 * this.width), (int)(this.y + (1.215 + 0.275 * 2 / 7) * this.height),
                (int)(this.x + 0.5 * this.width),(int)(this.y + (1.215 + 0.275 * 2 / 7) * this.height));
        g.drawLine((int)(this.x + 0.5 * this.width), (int)(this.y + (1.215 + 0.275 * 2 / 7) * this.height),
                (int)(this.x + 0.5 * this.width),(int)(this.y + 1.215 * this.height));
        g.drawLine((int)(this.x + 0.6 * this.width), (int)(this.y + (1.215 + 0.275 * 2 / 7) * this.height),
                (int)(this.x + 0.6 * this.width),(int)(this.y + 1.215 * this.height));

    }
}