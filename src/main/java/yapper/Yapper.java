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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInputString = scanner.nextLine();
            if (userInputString.trim().equals(
                    StringStorage.BYE_INSTRUCTION_PREFIX)) {
                break;
            } else if (userInputString.trim().startsWith(
                    StringStorage.BYE_INSTRUCTION_PREFIX)) {
                StringStorage.printWithDividers(
                    StringStorage.BYE_INSTRUCTION_PREFIX
                    + " does not need other parameters");
            }
            InstructionHandler.handleInstruction(taskHandler, userInputString);
        }
        scanner.close();
    }

    // Program Start/End with Startup/Shutdown Messages respectively
    public static void main(String[] args) {
        // Initialize
//        TaskHandler taskHandler = new TaskHandler();
        TaskHandler taskHandler = SaveFileHandler.loadTasks();
        if (taskHandler.getCurrTaskTotal() == 0) {
            System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
            System.out.println(StringStorage.START_UP_MESSAGE);
            System.out.println(StringStorage.LINE_DIVIDER);
            System.out.println(StringStorage.HELP_MESSAGE);
            System.out.println(StringStorage.LINE_DIVIDER_INPUT);
        } else {
            System.out.println("let us resume where we left off, shall we?");
        }
        // Startup ChatBot Program
        startYappin(taskHandler);

        System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
        System.out.println(StringStorage.SHUT_DOWN_MESSAGE);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
