package com.hook.seaBattle;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by User on 30.12.15.
 */
public class Comp extends Player {
    String name = "Comp";
    Field field;
    char[][] masVerify;

    public Comp() {
        this.field = new Field();
        field.init();
        this.masVerify = field.getMas();
    }

    @Override
    public Coordinates getShot() {
//        Определение координаты выстрела
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        while(masVerify[y][x]!= '.'){
            x = random.nextInt(10);
            y = random.nextInt(10);
        }
        masVerify[y][x] = '*';
        return new Coordinates(y, x);
    }

    @Override
    public void greeting(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Здравствуйте! :-)");
        System.out.printf("Вместо Вас играет %s", this.name);
        String s = scanner.nextLine();
    }
}
