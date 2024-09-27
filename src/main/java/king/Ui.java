package king;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

    private final static String LINE = "____________________________________________________________\n";

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public static void showError(String message) {
        System.out.println(LINE + message + LINE);
    }

    public static void toGreet(TaskList taskList) throws KingException, IOException {
        String logo =
                "| |/ /|_ _|| \\| | / _` |\n" +
                "| ' <  | | | .` || (_| |\n" +
                "|_|\\_\\|___||_|\\_| \\__,_|\n";

        System.out.println("\nHello from\n" + logo);

        if (Storage.checkFile()) {
            try {
                taskList.loadTasks();
                System.out.println("Welcome back! You have tasks saved previously.");
                taskList.printList();
            } catch (KingException e) {
                System.out.println(e.getMessage() + LINE);
            }
        } else {
            Storage.readFile();
            System.out.println(" Hello! I'm King" + "\n" +
                               " What can I do for you?\n" + LINE);
        }
    }

    public static void toExit() {
        System.out.println(LINE + " Bye. Hope to see you again soon!\n" + LINE);
    }
}
