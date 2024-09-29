package ui;

import parser.Parser;
import tasklist.TaskList;

public class EchoUI {
    private static final String SEPARATOR = "_".repeat(30);

    /**
     * Displays the greeting message.
     */
    public void displayGreeting() {
        String greetingMessage = "Hello! I'm Echo" + "\nWhat can I do for you?\n";
        printMessage(greetingMessage);
    }

    /**
     * Displays the exit message.
     */
    public void displayExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        printMessage(exitMessage);
    }

    /**
     * Processes user input until the user types "bye".
     * Saves list of tasks upon exiting.
     *
     * @param parser The parser used to interpret the user input.
     * @param taskList The task list that stores the tasks.
     */
    public void runChat(Parser parser, TaskList taskList) {
        String userInput;
        do {
            userInput = parser.getUserInput();
            parser.processUserInput(userInput, taskList);
        } while (!userInput.equalsIgnoreCase("bye"));

        taskList.saveTasks();
        displayExitMessage();
    }

    /**
     * Prints input message.
     *
     * @param message The message to be printed.
     */
    private void printMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }
}
