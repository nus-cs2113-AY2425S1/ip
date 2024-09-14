import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class ListUtils {

    //define filepath
    private static final String FILE_PATH = "./data/quag.txt";
    // create list of tasks
    private static List<Task> userInputs = new ArrayList<>();

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


    //create methods to add each respective task subclasses

    public static void addTodoToList(String description) {
        Todo todo = new Todo(description);
        userInputs.add(todo);
        //save list after adding
        saveToFile();
        System.out.println(" ____________________________________________________________");
        System.out.println("added: ");
        System.out.println("    " + todo);
        System.out.println("you have " + userInputs.size() + " quaggin tasks to do! get to work!");
        System.out.println(" ____________________________________________________________");
    }

    public static void addDeadlineToList(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        userInputs.add(deadline);
        saveToFile();
        System.out.println(" ____________________________________________________________");
        System.out.println("added: ");
        System.out.println("    " + deadline);
        System.out.println("you have " + userInputs.size() + " quaggin tasks to do! get to work!");
        System.out.println(" ____________________________________________________________");
    }

    public static void addEventToList(String description, String from, String to) {
        Event event = new Event(description, from, to);
        userInputs.add(event);
        saveToFile();
        System.out.println(" ____________________________________________________________");
        System.out.println("added: ");
        System.out.println("    " + event);
        System.out.println("you have " + userInputs.size() + " quaggin tasks to do! get to work!");
        System.out.println(" ____________________________________________________________");
    }

    public static void displayList() {
        if (!userInputs.isEmpty()) {
            System.out.println("____________________________________________________________");
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
                saveToFile();
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
                saveToFile();
                System.out.println(" ____________________________________________________________");
                System.out.println("quag! you're NOT done with this task :");
                System.out.println("  " + task);
                System.out.println(" ____________________________________________________________");
            }
        } else {
            System.out.println("invalid task number! quag");
        }
    }

    public static void saveToFile() {
        try {
            File dir = new File("./data");
            //create directory if file does not exist
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : userInputs) {
                if (task instanceof Todo) {
                    writer.write("T | " + (task.isDone ? "1" : "0") + " | " + task.description + "\n");
                } else if (task instanceof Deadline) {
                    writer.write("D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).by + "\n");
                } else if (task instanceof Event) {
                    writer.write("E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Event) task).from + " | " + ((Event) task).to + "\n");
                }

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    public static void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No such file! quag");
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" \\| ");
                switch (data[0]) {
                    case "T":
                        Todo todo = new Todo(data[2]);
                        if (data[1].equals("1")) {
                            todo.markAsDone();
                        }
                        userInputs.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(data[2], data[3]);
                        if (data[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        userInputs.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(data[2],data[3],data[4]);
                        if (data[1].equals("1")) {
                            event.markAsDone();
                        }
                        userInputs.add(event);
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
        }
    }
}
