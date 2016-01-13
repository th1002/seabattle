package com.hook.seaBattle;

/**
 * Created by User on 11.12.15.
 */
public class Manager {

    public static void main(String[] args) {
//        Создание объектов
        Field field = new Field();
        Player player = new Comp();

//        field.init();
//        field.createShips();
//        field.show();
//        field.battle(player.getShot());
//        field.show();
        createFieldAndSetShips(field, player);
        mainLoopOfGame(field, player);
    }

    private static void createFieldAndSetShips(Field field, Player player) {
//        Создание поля и установка корабля
        field.init();
//        player.greeting();
        field.createShips();
        field.show();
    }

    private static void mainLoopOfGame(Field field, Player player) {
//        Главный цикл программы
        do {
            field.battle(player.getShot());
            field.show();
            System.out.println("Статистика выстрелов по цели: " + Player.getCountShotsOnTarget());
            System.out.println("Статистика повторных выстрелов: " + Player.getCountRepeatShots());
            System.out.println("Статистика промахов: " + Player.getCountShotsBloomers());
            System.out.println("Статистика ошибочных выстрелов: " + Player.getCountErrors());
            System.out.println("Общая статистика выстрелов: " + (Player.getCountErrors() + Player.getCountShotsBloomers()
                    + Player.getCountRepeatShots() + Player.getCountShotsOnTarget()));
        } while (field.isFall());
    }


}
