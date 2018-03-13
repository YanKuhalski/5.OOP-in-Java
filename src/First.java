import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Square obj1 = new Square();
        obj1.input();
        obj1.output();
        Rectangle obj2 = new Rectangle();
        obj2.input();
        obj2.output();
        Triangle obj3 = new Triangle();
        obj3.input();
        obj3.output();
        Circle obj4 = new Circle();
        obj4.input();
        obj4.output();
    }
}

abstract class Shape {
    String name;

    abstract void input();

    void output() {
        System.out.println(this.name + " - площадь фигуры = " + area());
    }

    abstract int area();
}




 class Paralelogram extends Shape  {
    int height;
    int width;

    void input() {
        System.out.println("Введите высоту и ширину ");

        Scanner in = new Scanner(System.in);
        height = in.nextInt();
        width = in.nextInt();
    }

    int area() {
        int area = height * width;
        return area;
    }
}

class Square extends Paralelogram {
    Square() {
        name = "Квадрат";
    }
}

class Rectangle extends Paralelogram {
    Rectangle () {
        name = "Прямоугольник";
    }
}

class Triangle extends Shape  {
    int height;
    int width;

    void input() {
        name = "Треугольник";
        System.out.println("Введите высоту и ширину треугольника");
        Scanner in = new Scanner(System.in);
        height = in.nextInt();
        width = in.nextInt();
    }

    int area() {
        int area = width * height / 2;
        return area;
    }
}

class Circle extends Shape  {
    int radius;

    void input() {
        name = "Krug";
        System.out.println("Введите радиус круга");
        Scanner in = new Scanner(System.in);
        radius = in.nextInt();
    }

    int area() {
        double ploschad = radius* radius* 3.14;
        return (int) ploschad;
    }
}

