package com.hook.seaBattle;

import javafx.scene.control.Cell;

import java.util.Scanner;

/**
 * Created by User on 11.12.15.
 */
public class Field {
    private static final int SIZE = 10;
    public enum CellType {COVER, WOUND, PART_OF_SHIP, BLOOMER}

    private CellType[][] mas = new CellType[SIZE][SIZE];

    public CellType[][] getMas() {
        return mas;
    }
//    Инициализация массива
    void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                mas[i][j] = CellType.COVER;
            }
        }
    }
    void createShips() {
        System.out.println("Выберите тип расстановки  кораблей:\n 1.Автоматический \t2.Ручной");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int choice = Integer.parseInt(s);
        switch (choice) {
            case 1:
                automaticCreateShips();
                break;
            case 2:
                manualCreateShip();
                break;
            default:
                System.out.println("Неправильный ввод данных.");
        }
    }
//    Автоматически создать корабли соотвественно их типу и количеству
    private void automaticCreateShips() {
        int[][] type = Ship.getType();
        for (int i = type.length - 1; i > 0; i--) {
            for (int j = type[i].length - 1; j >= 0; j--) {
                if (type[i][j] != 1) {
                    setupShip(new Ship(i));
                    type[i][j] = 1;
                }
            }
        }
    }
//    Вручную создать корабли соотвественно их типу и количеству
    private void manualCreateShip(){
        System.out.println("Ручна расстановка находится на стадии разработки.");
        System.out.println("На данном этапе все равно используется автоматическая.");
        automaticCreateShips();
    }
//    Установить корабль
    void setupShip(Ship ship) {
        Ship calcShip = emulationSetupShip(ship);
//        Ship calcShip = ship;

        int currentX = calcShip.getPosition().getX();
        int currentY = calcShip.getPosition().getY();
//        Если вертикально на поле
        if (((currentX + calcShip.getCountPipe() - 1) <= SIZE) && calcShip.isVertical()) {
            for (int i = 0; i < calcShip.getCountPipe(); i++) {
                mas[currentX + i - 1][currentY - 1] = CellType.PART_OF_SHIP;
            }
        }
//        Если горизонтально на поле
        if (((currentY + calcShip.getCountPipe() - 1) <= SIZE) && !ship.isVertical())
            for (int i = 0; i < calcShip.getCountPipe(); i++) {
                mas[currentX - 1][currentY + i - 1] = CellType.PART_OF_SHIP;
            }
    }
//TODO X ПО ЧИСЛАМ Y ПО БУКВАМ!!! Х ОТ 0 ДО 10, Y ОТ 0 ДО 10!!!
//    Эмулирует (проверяет, имитирует) установку кораблей
    private Ship emulationSetupShip(Ship ship) {
        int currentX = ship.getPosition().getX();
        int currentY = ship.getPosition().getY();
        boolean isVertical = ship.isVertical();
        boolean isVerified = false;

        while (!isVerified) {
//            Если вертикальный
            isVerified = isVerifiedForVerticalPositionShip(ship, currentX, currentY, isVertical, isVerified);
//            Если горизонтальный
            isVerified = isVerifiedForHorizontalPositionShip(ship, currentX, currentY, isVertical, isVerified);
//            Если не прошел проверку
            if (!isVerified) {
//            Сдвигаем на один вниз, если в текущем все занято
                if (currentX != SIZE) {
                    currentX++;
                }
//            Если дошли до края, переходим к началу по Х
                else {
                    currentX = 1;
                }
//                    Если опять дошли до старого Х
                if (currentX == ship.getPosition().getX())
//                        Сдвигаем на один вправо, если в текущем все занято
                    if (currentY != SIZE) {
                        currentY++;
                    } else {
                        currentY = 1;
                    }
//                Если не нашлось места меняем расположение на обратное
                if (currentX == ship.getPosition().getX() && currentY == ship.getPosition().getY())
                    if (isVertical){
                        isVertical = false;
                    }else {
                        isVertical = true;
                    }
            }
        }

        return new Ship(new Coordinates(currentX, currentY), ship.getCountPipe(), isVertical);
    }

    private boolean isVerifiedForHorizontalPositionShip(Ship ship, int currentX, int currentY, boolean isVertical, boolean isVerified) {
        if (((currentY + ship.getCountPipe() - 1) <= SIZE) && !isVertical) {
            int countPipesEmulation = 0;
            for (int i = 0; i < ship.getCountPipe(); i++) {
//                    Координаты для проверки на место установки и вокруг
                int tempY = (currentY + i - 1) < SIZE ? (currentY + i - 1) : (SIZE - 1);
                int tempX = (currentX - 1) < SIZE ? (currentX - 1) : (SIZE - 1);
                int nextX = (tempX + 1) < SIZE ? (tempX + 1) : (SIZE - 1);
                int prevX = (tempX - 1) > 0 ? (tempX - 1) : 0;
                int nextY = (tempY + 1) < SIZE ? (tempY + 1) : (SIZE - 1);
                int prevY = (tempY - 1) > 0 ? (tempY - 1) : 0;
                if (mas[tempX][tempY] == CellType.PART_OF_SHIP ||
                        mas[prevX][tempY] == CellType.PART_OF_SHIP ||
                        mas[tempX][nextY] == CellType.PART_OF_SHIP ||
                        mas[nextX][tempY] == CellType.PART_OF_SHIP ||
                        mas[tempX][prevY] == CellType.PART_OF_SHIP) {
                    break;
                } else {
                    countPipesEmulation++;
                }
            }
            if (countPipesEmulation == ship.getCountPipe()) {
                isVerified = true;
            }
        }
        return isVerified;
    }

    private boolean isVerifiedForVerticalPositionShip(Ship ship, int currentX, int currentY, boolean isVertical, boolean isVerified) {
        if (((currentX + ship.getCountPipe() - 1) <= SIZE) && isVertical) {
            int countPipesEmulation = 0;
            for (int i = 0; i < ship.getCountPipe(); i++) {
//                    Координаты для проверки на место установки и вокруг
                int tempX = (currentX + i - 1) < SIZE ? (currentX + i - 1) : (SIZE - 1);
                int tempY = (currentY - 1) < SIZE ? (currentY - 1) : (SIZE - 1);
                int nextX = (tempX + 1) < SIZE ? (tempX + 1) : (SIZE - 1);
                int prevX = (tempX - 1) > 0 ? (tempX - 1) : 0;
                int nextY = (tempY + 1) < SIZE ? (tempY + 1) : (SIZE - 1);
                int prevY = (tempY - 1) > 0 ? (tempY - 1) : 0;
                if (mas[tempX][tempY] == CellType.PART_OF_SHIP ||
                        mas[tempX][prevY] == CellType.PART_OF_SHIP ||
                        mas[nextX][tempY] == CellType.PART_OF_SHIP ||
                        mas[tempX][nextY] == CellType.PART_OF_SHIP ||
                        mas[prevX][tempY] == CellType.PART_OF_SHIP) {
                    break;
                } else {
                    countPipesEmulation++;
                }
            }
            if (countPipesEmulation == ship.getCountPipe()) {
                isVerified = true;
            }
        }
        return isVerified;
    }
//    Принцип ведения боя
    void battle(Coordinates position) {

        if (position.getY() == -1 || position.getX() == -1) {
            System.out.println("Ошибка! Вы ввели неправильные координаты!");
            Player.setCountErrors();
        } else {
            switch (mas[position.getY()][position.getX()]) {
                case PART_OF_SHIP:
                    mas[position.getY()][position.getX()] = CellType.WOUND;
                    System.out.println(isCrashed(position) ? "Поздравляем! Вы потопили корабль!" : "УРА! Вы подбили корабль!");
                    Player.setCountShotsOnTarget();
                    break;
                case BLOOMER:
                    System.out.println("Вы сюда уже стрелляли :-).");
                    Player.setCountRepeatShots();
                    break;
                case WOUND:
                    System.out.println("Вы сюда уже стрелляли :-).");
                    Player.setCountRepeatShots();
                    break;
                case COVER:
                    mas[position.getY()][position.getX()] = CellType.BLOOMER;
                    System.out.println("Мимо...");
                    Player.setCountShotsBloomers();
                    break;
                default:
                    System.out.println("Error.");
                    Player.setCountErrors();
            }
        }
    }
//    Определение подбил или потопил корабль
    boolean isCrashed(Coordinates position) {
        int nextX = (position.getX() + 1) < SIZE ? (position.getX() + 1) : (SIZE - 1);
        int prevX = (position.getX() - 1) > 0 ? (position.getX() - 1) : 0;
        int nextY = (position.getY() + 1) < SIZE ? (position.getY() + 1) : (SIZE - 1);
        int prevY = (position.getY() - 1) > 0 ? (position.getY() - 1) : 0;
        int currentX = position.getX();
        int currentY = position.getY();
//        Проверка наличия неподбитых труб по направлениям
//        ↑→↓←
        if (mas[prevY][currentX] == CellType.PART_OF_SHIP || mas[currentY][nextX] == CellType.PART_OF_SHIP ||
                mas[nextY][currentX] == CellType.PART_OF_SHIP || mas[currentY][prevX] == CellType.PART_OF_SHIP)
            return false;
//        ↑
        if (mas[prevY][currentX] == CellType.WOUND)
            while (mas[prevY][currentX]!= CellType.COVER) {
                if(mas[prevY][currentX] == CellType.PART_OF_SHIP)
                    return false;
                if(prevY > 0)
                    prevY -= 1;
                else
                    break;
            }
//        →
        if (mas[currentY][nextX] == CellType.WOUND)
            while (mas[currentY][nextX] != CellType.COVER) {
                if (mas[currentY][nextX] == CellType.PART_OF_SHIP)
                    return false;
                if (nextX < (SIZE - 1))
                    nextX += 1;
                else
                    break;
            }
//        ↓
        if (mas[nextY][currentX] == CellType.WOUND)
            while (mas[nextY][currentX] != CellType.COVER) {
                if (mas[nextY][currentX] == CellType.PART_OF_SHIP)
                    return  false;
                if(nextY < (SIZE - 1))
                    nextY += 1;
                else
                    break;
            }
//        ←
        if (mas[currentY][prevX] == CellType.WOUND)
            while (mas[currentY][prevX] != CellType.COVER) {
                if (mas[currentY][prevX] == CellType.PART_OF_SHIP)
                    return false;
                if (prevX > 0)
                    prevX -= 1;
                else
                    break;
            }

        return true;
    }
//    Показать поле битвы
    void show() {
//            Вывод поля на экран
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                showGraficElementsForBattleField(i, j, mas[i][j]);
            }
            System.out.println();
        }
    }

    private void showGraficElementsForBattleField(int i, int j, CellType cellType) {
//        Шкала долготы
        if(i == 0 && j == 0) {
            for (int a = 0; a < (SIZE + 1); a++) {
                if (a != 0)
//                    1039 это минус один от большой кирилической буквы А (1040) в int
                    System.out.print((char)(a + 1039) + "  ");
                else
                    System.out.print("  ┃ ");
            }
            System.out.println();
            for (int b = 0; b < (SIZE + 1); b++) {
                System.out.print("---");
            }
            System.out.println();
        }
//                Шкала широты
        if (j == 0) {
            if ((i + 1) < SIZE)
                System.out.print((i + 1) + " ┃ ");
            else
                System.out.print((i + 1) + "┃ ");
        }
//        Отображение частей поля
        switch (cellType) {
            case COVER:
                System.out.print(".  ");
                break;
            case WOUND:
                System.out.print("X  ");
                break;
            case PART_OF_SHIP:
                System.out.print("O  ");
                break;
            case BLOOMER:
                System.out.print("*  ");
                break;
            default:
                System.out.print("E  ");
        }
    }
//    Определение есть ли еще корабли на поле
    boolean isFall() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mas[i][j] == CellType.PART_OF_SHIP)
                    return true;
            }
        }
        System.out.println("Игра окончена. Вы всех потопили! Поздравляем!:-)");
        return false;
    }
}
