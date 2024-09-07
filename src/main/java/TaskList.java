import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        System.out.println("--------------------------------------------");
        this.taskList.add(task);
        System.out.println("Okay! I have added this task: ");
        System.out.println(task.toString());
        System.out.printf("You currently have %d tasks in your list \n", taskList.size());
        System.out.println("--------------------------------------------");
    }

    public void listTasks() {
        System.out.println("--------------------------------------------");
        System.out.println("Here are your current tasks: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + this.taskList.get(i));
        }
        System.out.println("--------------------------------------------");
    }

    public void addToDo(HashMap<String, String> commandArguments) {
        ToDo task = new ToDo(commandArguments.get("argument"));
        addTask(task);
    }

    public void addDeadline(HashMap<String, String> commandArguments) {
        String argument = commandArguments.get("argument");
        String by = commandArguments.get("/by");
        Deadline task = new Deadline(argument, by);
        addTask(task);
    }

    public void addEvent(HashMap<String, String> commandArguments) {
        String argument = commandArguments.get("argument");
        String from = commandArguments.get("/from");
        String to = commandArguments.get("/to");
        Event task = new Event(argument, from, to);
        addTask(task);
    }

    public void markTaskAsDone(HashMap<String, String> commandArguments) {
        int index = Integer.parseInt(commandArguments.get("argument")) - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        System.out.println("--------------------------------------------");
        System.out.println("Great job! I have marked this task as done: ");
        System.out.println(task);
        System.out.println("--------------------------------------------");
    }

    public void markTaskAsNotDone(HashMap<String, String> commandArguments) {
        int index = Integer.parseInt(commandArguments.get("argument")) - 1;
        Task task = taskList.get(index);
        task.markAsNotDone();
        System.out.println("--------------------------------------------");
        System.out.println("Okay, I have marked this task as not done: ");
        System.out.println(task);
        System.out.println("--------------------------------------------");
    }
}
