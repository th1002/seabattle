package com.hook.seaBattle;

import java.util.Scanner;

/**
 * Created by User on 11.12.15.
 */
public class Human extends Player {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//        Считывание данных у пользователя и произведение выстрела
    @Override
    public Coordinates getShot() {
//        Класс считывания потока
        Coordinates position = new Coordinates();
        Scanner scanner = new Scanner(System.in);

//        Определение координаты выстрела
        System.out.print("Координата широты---> ");
        String s  = scanner.nextLine();
        int x = Integer.parseInt(s);
        System.out.print("Координата долготы---> ");
        s  = scanner.nextLine();
//        char А = int 1040 char а = int 1072
        int y = ((int)s.charAt(0) < 1072) ? (int)s.charAt(0) - 1039 : (int)s.charAt(0) - 1071;

//        Проверка координат >1 потому что на 0 индексе расположены шкалы по y
//        по x за компанию (для понимания)
        if ((y >= 1 && y <= 10) && (x >= 1 && x <= 10)) {
            position = new Coordinates(y - 1, x - 1);
        } else {
            position = new Coordinates(-1, -1);
        }

        return position;
    }

    @Override
    public void greeting(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Здравствуйте! :-)");
        System.out.println("Представтесь, пожалуйста: ");
        this.name = scanner.nextLine();
        System.out.printf("Ну что, %s, поиграем?\n", this.name);
    }
}
