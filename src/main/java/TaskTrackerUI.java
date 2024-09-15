import java.util.ArrayList;
import java.util.HashMap;

public class TaskTrackerUI {
    public ArrayList<Task> tasks;
    public TaskTrackerUI() {
        this.tasks = new ArrayList<>();
    }
    // Helpers /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addTaskWithUI(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
    public int parseValidTaskIndex(HashMap<String, String> argumentsList, ArrayList<Task> tasks)
    throws CuboydException{
        String commandFormatPlea = " Please run the command again with `<command> <task index>`!";
        int index;
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            System.out.println();
            throw new CuboydException("No Task Index was given!" + commandFormatPlea);
        }
        try {
            index = Integer.parseInt(argumentsList.get(CommandParser.ARGUMENT_MAIN)) - 1;
        } catch (NumberFormatException e){
            throw new CuboydException("Task Index given not a valid number!" + commandFormatPlea);
        }
        if (index < 0 || index >= this.tasks.size()){
            throw new CuboydException("Task Index out of range! There are only " + String.valueOf(this.tasks.size()) +
                    " tasks." + commandFormatPlea);
        }
        return index;
    }
    // Menu Options - List /////////////////////////////////////////////////////////////////////////////////////////////
    public void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int currentItemIndex = 0; currentItemIndex < this.tasks.size(); currentItemIndex++){
            System.out.println(String.valueOf(currentItemIndex+1) + "." + this.tasks.get(currentItemIndex));
        }
    }
    // Menu Options - Add //////////////////////////////////////////////////////////////////////////////////////////////
    public void addTodo(HashMap<String,String> argumentsList){
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            String commandFormatPlea = " Please run the command again with `todo <description>`!";
            System.out.println("No description was given!" + commandFormatPlea);
            return;
        }
        addTaskWithUI(new ToDo(argumentsList.get("main")));
    }
    public void addDeadline(HashMap<String,String> argumentsList) throws CuboydException {
        String commandFormatPlea = " Please run the command again with `deadline <description> /by <by date>`!";
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            throw new CuboydException("No description was given!" + commandFormatPlea);
        }
        if (argumentsList.get("/by") == null){
            throw new CuboydException("/by not given!" + commandFormatPlea);
        }
        addTaskWithUI(new Deadline(argumentsList.get("main"), argumentsList.get("/by")));
    }
    public void addEvent(HashMap<String,String> argumentsList) throws CuboydException {
        String commandFormatPlea = " Please run the command again with " +
                "`event <description> /from <from date> /to <to date>`!";
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            throw new CuboydException("No description was given!" + commandFormatPlea);
        }
        if (argumentsList.get("/from") == null){
            throw new CuboydException("/from not given!" + commandFormatPlea);
        }
        if (argumentsList.get("/to") == null){
            throw new CuboydException("/to not given!" + commandFormatPlea);
        }
        addTaskWithUI(new Event(
                argumentsList.get("main"),
                argumentsList.get("/from"),
                argumentsList.get("/to")
        ));
    }
    // Menu Options - Mark /////////////////////////////////////////////////////////////////////////////////////////////
    public void markTask(HashMap<String,String> argumentsList) throws CuboydException {
        int index = parseValidTaskIndex(argumentsList, tasks);
        Task currentTask = tasks.get(index);
        currentTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currentTask.toString());
    }
    public void unmarkTask(HashMap<String,String> argumentsList) throws CuboydException {
        int index = parseValidTaskIndex(argumentsList, tasks);
        Task currentTask = tasks.get(index);
        currentTask.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currentTask.toString());
    }
    // Menu Options - Delete ///////////////////////////////////////////////////////////////////////////////////////////
    public void deleteTask(HashMap<String,String> argumentsList) throws CuboydException {
        int index = parseValidTaskIndex(argumentsList, tasks);
        Task currentTask = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + currentTask.toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
}