import java.util.Scanner;

public class Ryan {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        Utils.horizontalLine();
        System.out.println("Hello! I'm Ryan\nWhat can I do for you?");
        Utils.horizontalLine();

        while (!exit) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                exit = true;
                Utils.horizontalLine();
                continue;
            }

            Utils.horizontalLine();
            System.out.println(command);
            Utils.horizontalLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        Utils.horizontalLine();
    }
}
