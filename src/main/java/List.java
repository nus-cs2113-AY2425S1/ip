import exception.EmptyDateFieldException;
import exception.EmptyDescriptionException;
import task.*;

import java.util.ArrayList;
import java.util.Arrays;

public class List {
    public static final String INVALID_EVENT_INPUT_MESSAGE = "event <event name> /from <start date/time> /end <end date/time>";
    public static final String INVALID_DEADLINE_INPUT_MESSAGE = "deadline <deadline name> /by <deadline>";
    public static final String INVALID_TODO_INPUT_MESSAGE = "todo <task name>";
    public static final String DEADLINE_BY_KEYWORD = "/by";
    public static final String EVENT_FROM_KEYWORD = "/from";
    public static final String EVENT_TO_KEYWORD = "/to";
    private static final String INVALID_MARK_MESSAGE = "mark <task index>";
    private static final String INVALID_UNMARK_MESSAGE = "unmark <task index>";
    private static final String INVALID_DELETE_MESSAGE = "delete <task index>";

    private int numItems;
    private Task[] itemList = new Task[0];
    ArrayList<Task> itemArrayList = new ArrayList<>(Arrays.asList(itemList));



    public List() {
        this.numItems = 0;
    }

    public int getNumItems() {
        return itemArrayList.size();
    }

    public void addItem(String line) {
        if (isValidEvent(line)) {
            addEvent(line);
        } else if (isValidDeadline(line)) {
            addDeadline(line);
        } else if (isTodo(line)) {
            addTodo(line);
        } else {
            printInvalidTaskMessage();
        }
    }

    private boolean isValidEvent(String line) {
        return line.startsWith("event") &&
                line.contains(EVENT_FROM_KEYWORD) &&
                line.contains(EVENT_TO_KEYWORD);
    }

    private boolean isValidDeadline(String line) {
        return line.startsWith("deadline") && line.contains(DEADLINE_BY_KEYWORD);
    }

    private boolean isTodo(String line) {
        return line.startsWith("todo");
    }

    private static void printInvalidTaskMessage() {
        System.out.println("\tInvalid command format:" + System.lineSeparator() + "\t\t" + INVALID_TODO_INPUT_MESSAGE
                + System.lineSeparator() + "\t\t" + INVALID_DEADLINE_INPUT_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_EVENT_INPUT_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_MARK_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_UNMARK_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_DELETE_MESSAGE);
    }

    private void addEvent(String line) {
        try {
            String eventDescription = extractEventDescription(line);
            String eventStartDate = extractEventStartDate(line);
            String eventEndDate = extractEventEndDate(line);
            Event newEvent = new Event(eventDescription, eventStartDate, eventEndDate);
            itemArrayList.add(newEvent);
            outputAddedMessage(newEvent);
            numItems += 1;
        } catch (EmptyDescriptionException e) {
            printTaskDescriptionEmptyMessage();
        } catch (EmptyDateFieldException e) {
            System.out.println("\tError: Date field(s) cannot be empty");
        }
    }

    private void addTodo(String line) {
        try {
            String todoDescription = extractTodoDescription(line);
            Todo newTodo = new Todo(todoDescription);
            itemArrayList.add(newTodo);
            outputAddedMessage(newTodo);
            numItems += 1;
        } catch (EmptyDescriptionException e) {
            printTaskDescriptionEmptyMessage();
        }
    }

    private static void printTaskDescriptionEmptyMessage() {
        System.out.println("\tError: The task description cannot be empty.");
    }

    private void addDeadline(String line) {
        try {
            String deadlineDescription = extractDeadlineDescription(line);
            String deadlineDate = extractDeadlineDate(line);
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            itemArrayList.add(newDeadline);
            outputAddedMessage(newDeadline);
            numItems += 1;
        } catch (EmptyDescriptionException e) {
            printTaskDescriptionEmptyMessage();
        } catch (EmptyDateFieldException e) {
            System.out.println("\tError: Date field(s) cannot be empty");
        }
    }

    private void outputAddedMessage(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + (numItems+1) + " tasks in the list.");
    }

    private String extractTodoDescription(String line) throws EmptyDescriptionException {
        String todoDescription;
        todoDescription = line.replaceFirst("todo", "").trim();

        taskDescriptionNotEmpty(todoDescription);

        return todoDescription;
    }

    private String extractDeadlineDescription(String line) throws EmptyDescriptionException {
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

    private String extractDeadlineDate(String line) {
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

    private String extractEventDescription(String line) throws EmptyDescriptionException {
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

    private String extractEventEndDate(String line) {
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

    private String extractEventStartDate(String line) {
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

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        int i = 0;
        for (Task a: itemArrayList) {
            System.out.println("\t" + (i + 1) + "." + a);
            i += 1;
        }
    }

    public void markItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(5));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                printInputIndexOutOfRangeMessage();
            } else {
                this.markListItemAsDone(itemNum);
                printTaskMarkedMessage(itemNum);
            }
        } catch (NumberFormatException  e) {
            printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            printUnknownErrorMessage();
        }
    }

    private static void printUnknownErrorMessage() {
        System.out.println("Unknown error experienced.");
    }

    private void printTaskMarkedMessage(int itemNum) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + itemArrayList.get(itemNum - 1));
    }

    private static void printInputIndexOutOfRangeMessage() {
        System.out.println("\tInput index number out of range.");
    }

    public void unmarkItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                printInputIndexOutOfRangeMessage();
            } else {
                this.markListItemAsUnDone(itemNum);
                printTaskUnmarkedMessage(itemNum);
            }
        } catch (NumberFormatException  e) {
            printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            printUnknownErrorMessage();
        }
    }

    private void printTaskUnmarkedMessage(int itemNum) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + itemArrayList.get(itemNum - 1));
    }

    private static void printInputIndexNotAnIntegerMessage() {
        System.out.println("\tInput index was not a integer.");
    }

    public void markListItemAsDone(int itemNum) {
        itemArrayList.get(itemNum - 1).markAsDone();
    }

    public void markListItemAsUnDone(int itemNum) {
        itemArrayList.get(itemNum - 1).markAsUnDone();
    }

    public void deleteItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                printInputIndexOutOfRangeMessage();
            } else {
                Task deletedTask = itemArrayList.get(itemNum - 1);
                this.deleteListItem(itemNum);
                printTaskDeletedMessage(deletedTask);
            }
        } catch (NumberFormatException  e) {
            printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            printUnknownErrorMessage();
        }
    }

    private void deleteListItem(int itemNum) {
        itemArrayList.remove(itemNum - 1);
    }

    private void printTaskDeletedMessage(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + itemArrayList.size() + " tasks in the list.");
    }

    public String getFormattedTasks() {
        String outputString = "";
        for (Task a: itemArrayList) {
                outputString += a.formattedTask() + System.lineSeparator();
        }
        return outputString;
    }
}
