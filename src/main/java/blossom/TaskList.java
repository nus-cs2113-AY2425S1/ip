package blossom;

import blossom.task.Deadline;
import blossom.task.Event;
import blossom.task.Task;
import blossom.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private final int LENGTH_OF_TODO = 5;
    private final int LENGTH_OF_DEADLINE = 9;
    private final int LENGTH_OF_EVENT = 6;
    private ArrayList<Task> listOfTasks;
    private final String HORIZONTAL_LINE = "____________________________________________________________";
    private Ui ui;

    TaskList(Ui ui) {
        this.listOfTasks = new ArrayList<>();
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return listOfTasks;
    }

    public void markAndUnmarkItem(int itemIndex, String action) {
        itemIndex--;
        // If this item is marked - change boolean
        Task item = this.listOfTasks.get(itemIndex);
        if (item != null) {
            System.out.println(HORIZONTAL_LINE);
            if(action.equalsIgnoreCase("mark")) {
                item.markAsDone();
                System.out.println("Yayy~~ Good job in getting this done!");
                System.out.println(item);
            } else {
                item.markAsUndone();
                System.out.println("Hope you get this done soon! :D");
                System.out.println(item);
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public void addTask (String input) throws BlossomException {
        System.out.println(HORIZONTAL_LINE);
        catchInputErrors(input);
        Task item = createTask(input);
        System.out.println("Ok!! I've added this task *\\ (>o<) /*");
        System.out.println(item.toString());
        this.listOfTasks.add(item);
        ui.printNumberOfTasks(this.listOfTasks);
    }

    public Task createTask(String input) throws BlossomException {
        Task item;
        if(input.contains("todo")) {
            item = new Todo(input.substring(LENGTH_OF_TODO));
        } else if (input.contains("deadline")) {
            String[] parts = input.substring(LENGTH_OF_DEADLINE).split(" /by ");
            item = new Deadline(parts[0], parts[1]);
        } else if (input.contains("event")) {
            String[] parts = input.substring(LENGTH_OF_EVENT).split(" /from | /to ");
            if(parts.length < 3) throw new BlossomException("Event must include both start and end times.");
            item = new Event(parts[0], parts[1], parts[2]);
        } else {
            throw new BlossomException("Sorry~~ idk what that means ( • ᴖ • ｡)");
        }
        return item;
    }

    public void catchInputErrors(String input) throws BlossomException {
        if (input == null || input.trim().isEmpty()) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a task cannot be empty~~ ");
        } else if (input.trim().equals("todo")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a todo cannot be empty~~ ");
        } else if (input.trim().equals("deadline")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a deadline cannot be empty~~ ");
        } else if (input.trim().equals("event")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of an event cannot be empty~~ ");
        }
    }

    public void deleteTask(int indexOfTask) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Removing this task now! ᕙ(  •̀ ᗜ •́  )ᕗ ");
        System.out.println(this.listOfTasks.get(indexOfTask-1).toString());
        System.out.println(HORIZONTAL_LINE);
        this.listOfTasks.remove(indexOfTask-1);
        ui.printNumberOfTasks(this.listOfTasks);
    }

    public void findTask(String keyword) {
        System.out.println(HORIZONTAL_LINE);
        int orderInList = 1;
        boolean found = false;
        for(Task item : listOfTasks) {
           if(item.toString().contains(keyword)) {
               if(!found) {
                   System.out.println("Here are the matching tasks in your list! ૮ ˶ᵔ ᵕ ᵔ˶ ა");
                   found = true;
               }
               System.out.println(orderInList+ ". " + item);
           }
           orderInList++;
        }
        if(!found) {
            System.out.println("Oops, no tasks found! . ‸ .");
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
