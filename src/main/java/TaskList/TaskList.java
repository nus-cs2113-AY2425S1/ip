package TaskList;
import Parser.Parser;
import Ui.Ui;
import Storage.Storage;
import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import constants.Statements;
import constants.Warnings;
import exceptions.IllegalEmptyException;
import exceptions.IllegalIndexException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> items;
    private Storage storage;
    private static Ui ui = new Ui();
    private Parser parser = new Parser();

    public TaskList() {
        items = new ArrayList<>();
        storage = new Storage();
        items = loadTask();
    }


    public void markItem(String[] splitInputs) throws IllegalTaskException, IllegalEmptyException {
        parser.validateMark(splitInputs,items);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(true);

        ui.printMarkOutput(items.get(index));

        storage.saveNewData(items);
    }

    public void unmarkItem(String[] splitInputs) throws IllegalTaskException, IllegalEmptyException {
        parser.validateMark(splitInputs,items);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(false);
        ui.printMarkOutput(items.get(index));

        storage.saveNewData(items);
    }

    public void addTodo(String input) throws IllegalEmptyException {
        String task = Parser.trimString(input);
        Todo todo = new Todo(task);
        items.add(todo);

        ui.printTodoMessage(todo.createTaskList(),items);

        storage.saveNewData(items);
    }

    public void addDeadline(String input) throws IllegalEmptyException, IllegalKeywordException {

        if (!input.contains("by")) {
            throw new IllegalKeywordException(Warnings.VALID_DEADLINE_KEYWORD_WARNING);
        }

        String description = Parser.trimString(input);
        String[] descriptionSubstrings = description.split("by", 2);
        String deadlineDescription = descriptionSubstrings[0].trim();
        String by = descriptionSubstrings[1].trim();

        if(deadlineDescription.isEmpty() || by.isEmpty()) {
            throw new IllegalEmptyException("Please enter a valid deadline or description!");
        }

        Deadline deadline = new Deadline(deadlineDescription, by);
        items.add(deadline);

        ui.printDeadlineMessage(deadline.createTaskList(), items);

        storage.saveNewData(items);
    }

    public void addEvent(String input) throws IllegalEmptyException, IllegalKeywordException {

        input = Parser.trimString(input);

        if (!input.contains("from") || !input.contains("to")) {
            throw new IllegalKeywordException(Warnings.VALID_EVENT_KEYWORD_WARNING);
        }


        String[] splitInputs = input.split("from|to");

        if (splitInputs.length < 3) {
            throw new IllegalEmptyException("Please fulfil all criteria: event description, " +
                    "start time and end time!");
        }

        String start = splitInputs[1].trim();
        String end = splitInputs[2].trim();
        String eventDescription = splitInputs[0].trim();

        if(eventDescription.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new IllegalEmptyException("Please enter a valid description, start time and end time!");
        }

        Event event = new Event(eventDescription, start, end);
        items.add(event);

        ui.printEventMessage(event.createTaskList(),items);

        storage.saveNewData(items);
    }

    public void deleteItem(String[] splitInputs) throws IllegalIndexException {

        int deleteIndex = Integer.parseInt(splitInputs[1]) - 1;

        if (deleteIndex < 0 || deleteIndex >= items.size()) {
            throw new IllegalIndexException(Warnings.VALID_INDEX_WARNING + items.size());
        }

        Task deleteItem = items.get(deleteIndex);
        items.remove(deleteIndex);

        ui.printDeleteMessage(deleteItem);
        storage.saveNewData(items);
    }

    public ArrayList<Task> loadTask(){
        items = storage.loadExistingData();
        return items;
    }

    public void printList() {
        ui.printLine();
        System.out.println(Statements.LIST_TASKS);

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i).getStatusIcon() + " " + items.get(i).getDescription());
        }
        ui.printLine();
    }
}


