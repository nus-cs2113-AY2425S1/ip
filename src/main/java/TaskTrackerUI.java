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
    public static final int INVALID_TASK_INDEX = -1;
    public int parseValidTaskIndex(HashMap<String, String> argumentsList, ArrayList<Task> tasks){
        String commandFormatPlea = " Please run the command again with `<command> <task index>`!";
        int index = INVALID_TASK_INDEX;
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            System.out.println("No Task Index was given!" + commandFormatPlea);
            return INVALID_TASK_INDEX;
        }
        try {
            index = Integer.parseInt(argumentsList.get(CommandParser.ARGUMENT_MAIN)) - 1;
        } catch (NumberFormatException e){
            System.out.println("Task Index given not a valid number!" + commandFormatPlea);
            return INVALID_TASK_INDEX;
        }
        if (index < 0 || index >= this.tasks.size()){
            System.out.println("Task Index out of range! There are only " + String.valueOf(this.tasks.size()) +
                    " tasks." + commandFormatPlea);
            return INVALID_TASK_INDEX;
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
    public void addDeadline(HashMap<String,String> argumentsList){
        String commandFormatPlea = " Please run the command again with `deadline <description> /by <by date>`!";
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            System.out.println("No description was given!" + commandFormatPlea);
            return;
        }
        if (argumentsList.get("/by") == null){
            System.out.println("/by not given!" + commandFormatPlea);
            return;
        }
        addTaskWithUI(new Deadline(argumentsList.get("main"), argumentsList.get("/by")));
    }
    public void addEvent(HashMap<String,String> argumentsList){
        String commandFormatPlea = " Please run the command again with " +
                "`event <description> /from <from date> /to <to date>`!";
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            System.out.println("No description was given!" + commandFormatPlea);
            return;
        }
        if (argumentsList.get("/from") == null){
            System.out.println("/from not given!" + commandFormatPlea);
            return;
        }
        if (argumentsList.get("/to") == null){
            System.out.println("/to not given!" + commandFormatPlea);
            return;
        }
        addTaskWithUI(new Event(
                argumentsList.get("main"),
                argumentsList.get("/from"),
                argumentsList.get("/to")
        ));
    }
    // Menu Options - Mark /////////////////////////////////////////////////////////////////////////////////////////////
    public void markTask(HashMap<String,String> argumentsList){
        int index = parseValidTaskIndex(argumentsList, tasks);
        if (index == INVALID_TASK_INDEX){
            return;
        }
        Task currentTask = tasks.get(index);
        currentTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currentTask.toString());
    }
    public void unmarkTask(HashMap<String,String> argumentsList){
        int index = parseValidTaskIndex(argumentsList, tasks);
        if (index == INVALID_TASK_INDEX){
            return;
        }
        Task currentTask = tasks.get(index);
        currentTask.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currentTask.toString());
    }
}