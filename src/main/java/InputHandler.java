import exception.InvalidCreateTaskException;
import exception.InvalidMarkException;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * The InputHandler reads user input and controls the main program flow accordingly.
 * It handles commands related to task management, such as listing tasks, marking tasks as done,
 * deleting tasks, searching for tasks, and adding new tasks.
 */

public class InputHandler {

    /**
     * Executes the input handling loop, reading user input and processing commands.
     */
    public static void execute() {
        String userInput;
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                userInput = scanner.nextLine();
                int result = InputHandler.inputHandler(userInput);
                if (result == -1) {
                    break;
                }
            }
        } catch (InvalidMarkException e) {
            System.out.println("This task does not exist, please check again.");
        }
    }

    /**
     * Handles user input and performs corresponding actions based on the command.
     *
     * @param userInput The user's input string.
     * @return An integer indicating the result of the input handling.
     *         -1: Exit command ("bye")
     *          0: No specific action (e.g., empty input)
     *          1: Successful execution of a command
     * @throws InvalidMarkException If an invalid task index is provided for marking/unmarking/deleting.
     */
    public static int inputHandler(String userInput) throws InvalidMarkException {
        switch (userInput) {

        case "bye":
        case "quit":
            return -1;

        case "list":
            if (TaskList.tasks.isEmpty()) {
                UI.printContent("You don't have any tasks!");
                return 1;
            }

            TaskList.printTaskList();
            return 1;

        case "help":
            UI.printHelp();
            return 1;
        }

        // Case: Mark, Unmark, Delete, Search
        String[] userInputSplit = userInput.split(" ");

        if (userInputSplit[0].equals("search")) {
            if (userInput.length() > "search ".length()) {
                ArrayList<Task> searchResult = SearchEngine.search(userInput.substring("search ".length()));
                if (searchResult.isEmpty()) {
                    System.out.println("You don't have any tasks matching this description!");
                } else {
                    TaskList.printSearchResult(searchResult);
                }
            } else {
                UI.printContent("Please provide a search argument!");
            }
            return 1;
        }

        // Test if the input is formatted like a mark/unmark command
        if (isMarkCommandType(userInput)) {
            int taskIndex = parseInt(userInputSplit[1]) - 1;
            if (taskIndex >= TaskList.tasks.size()) {
                UI.printContent("This task does not exist, please check again.");
                return 0;
            }

            String command = userInputSplit[0];
            Task task = TaskList.tasks.get(taskIndex);
            switch (command) {
            case "mark":
                task.markAsDone();
                return 1;
            case "unmark":
                task.markAsUndone();
                return 1;
            case "delete":
                Task.deleteTask(taskIndex);
                return 1;
            }
        }

        // Case: Add task
        if (!userInput.isEmpty()) {
            try {
                Task.createNewTask(userInput);
                return 1;
            } catch (InvalidCreateTaskException e) {
                UI.printContent("Error: Invalid command syntax. Please provide a task type with corresponding parameters.");
            }
            return 1;
        }
        return 0;
    }

    /**
     * Checks if the input corresponds to a mark/unmark/delete command.
     *
     * @param input The user's input string.
     * @return true if the input matches the format of a mark/unmark/delete command, false otherwise.
     */
    public static boolean isMarkCommandType(String input) {
        String[] inputSplit = input.split(" ");
        return inputSplit.length == 2 && inputSplit[0].matches("mark|unmark|delete")
                && inputSplit[1].matches("\\d+(\\.\\d+)?");
    }
}
