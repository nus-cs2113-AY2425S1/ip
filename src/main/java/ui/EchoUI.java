package ui;

import parser.Parser;
import tasklist.TaskList;

public class EchoUI {
    private static final String SEPARATOR = "_".repeat(30);

    public void displayGreeting() {
        String greetingMessage = "Hello! I'm Echo" + "\nWhat can I do for you?\n";
        printMessage(greetingMessage);
    }

    public void displayExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        printMessage(exitMessage);
    }

    public void runChat(Parser parser, TaskList taskList) {
        String userInput;
        do {
            userInput = parser.getUserInput();
            parser.processUserInput(userInput, taskList);
        } while (!userInput.equalsIgnoreCase("bye"));

        taskList.saveTasks();
        displayExitMessage();
    }

    private void printMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }
}
