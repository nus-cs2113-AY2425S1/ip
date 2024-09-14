import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    //class to store task
    public static class Task {
        String description;
        boolean isDone;
        Task(String description) {
            this.description = description;
            this.isDone = false;
        }
        void markAsDone() {
            this.isDone = true;
        }

        void markAsNotDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + (isDone ? "X" : " ") + "] " + description;
        }

    }

    private static class Todo extends Task {
        Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        protected String from;
        protected String to;
        Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
        }

    }
    // create list of tasks
    private static List<Task> userInputs = new ArrayList<>();

    //create methods to add each respective task subclasses

    public static void addTaskToList(Task task) {
        userInputs.add(task);
        System.out.println(" ____________________________________________________________");
        System.out.println("added: ");
        System.out.println("    " + task);
        System.out.println("you have " + userInputs.size() + " quaggin tasks to do! get to work!");
        System.out.println(" ____________________________________________________________");
    }

    // Adding Todo task
    public static void addTodoToList(String description) {
        Todo todo = new Todo(description);
        addTaskToList(todo); // Reuse the helper method
    }

    // Adding Deadline task
    public static void addDeadlineToList(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        addTaskToList(deadline); // Reuse the helper method
    }

    // Adding Event task
    public static void addEventToList(String description, String from, String to) {
        Event event = new Event(description, from, to);
        addTaskToList(event); // Reuse the helper method
    }
    public static void displayList() {
        if (!userInputs.isEmpty()) {
            System.out.println(" ____________________________________________________________");
            for (int i = 0; i < userInputs.size(); i++) {
                System.out.println((i+1)+". " + userInputs.get(i));
            }
            System.out.println("____________________________________________________________");
        }
        else {
            System.out.println("nothing in list! quag");
        }
    }

    public static void markAsDone(int index) {
        if (index >= 1 && index <= userInputs.size()) {
            Task task = userInputs.get(index - 1);
            if (task.isDone) {
                System.out.println(" ____________________________________________________________");
                System.out.println("You've already quaggin done this:");
                System.out.println("  " + task);
                System.out.println(" ____________________________________________________________");
            } else {
                task.markAsDone();
                System.out.println(" ____________________________________________________________");
                System.out.println("quag! you're done with this task :");
                System.out.println("  " + task);
                System.out.println(" ____________________________________________________________");
            }
        } else {
            System.out.println("invalid task number! quag");
        }
    }

    public static void markAsNotDone(int index) {
        if (index >= 1 && index <= userInputs.size()) {
            Task task = userInputs.get(index - 1);
            if (!task.isDone) {
                System.out.println(" ____________________________________________________________");
                System.out.println("quag! you ain't even done w this yet:");
                System.out.println("  " + task);
                System.out.println(" ____________________________________________________________");
            } else {
                task.markAsNotDone();
                System.out.println(" ____________________________________________________________");
                System.out.println("quag! you're NOT done with this task :");
                System.out.println("  " + task);
                System.out.println(" ____________________________________________________________");
            }
        } else {
            System.out.println("invalid task number! quag");
        }
    }

    public static void deleteTask(int taskIndex) {
        System.out.println(" ____________________________________________________________");
        System.out.println("quag! deleted this task :");
        System.out.println("  " + userInputs.get(taskIndex - 1));
        userInputs.remove(taskIndex - 1);
        System.out.println("You have " + userInputs.size() + " quaggin tasks to do! get to work!");
        System.out.println(" ____________________________________________________________");
    }
}
