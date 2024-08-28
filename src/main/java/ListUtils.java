import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    //class to store task
    private static class Task {
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
    private static List<Task> userInputs = new ArrayList<>();
    public static void addToList (String input) {
        userInputs.add(new Task(input));
        System.out.println(" ____________________________________________________________");
        System.out.println("added: "+ input);
        System.out.println(" ____________________________________________________________");
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
}
