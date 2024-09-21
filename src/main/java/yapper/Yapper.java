package yapper;

import java.util.Scanner;

import yapper.instructions.InstructionHandler;
import yapper.io.SaveFileHandler;
import yapper.io.StringStorage;
import yapper.tasks.TaskHandler;

// Main function for Yapper
public class Yapper {

    // Main ChatBot Loop
    public static void startYappin(TaskHandler taskHandler) {
        System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
        System.out.println(StringStorage.START_UP_MESSAGE);
        System.out.println(StringStorage.LINE_DIVIDER);
        System.out.println(StringStorage.HELP_MESSAGE);
        System.out.println(StringStorage.LINE_DIVIDER_INPUT);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInputString = scanner.nextLine();
            if (userInputString.trim().equals("bye")) {
                break;
            }
            InstructionHandler.handleInstruction(taskHandler, userInputString);
        }

        System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
        System.out.println(StringStorage.SHUT_DOWN_MESSAGE);
        System.out.println(StringStorage.LINE_DIVIDER);
    }

    // Program Start
    public static void main(String[] args) {
        // Initialize
        TaskHandler taskHandler = SaveFileHandler.loadTasks();
        // Startup ChatBot Program
        startYappin(taskHandler);
    }
}
