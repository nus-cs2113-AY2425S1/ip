import java.util.ArrayList; 
import java.util.List; 

public class AddList {

    private List<Task> tasks; 
    protected static final String SEPARATOR = "\t ___________________________"; 

    public AddList() {
        this.tasks = new ArrayList<>(); 
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

}




