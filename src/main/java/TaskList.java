import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    public ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public int size(){
        return this.tasks.size();
    }
    public void clear(){
        this.tasks.clear();
    }
    public Task getTask(int index){
        return this.tasks.get(index);
    }
    // Listing /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String listTasks(){
        String output = "";
        for (int currentItemIndex = 0; currentItemIndex < this.tasks.size(); currentItemIndex++){
            output += String.valueOf(currentItemIndex+1) + "." + this.tasks.get(currentItemIndex) + "\n";
        }
        return output;
    }
    // Add Task ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }
    public Task addTodo(String description) throws CuboydException {
        if (description == null){
            throw new CuboydException("No description was given! Please run the command again with `todo <description>`!");
        }
        return this.addTask(new ToDo(description));
    }
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
    private void checkTaskIndexInRange(int taskIndex) throws CuboydException {
        String commandFormatPlea = " Please run the command again with `<command> <task index>`!";
        if (taskIndex < 0 || taskIndex >= this.tasks.size()){
            throw new CuboydException("Task Index out of range! There are only " + String.valueOf(this.size()) +
                    " tasks." + commandFormatPlea);
        }
    }
    public Task markTask(int taskIndex) throws CuboydException {
        checkTaskIndexInRange(taskIndex);
        Task currentTask = tasks.get(taskIndex);
        currentTask.markAsDone();
        return currentTask;
    }
    public Task unmarkTask(int taskIndex) throws CuboydException {
        checkTaskIndexInRange(taskIndex);
        Task currentTask = tasks.get(taskIndex);
        currentTask.markAsUndone();
        return currentTask;
    }
    public Task markTask(String taskIndexStr) throws CuboydException {
        return markTask(convertStringToUnvalidatedTaskIndex(taskIndexStr));
    }
    public Task unmarkTask(String taskIndexStr) throws CuboydException {
        return unmarkTask(convertStringToUnvalidatedTaskIndex(taskIndexStr));
    }
    // Menu Options - Delete ///////////////////////////////////////////////////////////////////////////////////////////
    public Task deleteTask(int taskIndex) throws CuboydException {
        checkTaskIndexInRange(taskIndex);
        Task currentTask = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return currentTask;
    }
    public Task deleteTask(String taskIndexStr) throws CuboydException {
        return deleteTask(convertStringToUnvalidatedTaskIndex(taskIndexStr));
    }
}