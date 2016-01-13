package com.hook.seaBattle;

/**
 * Created by User on 30.12.15.
 */
public abstract class Player {
    protected static int countShotsOnTarget = 0;
    protected static int countShotsBloomers = 0;
    protected static int countRepeatShots = 0;
    protected static int countErrors = 0;

    public static int getCountShotsBloomers() {
        return countShotsBloomers;
    }

    public static void setCountShotsBloomers() {
        Player.countShotsBloomers++;
    }

    public static int getCountRepeatShots() {
        return countRepeatShots;
    }

    public static void setCountRepeatShots() {
        Player.countRepeatShots++;
    }

    public static int getCountErrors() {
        return countErrors;
    }

    public static void setCountErrors() {
        Player.countErrors++;
    }

    public static int getCountShotsOnTarget() {
        return countShotsOnTarget;
    }

    public static void setCountShotsOnTarget() {
        Player.countShotsOnTarget++;
    }

    protected Coordinates getShot() {
        return new Coordinates(-1, -1);
    }

    protected void greeting() {
        System.out.println("Объект не имеет реализации метода greeting()");
    }


}
