/**
 * Created by User on 09.12.15.
 */
public class Breakfast {
    public static void main(String[] args) {
        breakfast("tomato");
        breakfast("rise");
        breakfast("potato");
    }
    static void breakfast(String something) {
        switch (something) {
            case "tomato":
                System.out.println("We are cooking borsch");
                break;
            case "rise":
                System.out.println("We're cooking soup");
                break;
            default:
                System.out.println("We are cooking " + something);
        }
    }
}
