import java.awt.*;

public abstract class GameObject {

    public abstract void draw(Graphics graphics);     // Создаем абстрактный метод, который будет иметь свою реализацию в каждом классе потомке

    public abstract void makePosition();        // Метод для центрирования координат
}
