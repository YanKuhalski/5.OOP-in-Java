import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.exit;

public class Second {
    public static void main(String[] args) {
        Base base = new Base();
        System.out.println(base.inputBase());
        base.getMiddleAge();
        base.getDate();
    }
}

abstract class Persone {
    String name;
    String surname;
    Date birthday;

    void input() {
    }

    void getDate() {
        SimpleDateFormat formatOfOutput = new SimpleDateFormat("dd.MM.yyyy ' время' kk:mm");
        System.out.print("\n День рождения cтудента  " + surname + " " + name + " :" + formatOfOutput.format(birthday));
    }
}

class Student extends Persone {
    static int numberOfStudents = 0;

    void input() {
        numberOfStudents = numberOfStudents + 1;
        System.out.println("Ввод инвормации о cтуденте " + numberOfStudents);
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя ");
        this.name = in.next();
        System.out.println("Введите фамилию ");
        this.surname = in.next();
        System.out.println("Введите дату и время рождения этого студента (в формате 'dd.MM.yyyy-hh:mm') ");
        String dateInSring = in.next();
        SimpleDateFormat formatOfInput = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
        try {
            Date uDate = formatOfInput.parse(dateInSring);
            this.birthday = uDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Base implements inputBase, middleAge {
    public Persone[] persones = new Persone[500];
    int size;

    void getDate() {
        for (int i = 0; i < size; i++) {
            persones[i].getDate();
        }
    }

    Base() {
        System.out.println("Вы ходите создать базу студентов ? (Да-1) ");
        Scanner in = new Scanner(System.in);
        int answer = in.nextInt();
        if (answer == 1) {
            System.out.println("Введите количество студентов в базе");
            size = in.nextInt();
            for (int i = 0; i < size; i++) {
                persones[i] = new Student();
            }
        } else {
            System.out.println("U can't do it");
            exit(1);
        }
    }

    public String inputBase() {
        for (int i = 0; i < size; i++) {
            persones[i].input();
        }
        return "База заполнена";
    }

    public void getMiddleAge() {
        Date now = new Date();
        long summTime = 0;
        for (int i = 0; i < size; i++) {
            summTime = summTime + (persones[i].birthday.getTime());
        }
        summTime = summTime / size;
        long difference = now.getTime() - summTime;
        int middleYears = (int) (difference / (24 * 60 * 60 * 1000));
        middleYears = (int) (middleYears / 365.25);
        int middleMonth = (int) (difference / (24 * 60 * 60 * 1000));
        middleMonth = (int) (middleMonth / 30.43) % 12;
        int middleDays = (int) (difference / (24 * 60 * 60 * 1000));
        middleDays = (int) ((middleDays % 365) % 30.43);
        int middleHours = (int) (difference / (60 * 60 * 1000));
        middleHours = (int) (((middleHours % 365) % 30.43) % 24);
        int middleMinutes = (int) (difference / (60 * 1000));
        middleMinutes = (int) ((((middleMinutes % 365) % 30.43) % 24) % 60);
        System.out.print("Средний возраст : " + middleYears);
        int tenRanK = middleYears % 10;
        int hundRanK = middleYears % 100;
        if ((tenRanK == 0) || (tenRanK > 4) && (tenRanK < 10) || (hundRanK > 10) && (hundRanK < 21))
            System.out.printf(" Лет ");
        else if (tenRanK == 1) System.out.printf(" Год ");
        else if ((tenRanK > 1) && (tenRanK < 5)) System.out.printf(" Года ");
        System.out.print(middleMonth);
        tenRanK = middleMonth % 10;
        hundRanK = middleMonth % 100;
        if ((tenRanK == 0) || (tenRanK > 4) && (tenRanK < 10) || (hundRanK > 10) && (hundRanK < 13))
            System.out.printf(" Месяцев ");
        else if (tenRanK == 1) System.out.printf(" Месяц ");
        else if ((tenRanK > 1) && (tenRanK < 5)) System.out.printf(" Месяца ");
        System.out.print(middleDays);
        tenRanK = middleDays % 10;
        hundRanK = middleDays % 100;
        if ((tenRanK == 0) || (tenRanK > 4) && (tenRanK < 10) || (hundRanK > 10) && (hundRanK < 21))
            System.out.printf("Дней ");
        else if (tenRanK == 1) System.out.printf(" День ");
        else if ((tenRanK > 1) && (tenRanK < 5)) System.out.printf(" Дня ");
        System.out.print(middleHours);
        tenRanK = middleHours % 10;
        hundRanK = middleHours % 100;
        if ((tenRanK == 0) || (tenRanK > 4) && (tenRanK < 10) || (hundRanK > 10) && (hundRanK < 21))
            System.out.printf(" Часов ");
        else if (tenRanK == 1) System.out.printf(" Час ");
        else if ((tenRanK > 1) && (tenRanK < 5)) System.out.printf(" Часа ");
        System.out.print(middleMinutes);
        tenRanK = middleMinutes % 10;
        hundRanK = middleMinutes % 100;
        if ((tenRanK == 0) || (tenRanK > 4) && (tenRanK < 10) || (hundRanK > 10) && (hundRanK < 21))
            System.out.printf(" Минут ");
        else if (tenRanK == 1) System.out.printf(" Минута ");
        else if ((tenRanK > 1) && (tenRanK < 5)) System.out.printf(" Минуты ");
    }
}

interface inputBase {
    String inputBase();
}

interface middleAge {
    void getMiddleAge();
}