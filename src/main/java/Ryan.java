import java.util.Scanner;

public class Ryan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Utils.horizontalLine();
        System.out.println("Hello! I'm Ryan\nWhat can I do for you?");
        Utils.horizontalLine();
        String command = scanner.nextLine();
        Utils.horizontalLine();
        System.out.println(command);
        Utils.horizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        Utils.horizontalLine();
    }
}
