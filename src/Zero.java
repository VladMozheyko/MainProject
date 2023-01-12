import java.awt.*;

public class Zero extends GameObject {
    private int x;    // Координаты, в которых будем рисовать нолик
    private int y;

    public Zero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void draw(Graphics graphics) {   // Конкретная реализация метода родителя

        graphics.setColor(Color.BLUE);
        graphics.drawOval(x, y,100, 100);
        graphics.drawOval(x+1, y+1, 98, 98);
    }

    @Override
    public void makePosition() {
        if(x <  200)
        {
            if (y < 200) {
                x = 50;
                y = 50;
            }
            if (200 <y && y < 400) {
                x = 50;
                y = 250;
            }
            if (400 < y && y < 600) {
                x = 50;
                y = 450;
            }
        }
        if(200<x && x < 400)
        {

            if (y < 200) {
                x = 250;
                y = 50;
            }
            if (200 < y && y < 400) {
                x = 250;
                y = 250;
            }
            if (400 < y && y < 600) {
                x = 250;
                y = 450;
            }
        }
        if(400<x && x<600)
        {
            if (y < 200) {
                x = 450;
                y = 50;
            }
            if (200 < y && y < 400) {
                x = 450;
                y = 250;
            }
            if (400 < y && y < 600) {
                x = 450;
                y = 450;
            }
        }

        System.out.println("Zero: " + x + " " + y);
    }
}


