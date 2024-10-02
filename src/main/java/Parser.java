import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for parsing user inputs into commands
 * that manipulate tasks in the task list. It handles commands such as
 * adding, deleting, marking tasks, and more.
 */
public class Parser {

    /**
     * Parses the user input and executes the appropriate command based on the first word of the input.
     *
     * @param userInput The full command entered by the user.
     * @return true if the system should continue accepting commands, false if the system should exit.
     */
    public static boolean parse(String userInput) {
        UI ui = new UI();
        String[] parts = userInput.split(" ");
        String command = parts[0];
        String description;

        switch (command) {
            case "quag":
                ui.showExit();
                return false;
            case "list":
                TaskList.displayList();
                break;
            case "mark":
                handleMarkCommand(parts, ui);
                break;
            case "unmark":
               handleUnmarkCommand(parts, ui);
                break;
            case "todo":
                //description is from parts[1] to end
                description = userInput.substring("todo".length()).trim();
                TaskList.addTodoToList(description);
                break;
            case "deadline":
                handleDeadlineCommand(userInput, ui);
                break;
            case "event":
                handleEventCommand(userInput, ui);
                break;
            case "delete":
                handleDeleteCommand(parts, ui);
                break;
            case "due":
                handleDueCommand(parts, ui);
                break;
            case "find":
                handleFindCommand(parts, ui);
                break;
            default:
                ui.showCommandList();
                break;
        }
        return true;
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param parts The split user input.
     * @param ui    The UI instance for displaying messages.
     */
    private static void handleMarkCommand(String[] parts, UI ui) {
        if (parts.length >= 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]);
                TaskList.markAsDone(taskIndex);
            } catch (NumberFormatException e) {
                ui.notNumberEntered();
            }
        } else {
            ui.noNumberEntered();
        }
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     *
     * @param parts The split user input.
     * @param ui    The UI instance for displaying messages.
     */
    private static void handleUnmarkCommand(String[] parts, UI ui) {
        if (parts.length >= 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]);
                TaskList.markAsNotDone(taskIndex);
            } catch (NumberFormatException e) {
                ui.notNumberEntered();
            }
        } else {
            ui.noNumberEntered();
        }
    }

    /**
     * Handles the "deadline" command to add a deadline task to the list
     * @param userInput The entire user Input which will be split in the function based on /by denotation
     * @param ui The UI instance for displaying messages.
     */
    private static void handleDeadlineCommand(String userInput, UI ui) {
        String description;
        try {
            //description is from parts[1] until / is detected
            //deadline is after /

            // int to denote length and position of "/by" separator
            int byLength = 4;
            int byIndex = userInput.indexOf("/by");

            //find position of /by separator
            if (byIndex != -1) {
                //extract the description and deadline
                description = userInput.substring("deadline".length(), byIndex).trim();
                // Skip over "/by "
                String by = userInput.substring(byIndex + byLength).trim();
                // Parse the date and time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime deadlineTime = LocalDateTime.parse(by, formatter);

                TaskList.addDeadlineToList(description, deadlineTime);
            }
        } catch (DateTimeParseException e) {
            ui.invalidFormat();
        }
    }

    /**
     * Handles the "event" command to add an event task to the list
     * @param userInput The entire user Input which will be split in the function based on /from and /to denotations
     * @param ui The UI instance for displaying messages.
     */
    private static void handleEventCommand(String userInput, UI ui) {
        String description;
        //description is from parts[1] until first /
        //from is in between first and second /
        //to is after second /

        // int to denote length and position of "/from" and "/to" separator
        int fromIndex = userInput.indexOf("/from");
        int fromLength = 6;
        int toIndex = userInput.indexOf("/to");
        int toLength = 4;

        if (fromIndex != -1 && toIndex != -1 && toIndex > fromIndex) {
            // extract description, from and to
            description = userInput.substring("event".length(), fromIndex).trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            String from = userInput.substring(fromIndex + fromLength, toIndex).trim();
            String to = userInput.substring(toIndex + toLength).trim();
            LocalDateTime fromTime = LocalDateTime.parse(from, formatter);
            LocalDateTime toTime = LocalDateTime.parse(to, formatter);
            TaskList.addEventToList(description, fromTime, toTime);
        }  else {
            ui.invalidFormat();
        }
    }

    /**
     * Handles the "delete" command to remove a task from the list.
     *
     * @param parts The split user input.
     * @param ui    The UI instance for displaying messages.
     */
    private static void handleDeleteCommand(String[] parts, UI ui) {
        if (parts.length >= 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]);
                TaskList.deleteTask(taskIndex);
            } catch (NumberFormatException e) {
                ui.notNumberEntered();
            }
        } else {
            ui.noNumberEntered();
        }
    }

    /**
     * Handles the "due" command and displays all deadlines due and events starting on the inputted date.
     *
     * @param parts The split user input.
     * @param ui    The UI instance for displaying messages.
     */
    private static void handleDueCommand(String[] parts, UI ui) {
        try {
            String dateString = parts[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate dueDate = LocalDate.parse(dateString, formatter);

            TaskList.printTasksOnDate(dueDate);
        } catch (DateTimeParseException e) {
            ui.invalidFormat();
        }
    }

    /**
     * Handles the "find" command and displays all tasks with descriptions that contain keyword.
     *
     * @param parts The split user input.
     * @param ui    The UI instance for displaying messages.
     */
    private static void handleFindCommand(String[] parts, UI ui) {
        if (parts.length >= 2) {
            String keyword = parts[1].trim();
            TaskList.printTaskWithKeyword(keyword);
        } else {
            ui.invalidFormat();
        }
    }
}
