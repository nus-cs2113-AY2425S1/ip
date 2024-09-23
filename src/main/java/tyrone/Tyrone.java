package tyrone;

import tyrone.command.InputHandler;
import tyrone.savemanager.FileReadWriter;

import java.util.Scanner;

public class Tyrone {
    public static void main(String[] args) {
        FileReadWriter.createSaveFile();
        FileReadWriter.initTaskListFromSaveFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Tyrone.\nWhat can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            if(InputHandler.isExitCommand(input)) {
                break;
            }
            InputHandler.handleInput(input);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
