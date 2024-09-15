package yapper;

import java.util.Scanner;

import yapper.instructions.InstructionHandler;
import yapper.io.StringStorage;
import yapper.tasks.TaskHandler;

// Main function for Yapper
public class Yapper {

    // Main ChatBot Loop
    public static void startYappin(TaskHandler taskHandler) {
        System.out.println(StringStorage.START_UP_MESSAGE);
        System.out.println(StringStorage.HELP_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInputString = scanner.nextLine();
            if (userInputString.trim().equals("bye")) {
                break;
            }
            InstructionHandler.handleInstruction(taskHandler, userInputString);
        }

        System.out.println(StringStorage.SHUT_DOWN_MESSAGE);
    }

    // Program Start
    public static void main(String[] args) {
        // Initialize
        TaskHandler taskHandler = new TaskHandler();
        // Startup ChatBot Program
        startYappin(taskHandler);
    }
}
