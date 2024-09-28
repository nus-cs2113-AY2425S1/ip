import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;


public class TaskList {

    
    public static UI ui = new UI();
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTaskToList(Task task) {
        tasks.add(task);
        ui.showLine();
        System.out.println("added: ");
        System.out.println("    " + task);
        System.out.println("you have " + tasks.size() + " quaggin tasks to do! get to work!");
        ui.showLine();
    }

    // Adding Todo task
    public static void addTodoToList(String description) {
        Todo todo = new Todo(description);
        addTaskToList(todo); // Reuse the helper method
        //save list after adding
        Storage.saveToFile();
    }

    // Adding Deadline task
    public static void addDeadlineToList(String description, LocalDateTime by) {
        Deadline deadline = new Deadline(description, by);
        addTaskToList(deadline); // Reuse the helper method
        Storage.saveToFile();
    }

    // Adding Event task
    public static void addEventToList(String description, LocalDateTime from, LocalDateTime to) {
        Event event = new Event(description, from, to);
        addTaskToList(event); // Reuse the helper method
        Storage.saveToFile();
    }

    public static void displayList() {
        if (!tasks.isEmpty()) {
            ui.showLine();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            ui.showLine();
        } else {
            System.out.println("nothing in list! quag");
        }
    }

    public static void markAsDone(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            if (task.isDone) {
                ui.showLine();
                ui.alreadyMarked();
                System.out.println("  " + task);
                ui.showLine();
            } else {
                task.markAsDone();
                Storage.saveToFile();
                ui.showLine();
                System.out.println("quag! you're done with this task :");
                System.out.println("  " + task);
                ui.showLine();
            }
        } else {
            System.out.println("invalid task number! quag");
        }
    }

    public static void markAsNotDone(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            if (!task.isDone) {
                ui.showLine();
                ui.alreadyUnmarked();
                System.out.println("  " + task);
                ui.showLine();
            } else {
                task.markAsNotDone();
                Storage.saveToFile();
                ui.showLine();
                System.out.println("quag! you're NOT done with this task :");
                System.out.println("  " + task);
                ui.showLine();
            }
        } else {
            System.out.println("invalid task number! quag");
        }
    }

    public static void deleteTask(int taskIndex) {
        ui.showLine();
        System.out.println("quag! deleted this task :");
        System.out.println("  " + tasks.get(taskIndex - 1));
        tasks.remove(taskIndex - 1);
        System.out.println("You have " + tasks.size() + " quaggin tasks to do! get to work!");
        ui.showLine();
        Storage.saveToFile();
    }

    public static void printTasksOnDate( LocalDate dueDate) {
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.by.toLocalDate().equals(dueDate)) {
                    System.out.println("  " + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.from.toLocalDate().equals(dueDate)) {
                    System.out.println("  " + task);
                }
            }
        }
    }

    public static void printTaskWithKeyword(String keyword) {
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  " + task);
            }
        }
    }
}

