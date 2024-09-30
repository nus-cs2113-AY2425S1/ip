package main;

import exception.EmptyDateFieldException;
import exception.EmptyDescriptionException;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * The Parser class is responsible for interpreting user inputs and extracting the relevant information
 * to perform the appropriate actions on the task list.
 */
public class Parser {
    private static final int MARK_WORD_LEN = 4;
    private static final int UNMARK_WORD_LEN = 6;
    private static final int INPUT_SPACE_BUFFER = 2;
    private static final int DELETE_WORD_LEN = 6;
    public static final String DEADLINE_BY_KEYWORD = "/by";
    public static final String EVENT_FROM_KEYWORD = "/from";
    public static final String EVENT_TO_KEYWORD = "/to";

    /**
     * Continuously reads user input and interprets the commands to modify the task list.
     *
     * @param in           Scanner to read user input.
     * @param storage
     * @param ui
     * @param userList     The current task list.
     */
    public static void getUserInput(Scanner in, Storage storage, Ui ui, List userList) {
        String line;
        while (true) {
            line = getLine(ui, in);

            if (isBye(line)) {
                ui.printByeMessage();
                ui.printHorizontalLine();
                break;

            } else if (isList(line)) {
                userList.printList();
                ui.printHorizontalLine();

            } else if (isMark(line)) {
                userList.markItem(line);
                ui.printHorizontalLine();
                storage.saveListToFile(userList);

            } else if (isUnmark(line)) {
                userList.unmarkItem(line);
                ui.printHorizontalLine();
                storage.saveListToFile(userList);

            } else if (isDelete(line)) {
                userList.deleteItem(line);
                ui.printHorizontalLine();
                storage.saveListToFile(userList);

            } else if (isFind(line)) {
                userList.findItem(line);
                ui.printHorizontalLine();
            } else {
                userList.addItem(line);
                ui.printHorizontalLine();
                storage.saveListToFile(userList);

            }
        }
    }

    /**
     * Reads a line of input from the user and prints a horizontal line after the input.
     * @param in Scanner to read user input.
     * @param ui
     * @return The user input line as a string.
     */
    private static String getLine(Ui ui, Scanner in) {
        String line;
        System.out.print(System.lineSeparator());
        line = in.nextLine();
        ui.printHorizontalLine();
        return line;
    }

    /**
     * Checks if the input command is to list all tasks.
     * @param line The input string.
     * @return True if the command is "list", false otherwise.
     */
    private static boolean isList(String line) {
        return line.equals("list");
    }

    /**
     * Checks if the input command is to exit the program.
     * @param line The input string.
     * @return True if the command is "bye", false otherwise.
     */
    private static boolean isBye(String line) {
        return line.equals("bye");
    }

    /**
     * Checks if the input command is to mark a task as done.
     * @param line The input string.
     * @return True if the command starts with "mark", false otherwise.
     */
    public static boolean isMark(String line) {
        return line.startsWith("mark ");
    }

    /**
     * Checks if the input command is to unmark a task (mark it as not done).
     * @param line The input string.
     * @return True if the command starts with "unmark", false otherwise.
     */
    public static boolean isUnmark(String line) {
        return line.startsWith("unmark ");
    }

    /**
     * Checks if the input command is to delete a task.
     * @param line The input string.
     * @return True if the command starts with "delete", false otherwise.
     */
    public static boolean isDelete(String line) {
        return line.startsWith("delete ");
    }

    public static boolean isFind(String line) {
        return line.startsWith("find ");
    }

    /**
     * Checks if the input string is a valid event format.
     * @param line The input string.
     * @return True if the command contains an event with the proper format.
     */
    public static boolean isValidEvent(String line) {
        return line.startsWith("event ") &&
                line.contains(EVENT_FROM_KEYWORD) &&
                line.contains(EVENT_TO_KEYWORD);
    }

    /**
     * Checks if the input string is a valid deadline format.
     * @param line The input string.
     * @return True if the command contains a deadline with the "/by" keyword.
     */
    public static boolean isValidDeadline(String line) {
        return line.startsWith("deadline ") && line.contains(DEADLINE_BY_KEYWORD);
    }

    /**
     * Checks if the input string is a valid todo task.
     * @param line The input string.
     * @return True if the command starts with "todo".
     */
    public static boolean isTodo(String line) {
        return line.startsWith("todo ");
    }

    /**
     * Extracts the description from a todo command.
     * @param line The input string.
     * @return The description of the todo task.
     * @throws EmptyDescriptionException if the description is empty.
     */
    public static String extractTodoDescription(String line) throws EmptyDescriptionException {
        String todoDescription;
        todoDescription = line.replaceFirst("todo", "").trim();

        taskDescriptionNotEmpty(todoDescription);

        return todoDescription;
    }

    public static String extractFindDescription(String line) throws EmptyDescriptionException {
        String findDescription;
        findDescription = line.replaceFirst("find", "").trim();

        taskDescriptionNotEmpty(findDescription);

        return findDescription;
    }

    /**
     * Extracts the description from a deadline command.
     * @param line The input string.
     * @return The description of the deadline task.
     * @throws EmptyDescriptionException if the description is empty.
     */
    public static String extractDeadlineDescription(String line) throws EmptyDescriptionException {
        String deadlineDescription;
        final int indexOfDeadlinePrefix = line.indexOf("/by");
        deadlineDescription = line.substring(0, indexOfDeadlinePrefix).replaceFirst("deadline", "").trim();

        taskDescriptionNotEmpty(deadlineDescription);

        return deadlineDescription;
    }

    /**
     * Checks if a task description is not empty.
     * @param taskDescription The task description to be checked.
     * @throws EmptyDescriptionException if the description is empty.
     */
    private static void taskDescriptionNotEmpty(String taskDescription) throws EmptyDescriptionException {
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Extracts the date from a deadline command.
     * @param line The input string.
     * @return The date of the deadline.
     * @throws EmptyDateFieldException if the date field is empty.
     */
    public static LocalDateTime extractDeadlineDate(String line) {
        LocalDateTime deadlineDate;
        final int indexOfDeadlinePrefix = line.indexOf("/by");
        String deadlineDateString = line.substring(indexOfDeadlinePrefix).replaceFirst("/by", "").trim();

        dateFieldNotEmpty(deadlineDateString);
        deadlineDate = TaskList.convertDeadlineDateAsLocalDateTime(deadlineDateString);

        return deadlineDate;
    }

    /**
     * Checks if a date field is not empty.
     * @param dateField The date field to be checked.
     * @throws EmptyDateFieldException if the date field is empty.
     */
    private static void dateFieldNotEmpty(String dateField) throws EmptyDateFieldException {
        if (dateField.isEmpty()) {
            throw new EmptyDateFieldException();
        }
    }

    /**
     * Extracts the description from an event command.
     * @param line The input string.
     * @return The description of the event.
     * @throws EmptyDescriptionException if the description is empty.
     */
    public static String extractEventDescription(String line) throws EmptyDescriptionException {
        String eventDescription;
        final int indexOfStartDatePrefix = line.indexOf("/from");
        final int indexOfEndDatePrefix = line.indexOf("/to");
        if (indexOfEndDatePrefix > indexOfStartDatePrefix) {
            eventDescription = line.substring(0, indexOfStartDatePrefix).replaceFirst("event", "").trim();
        } else {
            eventDescription = line.substring(0, indexOfEndDatePrefix).replaceFirst("event", "").trim();
        }

        taskDescriptionNotEmpty(eventDescription);

        return eventDescription;
    }

    /**
     * Extracts the end date from an event command.
     * @param line The input string.
     * @return The end date of the event.
     * @throws EmptyDateFieldException if the date field is empty.
     */
    public static String extractEventEndDate(String line) {
        String eventEndDate;
        final int indexOfStartDatePrefix = line.indexOf("/from");
        final int indexOfEndDatePrefix = line.indexOf("/to");
        if (indexOfEndDatePrefix > indexOfStartDatePrefix) {
            eventEndDate = line.substring(indexOfEndDatePrefix).replaceFirst("/to", "").trim();
        } else {
            eventEndDate = line.substring(indexOfEndDatePrefix, indexOfStartDatePrefix).replaceFirst("/to", "").trim();
        }

        dateFieldNotEmpty(eventEndDate);

        return eventEndDate;
    }

    /**
     * Extracts the start date from an event command.
     * @param line The input string.
     * @return The start date of the event.
     * @throws EmptyDateFieldException if the date field is empty.
     */
    public static String extractEventStartDate(String line) {
        String eventStartDate;
        final int indexOfStartDatePrefix = line.indexOf("/from");
        final int indexOfEndDatePrefix = line.indexOf("/to");
        if (indexOfStartDatePrefix > indexOfEndDatePrefix) {
            eventStartDate = line.substring(indexOfStartDatePrefix).replaceFirst("/from", "").trim();
        } else {
            eventStartDate = line.substring(indexOfStartDatePrefix, indexOfEndDatePrefix).replaceFirst("/from", "").trim();
        }

        dateFieldNotEmpty(eventStartDate);

        return eventStartDate;
    }
}
