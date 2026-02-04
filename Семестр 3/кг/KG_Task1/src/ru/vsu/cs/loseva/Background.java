package ru.vsu.cs.loseva;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    private int x;
    private int y;
    private int width;
    private int height;

    private BufferedImage imageGrass;
    private  BufferedImage imageGround;
    public Background(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        try {
            imageGrass = ImageIO.read(new File("D:\\grass.jpg"));
            imageGround = ImageIO.read(new File("D:\\ground.jpg"));
        }
        catch (Exception e) {

        }

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

    void drawBackground(final Graphics gr) throws IOException {
        Graphics2D g = (Graphics2D) gr;

        //grass
        g.drawImage(imageGrass, this.x, (int)(this.y + 0.7 * this.height), this.width, this.height, null);

        //sky
        g.setColor(new Color(84, 185, 247));
        g.fillRect(this.x, this.y, this.width, (int)(this.height * 0.7));

        //ground

        g.drawImage(imageGround, (int)(this.x + 0.5 * this.width), (int)(this.y + 0.485 * this.height),
                (int)(0.2 * this.width), (int)(0.1 * this.height), null);
        g.drawImage(imageGround, (int)(this.x + 0.4 * this.width), (int)(this.y + 0.585 * this.height),
                (int)(0.2 * this.width), (int)(0.115 * this.height), null);
        g.drawImage(imageGround, (int)(this.x + 0.6 * this.width), (int)(this.y + 0.585 * this.height),
                (int)(0.2 * this.width), (int)(0.115 * this.height), null);
    }
}
