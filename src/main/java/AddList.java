import java.io.FileNotFoundException;
import java.util.ArrayList; 

public class AddList {

    private ArrayList<Task> tasks; 
    public FileClass filePath; 
    
    protected static final String SEPARATOR = "\t ___________________________"; 

    public AddList(FileClass filePath) {
        this.filePath = filePath; 
        try {
            this.tasks = filePath.read();  // Attempt to read from the file
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list.");
            this.tasks = new ArrayList<>();  // Initialize with an empty list if file is not found
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    public void addTodo(String description) {
        Todo t = new Todo(description); 
        tasks.add(t);
        System.out.println(SEPARATOR); 
        System.out.println("\t Got it. I've added this task:"); 
        System.out.println("\t \t" + t); 
        System.out.println("Now you have " + tasks.size() + " tasks in the list."); 
    }

    public void addDeadline(String description, String by) {
        Deadline t = new Deadline(description, by); 
        tasks.add(t);
        System.out.println(SEPARATOR); 
        System.out.println("\t Got it. I've added this task:"); 
        System.out.println("\t \t" + t); 
        System.out.println("Now you have " + tasks.size() + " tasks in the list."); 
    }

    public void addEvent(String description, String from, String to) {
        Event t = new Event(description, from, to); 
        tasks.add(t);
        System.out.println(SEPARATOR); 
        System.out.println("\t Got it. I've added this task:"); 
        System.out.println("\t \t" + t); 
        System.out.println("Now you have " + tasks.size() + " tasks in the list."); 
    }

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

}




