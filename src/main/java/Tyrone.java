import java.util.Scanner;

public class Tyrone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldExit = false;

        System.out.println("Hello! I'm Tyrone.\nWhat can I do for you?");
        while (!shouldExit) {
            String command = scanner.nextLine();
            shouldExit = CommandHandler.isExitCommand(command);
            if (!shouldExit) {
                CommandHandler.handleCommand(command);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
