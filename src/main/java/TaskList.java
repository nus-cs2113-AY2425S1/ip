import exception.EmptyDateFieldException;
import exception.EmptyDescriptionException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class TaskList {

    public static void addEvent(ArrayList<Task> itemArrayList, String line) {
        try {
            String eventDescription = Parser.extractEventDescription(line);
            String eventStartDate = Parser.extractEventStartDate(line);
            String eventEndDate = Parser.extractEventEndDate(line);
            Event newEvent = new Event(eventDescription, eventStartDate, eventEndDate);
            itemArrayList.add(newEvent);
            Ui.printAddedMessage(itemArrayList, newEvent);
            //numItems += 1;
        } catch (EmptyDescriptionException e) {
            Ui.printTaskDescriptionEmptyMessage();
        } catch (EmptyDateFieldException e) {
            System.out.println("\tError: Date field(s) cannot be empty");
        }
    }

    public static void addTodo(ArrayList<Task> itemArrayList, String line) {
        try {
            String todoDescription = Parser.extractTodoDescription(line);
            Todo newTodo = new Todo(todoDescription);
            itemArrayList.add(newTodo);
            Ui.printAddedMessage(itemArrayList, newTodo);
            //numItems += 1;
        } catch (EmptyDescriptionException e) {
            Ui.printTaskDescriptionEmptyMessage();
        }
    }

    public static void addDeadline(ArrayList<Task> itemArrayList, String line) {
        try {
            String deadlineDescription = Parser.extractDeadlineDescription(line);
            String deadlineDate = Parser.extractDeadlineDate(line);
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            itemArrayList.add(newDeadline);
            Ui.printAddedMessage(itemArrayList, newDeadline);
            //numItems += 1;
        } catch (EmptyDescriptionException e) {
            Ui.printTaskDescriptionEmptyMessage();
        } catch (EmptyDateFieldException e) {
            System.out.println("\tError: Date field(s) cannot be empty");
        }
    }

    public static void markListItemAsDone(ArrayList<Task> itemArrayList, int itemNum) {
        itemArrayList.get(itemNum - 1).markAsDone();
    }

    public static void markListItemAsUnDone(ArrayList<Task> itemArrayList, int itemNum) {
        itemArrayList.get(itemNum - 1).markAsUnDone();
    }

    public static void deleteListItem(ArrayList<Task> itemArrayList, int itemNum) {
        itemArrayList.remove(itemNum - 1);
    }
}
