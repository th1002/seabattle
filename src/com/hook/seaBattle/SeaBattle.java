package com.hook.seaBattle;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by User on 10.12.15.
 */
public class SeaBattle {
    public static void main(String[] args) {
        char[] mas = new char[10];

//        Инициализация массива
        for (int i = 0; i < mas.length; i++) {
            mas[i] = '.';
        }

//        Установка корабля
        mas[4] = 'O';

//        Класс считывания потока
        Scanner scanner = new Scanner(System.in);
        int position;

//        Главный цикл программы
        do {
//        Считывание данных у пользователя и
//        произведение выстрела
            System.out.println("Введите номер");
            position = scanner.nextInt();

            switch (mas[position]) {
                case 'O':
                    System.out.println("УРА! Вы подбили корабль!");
                    mas[position] = 'X';
                    break;
                case '*':
                    System.out.println("Вы сюда уже стрелляли :-).");
                    break;
                default:
                    mas[position] = '*';
                    System.out.println("Мимо...");
                    break;
            }

//            Вывод поля на экран
            for (char c : mas) {
                System.out.print(c);
            }
            System.out.println();

        } while (mas[position] != 'X');
    }
}
