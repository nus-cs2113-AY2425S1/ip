import java.util.Scanner;

public class Tyrone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Tyrone.\nWhat can I do for you?");

        while (true) {
            String command = scanner.nextLine();
            if(CommandHandler.isExitCommand(command)) {
                break;
            }
            CommandHandler.handleCommand(command);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
