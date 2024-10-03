import exception.InvalidCreateTaskException;
import exception.InvalidMarkException;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class InputHandler {
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

    public static int inputHandler(String userInput) throws InvalidMarkException {
        switch (userInput) {

        case "bye":
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
            if (userInput.length() > 7) {
                ArrayList<Task> searchResult = SearchEngine.search(userInput.substring(7));
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
            if (taskIndex > TaskList.tasks.size()) {
                throw new InvalidMarkException();
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
                System.out.println("Error: Invalid command syntax. Please provide a task type with corresponding parameters.");
            }
            return 1;
        }
        return 0;
    }

    public static boolean isMarkCommandType(String input) {
        String[] inputSplit = input.split(" ");
        return inputSplit.length == 2 && inputSplit[0].matches("mark|unmark|delete")
                && inputSplit[1].matches("\\d+(\\.\\d+)?");
    }
}
