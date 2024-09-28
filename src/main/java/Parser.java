import exception.EmptyDateFieldException;
import exception.EmptyDescriptionException;

import java.util.Scanner;

public class Parser {
    private static final int MARK_WORD_LEN = 4;
    private static final int UNMARK_WORD_LEN = 6;
    private static final int INPUT_SPACE_BUFFER = 2;
    private static final int DELETE_WORD_LEN = 6;
    public static final String DEADLINE_BY_KEYWORD = "/by";
    public static final String EVENT_FROM_KEYWORD = "/from";
    public static final String EVENT_TO_KEYWORD = "/to";

    public static void getUserInput(Scanner in, String listFilePath, List userList) {
        String line;
        while (true) {
            line = getLine(in);

            if (isBye(line)) {
                Ui.printByeMessage();
                Ui.printHorizontalLine();
                break;

            } else if (isList(line)) {
                userList.printList();
                Ui.printHorizontalLine();

            } else if (isMark(line)) {
                userList.markItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(listFilePath, userList);

            } else if (isUnmark(line)) {
                userList.unmarkItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(listFilePath, userList);

            } else if (isDelete(line)) {
                userList.deleteItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(listFilePath, userList);

            } else {
                userList.addItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(listFilePath, userList);

            }
        }
    }

    private static String getLine(Scanner in) {
        String line;
        System.out.print(System.lineSeparator());
        line = in.nextLine();
        Ui.printHorizontalLine();
        return line;
    }

    private static boolean isList(String line) {
        return line.equals("list");
    }

    private static boolean isBye(String line) {
        return line.equals("bye");
    }

    private static boolean isMark(String line) {
        return line.length() >= (MARK_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 4).equals("mark");
    }

    private static boolean isUnmark(String line) {
        return line.length() >= (UNMARK_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 6).equals("unmark");
    }

    private static boolean isDelete(String line) {
        return line.length() >= (DELETE_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 6).equals("delete");
    }

    public static boolean isValidEvent(String line) {
        return line.startsWith("event") &&
                line.contains(EVENT_FROM_KEYWORD) &&
                line.contains(EVENT_TO_KEYWORD);
    }

    public static boolean isValidDeadline(String line) {
        return line.startsWith("deadline") && line.contains(DEADLINE_BY_KEYWORD);
    }

    public static boolean isTodo(String line) {
        return line.startsWith("todo");
    }

    public static String extractTodoDescription(String line) throws EmptyDescriptionException {
        String todoDescription;
        todoDescription = line.replaceFirst("todo", "").trim();

        taskDescriptionNotEmpty(todoDescription);

        return todoDescription;
    }

    public static String extractDeadlineDescription(String line) throws EmptyDescriptionException {
        String deadlineDescription;
        final int indexOfDeadlinePrefix = line.indexOf("/by");
        deadlineDescription = line.substring(0, indexOfDeadlinePrefix).replaceFirst("deadline", "").trim();

        taskDescriptionNotEmpty(deadlineDescription);

        return deadlineDescription;
    }

    private static void taskDescriptionNotEmpty(String taskDescription) throws EmptyDescriptionException {
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    public static String extractDeadlineDate(String line) {
        String deadlineDate;
        final int indexOfDeadlinePrefix = line.indexOf("/by");
        deadlineDate = line.substring(indexOfDeadlinePrefix).replaceFirst("/by", "").trim();

        dateFieldNotEmpty(deadlineDate);

        return deadlineDate;
    }

    private static void dateFieldNotEmpty(String dateField) throws EmptyDateFieldException {
        if (dateField.isEmpty()) {
            throw new EmptyDateFieldException();
        }
    }

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
