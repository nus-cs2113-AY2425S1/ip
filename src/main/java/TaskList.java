import java.io.FileNotFoundException;
import java.util.ArrayList; 

/**
 * The TaskList class manages a list of tasks and provides methods to perform commands on tasks. 
 * It interacts with the Storage class to read and write tasks from/to a file.
 */
public class TaskList {

    private ArrayList<Task> tasks; 
    
    public Storage filePath; 
    
    protected static final String SEPARATOR = "\t ___________________________"; 

    /**
     * Constructs a TaskList object with tasks loaded from the storage file.
     * If the file is not found, initializes the task list as empty.
     *
     * @param filePath The storage file containing saved tasks.
     */
    public TaskList(Storage filePath) {
        this.filePath = filePath; 
        try {
            this.tasks = filePath.read();  // Attempt to read from the file
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list.");
            this.tasks = new ArrayList<>();  // Initialize with an empty list if file is not found
        }
    }

    /**
     * Return the list of tasks
     * 
     * @return the ArrayList containing tasks. 
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    /**
     * Adds a Todo task to the task list.
     *
     * @param description The description of the Todo task.
     */
    public void addTodo(String description) {
        Todo t = new Todo(description); 
        tasks.add(t);
        System.out.println(SEPARATOR); 
        System.out.println("\t Got it. I've added this task:"); 
        System.out.println("\t \t" + t); 
        System.out.println("Now you have " + tasks.size() + " tasks in the list."); 
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param description The description of the Todo task.
     * @param by The deadline of the Todo task. 
     */
    public void addDeadline(String description, String by) {
        Deadline t = new Deadline(description, by); 
        tasks.add(t);
        System.out.println(SEPARATOR); 
        System.out.println("\t Got it. I've added this task:"); 
        System.out.println("\t \t" + t); 
        System.out.println("Now you have " + tasks.size() + " tasks in the list."); 
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    public void addEvent(String description, String from, String to) {
        Event t = new Event(description, from, to); 
        tasks.add(t);
        System.out.println(SEPARATOR); 
        System.out.println("\t Got it. I've added this task:"); 
        System.out.println("\t \t" + t); 
        System.out.println("Now you have " + tasks.size() + " tasks in the list."); 
    }

    /**
     * Lists all tasks in the task list.
     */
    public void displayEntries() {
        System.out.println(SEPARATOR); 
        if (tasks.size() == 0) {
            System.out.println("\t No entries available. "); 
        } else {
            System.out.println("\t Here are the tasks in your list: "); 
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + String.valueOf(i+1) + ". " + tasks.get(i)); 
            }
        }
    }

    /**
     * Marks task with that index as done
     *
     * @param i The index of the task to mark as done.
     */
    public void markAsDone(int i) {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i-1).markDone(); 
            System.out.println(SEPARATOR); 
            System.out.println("\t Nice! I've marked this task as done: "); 
            System.out.println("\t" + tasks.get(i-1)); 
            System.out.println(SEPARATOR); 
        } else {
            System.out.println("Invalid entry number!"); 
        }
    }

    /**
     * Unmarks task with that index as done
     *
     * @param i The index of the task to unmark as done.
     */
    public void unmarkAsDone(int i) {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i-1).unmarkDone(); 
            System.out.println(SEPARATOR); 
            System.out.println("\t OK, I've marked this task as not done yet: "); 
            System.out.println("\t" + tasks.get(i-1)); 
            System.out.println(SEPARATOR); 
        } else {
            System.out.println("Invalid entry number!"); 
        }
    }

    /**
     * Delete task with that index 
     *
     * @param i The index of the task to delete.
     */
    public void delete(int i) {
        if (i >= 1 && i <= tasks.size()) {
            System.out.println(SEPARATOR); 
            System.out.println("\t Noted. I've removed this task: "); 
            System.out.println("\t" + tasks.get(i-1)); 
            tasks.remove(i-1); 
            System.out.println("\t Now you have " + tasks.size() +" tasks in the list. "); 
            System.out.println(SEPARATOR); 

        }
    }

    /**
     * Finds tasks in the task list that contain the specified keyword
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void find(String keyword) {
        int isFound = 0; 
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                isFound++; 
                System.out.println("\t" + String.valueOf(isFound) + ". " + task); 
            }
        }
        if (isFound == 0) {
            System.out.println("No matching tasks found.");
        }
    }
}




