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
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents all the operations that handle the commands stated in the CommandHandler File
 */
public class TaskList {

    private static ArrayList<Task> items;
    private Storage storage;
    private static Ui ui = new Ui();
    private Parser parser = new Parser();

    /**
     * TaskList constructor
     */
    public TaskList() {
        items = new ArrayList<>();
        storage = new Storage();
        items = loadTask();
    }

    /**
     * Marks a task as completed
     *
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     */
    public void markItem(String[] splitInputs) throws IllegalTaskException, IllegalEmptyException {
        parser.validateMark(splitInputs,items);

        //get index of the item to be marked from splitInputs array;
        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(true);

        ui.printMarkOutput(items.get(index));

        storage.saveNewData(items);
    }

    /**
     * Remove mark from a task. Task is now uncompleted.
     *
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     */
    public void unmarkItem(String[] splitInputs) throws IllegalTaskException, IllegalEmptyException {
        parser.validateMark(splitInputs,items);

        //get index of the item to be unmarked from splitInputs array;
        int index = Integer.parseInt(splitInputs[1]) - 1;
        items.get(index).setDone(false);
        ui.printMarkOutput(items.get(index));

        storage.saveNewData(items);
    }
    /**
     * The method adds a 'Todo' task into the ArrayList.
     *
     * @param input A String containing the user's input
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
     * The method also adds a 'Deadline' task into the ArrayList.
     *
     * @param input A String containing the user's input
     * @throws IllegalKeywordException when the 'by' keyword is not found in the user's input
     * @throws IllegalEmptyException when one of the description and by criteria is empty
     */
    public void addDeadline(String input) throws IllegalEmptyException, IllegalKeywordException {
        //throws an error when the by keyword is missing from user's input
        if (!input.contains("by")) {
            throw new IllegalKeywordException(Warnings.VALID_DEADLINE_KEYWORD_WARNING);
        }
        //Execute the trimString() method to remove 'deadline' command from input
        String description = Parser.trimString(input);

        //Split description into substrings with 'by' delimiter
        String[] descriptionSubstrings = description.split("by", 2);
        //Substring before the 'by' delimiter represents the deadline description
        String deadlineDescription = descriptionSubstrings[0].trim();
        //Substring after  the 'by' delimiter represents when the deadline is by
        String by = descriptionSubstrings[1].trim();

        if(deadlineDescription.isEmpty() || by.isEmpty()) {
            throw new IllegalEmptyException(Warnings.VALID_DEADLINE_WARNING);
        }

        Deadline deadline = new Deadline(deadlineDescription, by);
        items.add(deadline);

        ui.printDeadlineMessage(deadline.createTaskList(), items);

        //Stores the new deadline task into the txt file
        storage.saveNewData(items);
    }
    /**
     * The method also adds an 'Event' task into the ArrayList.
     *
     * @param input A String containing the user's input
     * @throws IllegalKeywordException when either the 'from' or 'to' keyword is missing from the user's input
     * @throws IllegalEmptyException when one of the from, to and description criteria is empty
     */
    public void addEvent(String input) throws IllegalEmptyException, IllegalKeywordException {

        //Execute the trimString() method to remove 'event' command from input
        input = Parser.trimString(input);

        //throws an error when the 'from' or 'to' keyword is missing from user's input
        if (!input.contains("from") || !input.contains("to")) {
            throw new IllegalKeywordException(Warnings.VALID_EVENT_KEYWORD_WARNING);
        }

        //Split description into substrings with both the 'from' and 'to' delimiter.
        String[] splitInputs = input.split("from|to");


        if (splitInputs.length < 3) {
            throw new IllegalEmptyException(Warnings.INCOMPLETE_EVENT_WARNING);
        }
        //Substring in between the 'from' and 'to' delimiter represents the event's start time.
        String start = splitInputs[1].trim();
        //Substring after the 'to' delimiter represents the event's end time
        String end = splitInputs[2].trim();
        //Substring before the 'from' delimiter represents the event's description
        String eventDescription = splitInputs[0].trim();

        if(eventDescription.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new IllegalEmptyException(Warnings.EMPTY_EVENT_WARNING);
        }

        Event event = new Event(eventDescription, start, end);
        items.add(event);

        ui.printEventMessage(event.createTaskList(),items);
        //Stores the new event task into the txt file
        storage.saveNewData(items);
    }

    /**
     * The method also deletes a task from the ArrayList.
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     * @throws IllegalIndexException when the index of item is < 0 or > tasks.size();
     */
    public void deleteItem(String[] splitInputs) throws IllegalIndexException {

        //Get the index of item to be deleted
        int deleteIndex = Integer.parseInt(splitInputs[1]) - 1;
        //throws an error when index is out or range
        if (deleteIndex < 0 || deleteIndex >= items.size()) {
            throw new IllegalIndexException(Warnings.VALID_INDEX_WARNING + items.size());
        }

        Task deleteItem = items.get(deleteIndex);
        items.remove(deleteIndex);

        ui.printDeleteMessage(deleteItem);
        storage.saveNewData(items);
    }

    /**
     * Returns an ArrayList containing the pre-existing tasks
     * Calls the loadExistingData() method in storage class
     *
     * @return An ArrayList containing the pre-existing tasks
     */
    public ArrayList<Task> loadTask(){
        items = storage.loadExistingData();
        return items;
    }

    /**
     * Prints the existing list of tasks when the 'list' command is called
     */
    public void printList() {
        ui.printLine();
        System.out.println(Statements.LIST_TASKS);

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i).getStatusIcon() + " " + items.get(i).getDescription());
        }
        ui.printLine();
    }

    /**
     * Find tasks in the list containing the specified keyword
     *
     * @param input A String containing the user's input
     */
    public void findItem(String input) {
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

        //Print "no task found" when no task in the list contains the regex
        if (listCount == 0) {
            System.out.println(Statements.NO_TASK_FOUND);
        }

        ui.printLine();
    }
}


