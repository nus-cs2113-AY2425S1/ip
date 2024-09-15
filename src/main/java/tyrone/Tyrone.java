package tyrone;

import tyrone.command.CommandHandler;
import tyrone.savemanager.FileReadWriter;

import java.util.Scanner;

public class Tyrone {
    public static void main(String[] args) {
        FileReadWriter.createSaveFile();
        FileReadWriter.initTaskListFromSaveFile();
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
