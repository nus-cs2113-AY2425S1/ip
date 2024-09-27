import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * Initialises the task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns size of the task list
     * @return Size of the task list
     */
    public int size(){
        return this.tasks.size();
    }

    /**
     * Clear all tasks in list
     */
    public void clear(){
        this.tasks.clear();
    }

    /**
     * Gets a task from a task list, given an index
     * @param index Index of task in the list
     * @return Task at given list index
     */
    public Task getTask(int index){
        return this.tasks.get(index);
    }
    // Listing /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Returns a String that lists all tasks in the task list
     * @return List of Tasks as a String
     */
    public String listTasks(){
        String output = "";
        for (int currentItemIndex = 0; currentItemIndex < this.tasks.size(); currentItemIndex++){
            output += String.valueOf(currentItemIndex+1) + "." + this.tasks.get(currentItemIndex) + "\n";
        }
        return output;
    }

    // Add Task ////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Adds task to the task list
     * @param task Task to Add
     * @return Task that was added
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Adds todo to the task list
     * @param description Description of todo
     * @return todo that was Added
     * @throws CuboydException If any parameter was not provided
     */
    public Task addTodo(String description) throws CuboydException {
        if (description == null){
            throw new CuboydException("No description was given! Please run the command again with `todo <description>`!");
        }
        return this.addTask(new ToDo(description));
    }

    /**
     * Adds deadline to the task list
     * @param description Description of deadline
     * @param by End date of deadline
     * @return deadline that was Added
     * @throws CuboydException If any parameter was not provided
     */
    public Task addDeadline(String description, String by) throws CuboydException {
        String commandFormatPlea = " Please run the command again with `deadline <description> /by <by date>`!";
        if (description == null){
            throw new CuboydException("No description was given!" + commandFormatPlea);
        }
        if (by == null){
            throw new CuboydException("/by not given!" + commandFormatPlea);
        }
        return this.addTask(new Deadline(description, by));
    }

    /**
     * Adds event to the task list
     * @param description Description of event
     * @param from Start date of event
     * @param to End date of event
     * @return event that was Added
     * @throws CuboydException If any parameter was not provided
     */
    public Task addEvent(String description, String from, String to) throws CuboydException {
        String commandFormatPlea = " Please run the command again with " +
                "`event <description> /from <from date> /to <to date>`!";
        if (description == null){
            throw new CuboydException("No description was given!" + commandFormatPlea);
        }
        if (from == null){
            throw new CuboydException("/from not given!" + commandFormatPlea);
        }
        if (to == null){
            throw new CuboydException("/to not given!" + commandFormatPlea);
        }
        return this.addTask(new Event(description, from, to));
    }

    // Menu Options - Mark /////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Converts a String to an Integer. Meant to convert a command argument into an integer
     * representing the task index.
     * @param taskIndexStr String storing the task index
     * @return Integer representing the Task Index
     * @throws CuboydException If given string is not a valid integer
     */
    private int convertStringToUnvalidatedTaskIndex(String taskIndexStr) throws CuboydException {
        String commandFormatPlea = " Please run the command again with `<command> <task index>`!";
        if (taskIndexStr == null){
            throw new CuboydException("No Task Index was given!" + commandFormatPlea);
        }

        try {
            return Integer.parseInt(taskIndexStr) - 1;
        } catch (NumberFormatException e){
            throw new CuboydException("Task Index given not a valid number!" + commandFormatPlea);
        }
        // throw new Exception("INVALID STATE - CHECK WITH AUTHOR OF CODE!!!");
    }

    /**
     * Checks if the taskIndex given is in range of all the tasks currently in the list.
     * @param taskIndex List Index of the task
     * @throws CuboydException If <code>taskIndex</code> is invalid
     */
    private void checkTaskIndexInRange(int taskIndex) throws CuboydException {
        String commandFormatPlea = " Please run the command again with `<command> <task index>`!";
        if (taskIndex < 0 || taskIndex >= this.tasks.size()){
            throw new CuboydException("Task Index out of range! There are only " + String.valueOf(this.size()) +
                    " tasks." + commandFormatPlea);
        }
    }

    /**
     * Marks a task in the task list
     * @param taskIndex List Index of the task to mark
     * @return Task that was marked
     * @throws CuboydException If <code>taskIndex</code> is invalid
     */
    public Task markTask(int taskIndex) throws CuboydException {
        checkTaskIndexInRange(taskIndex);
        Task currentTask = tasks.get(taskIndex);
        currentTask.markAsDone();
        return currentTask;
    }

    /**
     * Unmarks a task in the task list
     * @param taskIndex List Index of the task to unmark
     * @return Task that was marked
     * @throws CuboydException If <code>taskIndex</code> is invalid
     */
    public Task unmarkTask(int taskIndex) throws CuboydException {
        checkTaskIndexInRange(taskIndex);
        Task currentTask = tasks.get(taskIndex);
        currentTask.markAsUndone();
        return currentTask;
    }

    /**
     * Marks a task in the task list
     * @param taskIndexStr List Index of the task to mark as a String
     * @return Task that was marked
     * @throws CuboydException If <code>taskIndexStr</code> is invalid
     */
    public Task markTask(String taskIndexStr) throws CuboydException {
        return markTask(convertStringToUnvalidatedTaskIndex(taskIndexStr));
    }

    /**
     * Unmarks a task in the task list
     * @param taskIndexStr List Index of the task to unmark as a String
     * @return Task that was unmarked
     * @throws CuboydException If <code>taskIndexStr</code> is invalid
     */
    public Task unmarkTask(String taskIndexStr) throws CuboydException {
        return unmarkTask(convertStringToUnvalidatedTaskIndex(taskIndexStr));
    }

    // Menu Options - Delete ///////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Deletes a task in the task list
     * @param taskIndex List Index of the task to unmark
     * @return Task that was deleted
     * @throws CuboydException If <code>taskIndex</code> is invalid
     */
    public Task deleteTask(int taskIndex) throws CuboydException {
        checkTaskIndexInRange(taskIndex);
        Task currentTask = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return currentTask;
    }

    /**
     * Deletes a task in the task list
     * @param taskIndexStr List Index of the task to unmark as a String
     * @return Task that was deleted
     * @throws CuboydException If <code>taskIndexStr</code> is invalid
     */
    public Task deleteTask(String taskIndexStr) throws CuboydException {
        return deleteTask(convertStringToUnvalidatedTaskIndex(taskIndexStr));
    }
}