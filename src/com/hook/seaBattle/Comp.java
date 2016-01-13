package com.hook.seaBattle;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by User on 30.12.15.
 */
public class Comp extends Player {
    String name = "Comp";

    @Override
    public Coordinates getShot() {
//        Определение координаты выстрела
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        Field field = new Field();
        char[][] masVerify = field.getMas();
        while(masVerify[y][x]!='*'){
            x = random.nextInt(10);
            y = random.nextInt(10);
        }
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
