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
    public static int parseValidTaskIndex(HashMap<String, String> argumentsList, ArrayList<Task> tasks){
        int index = INVALID_TASK_INDEX;
        if (argumentsList.get("main") == null){
            return index;
        }
        try {
            index = Integer.parseInt(argumentsList.get("main")) - 1;
        } catch (NumberFormatException e){
            return index;
        }
        if (index < 0 || index >= tasks.size()){
            return index;
        }
        return index;
    }

    // Menu Options - List /////////////////////////////////////////////////////////////////////////////////////////////
    public void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int currentItemIndex = 0; currentItemIndex< this.tasks.size(); currentItemIndex++){
            System.out.println(String.valueOf(currentItemIndex+1) + "." + this.tasks.get(currentItemIndex));
        }
    }

    // Menu Options - Add //////////////////////////////////////////////////////////////////////////////////////////////
    public void addTodo(HashMap<String,String> argumentsList){
        if (argumentsList.get("main") == null){
            System.out.println("No description was given!");
            return;
        }
        addTaskWithUI(new ToDo(argumentsList.get("main")));
    }
    public void addDeadline(HashMap<String,String> argumentsList){
        if (argumentsList.get("main") == null){
            System.out.println("No description was given!");
            return;
        }
        if (argumentsList.get("/by") == null){
            System.out.println("/by not given!");
            return;
        }
        addTaskWithUI(new Deadline(argumentsList.get("main"), argumentsList.get("/by")));
    }
    public void addEvent(HashMap<String,String> argumentsList){
        if (argumentsList.get("main") == null){
            System.out.println("No description was given!");
            return;
        }
        if (argumentsList.get("/from") == null){
            System.out.println("/from not given!");
            return;
        }
        if (argumentsList.get("/to") == null){
            System.out.println("/to not given!");
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
            System.out.println("That is not a valid index!");
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
            System.out.println("That is not a valid index!");
            return;
        }
        Task currentTask = tasks.get(index);
        currentTask.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currentTask);
    }
}