package TaskList;

import Parser.Parser;
import Storage.Storage;
import Ui.Ui;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TaskList {

    //ArrayList to store all the tasks
    private final ArrayList<Task> tasks;

    //TaskList constructor
    public TaskList() {
        tasks = new ArrayList<>();
    }

    //Return the Task at the given index from the tasks ArrayList
    public Task getTask(int index) throws IllegalIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalIndexException("Please enter a valid task number from 0 to " + tasks.size());
        }
        return tasks.get(index);
    }

    /**
     * Marks a task as completed
     *
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     * @param count       Current number of items in the list before executing the command.
     */
    public void markItem(String[] splitInputs, int count) throws IllegalTaskException {
        Parser.validateMark(splitInputs, count);

        //get index of the item to be marked from splitInputs array;
        int index = Integer.parseInt(splitInputs[1]) - 1;
        tasks.get(index).setDone(true);

        Ui.printMarkOutput(tasks.get(index));
    }

    /**
     * Remove mark from a task. Task is now uncompleted.
     *
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     * @param count       Current number of items in the list before executing the command.
     */
    public void unmarkItem(String[] splitInputs, int count) throws IllegalTaskException {
        Parser.validateMark(splitInputs, count);

        int index = Integer.parseInt(splitInputs[1]) - 1;
        tasks.get(index).setDone(false);
        Ui.printMarkOutput(tasks.get(index));
    }

    /**
     * Returns the final number of items in the list after executing the command.
     * The method adds a 'Todo' task into the tasks ArrayList.
     *
     * @param count Current number of items in the list before executing the command.
     * @param input A String containing the user's input
     * @return Final number of items in the list after executing the command.
     */
    public int addTodo(int count, String input) throws IllegalEmptyException {

        //Execute the trimString() method to extract task from input
        String task = Parser.trimString(input);
        Todo todo = new Todo(task);
        tasks.add(todo);

        Ui.printTodoMessage(count, todo.createTaskList());

        //Stores the new todo task into the txt file
        Storage.saveNewData(todo.createTodoTxt());
        return count + 1;
    }

    /**
     * Returns the final number of items in the list after executing the command.
     * The method also adds a 'Deadline' task into the tasks ArrayList.
     *
     * @param count Current number of items in the list before executing the command.
     * @param input A String containing the user's input
     * @return Final number of items in the list after executing the command.
     * @throws IllegalKeywordException when the 'by' keyword is not found in the user's input
     */
    public int addDeadline(int count, String input) throws IllegalEmptyException, IllegalKeywordException {

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


        Deadline deadline = new Deadline(deadlineDescription, by);
        tasks.add(deadline);

        Ui.printDeadlineMessage(count, deadline.createTaskList());

        //Stores the new deadline task into the txt file
        Storage.saveNewData(deadline.createDeadlineTxt());
        return count + 1;
    }

    /**
     * Returns the final number of items in the list after executing the command.
     * The method also adds an 'Event' task into the tasks ArrayList.
     *
     * @param count Current number of items in the list before executing the command.
     * @param input A String containing the user's input
     * @return Final number of items in the list after executing the command.
     * @throws IllegalKeywordException when either the 'from' or 'to' keyword is missing from the user's input
     */
    public int addEvent(int count, String input) throws IllegalEmptyException, IllegalKeywordException {

        //Execute the trimString() method to remove 'event' command from input
        input = Parser.trimString(input);

        //throws an error when the 'from' or 'to' keyword is missing from user's input
        if (!input.contains("from") || !input.contains("to")) {
            throw new IllegalKeywordException(Warnings.VALID_EVENT_KEYWORD_WARNING);
        }

        //Split description into substrings with both the 'from' and 'to' delimiter.
        String[] splitInputs = input.split("from|to");
        //Substring in between the 'from' and 'to' delimiter represents the event's start time.
        String start = splitInputs[1].trim();
        //Substring after the 'to' delimiter represents the event's end time
        String end = splitInputs[2].trim();
        //Substring before the 'from' delimiter represents the event's description
        String eventDescription = splitInputs[0].trim();

        Event event = new Event(eventDescription, start, end);
        tasks.add(event);

        Ui.printEventMessage(count, event.createTaskList());

        //Stores the new event task into the txt file
        Storage.saveNewData(event.createEventTxt());
        return count + 1;
    }

    /**
     * Returns the final number of items in the list after executing the command.
     * The method also deletes a task from the ArrayList.
     *
     * @param count       Current number of items in the list before executing the command.
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     * @return Final number of items in the list after executing the command.
     * @throws IllegalIndexException when the index of item is < 0 or > tasks.size();
     */
    public int deleteItem(int count, String[] splitInputs) throws IllegalIndexException {

        //Get the index of item to be deleted
        int deleteIndex = Integer.parseInt(splitInputs[1]) - 1;

        //throws an error when index is out or range
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new IllegalIndexException(Warnings.VALID_INDEX_WARNING + count);
        }

        Task deleteItem = tasks.get(deleteIndex);
        tasks.remove(deleteIndex);

        Ui.printDeleteMessage(deleteItem);
        return count - 1;
    }

    /**
     * Find tasks in the list containing the specified keyword
     *
     * @param input A String containing the user's input
     */
    public void findItem(String input) {
        Ui.printLine();
        System.out.println(Statements.LIST_FIND_TASK);

        //Get the string regex to be checked
        String regex = input.split(" ", 2)[1].trim();
        //Create a pattern from regex
        Pattern pattern = Pattern.compile(regex);

        int listCount = 0;

        for (Task task : tasks) {
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

        Ui.printLine();
    }
}
