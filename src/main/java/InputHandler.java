import exception.InvalidCreateTaskException;
import exception.InvalidMarkException;

import static java.lang.Integer.parseInt;

public class InputHandler {
    public static int inputHandler(String userInput) throws InvalidMarkException {
        switch (userInput) {

        // Case: Terminate program
        case "bye":
            return -1;

        // Case: See list
        case "list":
            if (Task.tasksCount == 0) {
                UI.printContent("You don't have any tasks!");
                return 1;
            }

            System.out.println(UI.DIVIDER_LINE);
            for (int i = 1; i <= Task.tasksCount; i++) {
                System.out.println(i + ". " + Aerus.tasks.get(i - 1).toString());
            }
            System.out.println(UI.DIVIDER_LINE);
            return 1;
        }

        // Case: Mark & Unmark
        String[] userInputSplit = userInput.split(" ");

        // Test if the input is formatted like a mark/unmark command
        if (isMarkCommandType(userInput)) {
            int taskIndex = parseInt(userInputSplit[1]) - 1;
            if (taskIndex >= Task.tasksCount) {
                throw new InvalidMarkException();
            }
            String command = userInputSplit[0];
            Task task = Aerus.tasks.get(taskIndex);
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
