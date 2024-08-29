import java.util.Scanner;
import java.util.ArrayList;

public class Ryan {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        Utils.horizontalLine();
        System.out.println("Hello! I'm Ryan\nWhat can I do for you?");
        Utils.horizontalLine();

        while (!exit) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                exit = true;
                Utils.horizontalLine();
            }
            else if (command.equals("list")) {
                Utils.horizontalLine();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
                Utils.horizontalLine();
            } else {
                Utils.horizontalLine();
                list.add(command);
                System.out.println("added: " + command);
                Utils.horizontalLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        Utils.horizontalLine();
    }
}
