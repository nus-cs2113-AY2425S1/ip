import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import constants.Utils;
import constants.Warnings;
import exceptions.IllegalEmptyException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;
import java.util.ArrayList;
import Ui.*;
import Storage.*;


public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void markItem(String[] splitInputs, int count) throws IllegalTaskException {
        Parser.validateMark(splitInputs, count);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        tasks.get(index).setDone(true);

        Ui.printMarkOutput(tasks.get(index));
    }

    public void unmarkItem(String[] splitInputs, int count) throws IllegalTaskException {
        Parser.validateMark(splitInputs, count);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        tasks.get(index).setDone(false);
        Ui.printMarkOutput(tasks.get(index));
    }

    public int addTodo(int count, String input) throws IllegalEmptyException {
        String task = Parser.trimString(input);
        Todo todo = new Todo(task);
        tasks.add(todo);

        Ui.printTodoMessage(count, todo.createTodoList());

        Storage.saveNewData(todo.createTodoTxt(), Utils.TODO);
        return count + 1;
    }

    public int addDeadline(int count, String input) throws IllegalEmptyException, IllegalKeywordException {

        if (!input.contains("by")) {
            throw new IllegalKeywordException(Warnings.VALID_DEADLINE_KEYWORD_WARNING);
        }

        String description = Parser.trimString(input);
        String[] descriptionSubstrings = description.split("by", 2);
        String deadlineDescription = descriptionSubstrings[0].trim();
        String by = descriptionSubstrings[1].trim();

        Deadline deadline = new Deadline(deadlineDescription, by);
        tasks.add(deadline);

        Ui.printDeadlineMessage(count, deadline.createDeadlineList());

        Storage.saveNewData(deadline.createDeadlineTxt(), Utils.DEADLINE);
        return count + 1;
    }

    public int addEvent(int count, String input) throws IllegalEmptyException, IllegalKeywordException {

        input = Parser.trimString(input);

        if (!input.contains("from") || !input.contains("to")) {
            throw new IllegalKeywordException(Warnings.VALID_EVENT_KEYWORD_WARNING);
        }

        String[] splitInputs = input.split("from|to");
        String start = splitInputs[1].trim();
        String end = splitInputs[2].trim();
        String eventDescription = splitInputs[0].trim();

        Event event = new Event(eventDescription, start, end);
        tasks.add(event);

        Ui.printEventMessage(count, event.createEventList());

        Storage.saveNewData(event.createEventTxt(), Utils.EVENT);
        return count + 1;
    }

    public int deleteItem(int count, String[] splitInputs) throws IllegalTaskException {

        int deleteIndex = Integer.parseInt(splitInputs[1]) - 1;

        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new IllegalTaskException(Warnings.VALID_INDEX_WARNING + count);
        }

        Task deleteItem = tasks.get(deleteIndex);
        tasks.remove(deleteIndex);

        Ui.printDeleteMessage(deleteItem);
        return count - 1;
    }
}
