import java.util.Scanner;

/**
 * Created by User on 09.12.15.
 */
public class Home {
    public static void main(String[] args) {
//        System.out.println("This is Home");
//        kitchen();
//        bedroom();

        System.out.println("а = " + (int)'а');
        Scanner scanner = new Scanner(System.in);
        System.out.print("Координата долготы---> ");
        String s  = scanner.nextLine();
        int y;
        if (s.equals("А"))
            y = (int)s.charAt(0) - 1039;
        else
            y = 111111;
        System.out.println(y);
    }

    static void kitchen() {
        System.out.println("In kitchen");
    }

    static void bedroom() {
        System.out.println("In bedroom");
    }
}

