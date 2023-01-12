  /*
  Задача
  Реализовать игру крестики нолики
   */

  /*
  Теория
  В Java существуют библиотеки для создания графического интерфейса. Для того, чтобы создать окно достаточно
  унаследоваться от соответсвующего класса

  Для рисования в Java используется класс Graphics

  Внутренние классы имеют широкое практическое применение. Боятся их не стоит. Они такие же как и обычные классы. Класс
  делают внутренним, если понятно, что его функционал не понадобится за пределами текущего класса(возможны варианты, их пока
  опускаем).

  Для обработки события пользователя, в частность события нажатия клавиш мыши есть специальные классы, интерфейсы и т.д.
  Ими можно достаточно легко пользоваться, просто добавив их в свой проект. Работа с ООП

  ArrayList - класс для работы с массивами. Более функциональный, чем обычные массивы. Массив на стеройдах.



   */

  /*
  План работы
  1)Сделать окно
  2)Нарисовать на окне сетку
  3)Обработать события мыши
  4)Рисовать крестики или нолик в зависимости от нажатия кнопки
  Для отрисовки крестиков и ноликов создадаим классы, т.к. классы имеют общие параметры, создадим абстрактный класс предок
  для элементов игры, в котором будет абстрактный метод для рисования
  5) Добиться отображения элемента центре прямоугльника сетки
  6) Корректная отрисовка элементов
  7)Добиться исполнения правил игры

   */

  import javax.swing.*;
  import java.awt.*;
  import java.awt.event.MouseAdapter;
  import java.awt.event.MouseEvent;
  import java.util.ArrayList;

  // Наследуемся от класса JFrame. Он позволит нам создавать окно.
  public class Main extends JFrame {
      // Создаю массив для работы с классом Zero. В кривых скобочках указываю тип, который будет храниться в массиве
      ArrayList<Zero> zeros = new ArrayList<>();
      // Создаю массив для работы с крестиками
      ArrayList<Cross> crosses = new ArrayList<>();

      int count = 1; // Счетчик нажатий
      int gameCount = 0; // Счетчик ходов

    public static void main(String[] args) {

        Main main = new Main(Res.TITLE);    // Создаем объект, который сделает окно. Передаем в конструктор название окна
        main.setSize(Res.WINDOW_WIDTH, Res.WINDOW_HEIGHT);      // Задаем размеры окна
        main.setResizable(false);   // Запрещаем менять размеры окна
        main.setVisible(true);     // Делаем окно видимым
    }
                    // throws появился автоматически, он нужен для обработки непредвиденных ситуация, пока на него не обращаем внимания
    public Main(String title) throws HeadlessException {        // Создаем конструктор, который будет принимать название окна
      super(title);     // super - ссылка на класс родитель. Передаем в конструктор родителя название окна. Так нужно, чтобы окно работало.

        GameField panel = new GameField();     // Создаем объект для работы с игровой панелью. На основе внутреннего класса

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);    // Передаем событие классу родителю. Это нужно, чтобы код работал. Пока просто пишем так
                int x = e.getX();      // Получаем координаты нажатия мыши
                int y = e.getY();

               if(gameCount < 9  && emptyField(x, y)) {
                   System.out.println(x + " " + y);  // Выводим в консоль координаты нажатия


                   if (count == 1) {        // Если очередь нолика
                       Zero zero = new Zero(x, y); // Создаю объект для работы с ноликом
                       zero.makePosition();   // Центрирую координаты нолика
                       System.out.println(zero.getX() + " " + zero.getY() );
                       zeros.add(zero);  // Добавляю в массив(ArrayList) созданный объект
                       count = 2;           // Передаем очередь крестику
                   } else {                     //Если очередь кретика

                       Cross cross = new Cross(x, y);
                       cross.makePosition();
                       crosses.add(cross);  // Добавляем крестик
                       count = 1;    // Передаем очередь нолику
                   }


                   panel.repaint(); // Перерисовка игровой панели, чтобы были видны изменения

                   gameCount++;
               }
               else{


               }
            }
        });


          setContentPane(panel);  // Добавляем элемент в окно.


    }

      private boolean emptyField(int x, int y) {
        Zero centerCoordinate = new Zero(x, y);   // Приводим координаты к центру для этого создаем объект типа Zero. Он нужен только для уентрирования координат
        centerCoordinate.makePosition();    // Центрируем координаты

          for (int i = 0; i < zeros.size(); i++) {    // Проверяем заняты ли координаты ноликами
              if(zeros.get(i).getX() == centerCoordinate.getX() &&
              zeros.get(i).getY() == centerCoordinate.getY()){
                  System.out.println("Место занято");
                  return false;
              }
          }

          for (int i = 0; i < crosses.size(); i++) {          // Проверяем заняты ли координаты крестиками
                 if(crosses.get(i).getX() == centerCoordinate.getX() &&
                 crosses.get(i).getY() == centerCoordinate.getY()){
                     System.out.println("Место занято");
                     return false;
                 }
          }
               return true;
      }

      //Наследуемся от класса JPanel, чтобы нарисовать сетку и прослушать события нажатия кнопки мыши
    public class GameField extends JPanel{

        @Override
        public void paint(Graphics g) {       // Переопределяем метод paint для рисования. Класс Graphics - кисть для рисования
            super.paint(g);

            g.setColor(Color.GRAY);     // Устанавливаем цвет для кисти


            for (int i = Res.CELL_SIZE; i < Res.FIELD_SIZE ; i += Res.CELL_SIZE) {   // Цикл для прорисовки сетки
                g.drawLine(0, i, Res.FIELD_SIZE, i);        // Горизонтальные линии
                g.drawLine(i, 0, i, Res.FIELD_SIZE);        // Вертикальные линии

            }

            for (int i = 0; i < zeros.size(); i++) {   // Перебираем нолики
                zeros.get(i).draw(g);   // Отрисовываем каждый нолик. Обращаемся к массиву(ArrayList) zeros, чтобы получить i-й элемент.
                // Вызываем у этого элемент метод draw для отрисовки нолика и передаем ему кисть(g)
            }

            for (int i = 0; i < crosses.size(); i++) {   // Перебираем крестики
                crosses.get(i).draw(g);   // Отрисовываем каждый крестик. Обращаемся к массиву(ArrayList) crosses, чтобы получить i-й элемент.
                // Вызываем у этого элемент метод draw для отрисовки крестика и передаем ему кисть(g)
            }


        }
    }
  }
