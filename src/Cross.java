import java.awt.*;

public class Cross extends GameObject {

    private int x;           // Устанавливаем координаты для крестика
    private int y;

    public Cross(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {           // Геттеры и сеттеры
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
    public void draw(Graphics graphics) {

        graphics.drawLine(x, y, x + 100, y + 100);  //gorizontal line
                                                              //vertical line
        graphics.drawLine(x + 1, y + 101, x + 101, y + 1);
        graphics.drawLine(x + 2, y + 102, x + 102, y + 2);
        graphics.drawLine(x + 3, y + 103, x + 103, y + 3);
        graphics.drawLine(x + 4, y + 104, x + 104, y + 4);
    }

    @Override
    public void makePosition() {    // Центрирование координат
        if (x < 200) {
            if (y < 200) {
                x = 50;
                y = 50;
            }
            if (200 < y && y < 400) {
                x = 50;
                y = 250;
            }
            if (400 < y && y < 600) {
                x = 50;
                y = 450;
            }
        }
        if (200 < x && x < 400) {
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
        if (400 < x && x < 600) {
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
    }
}


