package tasklist;
import parser.Parser;
import ui.Ui;
import storage.Storage;
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
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/** Represents all the operations that handle the commands stated in the CommandHandler File. */
public class TaskList {

    private static ArrayList<Task> items;
    private Storage storage;
    private static Ui ui = new Ui();
    private Parser parser = new Parser();

    /**
     * TaskList constructor.
     */
    public TaskList() {
        items = new ArrayList<>();
        storage = new Storage();
        items = loadTask();
    }

    /**
     * Marks a task as completed.
     *
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     * @throws IllegalEmptyException if index is out of range or when the index is not a number.
     * @throws IllegalTaskException if there is no index written.
     */
    public void markItem(String[] splitInputs) throws IllegalTaskException, IllegalEmptyException {
        parser.validateIndex(splitInputs,items);

        //get index of the item to be marked from splitInputs array;
        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(true);

        ui.printMarkOutput(items.get(index));

        storage.saveNewData(items);
    }

    /**
     * Removes mark from a task. Task is now uncompleted.
     *
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     * @throws IllegalTaskException if index is out of range or when the index is not a number.
     * @throws IllegalEmptyException if there is no index written.
     */
    public void unmarkItem(String[] splitInputs) throws IllegalTaskException, IllegalEmptyException {
        parser.validateIndex(splitInputs,items);

        //get index of the item to be unmarked from splitInputs array;
        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(false);
        ui.printMarkOutput(items.get(index));

        storage.saveNewData(items);
    }
    /**
     * Adds a 'Todo' task into the ArrayList.
     *
     * @param input A String containing the user's input.
     * @throws IllegalEmptyException when the input only contains the command with no description,
     * OR when the description string is empty.
     */
    public void addTodo(String input) throws IllegalEmptyException {
        //Execute the trimString() method to extract task from input
        String task = Parser.trimString(input);
        Todo todo = new Todo(task);
        items.add(todo);

        ui.printTodoMessage(todo.createTaskList(),items);
        //Stores the new todo task into the txt file
        storage.saveNewData(items);
    }

    /**
     * Adds a 'Deadline' task into the ArrayList.
     *
     * @param input A String containing the user's input.
     * @throws IllegalKeywordException when the 'by' keyword is not found in the user's input.
     * @throws IllegalEmptyException when one of the description and when the by criteria is empty.
     */
    public void addDeadline(String input) throws IllegalEmptyException, IllegalKeywordException {

        //Parse string and remove 'deadline' command from input
        String description = Parser.trimString(input);

        if (!input.contains(" by ")) {
            throw new IllegalKeywordException(Warnings.VALID_DEADLINE_KEYWORD_WARNING);
        }

        String[] descriptionSubstrings = description.split(" by ", 2);

        if (descriptionSubstrings.length < 2) {
            throw new IllegalEmptyException(Warnings.INCOMPLETE_DEADLINE_WARNING);
        }

        String deadlineDescription = descriptionSubstrings[0].trim();
        String by = descriptionSubstrings[1].trim();

        if (deadlineDescription.isEmpty() || by.isEmpty()) {
            throw new IllegalEmptyException(Warnings.VALID_DEADLINE_WARNING);
        }

        Deadline deadline = new Deadline(deadlineDescription, by);
        items.add(deadline);

        ui.printDeadlineMessage(deadline.createTaskList(), items);
        storage.saveNewData(items);
    }

    /**
     * Adds an 'Event' task into the ArrayList.
     *
     * @param input A String containing the user's input.
     * @throws IllegalKeywordException when either the 'from' or 'to' keyword is missing from the user's input.
     * @throws IllegalEmptyException when one of the "from", "to" and description criteria is empty.
     */
    public void addEvent(String input) throws IllegalEmptyException, IllegalKeywordException {

        //Parse string and remove 'event' command from input
        input = Parser.trimString(input);

        if (!input.contains(" from ") || !input.contains(" to ")) {
            throw new IllegalKeywordException(Warnings.VALID_EVENT_KEYWORD_WARNING);
        }

        String[] splitInputs = input.split(" from | to ");

        if (splitInputs.length < 3) {
            throw new IllegalEmptyException(Warnings.INCOMPLETE_EVENT_WARNING);
        }

        String start = splitInputs[1].trim();
        String end = splitInputs[2].trim();
        String eventDescription = splitInputs[0].trim();

        if (eventDescription.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new IllegalEmptyException(Warnings.EMPTY_EVENT_WARNING);
        }

        Event event = new Event(eventDescription, start, end);
        items.add(event);

        ui.printEventMessage(event.createTaskList(),items);
        storage.saveNewData(items);
    }

    /**
     * Deletes a task from the ArrayList.
     *
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     * @throws IllegalIndexException when the index of item is < 0 or > tasks.size().
     * @throws IllegalEmptyException when there is no index written.
     * @throws IllegalTaskException if index is out of range or when the index is not a number.
     */
    public void deleteItem(String[] splitInputs) throws IllegalIndexException,
            IllegalEmptyException, IllegalTaskException {

        parser.validateIndex(splitInputs,items);

        int deleteIndex = Integer.parseInt(splitInputs[1]) - 1;
        Task deleteItem = items.get(deleteIndex);
        items.remove(deleteIndex);

        ui.printDeleteMessage(deleteItem);
        storage.saveNewData(items);
    }

    /**
     * Returns an ArrayList containing the pre-existing tasks.
     * Calls the loadExistingData() method in storage class.
     *
     * @return An ArrayList containing the pre-existing tasks.
     */
    public ArrayList<Task> loadTask(){
        items = storage.loadExistingData();
        return items;
    }

    /**
     * Prints the existing list of tasks when the 'list' command is called.
     */
    public void printList() {
        ui.printLine();
        System.out.println(Statements.LIST_TASKS);

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i).createTaskList());
        }
        ui.printLine();
    }

    /**
     * Finds tasks in the list containing the specified keyword.
     *
     * @param input A String containing the user's input
     */
    public void findItem(String input) throws IllegalEmptyException{
        parser.validateFindString(input);

        ui.printLine();
        System.out.println(Statements.LIST_FIND_TASK);

        //Get the string regex to be checked
        String regex = input.split(" ", 2)[1].trim();
        //Create a pattern from regex
        Pattern pattern = Pattern.compile(regex);

        int listCount = 0;

        for (Task task : items) {
            //Create a matcher for the task description
            Matcher matcher = pattern.matcher(task.getDescription());
            //if the regex is found in the string, print the task
            if (matcher.find()) {
                listCount++;
                System.out.println(listCount + task.createTaskList());
            }
        }

        if (listCount == 0) {
            System.out.println(Statements.NO_TASK_FOUND);
        }

        ui.printLine();
    }
}


